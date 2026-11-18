package com.basenest.core.service.impl;

import com.basenest.core.dao.dos.ai.AiModelDO;
import com.basenest.core.dao.mapper.ai.AiModelMapper;
import com.basenest.core.service.IAiModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName AiModelServiceImpl
 * @Description AI model service impl
 * @Author happyboy
 * @Date 2026/11/19
 * @Version 1.0
 */
@Service
@Transactional
public class AiModelServiceImpl implements IAiModelService {

    @Autowired
    private AiModelMapper aiModelMapper;

    @Override
    public Long addModel(AiModelDO aiModel) {
        aiModelMapper.insert(aiModel);
        return aiModel.getId();
    }

    @Override
    public AiModelDO getModelById(Long id) {
        return aiModelMapper.selectById(id);
    }

    @Override
    public AiModelDO getModelByVendorAndModelCode(Long vendorId, String modelCode) {
        return aiModelMapper.selectByVendorAndModelCode(vendorId, modelCode);
    }

    @Override
    public List<AiModelDO> getAllModels() {
        return aiModelMapper.selectAll();
    }

    @Override
    public List<AiModelDO> getModelsByVendorId(Long vendorId) {
        return aiModelMapper.selectByVendorId(vendorId);
    }

    @Override
    public List<AiModelDO> getModelsByModality(String modality) {
        return aiModelMapper.selectByModality(modality);
    }

    @Override
    public boolean updateModel(AiModelDO aiModel) {
        return aiModelMapper.update(aiModel) > 0;
    }

    @Override
    public boolean deleteModel(Long id) {
        return aiModelMapper.delete(id) > 0;
    }

    @Override
    public List<AiModelDO> searchModels(String keyword) {
        return aiModelMapper.searchByName(keyword);
    }
}