package com.basenest.core.service;

import com.basenest.core.dao.dos.UserLlmUsageDO;

import java.util.List;

public interface IUserLlmUsageService {
    Long addUsage(UserLlmUsageDO usage);

    UserLlmUsageDO getUsageById(Long id);

    List<UserLlmUsageDO> getUsagesByUserId(Long userId);

    List<UserLlmUsageDO> getUsagesByTenantId(Long tenantId);

    boolean deleteUsage(Long id);
}