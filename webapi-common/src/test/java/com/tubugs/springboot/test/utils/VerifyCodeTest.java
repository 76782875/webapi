package com.tubugs.springboot.test.utils;

import com.tubugs.springboot.utils.ResourceUtil;
import com.tubugs.springboot.utils.VerifyCodeUtil;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by xuzhang on 2017/8/29.
 */
public class VerifyCodeTest {
    @Test
    public void test() throws IOException {
        String code = VerifyCodeUtil.generateVerifyCode(4);
        System.out.println(code);
        File file = new File(ResourceUtil.getAbsolutePath("code.png"));
        VerifyCodeUtil.outputImage(200, 50, file, code);
        System.out.println("查看验证码：" + file.getAbsolutePath());
    }
}
