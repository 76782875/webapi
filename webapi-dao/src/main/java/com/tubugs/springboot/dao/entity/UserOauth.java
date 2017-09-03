package com.tubugs.springboot.dao.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user_oauth")
public class UserOauth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_no")
    private Long userNo;

    /**
     * 三方登录类型，常用的有（qq、wechat）
     */
    @Column(name = "oauth_name")
    private String oauthName;

    @Column(name = "oauth_id")
    private String oauthId;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return user_no
     */
    public Long getUserNo() {
        return userNo;
    }

    /**
     * @param userNo
     */
    public void setUserNo(Long userNo) {
        this.userNo = userNo;
    }

    /**
     * 获取三方登录类型，常用的有（qq、wechat）
     *
     * @return oauth_name - 三方登录类型，常用的有（qq、wechat）
     */
    public String getOauthName() {
        return oauthName;
    }

    /**
     * 设置三方登录类型，常用的有（qq、wechat）
     *
     * @param oauthName 三方登录类型，常用的有（qq、wechat）
     */
    public void setOauthName(String oauthName) {
        this.oauthName = oauthName;
    }

    /**
     * @return oauth_id
     */
    public String getOauthId() {
        return oauthId;
    }

    /**
     * @param oauthId
     */
    public void setOauthId(String oauthId) {
        this.oauthId = oauthId;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}