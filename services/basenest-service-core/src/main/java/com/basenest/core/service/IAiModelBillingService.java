package com.basenest.core.service;

import com.basenest.core.dao.dos.ai.AiModelBillingDO;

import java.util.List;

public interface IAiModelBillingService {
    Long addBilling(AiModelBillingDO aiModelBilling);

    AiModelBillingDO getBillingById(Long id);

    AiModelBillingDO getActiveBillingByModelId(Long modelId);

    List<AiModelBillingDO> getAllBillings();

    List<AiModelBillingDO> getBillingsByModelId(Long modelId);

    boolean updateBilling(AiModelBillingDO aiModelBilling);

    boolean deleteBilling(Long id);
}