package com.tubugs.springboot.service.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xuzhang on 2017/8/30.
 */
public class LogServiceLogbackImpl implements LogService {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void error(String account, String session_id, String msg, Exception ex) {
        logger.error("account:{},session:{},msg:{}", account, session_id, msg, ex);
    }

    @Override
    public void error(String account, String session_id, String msg) {
        logger.error("account:{},session:{},msg:{}", account, session_id, msg);
    }

    @Override
    public void warn(String account, String session_id, String msg) {
        logger.warn("account:{},session:{},msg:{}", account, session_id, msg);
    }

    @Override
    public void info(String account, String session_id, String msg) {
        logger.info("account:{},session:{},msg:{}", account, session_id, msg);
    }

    @Override
    public void debug(String account, String session_id, String msg) {
        logger.debug("account:{},session:{},msg:{}", account, session_id, msg);
    }

    @Override
    public void error(String msg, Exception ex) {
        logger.error(msg, ex);
    }

    @Override
    public void error(String msg) {
        logger.error(msg);
    }

    @Override
    public void warn(String msg) {
        logger.warn(msg);
    }

    @Override
    public void info(String msg) {
        logger.info(msg);
    }

    @Override
    public void debug(String msg) {
        logger.debug(msg);
    }
}
