package com.tubugs.springboot.test.service.log;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xuzhang on 2017/8/31.
 */

public class LogTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void test() {
        logger.debug("测试");
        logger.info("测试");
        logger.warn("测试");
        logger.error("测试");
        try {
            int a = 1 / 0;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }
}
