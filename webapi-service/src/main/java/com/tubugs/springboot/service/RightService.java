package com.tubugs.springboot.service;

import com.tubugs.springboot.dao.entity.Organization;
import com.tubugs.springboot.dao.entity.Permission;
import com.tubugs.springboot.dao.entity.Role;
import com.tubugs.springboot.dao.entity.RoleAndPermission;
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
    private RoleAndPermissionMapper roleAndPermissionMapper;
    @Autowired
    private OrganizationMapper organizationMapper;
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private ExtMapper extMapper;

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

}
