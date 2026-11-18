package com.basenest.core.dao.mapper.ai;

import com.basenest.core.dao.dos.ai.AiVendorDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName AiVendorMapper
 * @Description AI vendor mapper
 * @Author happyboy
 * @Date 2026/11/19
 * @Version 1.0
 */
@Mapper
public interface AiVendorMapper {
    int insert(@Param("aiVendorDO") AiVendorDO aiVendor);

    AiVendorDO selectById(@Param("id") Long id);

    AiVendorDO selectByVendorCode(@Param("vendorCode") String vendorCode);

    List<AiVendorDO> selectAll();

    int update(@Param("aiVendorDO") AiVendorDO aiVendor);

    int delete(@Param("id") Long id);

    List<AiVendorDO> searchByName(@Param("keyword") String keyword);
}