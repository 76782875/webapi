package com.tubugs.springboot.test.utils;

import com.tubugs.springboot.utils.FileUtil;
import com.tubugs.springboot.utils.ResourceUtil;
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
public class FileTest {

    @Autowired
    private FileUtil fileUtil;

    @Test
    public void test() {
        String txt = fileUtil.readTxt(ResourceUtil.getAbsolutePath("city.txt"), "UTF-8");
        System.out.println(txt);

        txt = fileUtil.readTxt(ResourceUtil.getAbsolutePath("logback.xml"), "UTF-8");
        System.out.println(txt);
    }
}
