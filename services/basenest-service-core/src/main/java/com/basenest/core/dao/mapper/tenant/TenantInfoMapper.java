package com.basenest.core.dao.mapper.tenant;

import com.basenest.core.dao.dos.tenant.TenantInfoDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName TenantInfoMapper
 * @Description Tenant info mapper
 * @Author happyboy
 * @Date 2025/12/9 11:45
 * @Version 1.0
 */
@Mapper
public interface TenantInfoMapper {
    int insert(@Param("tenantInfoDO") TenantInfoDO tenantInfo);

    TenantInfoDO selectById(@Param("id") Long id);

    TenantInfoDO selectByCode(@Param("code") String code);

    List<TenantInfoDO> selectAll();

    int update(@Param("tenantInfoDO") TenantInfoDO tenantInfo);

    int delete(@Param("id") Long id);

    List<TenantInfoDO> searchByName(@Param("keyword") String keyword);
}