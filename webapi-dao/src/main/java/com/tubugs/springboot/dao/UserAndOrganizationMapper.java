package com.tubugs.springboot.dao;

import com.tubugs.springboot.dao.entity.UserAndOrganization;
import com.tubugs.springboot.dao.entity.UserAndOrganizationExample;
import com.tubugs.springboot.dao.entity.UserAndOrganizationKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserAndOrganizationMapper {
    long countByExample(UserAndOrganizationExample example);

    int deleteByExample(UserAndOrganizationExample example);

    int deleteByPrimaryKey(UserAndOrganizationKey key);

    int insert(UserAndOrganization record);

    int insertSelective(UserAndOrganization record);

    List<UserAndOrganization> selectByExample(UserAndOrganizationExample example);

    UserAndOrganization selectByPrimaryKey(UserAndOrganizationKey key);

    int updateByExampleSelective(@Param("record") UserAndOrganization record, @Param("example") UserAndOrganizationExample example);

    int updateByExample(@Param("record") UserAndOrganization record, @Param("example") UserAndOrganizationExample example);

    int updateByPrimaryKeySelective(UserAndOrganization record);

    int updateByPrimaryKey(UserAndOrganization record);
}