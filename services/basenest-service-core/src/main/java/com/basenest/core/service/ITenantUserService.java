package com.basenest.core.service;

import com.basenest.core.dao.dos.tenant.TenantUserDO;

import java.util.List;

public interface ITenantUserService {
    Long addRelation(TenantUserDO tenantUser);

    TenantUserDO getRelationById(Long id);

    TenantUserDO getRelation(Long tenantId, Long userId);

    List<TenantUserDO> getByTenantId(Long tenantId);

    List<TenantUserDO> getByUserId(Long userId);

    boolean updateRelation(TenantUserDO tenantUser);

    boolean deleteRelation(Long id);
}