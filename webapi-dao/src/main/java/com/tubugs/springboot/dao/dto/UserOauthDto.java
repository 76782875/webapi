package com.tubugs.springboot.dao.dto;

/**
 * Created by xuzhang on 2017/9/3.
 */
public class UserOauthDto {
    private Long id;

    private Long userNo;

    private String name;

    public UserOauthDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserNo() {
        return userNo;
    }

    public void setUserNo(Long userNo) {
        this.userNo = userNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
