package com.tubugs.springboot.test.utils;

import com.tubugs.springboot.utils.HttpUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by xuzhang on 2017/8/29.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HttpTest {

    @Autowired
    private HttpUtil httpUtil;

    @Test
    public void test() {
        System.out.println(httpUtil.doGet("http://www.bootcss.com/"));
        System.out.println(httpUtil.doGet("https://www.baidu.com/"));
    }
}
