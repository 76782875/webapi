package com.tubugs.springboot.service.log.data;

import java.util.Date;

/**
 * Created by xuzhang on 2017/8/30.
 */
public class LogInfo {
    /**
     * 登录用户账号，如果没有传null
     */
    private String account;
    /**
     * 会话标识（客户端产生、透传到服务端各个组件；）
     */
    private String session_id;
    /**
     * 来源（接口、测试工具、管理系统）
     */
    private String source;
    /**
     * 来源IP地址
     */
    private String ip;
    /**
     * ERROR,WARN,INFO,DEBUG
     */
    private String level;
    /**
     * 日志内容
     */
    private String msg;
    /**
     * 日志时间
     */
    private Date create_time;


    public LogInfo() {
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}
