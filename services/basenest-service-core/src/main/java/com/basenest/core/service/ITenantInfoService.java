package com.basenest.core.service;

import com.basenest.core.dao.dos.tenant.TenantInfoDO;

import java.util.List;

public interface ITenantInfoService {
    Long addTenant(TenantInfoDO tenantInfo);

    TenantInfoDO getTenantById(Long id);

    TenantInfoDO getTenantByCode(String code);

    List<TenantInfoDO> getAllTenants();

    boolean updateTenant(TenantInfoDO tenantInfo);

    boolean deleteTenant(Long id);

    List<TenantInfoDO> searchTenants(String keyword);
}