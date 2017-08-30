package com.tubugs.springboot.test.utils;

import com.tubugs.springboot.utils.HttpUtil;
import org.junit.Test;

/**
 * Created by xuzhang on 2017/8/29.
 */
public class HttpTest {
    @Test
    public void test() {
        System.out.println(HttpUtil.doGet("http://www.bootcss.com/"));
        System.out.println(HttpUtil.doGet("https://www.baidu.com/"));
    }
}
