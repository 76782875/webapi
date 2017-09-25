package com.tubugs.springboot.service;

import com.tubugs.springboot.dao.entity.*;
import com.tubugs.springboot.dao.mapper.*;
import com.tubugs.springboot.frame.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 权限
 * Created by xuzhang on 2017/9/1.
 */
@Service
public class RightService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private OrganizationMapper organizationMapper;
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private RoleAndPermissionMapper roleAndPermissionMapper;
    @Autowired
    private UserAndRoleMapper userAndRoleMapper;
    @Autowired
    private UserAndOrganizationMapper userAndOrganizationMapper;
    @Autowired
    private ExtMapper extMapper;

    //region 角色管理

    /**
     * 查询所有角色
     */
    public List<Role> getAllRole() {
        return roleMapper.selectAll();
    }

    /**
     * 删除角色
     *
     * @param id
     */
    public void deleteRole(Long id) {
        Validator.checkNotNull(id, "角色编号");
        roleMapper.deleteByPrimaryKey(id);
    }

    /**
     * 添加角色
     */
    public void addRole(String name, String description) {
        Validator.checkLength(0, 20, name, "角色名称");
        Validator.checkLength(0, 200, description, "角色描述");
        Role role = new Role();
        role.setName(name);
        role.setDescription(description);
        role.setCreateTime(new Date());
        role.setUpdateTime(new Date());
        roleMapper.insert(role);
    }

    /**
     * 修改角色
     */
    public void modifyRole(Long id, String name, String description) {
        Validator.checkNotNull(id, "角色编号");
        Validator.checkLength(0, 20, name, "角色名称");
        Validator.checkLength(0, 200, description, "角色描述");
        Role role = new Role();
        role.setId(id);
        role.setName(name);
        role.setDescription(description);
        role.setUpdateTime(new Date());
        roleMapper.updateByPrimaryKeySelective(role);
    }
    //endregion

    //region 权限管理

    /**
     * 查询所有权限
     */
    public List<Permission> getAllPermission() {
        return permissionMapper.selectAll();
    }

    /**
     * 删除权限
     */
    public void deletePermission(Long id) {
        Validator.checkNotNull(id, "权限编号");
        permissionMapper.deleteByPrimaryKey(id);
    }

    /**
     * 添加权限
     */
    public void addPermission(String name, String description, String value) {
        Validator.checkLength(0, 20, name, "权限名称");
        Validator.checkLength(0, 200, description, "权限描述");
        Validator.checkLength(0, 50, value, "权限值");
        Permission t = new Permission();
        t.setName(name);
        t.setDescription(description);
        t.setValue(value);
        t.setCreateTime(new Date());
        t.setUpdateTime(new Date());
        permissionMapper.insert(t);
    }

    /**
     * 添加权限
     */
    public void modifyPermission(Long id, String name, String description, String value) {
        Validator.checkNotNull(id, "权限编号");
        Validator.checkLength(0, 20, name, "权限名称");
        Validator.checkLength(0, 200, description, "权限描述");
        Validator.checkLength(0, 50, value, "权限值");
        Permission t = new Permission();
        t.setId(id);
        t.setName(name);
        t.setDescription(description);
        t.setValue(value);
        t.setUpdateTime(new Date());
        permissionMapper.updateByPrimaryKeySelective(t);
    }
    //endregion

    //region 组织管理

    /**
     * 查询所有组织
     */
    public List<Organization> getAllOrganization() {
        return organizationMapper.selectAll();
    }

    /**
     * 删除组织
     *
     * @param id
     */
    public void deleteOrganization(Long id) {
        Validator.checkNotNull(id, "组织编号");
        organizationMapper.deleteByPrimaryKey(id);
    }

    /**
     * 添加组织
     *
     * @param name
     * @param description
     * @param pid
     */
    public void addOrganization(String name, String description, Long pid) {
        Validator.checkLength(0, 20, name, "组织名称");
        Validator.checkLength(0, 200, description, "组织描述");
        Organization t = new Organization();
        t.setName(name);
        t.setDescription(description);
        t.setPid(pid);
        t.setCreateTime(new Date());
        t.setUpdateTime(new Date());
        organizationMapper.insert(t);
    }

    /**
     * 修改组织
     *
     * @param id
     * @param name
     * @param description
     * @param pid
     */
    public void modifyOrganization(Long id, String name, String description, Long pid) {
        Validator.checkNotNull(id, "组织编号");
        Validator.checkLength(0, 20, name, "组织名称");
        Validator.checkLength(0, 200, description, "组织描述");
        Organization t = new Organization();
        t.setId(id);
        t.setName(name);
        t.setDescription(description);
        t.setPid(pid);
        t.setUpdateTime(new Date());
        organizationMapper.updateByPrimaryKeySelective(t);
    }
    //endregion

    //region 角色权限管理

    /**
     * 添加角色权限
     *
     * @param roleId
     * @param permissionId
     */
    public void addRolePermission(Long roleId, Long permissionId) {
        Validator.checkNotNull(roleId, "角色编号");
        Validator.checkNotNull(permissionId, "权限编号");
        RoleAndPermission t = new RoleAndPermission();
        t.setRoleId(roleId);
        t.setPermissionId(permissionId);
        t.setCreateTime(new Date());
        roleAndPermissionMapper.insert(t);
    }

    /***
     * 删除角色权限
     * @param roleId
     * @param permissionId
     */
    public void deleteRolePermission(Long roleId, Long permissionId) {
        Validator.checkNotNull(roleId, "角色编号");
        Validator.checkNotNull(permissionId, "权限编号");
        RoleAndPermission t = new RoleAndPermission();
        t.setRoleId(roleId);
        t.setPermissionId(permissionId);
        roleAndPermissionMapper.delete(t);
    }

    /**
     * 查询角色权限
     *
     * @param roleId
     * @return
     */
    public List<Permission> selectRolePermission(Long roleId) {
        Validator.checkNotNull(roleId, "角色编号");
        return extMapper.queryRolePermission(roleId);
    }
    //endregion

    //region 用户角色管理

    /**
     * 查询用户角色
     *
     * @param userNo
     * @return
     */
    public List<Role> queryUserRole(Long userNo) {
        Validator.checkNotNull(userNo, "用户编号");
        return extMapper.queryUserRole(userNo);
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
    //endregion

    //region 用户组织管理

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
    //endregion
}
