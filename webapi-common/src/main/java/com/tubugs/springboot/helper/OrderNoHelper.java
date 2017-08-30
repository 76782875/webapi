package com.tubugs.springboot.helper;

import com.tubugs.springboot.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created by xuzhang on 2017/8/24.
 */
@Service
public class OrderNoHelper {
    private String REDIS_KEY = "OrderNo:";

    @Autowired
    private StringRedisTemplate template;

    /**
     * 生成订单编号，共16位，前12位为时间，后4位通过Redis自增控制。
     * //TODO 说明 1秒生成1万个订单可能性太小，故认为不会碰撞
     *
     * @return
     */
    public long generate() {
        String time = String.valueOf(DateUtil.getString("yyMMddHHmmss"));
        String key = REDIS_KEY + time;
        String suffix = String.format("%04d", template.opsForValue().increment(key, 1));
        template.expire(key, 1, TimeUnit.MINUTES);
        return Long.parseLong(time + suffix);
    }
}
