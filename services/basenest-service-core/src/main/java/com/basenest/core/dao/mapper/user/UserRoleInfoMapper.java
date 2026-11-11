package com.basenest.core.dao.mapper;

import com.basenest.core.dao.dos.UserRoleInfoDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName UserRoleInfoMapper
 * @Description User role info mapper
 * @Author happyboy
 * @Date 2025/12/9 11:45
 * @Version 1.0
 */
@Mapper
public interface UserRoleInfoMapper {
    int insert(@Param("userRoleInfoDO") UserRoleInfoDO userRoleInfo);

    UserRoleInfoDO selectById(@Param("id") Long id);

    UserRoleInfoDO selectByTenantAndCode(@Param("tenantId") Long tenantId,
                                         @Param("code") String code);

    List<UserRoleInfoDO> selectByTenantId(@Param("tenantId") Long tenantId);

    int update(@Param("userRoleInfoDO") UserRoleInfoDO userRoleInfo);

    int delete(@Param("id") Long id);
}