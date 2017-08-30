package com.tubugs.springboot.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by xuzhang on 2017/8/27.
 */
@Component
public class RedisHelper {
    @Autowired
    private StringRedisTemplate redis;

    public void setAndExpire(String key, String value, long timeout, TimeUnit timeUnit) {
        redis.opsForValue().set(key, value);
        redis.expire(key, timeout, timeUnit);
    }

    public String get(String key) {
        return redis.opsForValue().get(key);
    }

    public void set(String key, String value) {
        redis.opsForValue().set(key, value);
    }
}
