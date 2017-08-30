package com.tubugs.springboot.test.helper;

import com.tubugs.springboot.helper.RsaHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by xuzhang on 2017/8/27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RsaTest {
    @Autowired
    private RsaHelper rsaHelper;

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void test1() {
        String input = "兔八哥";
        System.out.println("input:" + input);
        String encode = rsaHelper.encrypt("");
        System.out.println("encode:" + encode);
        String decode = rsaHelper.decrypt(encode);
        System.out.println("decode:" + decode);
    }
}
