package com.tubugs.springboot.service.log;

import com.tubugs.springboot.service.log.data.LogInfo;

/**
 * Created by xuzhang on 2017/8/30.
 */
public class LogServiceElkImpl implements LogService {

    private String ip;
    private String source;

    public LogServiceElkImpl() {
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public void error(String account, String session_id, String msg, Exception ex) {

    }

    @Override
    public void error(String account, String session_id, String msg) {

    }

    @Override
    public void warn(String account, String session_id, String msg) {

    }

    @Override
    public void info(String account, String session_id, String msg) {

    }

    @Override
    public void debug(String account, String session_id, String msg) {

    }

    @Override
    public void error(String msg, Exception ex) {

    }

    @Override
    public void error(String msg) {

    }

    @Override
    public void warn(String msg) {

    }

    @Override
    public void info(String msg) {

    }

    @Override
    public void debug(String msg) {

    }


    private void log(String account, String session_id, String msg) {
        LogInfo info = new LogInfo();
    }
}
