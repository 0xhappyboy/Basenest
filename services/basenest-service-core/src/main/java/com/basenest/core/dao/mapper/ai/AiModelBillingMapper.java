package com.basenest.core.dao.mapper.ai;

import com.basenest.core.dao.dos.ai.AiModelBillingDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName AiModelBillingMapper
 * @Description AI model billing mapper
 * @Author happyboy
 * @Date 2026/11/19
 * @Version 1.0
 */
@Mapper
public interface AiModelBillingMapper {
    int insert(@Param("aiModelBillingDO") AiModelBillingDO aiModelBilling);

    AiModelBillingDO selectById(@Param("id") Long id);

    AiModelBillingDO selectActiveByModelId(@Param("modelId") Long modelId);

    List<AiModelBillingDO> selectAll();

    List<AiModelBillingDO> selectByModelId(@Param("modelId") Long modelId);

    int update(@Param("aiModelBillingDO") AiModelBillingDO aiModelBilling);

    int delete(@Param("id") Long id);
}