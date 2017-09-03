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
     * 注册时发送手机验证码
     *
     * @param account
     * @return
     */
    public ResultDto registerSendCode(String account) {
        long times = redisHelper.incAndExpire(String.format(RedisKey.REGISTER_CODE_SEND_TIMES, account), 1, TimeUnit.DAYS);
        if (times > 5) {//防止暴力破坏
            return new ResultDto(false, "每天只能发送5次手机验证码");
        }
        String code = smsAbility.sendVerifyCode(account);
        SessionManager.setSession(SessionKey.REGISTER_CODE, code);
        SessionManager.setSession(SessionKey.REGISTER_PHONE, account);
        return new ResultDto(true, "成功");
    }

    /**
     * 手机注册
     */
    public ResultDto register(String password, String client_code) {
        //验证码校验
        String account = SessionManager.getSession(SessionKey.REGISTER_PHONE);
        if (StringUtils.isEmpty(account))
            return new ResultDto(false, "请先发送验证码");
        long times = redisHelper.incAndExpire(String.format(RedisKey.REGISTER_CODE_FAIL_TIMES, account), 1, TimeUnit.DAYS);
        if (times > 5) {//防止暴力注册
            return new ResultDto(false, "您已连续5次验证失败");
        }
        String code = SessionManager.getSession(SessionKey.REGISTER_CODE);
        if (!client_code.equals(code)) {
            return new ResultDto(false, "验证码错误");
        }
        //移除session
        SessionManager.removeSession(SessionKey.REGISTER_CODE);
        SessionManager.removeSession(SessionKey.REGISTER_PHONE);
        //注册
        User user = new User();
        user.setAccount(account);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setStatus(StatusKey.Available);
        String salt = randomNumberGenerator.nextBytes().toHex();
        user.setSalt(salt);
        user.setPassword(PwdUtil.computePwdWithSalt(password, salt));
        int tryTime = 3;
        while (tryTime > 0) {
            try {
                //生成的用户编号有可能会冲突，故尝试三次
                //TODO 暂不支持数据库水平分库
                user.setNo(NumberUtil.generateUserNo());
                userMapper.insert(user);
                return new ResultDto(true, "成功");
            } catch (Exception ex) {
                tryTime--;
            }
        }
        return new ResultDto(false, "注册失败,用户编号不够用");
    }

    /**
     * 登录
     *
     * @param account
     * @param password
     * @return 自动登录token
     */
    public ResultDto login(String account, String password) {
        //登录(使用shiro)
        UsernamePasswordToken token = new UsernamePasswordToken(account, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (IncorrectCredentialsException ex) {
            return new ResultDto(false, "密码错误"); //TODO 改成配置
        } catch (ExcessiveAttemptsException ex) {
            return new ResultDto(false, "连续输错5次密码，1天内不能继续登录"); //TODO 改成配置
        } catch (UnknownAccountException ex) {
            return new ResultDto(false, "用户不存在"); //TODO 改成配置
        } catch (DisabledAccountException ex) {
            return new ResultDto(false, "用户状态不可用"); //TODO 改成配置
        }
        //生成自动登录的token
        String auto_login_key = String.format(RedisKey.LOGIN_AUTO, account);
        String auto_login_value = autoLoginHelper.encrypt(account + SPLIT_STRING + password);
        redisHelper.setAndExpire(auto_login_key, auto_login_value, 7, TimeUnit.DAYS);
        //防止用户多地同时登录
        String only_login_key = String.format(RedisKey.LOGIN_ONLY, account);
        String only_login_value = SessionManager.getSessionID();
        redisHelper.setAndExpire(only_login_key, only_login_value, 7, TimeUnit.DAYS);
        //设置用户编号到Session中
        User user = getUserByAccount(account);
        SessionManager.setSession(SessionKey.USER_NO, String.valueOf(user.getNo()));
        return new ResultDto(true, "成功", auto_login_value); //TODO 改成配置
    }

    /**
     * 自动登录
     *
     * @param client_token 令牌
     * @return
     */
    public ResultDto loginAuto(String client_token) {
        String accountAndPassword = autoLoginHelper.decrypt(client_token);
        //client_token解密失败，需要重新登录
        if (StringUtils.isEmpty(accountAndPassword))
            return new ResultDto(false, "client_token错误"); //TODO 改成配置
        String[] results = accountAndPassword.split(SPLIT_STRING);
        String account = results[0];
        String password = results[1];
        //客户端token与服务端token不一致，需要重新登录
        String auto_login_key = String.format(RedisKey.LOGIN_AUTO, account);
        String server_token = redisHelper.get(auto_login_key);
        if (!client_token.equals(server_token))
            return new ResultDto(false, "token不一致");//TODO 改成配置
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
        User user = getUserByAccount(account);
        if (user == null)
            return new ResultDto(false, "用户不存在");
        String computed_old_pwd = PwdUtil.computePwdWithSalt(old_pwd, user.getSalt());
        if (!user.getPassword().equals(computed_old_pwd))
            return new ResultDto(false, "原始密码错误");

        //更新用户密码
        updateUserPwd(account, user.getSalt(), new_pwd);
        return login(account, new_pwd);
    }

    /**
     * 忘记密码-发送验证码
     *
     * @param account
     * @return
     */
    public ResultDto forgetSendCode(String account) {
        long times = redisHelper.incAndExpire(String.format(RedisKey.FORGET_CODE_SEND_TIMES, account), 1, TimeUnit.DAYS);
        if (times > 5) {//防止暴力破坏
            return new ResultDto(false, "每天只能发送5次手机验证码");
        }
        String code = smsAbility.sendVerifyCode(account);
        SessionManager.setSession(SessionKey.FORGET_PHONE, account);
        SessionManager.setSession(SessionKey.FORGET_CODE, code);
        return new ResultDto(true, "成功");
    }

    /**
     * 忘记密码-验证验证码
     *
     * @param client_code
     * @return
     */
    public ResultDto forgetVerifyCode(String client_code) {
        String server_phone = SessionManager.getSession(SessionKey.FORGET_PHONE);
        String server_code = SessionManager.getSession(SessionKey.FORGET_CODE);
        if (StringUtils.isEmpty(server_phone) || StringUtils.isEmpty(server_code))
            return new ResultDto(false, "需要先发送验证码");

        long times = redisHelper.incAndExpire(String.format(RedisKey.FORGET_CODE_FAIL_TIMES, server_phone), 1, TimeUnit.DAYS);
        if (times > 5) {//防止暴力验证
            return new ResultDto(false, "您已连续5次验证失败");
        }

        if (!client_code.equals(server_code)) {
            return new ResultDto(false, "验证码错误");
        }

        SessionManager.setSession(SessionKey.FORGET_CODE_PASS, "pass");
        return new ResultDto(true, "成功");
    }

    /**
     * 忘记密码-修改密码
     *
     * @param new_pwd
     * @return
     */
    public ResultDto forgetModifyPwd(String new_pwd) {
        String forget_code_pass = SessionManager.getSession(SessionKey.FORGET_CODE_PASS);
        if (StringUtils.isEmpty(forget_code_pass))
            return new ResultDto(false, "请先完成校验");
        String account = SessionManager.getSession(SessionKey.FORGET_PHONE);
        if (StringUtils.isEmpty(account))
            return new ResultDto(false, "账号为空");
        User user = getUserByAccount(account);
        if (user == null)
            return new ResultDto(false, "不存在此用户");

        //更新用户密码
        updateUserPwd(account, user.getSalt(), new_pwd);

        //移除对应session
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
