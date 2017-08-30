package com.tubugs.springboot.service;

import com.tubugs.springboot.consts.WebRedisKey;
import com.tubugs.springboot.dao.UserMapper;
import com.tubugs.springboot.dao.entity.User;
import com.tubugs.springboot.dao.entity.UserExample;
import com.tubugs.springboot.dto.ResultDto;
import com.tubugs.springboot.helper.PasswordHelper;
import com.tubugs.springboot.helper.RedisHelper;
import com.tubugs.springboot.helper.AutoLoginHelper;
import com.tubugs.springboot.frame.SessionManager;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by xuzhang on 2017/8/26.
 */
@Service
public class UserService {
    public static final String SPLIT_STRING = "-----";
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AutoLoginHelper rsaHelper;

    @Autowired
    private RedisHelper redis;


    /**
     * 注册
     */
    public boolean register(String account, String password) {
        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setGmt_create(new Date());
        user.setGmt_modified(new Date());
        PasswordHelper.encryptPassword(user);
        userMapper.insert(user);
        return true;
    }

    /**
     * 登录
     *
     * @param account
     * @param password
     * @return 自动登录token
     */
    public ResultDto login(String account, String password) {
        //使用shiro登录
        UsernamePasswordToken token = new UsernamePasswordToken(account, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (IncorrectCredentialsException ex) {
            //TODO 改成配置
            return new ResultDto(false, "密码错误");
        } catch (ExcessiveAttemptsException ex) {
            //TODO 改成配置
            return new ResultDto(false, "连续输错5次密码，一日内不能继续登录");
        }

        //生成自动登录的token
        String auto_login_key = String.format(WebRedisKey.AUTO_LOGIN, account);
        String auto_login_value = rsaHelper.encrypt(account + SPLIT_STRING + password);
        redis.setAndExpire(auto_login_key, auto_login_value, 7, TimeUnit.DAYS);

        //防止用户同时登录的控制
        redis.setAndExpire(String.format(WebRedisKey.USER_UNIQUE_SESSIONID, account), SessionManager.getSessionID(), 1, TimeUnit.DAYS);

        //TODO 改成配置
        return new ResultDto(true, "成功", auto_login_value);
    }

    /**
     * 查询用户信息，不显示密码、更新时间
     *
     * @param name
     * @return
     */
    public User getUserInfoByAccount(String name) {
        User user = getUserByAccount(name);
        if (user != null) {
            user.setPassword(null);
            user.setSalt(null);
            user.setGmt_modified(null);
        }
        return user;
    }

    /**
     * 查询用户全量数据
     *
     * @param name
     * @return
     */
    public User getUserByAccount(String name) {
        UserExample example = new UserExample();
        example.createCriteria().andAccountEqualTo(name);
        List<User> users = userMapper.selectByExample(example);
        return users != null && users.size() > 0 ? users.get(0) : null;
    }
}
