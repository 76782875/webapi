package com.tubugs.springboot.dao.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user_and_organization")
public class UserAndOrganization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_no")
    private Long userNo;

    @Column(name = "organization_id")
    private Long organizationId;

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
     * @return organization_id
     */
    public Long getOrganizationId() {
        return organizationId;
    }

    /**
     * @param organizationId
     */
    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
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