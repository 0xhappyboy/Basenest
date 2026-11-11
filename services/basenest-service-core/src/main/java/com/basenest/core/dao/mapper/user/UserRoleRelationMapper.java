package com.basenest.core.dao.mapper;

import com.basenest.core.dao.dos.UserRoleRelationDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName UserRoleRelationMapper
 * @Description User role relation mapper
 * @Author happyboy
 * @Date 2025/12/9 11:45
 * @Version 1.0
 */
@Mapper
public interface UserRoleRelationMapper {
    int insert(@Param("userRoleRelationDO") UserRoleRelationDO userRoleRelation);

    UserRoleRelationDO selectById(@Param("id") Long id);

    List<UserRoleRelationDO> selectByTenantAndUser(@Param("tenantId") Long tenantId,
                                                   @Param("userId") Long userId);

    int update(@Param("userRoleRelationDO") UserRoleRelationDO userRoleRelation);

    int delete(@Param("id") Long id);
}