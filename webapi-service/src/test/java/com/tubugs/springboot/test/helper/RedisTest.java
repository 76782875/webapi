package com.tubugs.springboot.test.helper;

import com.tubugs.springboot.Application;
import com.tubugs.springboot.helper.RedisHelper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by xuzhang on 2017/8/26.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisHelper redisHelper;

    @Test
    public void set() {
        //redis基础操作
        String input = "兔八哥";
        String key = "test.string.set";
        redisHelper.set(key, input);
        Assert.assertEquals(input, redisHelper.get(key));
    }

}
