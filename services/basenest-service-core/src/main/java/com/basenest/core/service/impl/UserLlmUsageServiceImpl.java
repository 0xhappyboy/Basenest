package com.basenest.core.service.impl;

import com.basenest.core.dao.dos.UserLlmUsageDO;
import com.basenest.core.dao.mapper.UserLlmUsageMapper;
import com.basenest.core.service.IUserLlmUsageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName UserLlmUsageServiceImpl
 * @Description User LLM usage service impl
 * @Author happyboy
 * @Date 2026/11/11
 * @Version 1.0
 */
@Service
@Transactional
public class UserLlmUsageServiceImpl implements IUserLlmUsageService {

    @Autowired
    private UserLlmUsageMapper userLlmUsageMapper;

    @Override
    public Long addUsage(UserLlmUsageDO usage) {
        userLlmUsageMapper.insert(usage);
        return usage.getId();
    }

    @Override
    public UserLlmUsageDO getUsageById(Long id) {
        return userLlmUsageMapper.selectById(id);
    }

    @Override
    public List<UserLlmUsageDO> getUsagesByUserId(Long userId) {
        return userLlmUsageMapper.selectByUserId(userId);
    }

    @Override
    public List<UserLlmUsageDO> getUsagesByTenantId(Long tenantId) {
        return userLlmUsageMapper.selectByTenantId(tenantId);
    }

    @Override
    public boolean deleteUsage(Long id) {
        return userLlmUsageMapper.delete(id) > 0;
    }
}