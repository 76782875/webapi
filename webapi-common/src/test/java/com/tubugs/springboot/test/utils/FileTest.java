package com.tubugs.springboot.test.utils;

import com.tubugs.springboot.utils.FileUtil;
import com.tubugs.springboot.utils.ResourceUtil;
import org.junit.Test;

/**
 * Created by xuzhang on 2017/8/29.
 */
public class FileTest {

    @Test
    public void test() {
        String txt = FileUtil.readTxt(ResourceUtil.getAbsolutePath("city.txt"), "UTF-8");
        System.out.println(txt);

        txt = FileUtil.readTxt(ResourceUtil.getAbsolutePath("logback.xml"), "UTF-8");
        System.out.println(txt);
    }
}
