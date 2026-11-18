package com.basenest.core.dao.mapper.ai;

import com.basenest.core.dao.dos.ai.AiModelCreditRateDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName AiModelCreditRateMapper
 * @Description AI model credit rate mapper
 * @Author happyboy
 * @Date 2026/11/19
 * @Version 1.0
 */
@Mapper
public interface AiModelCreditRateMapper {
    int insert(@Param("aiModelCreditRateDO") AiModelCreditRateDO aiModelCreditRate);

    AiModelCreditRateDO selectById(@Param("id") Long id);

    AiModelCreditRateDO selectActiveByModelId(@Param("modelId") Long modelId);

    List<AiModelCreditRateDO> selectAll();

    List<AiModelCreditRateDO> selectByModelId(@Param("modelId") Long modelId);

    int update(@Param("aiModelCreditRateDO") AiModelCreditRateDO aiModelCreditRate);

    int delete(@Param("id") Long id);
}