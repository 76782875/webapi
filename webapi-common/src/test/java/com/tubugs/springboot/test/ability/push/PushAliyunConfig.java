package com.tubugs.springboot.test.ability.push;

import com.tubugs.springboot.ability.push.PushAbility;
import com.tubugs.springboot.ability.push.PushAbilityAliyunImpl;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * Created by xuzhang on 2017/9/1.
 */
@TestConfiguration
public class PushAliyunConfig {
    @Bean
    public PushAbility push() {
        return new PushAbilityAliyunImpl();
    }
}
