package com.tubugs.springboot.dao.mapper;

import com.tubugs.springboot.dao.dto.UserOauthDto;
import com.tubugs.springboot.dao.dto.UserOrganizationDto;
import com.tubugs.springboot.dao.dto.UserRoleDto;
import com.tubugs.springboot.dao.entity.Permission;
import com.tubugs.springboot.dao.entity.Role;
import com.tubugs.springboot.dao.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 自定义方法
 * Created by xuzhang on 2017/9/3.
 */
public interface ExtMapper {

    /**
     * 查询角色权限
     *
     * @param roleId
     * @return
     */
    List<Permission> queryRolePermission(@Param("roleId") Long roleId);

    /**
     * 查询用户角色
     *
     * @param userNo
     * @return
     */
    List<Role> queryUserRole(@Param("userNo") Long userNo);

    /**
     * 分页查询用户全量数据
     *
     * @param pageIndex
     * @param pageSize
     * @param keywords
     * @param userStatusFilter
     * @param roleFilter
     * @param organizationFilter
     * @return
     */
    List<User> queryUserAllPaging(@Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize, @Param("keywords") String keywords, @Param("userStatusFilter") Byte userStatusFilter, @Param("roleFilter") Long roleFilter, @Param("organizationFilter") Long organizationFilter);

    /**
     * 查询用户总数
     *
     * @param keywords
     * @param userStatusFilter
     * @param roleFilter
     * @param organizationFilter
     * @return
     */
    Long countUserAllPaging(@Param("keywords") String keywords, @Param("userStatusFilter") Byte userStatusFilter, @Param("roleFilter") Long roleFilter, @Param("organizationFilter") Long organizationFilter);

    /**
     * 查询用户角色(批量)
     *
     * @param userNos
     */
    List<UserRoleDto> queryUserRoleBatch(@Param("userNos") List<Long> userNos);

    /**
     * 查询用户组织(批量)
     *
     * @param userNos
     */
    List<UserOrganizationDto> queryUserOrganizationBatch(@Param("userNos") List<Long> userNos);

    /**
     * 查询用户三方账号(批量)
     *
     * @param userNos
     */
    List<UserOauthDto> queryUserOauthBatch(@Param("userNos") List<Long> userNos);
}
