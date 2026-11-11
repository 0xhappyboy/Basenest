package com.basenest.core.service.impl;

import com.basenest.core.dao.dos.tenant.TenantUserDO;
import com.basenest.core.dao.mapper.tenant.TenantUserMapper;
import com.basenest.core.service.ITenantUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName TenantUserServiceImpl
 * @Description Tenant-user relation service impl
 * @Author happyboy
 * @Date 2026/11/11
 * @Version 1.0
 */
@Service
@Transactional
public class TenantUserServiceImpl implements ITenantUserService {

    @Autowired
    private TenantUserMapper tenantUserMapper;

    @Override
    public Long addRelation(TenantUserDO tenantUser) {
        tenantUserMapper.insert(tenantUser);
        return tenantUser.getId();
    }

    @Override
    public TenantUserDO getRelationById(Long id) {
        return tenantUserMapper.selectById(id);
    }

    @Override
    public TenantUserDO getRelation(Long tenantId, Long userId) {
        return tenantUserMapper.selectByTenantAndUser(tenantId, userId);
    }

    @Override
    public List<TenantUserDO> getByTenantId(Long tenantId) {
        return tenantUserMapper.selectByTenantId(tenantId);
    }

    @Override
    public List<TenantUserDO> getByUserId(Long userId) {
        return tenantUserMapper.selectByUserId(userId);
    }

    @Override
    public boolean updateRelation(TenantUserDO tenantUser) {
        return tenantUserMapper.update(tenantUser) > 0;
    }

    @Override
    public boolean deleteRelation(Long id) {
        return tenantUserMapper.delete(id) > 0;
    }
}