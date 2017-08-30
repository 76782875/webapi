package com.tubugs.springboot.test.utils;

import com.tubugs.springboot.utils.UUIDUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by xuzhang on 2017/8/29.
 */
public class UUIDTest {
    @Test
    public void test() {
        String uuid = UUIDUtil.generate();
        System.out.println(uuid);
        Assert.assertEquals(uuid.length(), 32);
    }
}
