package com.tubugs.springboot.frame;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.util.StringUtils;

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
        return getSession().getId().toString();
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

    public static String getSession(String key) {
        return getSession().getAttribute(key).toString();
    }


    public static void setSession(String key, String value) {
        getSession().setAttribute(key, value);
    }

    public static void removeSession(String key) {
        getSession().removeAttribute(key);
    }

    private static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }
}
