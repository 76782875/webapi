package com.tubugs.springboot.frame.shiro;

import com.tubugs.springboot.consts.RedisKey;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * Created by xuzhang on 2017/8/26.
 */
public class MyShiroLoginLimit extends HashedCredentialsMatcher {

    @Autowired
    private StringRedisTemplate redis;

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        // 自定义一个验证过程：当用户连续输入密码错误5次以上禁止用户登录一段时间(一天)
        String account = (String) token.getPrincipal();
        String key = String.format(RedisKey.LOGIN_FAIL_TIMES, account);
        if (redis.opsForValue().increment(key, 1) > 5) {
            throw new ExcessiveAttemptsException();
        }
        redis.expire(key, 1, TimeUnit.DAYS);
        boolean match = super.doCredentialsMatch(token, info);
        if (match) {
            redis.delete(key);
        }
        return match;
    }
}