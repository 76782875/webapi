package com.tubugs.springboot.test.service.log;

import com.tubugs.springboot.service.log.LogService;
import com.tubugs.springboot.service.log.LogServiceElkImpl;
import com.tubugs.springboot.utils.IPUtil;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * Created by xuzhang on 2017/8/31.
 */
@TestConfiguration
public class ElkConfig {
    @Bean
    public LogService log() {
        LogServiceElkImpl logService = new LogServiceElkImpl();
        logService.setIp(IPUtil.getLocalIP());
        logService.setSource("测试工具");
        return logService;
    }
}
