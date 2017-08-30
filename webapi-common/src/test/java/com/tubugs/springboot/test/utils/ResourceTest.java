package com.tubugs.springboot.test.utils;

import com.tubugs.springboot.utils.ResourceUtil;
import org.junit.Test;

/**
 * Created by xuzhang on 2017/8/29.
 */
public class ResourceTest {

    @Test
    public void test() {
        String path = ResourceUtil.getAbsolutePath("application.properties");
        System.out.println(path);
    }
}
