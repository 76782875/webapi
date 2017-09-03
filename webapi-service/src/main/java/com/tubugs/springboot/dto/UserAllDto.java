package com.tubugs.springboot.dto;


import com.tubugs.springboot.dao.dto.UserOauthDto;
import com.tubugs.springboot.dao.dto.UserOrganizationDto;
import com.tubugs.springboot.dao.dto.UserRoleDto;
import com.tubugs.springboot.dao.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuzhang on 2017/9/3.
 */
public class UserAllDto {
    private User user;
    private List<UserRoleDto> roles;
    private List<UserOrganizationDto> organizations;
    private List<UserOauthDto> oauths;

    public UserAllDto(User user) {
        this.user = user;
        this.roles = new ArrayList<UserRoleDto>();
        this.organizations = new ArrayList<UserOrganizationDto>();
        this.oauths = new ArrayList<UserOauthDto>();
    }

    public UserAllDto() {
        this.roles = new ArrayList<UserRoleDto>();
        this.organizations = new ArrayList<UserOrganizationDto>();
        this.oauths = new ArrayList<UserOauthDto>();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<UserRoleDto> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRoleDto> roles) {
        this.roles = roles;
    }

    public List<UserOrganizationDto> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<UserOrganizationDto> organizations) {
        this.organizations = organizations;
    }

    public List<UserOauthDto> getOauths() {
        return oauths;
    }

    public void setOauths(List<UserOauthDto> oauths) {
        this.oauths = oauths;
    }
}
