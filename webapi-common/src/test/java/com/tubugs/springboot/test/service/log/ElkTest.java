package com.tubugs.springboot.test.service.log;

import com.tubugs.springboot.service.log.LogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by xuzhang on 2017/8/31.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Import(ElkConfig.class)
public class ElkTest {
    @Autowired
    private LogService logService;

    @Test
    public void log() {
        logService.debug("elk测试");
        logService.info("elk测试");
        logService.warn("elk测试");
        logService.error("elk测试");
        logService.debug("兔八哥", "1", "elk测试");
        logService.info("兔八哥", "1", "elk测试");
        logService.warn("兔八哥", "1", "elk测试");
        logService.error("兔八哥", "1", "elk测试");
        try {
            int result = 100 / 0;
        } catch (Exception ex) {
            logService.error(ex.getMessage(), ex);
        }
    }
}
