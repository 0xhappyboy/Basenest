package com.basenest.core.service;

import com.basenest.core.dao.dos.ai.AiModelDO;

import java.util.List;

public interface IAiModelService {
    Long addModel(AiModelDO aiModel);

    AiModelDO getModelById(Long id);

    AiModelDO getModelByVendorAndModelCode(Long vendorId, String modelCode);

    List<AiModelDO> getAllModels();

    List<AiModelDO> getModelsByVendorId(Long vendorId);

    List<AiModelDO> getModelsByModality(String modality);

    boolean updateModel(AiModelDO aiModel);

    boolean deleteModel(Long id);

    List<AiModelDO> searchModels(String keyword);
}