package com.tubugs.springboot.service;

import com.tubugs.springboot.ability.file.FileAbility;
import com.tubugs.springboot.ability.sms.SMSAbility;
import com.tubugs.springboot.consts.FileTagKey;
import com.tubugs.springboot.consts.RedisKey;
import com.tubugs.springboot.consts.SessionKey;
import com.tubugs.springboot.consts.StatusKey;
import com.tubugs.springboot.dao.entity.User;
import com.tubugs.springboot.dao.mapper.UserMapper;
import com.tubugs.springboot.dto.ResultDto;
import com.tubugs.springboot.frame.SessionManager;
import com.tubugs.springboot.frame.validator.Validator;
import com.tubugs.springboot.helper.AutoLoginHelper;
import com.tubugs.springboot.helper.RedisHelper;
import com.tubugs.springboot.utils.NumberUtil;
import com.tubugs.springboot.utils.PwdUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by xuzhang on 2017/9/1.
 */
@Service
public class UserService {
    //用户密码加密
    private final RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();


    //自动登录加密分隔符
    private final String SPLIT_STRING = "《-----》";

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AutoLoginHelper autoLoginHelper;
    @Autowired
    private RedisHelper redisHelper;
    @Autowired
    private FileAbility fileAbility;
    @Autowired
    private SMSAbility smsAbility;

    /**
     * 查询用户账号数据
     *
     * @param account
     * @return
     */
    public User getUserByAccount(String account) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("account", account);
        List<User> users = userMapper.selectByExample(example);
        return users != null && users.size() > 0 ? users.get(0) : null;
    }

    /**
     * 查询用户账号数据
     *
     * @param phone
     * @return
     */
    public User getUserByPhone(String phone) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("phone", phone);
        List<User> users = userMapper.selectByExample(example);
        return users != null && users.size() > 0 ? users.get(0) : null;
    }

    /**
     * 注册时发送手机验证码
     *
     * @param phone
     * @return
     */
    public ResultDto phoneRegisterSendCode(String phone) {
        //1.手机号合法性检查
        Validator.checkPhone(phone);
        //2.用户是否注册过检查
        if (getUserByPhone(phone) != null)
            return new ResultDto(false, "此手机号已注册过");
        //3.防止短信接口被暴力调用
        long times = redisHelper.incAndExpire(String.format(RedisKey.REGISTER_CODE_SEND_TIMES, phone), 1, TimeUnit.DAYS);
        if (times > 5)
            return new ResultDto(false, "发送验证码超过每日上限5次");
        //4.发送短信验证码
        String code = smsAbility.sendVerifyCode(phone);
        //5.会话中保存手机号、验证码
        SessionManager.setSession(SessionKey.REGISTER_CODE, code);
        SessionManager.setSession(SessionKey.REGISTER_PHONE, phone);
        return new ResultDto(true, "成功");
    }

    /**
     * 手机注册
     */
    public ResultDto phoneRegister(String password, String client_code) {
        //1.参数校验
        Validator.checkNotNull(password, "密码");
        Validator.checkNotNull(client_code, "验证码");
        //2.查看手机号是否发送过验证码
        String account = SessionManager.getSession(SessionKey.REGISTER_PHONE);
        String code = SessionManager.getSession(SessionKey.REGISTER_CODE);
        if (StringUtils.isEmpty(account) || StringUtils.isEmpty(code))
            return new ResultDto(false, "请先发送验证码");
        //3.防止暴力注册
        long times = redisHelper.incAndExpire(String.format(RedisKey.REGISTER_CODE_FAIL_TIMES, account), 1, TimeUnit.DAYS);
        if (times > 5)
            return new ResultDto(false, "超过每日失败验证上限5次");
        //4.校验验证码是否正确
        if (!client_code.equals(code))
            return new ResultDto(false, "验证码错误");
        //5.移除会话中的手机号、验证码
        SessionManager.removeSession(SessionKey.REGISTER_CODE);
        SessionManager.removeSession(SessionKey.REGISTER_PHONE);
        //6.注册
        String salt = randomNumberGenerator.nextBytes().toHex();
        User user = new User();
        user.setAccount(account);
        user.setPhone(account);
        user.setStatus(StatusKey.Available);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setSalt(salt);
        user.setPassword(PwdUtil.computePwdWithSalt(password, salt));
        int tryTime = 3;
        while (tryTime > 0) {
            try {
                //生成的用户编号有可能会冲突，故尝试三次 TODO 暂不支持数据库水平分库
                user.setNo(NumberUtil.generateUserNo());
                userMapper.insert(user);
                return new ResultDto(true, "成功");
            } catch (Exception ex) {
                tryTime--;
            }
        }
        return new ResultDto(false, "注册失败，需增加用户编号位数");
    }

    /**
     * 登录
     *
     * @param account
     * @param password
     * @return 自动登录token
     */
    public ResultDto login(String account, String password) {
        //1.参数校验
        Validator.checkNotNull(account, "账号");
        Validator.checkNotNull(password, "密码");
        //2.登录(使用shiro)
        UsernamePasswordToken token = new UsernamePasswordToken(account, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (IncorrectCredentialsException ex) {
            return new ResultDto(false, "密码错误");
        } catch (ExcessiveAttemptsException ex) {
            return new ResultDto(false, "连续输错5次密码，1天内不能继续登录");
        } catch (UnknownAccountException ex) {
            return new ResultDto(false, "用户不存在");
        } catch (DisabledAccountException ex) {
            return new ResultDto(false, "用户状态不可用");
        }
        //3.生成自动登录的token
        String auto_login_key = String.format(RedisKey.LOGIN_AUTO, account);
        String auto_login_value = autoLoginHelper.encrypt(account + SPLIT_STRING + password);
        redisHelper.setAndExpire(auto_login_key, auto_login_value, 7, TimeUnit.DAYS);
        //4.防止用户同时登录
        String only_login_key = String.format(RedisKey.LOGIN_ONLY, account);
        String only_login_value = SessionManager.getSessionID();
        redisHelper.setAndExpire(only_login_key, only_login_value, 7, TimeUnit.DAYS);
        //5.设置用户编号到Session中
        User user = getUserByAccount(account);
        SessionManager.setSession(SessionKey.USER_NO, String.valueOf(user.getNo()));
        return new ResultDto(true, "成功", auto_login_value);
    }

    /**
     * 自动登录
     *
     * @param client_token 令牌
     * @return
     */
    public ResultDto loginAuto(String client_token) {
        //1.参数校验
        Validator.checkNotNull(client_token, "令牌");
        //2.解密令牌
        String accountAndPassword = autoLoginHelper.decrypt(client_token);
        if (StringUtils.isEmpty(accountAndPassword))
            return new ResultDto(false, "解密令牌失败");
        //3.校验令牌是否正确
        String[] results = accountAndPassword.split(SPLIT_STRING);
        String account = results[0];
        String password = results[1];
        String auto_login_key = String.format(RedisKey.LOGIN_AUTO, account);
        String server_token = redisHelper.get(auto_login_key);
        if (!client_token.equals(server_token))
            return new ResultDto(false, "令牌错误");
        //4.登录
        return login(account, password);
    }

    /**
     * 修改个人资料
     *
     * @param account
     * @param nick_name
     * @param avatar
     * @param email
     * @param sex
     * @param birthday
     * @return
     * @throws IOException
     */
    public Boolean modifyInfo(String account, String nick_name, MultipartFile avatar, String email, byte sex, String birthday) throws IOException {
        //1.参数校验
        Validator.checkNotNull(account, "账号");
        //2.更新用户
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("account", account);
        User user = new User();
        user.setNickName(nick_name);
        if (avatar != null)
            user.setAvatar(fileAbility.savePicture(FileTagKey.AVATAR, avatar));
        user.setEmail(email);
        user.setSex(sex);
        if (!StringUtils.isEmpty(birthday))
            user.setBirthday(new Date(birthday));
        userMapper.updateByExampleSelective(user, example);
        return true;
    }

    /**
     * 修改密码
     *
     * @param account
     * @param old_pwd
     * @param new_pwd
     * @return
     */
    public ResultDto modifyPwd(String account, String old_pwd, String new_pwd) {
        //1.参数校验
        Validator.checkNotNull(account, "账号");
        Validator.checkNotNull(old_pwd, "旧密码");
        Validator.checkNotNull(new_pwd, "新密码");
        //2.查询用户
        User user = getUserByAccount(account);
        if (user == null)
            return new ResultDto(false, "用户不存在");
        //3.校验密码
        String computed_old_pwd = PwdUtil.computePwdWithSalt(old_pwd, user.getSalt());
        if (!user.getPassword().equals(computed_old_pwd))
            return new ResultDto(false, "原始密码错误");
        //4.更新用户密码
        updateUserPwd(account, user.getSalt(), new_pwd);
        //5.重新登录
        return login(account, new_pwd);
    }

    /**
     * 忘记密码-发送验证码
     *
     * @param phone
     * @return
     */
    public ResultDto phoneForgetSendCode(String phone) {
        //1.手机号合法性检查
        Validator.checkPhone(phone);
        //2.查询用户
        User user = getUserByPhone(phone);
        if (user == null)
            return new ResultDto(false, "用户不存在");
        //3.防止暴力破坏
        long times = redisHelper.incAndExpire(String.format(RedisKey.FORGET_CODE_SEND_TIMES, phone), 1, TimeUnit.DAYS);
        if (times > 5)
            return new ResultDto(false, "发送验证码超过每日上限5次");
        //4.发送验证码
        String code = smsAbility.sendVerifyCode(phone);
        //5.会话添加手机号、验证码
        SessionManager.setSession(SessionKey.FORGET_PHONE, phone);
        SessionManager.setSession(SessionKey.FORGET_CODE, code);
        return new ResultDto(true, "成功");
    }

    /**
     * 忘记密码-验证验证码
     *
     * @param client_code
     * @return
     */
    public ResultDto phoneForgetVerifyCode(String client_code) {
        //1.参数校验
        Validator.checkNotNull(client_code, "验证码");
        //2.是否发送过验证
        String server_phone = SessionManager.getSession(SessionKey.FORGET_PHONE);
        String server_code = SessionManager.getSession(SessionKey.FORGET_CODE);
        if (StringUtils.isEmpty(server_phone) || StringUtils.isEmpty(server_code))
            return new ResultDto(false, "需要先发送验证码");
        //3.防止暴力验证
        long times = redisHelper.incAndExpire(String.format(RedisKey.FORGET_CODE_FAIL_TIMES, server_phone), 1, TimeUnit.DAYS);
        if (times > 5)
            return new ResultDto(false, "超过每日失败验证上限5次");
        //4.校验验证码是否正确
        if (!client_code.equals(server_code))
            return new ResultDto(false, "验证码错误");
        //5.会话设置校验结果
        SessionManager.setSession(SessionKey.FORGET_CODE_PASS, "pass");
        return new ResultDto(true, "成功");
    }

    /**
     * 忘记密码-修改密码
     *
     * @param new_pwd
     * @return
     */
    public ResultDto phoneForgetModifyPwd(String new_pwd) {
        //1.参数校验
        Validator.checkNotNull(new_pwd, "密码");
        //2.验证码是否校验通过
        String forget_code_pass = SessionManager.getSession(SessionKey.FORGET_CODE_PASS);
        if (StringUtils.isEmpty(forget_code_pass))
            return new ResultDto(false, "请先完成校验");
        //3.查询会话中的账号
        String phone = SessionManager.getSession(SessionKey.FORGET_PHONE);
        if (StringUtils.isEmpty(phone))
            return new ResultDto(false, "账号为空");
        //4.查询对应用户
        User user = getUserByPhone(phone);
        if (user == null)
            return new ResultDto(false, "不存在此用户");
        //5.更新用户密码
        updateUserPwd(user.getAccount(), user.getSalt(), new_pwd);
        //6.清除会话中的标记位
        SessionManager.removeSession(SessionKey.FORGET_PHONE);
        SessionManager.removeSession(SessionKey.FORGET_CODE);
        SessionManager.removeSession(SessionKey.FORGET_CODE_PASS);
        return new ResultDto(true, "成功");
    }

    /**
     * 更新用户密码
     *
     * @param account
     * @param salt
     * @param pwd
     */
    private void updateUserPwd(String account, String salt, String pwd) {
        String computed_pwd = PwdUtil.computePwdWithSalt(pwd, salt);
        User user = new User();
        user.setPassword(computed_pwd);
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("account", account);
        userMapper.updateByExampleSelective(user, example);
    }
}
