package com.tubugs.springboot.dao;

import com.tubugs.springboot.dao.entity.UserAndRole;
import com.tubugs.springboot.dao.entity.UserAndRoleExample;
import com.tubugs.springboot.dao.entity.UserAndRoleKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserAndRoleMapper {
    long countByExample(UserAndRoleExample example);

    int deleteByExample(UserAndRoleExample example);

    int deleteByPrimaryKey(UserAndRoleKey key);

    int insert(UserAndRole record);

    int insertSelective(UserAndRole record);

    List<UserAndRole> selectByExample(UserAndRoleExample example);

    UserAndRole selectByPrimaryKey(UserAndRoleKey key);

    int updateByExampleSelective(@Param("record") UserAndRole record, @Param("example") UserAndRoleExample example);

    int updateByExample(@Param("record") UserAndRole record, @Param("example") UserAndRoleExample example);

    int updateByPrimaryKeySelective(UserAndRole record);

    int updateByPrimaryKey(UserAndRole record);
}