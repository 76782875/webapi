package com.tubugs.springboot.frame;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * Created by xuzhang on 2017/8/23.
 */
public class SessionManager {

    /**
     * 会话ID
     *
     * @return
     */
    public static String getSessionID() {
        return getHttpSession().getId().toString();
    }

    /**
     * 用户账号
     *
     * @return
     */
    public static String getUserAccount() {
        Subject subject = SecurityUtils.getSubject();
        Object obj = subject.getPrincipal();
        return obj == null ? null : (String) obj;
    }

    private static Session getHttpSession() {
        return SecurityUtils.getSubject().getSession();
    }
}
