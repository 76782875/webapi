package com.tubugs.springboot.test.utils;

import com.tubugs.springboot.utils.IPUtil;
import org.junit.Test;

/**
 * Created by xuzhang on 2017/8/31.
 */
public class IPTest {

    @Test
    public void Test() {
        //TODO 去linux下测试下
        System.out.println(IPUtil.getLocalIP());
    }
}
