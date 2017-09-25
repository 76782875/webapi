package com.tubugs.springboot.frame.shiro;

import com.tubugs.springboot.consts.RedisKey;
import com.tubugs.springboot.consts.StatusKey;
import com.tubugs.springboot.dao.entity.User;
import com.tubugs.springboot.helper.RedisHelper;
import com.tubugs.springboot.service.UserService;
import com.tubugs.springboot.utils.PwdUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

/**
 * Created by xuzhang on 2017/8/26.
 */
public class MyShiroRealm extends AuthorizingRealm {
    @Autowired
    private RedisHelper redisHelper;


    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户的输入的账号.
        String account = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
        // 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        User user = userService.getUserByAccount(account);
        String key = String.format(RedisKey.LOGIN_FAIL_TIMES, account);
        // 用户不存在
        if (user == null)
            throw new UnknownAccountException();
        //当用户连续输入密码错误5次以上，禁止用户登录一段时间(一天)
        if (redisHelper.incAndExpire(key, 1, TimeUnit.DAYS) > 5)
            throw new ExcessiveAttemptsException();
        //密码不正确
        if (!user.getPassword().equals(PwdUtil.computePwdWithSalt(password, user.getSalt())))
            throw new IncorrectCredentialsException();
        //用户状态不可用
        if (!user.getStatus().equals(StatusKey.Available))
            throw new DisabledAccountException();
        //密码正确，清除对应key
        redisHelper.remove(key);
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(account, password, getName());
        return authenticationInfo;
    }
}
