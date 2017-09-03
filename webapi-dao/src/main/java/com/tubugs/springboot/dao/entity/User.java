package com.tubugs.springboot.dao.entity;

import java.util.Date;
import javax.persistence.*;

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 开始使用9位数，随着用户量增大，可以不断扩展
     */
    private Long no;

    private String account;

    private String password;

    private String salt;

    private String phone;

    @Column(name = "nick_name")
    private String nickName;

    private String avatar;

    private String email;

    /**
     * 0未知 1男 2女
     */
    private Byte sex;

    private Date birthday;

    /**
     * 0待审核 1正常 2禁用
     */
    private Byte status;

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
     * 获取开始使用9位数，随着用户量增大，可以不断扩展
     *
     * @return no - 开始使用9位数，随着用户量增大，可以不断扩展
     */
    public Long getNo() {
        return no;
    }

    /**
     * 设置开始使用9位数，随着用户量增大，可以不断扩展
     *
     * @param no 开始使用9位数，随着用户量增大，可以不断扩展
     */
    public void setNo(Long no) {
        this.no = no;
    }

    /**
     * @return account
     */
    public String getAccount() {
        return account;
    }

    /**
     * @param account
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return salt
     */
    public String getSalt() {
        return salt;
    }

    /**
     * @param salt
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return nick_name
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * @param nickName
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * @return avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * @param avatar
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取0未知 1男 2女
     *
     * @return sex - 0未知 1男 2女
     */
    public Byte getSex() {
        return sex;
    }

    /**
     * 设置0未知 1男 2女
     *
     * @param sex 0未知 1男 2女
     */
    public void setSex(Byte sex) {
        this.sex = sex;
    }

    /**
     * @return birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * @param birthday
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取0待审核 1正常 2禁用
     *
     * @return status - 0待审核 1正常 2禁用
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置0待审核 1正常 2禁用
     *
     * @param status 0待审核 1正常 2禁用
     */
    public void setStatus(Byte status) {
        this.status = status;
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