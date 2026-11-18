package com.basenest.core.service.impl;

import com.basenest.core.dao.dos.ai.AiModelCreditRateDO;
import com.basenest.core.dao.mapper.ai.AiModelCreditRateMapper;
import com.basenest.core.service.IAiModelCreditRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName AiModelCreditRateServiceImpl
 * @Description AI model credit rate service impl
 * @Author happyboy
 * @Date 2026/11/19
 * @Version 1.0
 */
@Service
@Transactional
public class AiModelCreditRateServiceImpl implements IAiModelCreditRateService {

    @Autowired
    private AiModelCreditRateMapper aiModelCreditRateMapper;

    @Override
    public Long addCreditRate(AiModelCreditRateDO aiModelCreditRate) {
        aiModelCreditRateMapper.insert(aiModelCreditRate);
        return aiModelCreditRate.getId();
    }

    @Override
    public AiModelCreditRateDO getCreditRateById(Long id) {
        return aiModelCreditRateMapper.selectById(id);
    }

    @Override
    public AiModelCreditRateDO getActiveCreditRateByModelId(Long modelId) {
        return aiModelCreditRateMapper.selectActiveByModelId(modelId);
    }

    @Override
    public List<AiModelCreditRateDO> getAllCreditRates() {
        return aiModelCreditRateMapper.selectAll();
    }

    @Override
    public List<AiModelCreditRateDO> getCreditRatesByModelId(Long modelId) {
        return aiModelCreditRateMapper.selectByModelId(modelId);
    }

    @Override
    public boolean updateCreditRate(AiModelCreditRateDO aiModelCreditRate) {
        return aiModelCreditRateMapper.update(aiModelCreditRate) > 0;
    }

    @Override
    public boolean deleteCreditRate(Long id) {
        return aiModelCreditRateMapper.delete(id) > 0;
    }
}