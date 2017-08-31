package com.tubugs.springboot.test.service.log;

import com.tubugs.springboot.service.log.LogService;
import com.tubugs.springboot.service.log.LogServiceLogbackImpl;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * Created by xuzhang on 2017/8/31.
 */
@TestConfiguration
public class LogBackConfig {
    @Bean
    public LogService log() {
        return new LogServiceLogbackImpl();
    }
}
