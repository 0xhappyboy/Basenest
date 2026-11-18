package com.basenest.core.service;

import com.basenest.core.dao.dos.ai.AiModelCreditRateDO;

import java.util.List;

public interface IAiModelCreditRateService {
    Long addCreditRate(AiModelCreditRateDO aiModelCreditRate);

    AiModelCreditRateDO getCreditRateById(Long id);

    AiModelCreditRateDO getActiveCreditRateByModelId(Long modelId);

    List<AiModelCreditRateDO> getAllCreditRates();

    List<AiModelCreditRateDO> getCreditRatesByModelId(Long modelId);

    boolean updateCreditRate(AiModelCreditRateDO aiModelCreditRate);

    boolean deleteCreditRate(Long id);
}