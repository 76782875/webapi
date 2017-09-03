package com.tubugs.springboot.dao.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user_and_role")
public class UserAndRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_no")
    private Long userNo;

    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "create_time")
    private Date createTime;

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
     * @return role_id
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * @param roleId
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
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
}