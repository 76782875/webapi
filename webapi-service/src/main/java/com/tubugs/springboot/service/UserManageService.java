package com.tubugs.springboot.service;

import com.tubugs.springboot.consts.StatusKey;
import com.tubugs.springboot.dao.dto.UserOauthDto;
import com.tubugs.springboot.dao.dto.UserOrganizationDto;
import com.tubugs.springboot.dao.dto.UserRoleDto;
import com.tubugs.springboot.dao.entity.User;
import com.tubugs.springboot.dao.entity.UserAndOrganization;
import com.tubugs.springboot.dao.entity.UserAndRole;
import com.tubugs.springboot.dao.mapper.ExtMapper;
import com.tubugs.springboot.dao.mapper.UserAndOrganizationMapper;
import com.tubugs.springboot.dao.mapper.UserAndRoleMapper;
import com.tubugs.springboot.dao.mapper.UserMapper;
import com.tubugs.springboot.dto.PagingDto;
import com.tubugs.springboot.dto.UserAllDto;
import com.tubugs.springboot.frame.validator.Validator;
import com.tubugs.springboot.helper.UserHelper;
import com.tubugs.springboot.utils.NumberUtil;
import com.tubugs.springboot.utils.PwdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户管理
 * Created by xuzhang on 2017/9/3.
 */
@Service
public class UserManageService {
    @Autowired
    private UserAndRoleMapper userAndRoleMapper;
    @Autowired
    private UserAndOrganizationMapper userAndOrganizationMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ExtMapper extMapper;

    /**
     * 分页查询用户全量数据（user,user_role,user_organization,user_oauth）
     * 考虑到性能，keywords只做前匹配
     */
    public PagingDto<UserAllDto> queryUserAllPaging(Integer pageIndex, Integer pageSize, String keywords, Byte userStatusFilter, Long roleFilter, Long organizationFilter) {
        Validator.checkSize(1, null, pageIndex, "页号");
        Validator.checkSize(1, 50, pageSize, "页大小");
        List<User> users = extMapper.queryUserAllPaging(pageIndex, pageSize, keywords, userStatusFilter, roleFilter, organizationFilter);
        Long total = extMapper.countUserAllPaging(keywords, userStatusFilter, roleFilter, organizationFilter);
        List<UserAllDto> dtos = new ArrayList<UserAllDto>();
        PagingDto<UserAllDto> r = new PagingDto<UserAllDto>(pageIndex, pageSize, total, dtos);
        List<Long> user_nos = UserHelper.getNos(users);
        if (user_nos == null)
            return r;

        //添加用户
        for (User user : users) {
            dtos.add(new UserAllDto(user));
        }

        //批量查询用户角色
        List<UserRoleDto> roleDtos = extMapper.queryUserRoleBatch(user_nos);
        for (UserAllDto dto : dtos) {
            for (UserRoleDto userRoleDto : roleDtos) {
                if (dto.getUser().getNo().equals(userRoleDto.getUserNo())) {
                    dto.getRoles().add(userRoleDto);
                }
            }
        }

        //批量查询用户组织
        List<UserOrganizationDto> organizationDtos = extMapper.queryUserOrganizationBatch(user_nos);
        for (UserAllDto dto : dtos) {
            for (UserOrganizationDto userOrganizationDto : organizationDtos) {
                if (dto.getUser().getNo().equals(userOrganizationDto.getUserNo())) {
                    dto.getOrganizations().add(userOrganizationDto);
                }
            }
        }

        //批量查询用户三方账号
        List<UserOauthDto> oauthDtos = extMapper.queryUserOauthBatch(user_nos);
        for (UserAllDto dto : dtos) {
            for (UserOauthDto userOauthDto : oauthDtos) {
                if (dto.getUser().getNo().equals(userOauthDto.getUserNo())) {
                    dto.getOauths().add(userOauthDto);
                }
            }
        }
        return r;
    }

    /**
     * 添加用户角色
     *
     * @param userNo
     * @param roleId
     */
    public void addUserRole(Long userNo, Long roleId) {
        Validator.checkNotNull(userNo, "用户编号");
        Validator.checkNotNull(roleId, "角色编号");
        UserAndRole t = new UserAndRole();
        t.setUserNo(userNo);
        t.setRoleId(roleId);
        t.setCreateTime(new Date());
        userAndRoleMapper.insert(t);
    }

    /**
     * 删除用户角色
     *
     * @param userNo
     * @param roleId
     */
    public void deleteUserRole(Long userNo, Long roleId) {
        Validator.checkNotNull(userNo, "用户编号");
        Validator.checkNotNull(roleId, "角色编号");
        UserAndRole t = new UserAndRole();
        t.setUserNo(userNo);
        t.setRoleId(roleId);
        userAndRoleMapper.delete(t);
    }

    /**
     * 添加用户组织
     *
     * @param userNo
     * @param organizationId
     */
    public void addUserOrganization(Long userNo, Long organizationId) {
        Validator.checkNotNull(userNo, "用户编号");
        Validator.checkNotNull(organizationId, "组织编号");
        UserAndOrganization t = new UserAndOrganization();
        t.setUserNo(userNo);
        t.setOrganizationId(organizationId);
        t.setCreateTime(new Date());
        userAndOrganizationMapper.insert(t);
    }

    /**
     * 删除用户组织
     *
     * @param userNo
     * @param organizationId
     */
    public void deleteUserOrganization(Long userNo, Long organizationId) {
        Validator.checkNotNull(userNo, "用户编号");
        Validator.checkNotNull(organizationId, "组织编号");
        UserAndOrganization t = new UserAndOrganization();
        t.setUserNo(userNo);
        t.setOrganizationId(organizationId);
        userAndOrganizationMapper.delete(t);
    }

    /**
     * 修改用户状态
     *
     * @param userNo
     * @param status
     */
    public void modifyUserStatus(Long userNo, Byte status) {
        Validator.checkNotNull(userNo, "用户编号");
        Validator.checkSize((int) StatusKey.Pending, (int) StatusKey.Unavailable, (int) status, "用户状态");
        Example e = new Example(User.class);
        e.createCriteria().andEqualTo("no", userNo);
        User t = new User();
        t.setStatus(status);
        t.setUpdateTime(new Date());
        userMapper.updateByExampleSelective(t, e);
    }

    /**
     * 创建用户
     *
     * @param account
     * @param password
     */
    public void addUser(String account, String password) {
        Validator.checkNotNull(account, "账号");
        Validator.checkNotNull(password, "密码");
        String salt = PwdUtil.generateSalt();
        String pwd = PwdUtil.computePwdWithSalt(password, salt);
        Long userNo = NumberUtil.generateUserNo();
        User t = new User();
        t.setAccount(account);
        t.setSalt(salt);
        t.setPassword(pwd);
        t.setNo(userNo);
        t.setStatus(StatusKey.Available);
        t.setCreateTime(new Date());
        t.setUpdateTime(new Date());
        userMapper.insert(t);
    }
}
