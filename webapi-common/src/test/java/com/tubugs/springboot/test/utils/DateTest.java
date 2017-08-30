package com.tubugs.springboot.test.utils;

import com.tubugs.springboot.utils.DateUtil;
import org.junit.Test;

/**
 * Created by xuzhang on 2017/8/29.
 */
public class DateTest {
    @Test
    public void test() {
        System.out.println(DateUtil.getString("yyyyMMdd"));
        System.out.println(DateUtil.getString("yyyyMMddHHmmss"));
        System.out.println(DateUtil.getTime());
    }
}
