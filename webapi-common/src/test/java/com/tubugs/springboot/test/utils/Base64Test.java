package com.tubugs.springboot.test.utils;

import com.tubugs.springboot.utils.Base64Util;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by xuzhang on 2017/8/29.
 */
public class Base64Test {

    @Test
    public void test() throws Exception {
        String input = "兔八哥";
        System.out.println("input:" + input);
        String base64String = Base64Util.encryptBASE64(input.getBytes());
        System.out.println("encode:" + base64String);
        String sourceString = new String(Base64Util.decryptBASE64(base64String));
        System.out.println("decode:" + sourceString);
        Assert.assertEquals(input, sourceString);
    }
}
