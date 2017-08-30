package com.tubugs.springboot.test.utils;

import com.tubugs.springboot.utils.NumberUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by xuzhang on 2017/8/25.
 */
public class NumberTest {

    @Test
    public void generate() {
        for (int i = 0; i < 10000000; i++) {
            Assert.assertEquals(String.valueOf(NumberUtil.generateUserNo()).length(), 10);
            Assert.assertEquals(String.valueOf(NumberUtil.generateVerifyCode()).length(), 6);
        }
    }
}
