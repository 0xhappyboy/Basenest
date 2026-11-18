package com.basenest.core.dao.mapper.ai;

import com.basenest.core.dao.dos.ai.AiModelDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName AiModelMapper
 * @Description AI model mapper
 * @Author happyboy
 * @Date 2026/11/19
 * @Version 1.0
 */
@Mapper
public interface AiModelMapper {
    int insert(@Param("aiModelDO") AiModelDO aiModel);

    AiModelDO selectById(@Param("id") Long id);

    AiModelDO selectByVendorAndModelCode(@Param("vendorId") Long vendorId, @Param("modelCode") String modelCode);

    List<AiModelDO> selectAll();

    List<AiModelDO> selectByVendorId(@Param("vendorId") Long vendorId);

    List<AiModelDO> selectByModality(@Param("modality") String modality);

    int update(@Param("aiModelDO") AiModelDO aiModel);

    int delete(@Param("id") Long id);

    List<AiModelDO> searchByName(@Param("keyword") String keyword);
}