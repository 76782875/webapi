package com.tubugs.springboot.test.service.sms;

import com.alibaba.fastjson.JSONObject;
import com.tubugs.springboot.service.sms.SMSServcie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by xuzhang on 2017/8/29.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Import(SMSAliyunConfig.class)
public class SMSAliyunTest {
    @Autowired
    private SMSServcie smsServcie;

    @Test
    public void sendVerifyCode() {
        //阿里云申请的短信模板：验证码${code}，有效期30分钟。如非本人操作，建议立即更改账户密码。
        smsServcie.sendVerifyCode("13721061552");
    }

    @Test
    public void send() {
        //阿里云申请的短信模板：尊敬的${name}，您的快递已在飞奔的路上，将在今天${time}送达您的手里，请留意查收。
        JSONObject json = new JSONObject();
        json.put("name", "猴九妹女士");
        json.put("time", "17:00");
        smsServcie.send("SMS_90785004", "兔八哥", "13721061552", json);
    }
}
