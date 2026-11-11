package com.basenest.core.service.impl;

import com.basenest.core.dao.dos.tenant.TenantInfoDO;
import com.basenest.core.dao.mapper.tenant.TenantInfoMapper;
import com.basenest.core.service.ITenantInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName TenantInfoServiceImpl
 * @Description Tenant info service impl
 * @Author happyboy
 * @Date 2026/11/11
 * @Version 1.0
 */
@Service
@Transactional
public class TenantInfoServiceImpl implements ITenantInfoService {

    @Autowired
    private TenantInfoMapper tenantInfoMapper;

    @Override
    public Long addTenant(TenantInfoDO tenantInfo) {
        tenantInfoMapper.insert(tenantInfo);
        return tenantInfo.getId();
    }

    @Override
    public TenantInfoDO getTenantById(Long id) {
        return tenantInfoMapper.selectById(id);
    }

    @Override
    public TenantInfoDO getTenantByCode(String code) {
        return tenantInfoMapper.selectByCode(code);
    }

    @Override
    public List<TenantInfoDO> getAllTenants() {
        return tenantInfoMapper.selectAll();
    }

    @Override
    public boolean updateTenant(TenantInfoDO tenantInfo) {
        return tenantInfoMapper.update(tenantInfo) > 0;
    }

    @Override
    public boolean deleteTenant(Long id) {
        return tenantInfoMapper.delete(id) > 0;
    }

    @Override
    public List<TenantInfoDO> searchTenants(String keyword) {
        return tenantInfoMapper.searchByName(keyword);
    }
}