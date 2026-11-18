package com.basenest.core.service.impl;

import com.basenest.core.dao.dos.ai.AiModelBillingDO;
import com.basenest.core.dao.mapper.ai.AiModelBillingMapper;
import com.basenest.core.service.IAiModelBillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName AiModelBillingServiceImpl
 * @Description AI model billing service impl
 * @Author happyboy
 * @Date 2026/11/19
 * @Version 1.0
 */
@Service
@Transactional
public class AiModelBillingServiceImpl implements IAiModelBillingService {

    @Autowired
    private AiModelBillingMapper aiModelBillingMapper;

    @Override
    public Long addBilling(AiModelBillingDO aiModelBilling) {
        aiModelBillingMapper.insert(aiModelBilling);
        return aiModelBilling.getId();
    }

    @Override
    public AiModelBillingDO getBillingById(Long id) {
        return aiModelBillingMapper.selectById(id);
    }

    @Override
    public AiModelBillingDO getActiveBillingByModelId(Long modelId) {
        return aiModelBillingMapper.selectActiveByModelId(modelId);
    }

    @Override
    public List<AiModelBillingDO> getAllBillings() {
        return aiModelBillingMapper.selectAll();
    }

    @Override
    public List<AiModelBillingDO> getBillingsByModelId(Long modelId) {
        return aiModelBillingMapper.selectByModelId(modelId);
    }

    @Override
    public boolean updateBilling(AiModelBillingDO aiModelBilling) {
        return aiModelBillingMapper.update(aiModelBilling) > 0;
    }

    @Override
    public boolean deleteBilling(Long id) {
        return aiModelBillingMapper.delete(id) > 0;
    }
}