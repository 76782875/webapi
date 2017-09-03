package com.tubugs.springboot;

import com.tubugs.springboot.ability.file.FileAbility;
import com.tubugs.springboot.ability.file.FileAbilityLocalImpl;
import com.tubugs.springboot.ability.push.PushAbility;
import com.tubugs.springboot.ability.push.PushAbilityAliyunImpl;
import com.tubugs.springboot.ability.sms.SMSAbility;
import com.tubugs.springboot.ability.sms.SMSAbilityAliyunImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by xuzhang on 2017/9/1.
 */
@Configuration
public class AbilityConfig {
    @Bean
    public PushAbility push() {
        return new PushAbilityAliyunImpl();
    }

    @Bean
    public SMSAbility sms() {
        return new SMSAbilityAliyunImpl();
    }

    @Bean
    public FileAbility file() {
        return new FileAbilityLocalImpl();
    }
}
