package com.tubugs.springboot.test.service.push;

import com.aliyuncs.exceptions.ClientException;
import com.tubugs.springboot.service.push.PushService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by xuzhang on 2017/9/1.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Import(PushAliyunConfig.class)
public class PushAliyunTest {

    @Autowired
    private PushService pushService;

    @Test
    public void testMessage() throws ClientException {
        boolean result = pushService.sendMessage("13721061552", "兔八哥", "兔八哥测试阿里云消息推送", null);
        Assert.assertTrue(result);
    }

    @Test
    public void testNotice() throws ClientException {
        boolean result = pushService.sendNotice("13721061552", "兔八哥", "兔八哥测试阿里云消息推送", null);
        Assert.assertTrue(result);
    }
}
