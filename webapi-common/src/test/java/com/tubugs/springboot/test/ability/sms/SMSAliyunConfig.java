package com.tubugs.springboot.test.ability.sms;

import com.tubugs.springboot.ability.sms.SMSAbility;
import com.tubugs.springboot.ability.sms.SMSAbilityAliyunImpl;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * Created by xuzhang on 2017/9/1.
 */
@TestConfiguration
public class SMSAliyunConfig {
    @Bean
    public SMSAbility sms() {
        return new SMSAbilityAliyunImpl();
    }
}
