package com.tubugs.springboot.test.properties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by xuzhang on 2017/8/29.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PropertiesTest {

    @Value("${sms.aliyun.verifycode.signName}")
    private String signName;

    @Test
    public void readChinese() {
        //测试读取properties中的中文
        //为了编辑方便，idea的file encoding中的文件格式一律utf-8，勾选transparent native to ascii conversion
        System.out.println(signName);
    }
}
