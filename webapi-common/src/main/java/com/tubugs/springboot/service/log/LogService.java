package com.tubugs.springboot.service.log;

/**
 * Created by xuzhang on 2017/8/30.
 */
public interface LogService {
    void error(String account, String session_id, String msg, Exception ex);

    void error(String account, String session_id, String msg);

    void warn(String account, String session_id, String msg);

    void info(String account, String session_id, String msg);

    void debug(String account, String session_id, String msg);

    void error(String msg, Exception ex);

    void error(String msg);

    void warn(String msg);

    void info(String msg);

    void debug(String msg);
}
