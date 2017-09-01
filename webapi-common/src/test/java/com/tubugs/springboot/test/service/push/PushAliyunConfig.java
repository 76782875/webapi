package com.tubugs.springboot.test.service.push;

import com.tubugs.springboot.service.push.PushService;
import com.tubugs.springboot.service.push.PushServiceAliyunImpl;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * Created by xuzhang on 2017/9/1.
 */
@TestConfiguration
public class PushAliyunConfig {
    @Bean
    public PushService push() {
        return new PushServiceAliyunImpl();
    }
}
