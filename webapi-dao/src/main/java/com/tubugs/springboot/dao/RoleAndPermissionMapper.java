package com.tubugs.springboot.dao;

import com.tubugs.springboot.dao.entity.RoleAndPermission;
import com.tubugs.springboot.dao.entity.RoleAndPermissionExample;
import com.tubugs.springboot.dao.entity.RoleAndPermissionKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleAndPermissionMapper {
    long countByExample(RoleAndPermissionExample example);

    int deleteByExample(RoleAndPermissionExample example);

    int deleteByPrimaryKey(RoleAndPermissionKey key);

    int insert(RoleAndPermission record);

    int insertSelective(RoleAndPermission record);

    List<RoleAndPermission> selectByExample(RoleAndPermissionExample example);

    RoleAndPermission selectByPrimaryKey(RoleAndPermissionKey key);

    int updateByExampleSelective(@Param("record") RoleAndPermission record, @Param("example") RoleAndPermissionExample example);

    int updateByExample(@Param("record") RoleAndPermission record, @Param("example") RoleAndPermissionExample example);

    int updateByPrimaryKeySelective(RoleAndPermission record);

    int updateByPrimaryKey(RoleAndPermission record);
}