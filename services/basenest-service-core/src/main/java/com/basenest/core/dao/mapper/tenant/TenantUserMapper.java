package com.basenest.core.dao.mapper.tenant;

import com.basenest.core.dao.dos.tenant.TenantUserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName TenantUserMapper
 * @Description Tenant-user relation mapper
 * @Author happyboy
 * @Date 2025/12/9 11:45
 * @Version 1.0
 */
@Mapper
public interface TenantUserMapper {
    int insert(@Param("tenantUserDO") TenantUserDO tenantUser);

    TenantUserDO selectById(@Param("id") Long id);

    TenantUserDO selectByTenantAndUser(@Param("tenantId") Long tenantId,
                                       @Param("userId") Long userId);

    List<TenantUserDO> selectByTenantId(@Param("tenantId") Long tenantId);

    List<TenantUserDO> selectByUserId(@Param("userId") Long userId);

    int update(@Param("tenantUserDO") TenantUserDO tenantUser);

    int delete(@Param("id") Long id);
}