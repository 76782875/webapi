package com.tubugs.springboot.test;

import com.tubugs.springboot.service.sms.SMSServcie;
import com.tubugs.springboot.service.sms.SMSServiceAliyunImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by xuzhang on 2017/8/29.
 */
@Configuration
public class Config {
    @Bean
    public SMSServcie smsServcie() {
        return new SMSServiceAliyunImpl();
    }
}
