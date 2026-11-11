package com.basenest.core.dao.mapper;

import com.basenest.core.dao.dos.UserLlmUsageDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName UserLlmUsageMapper
 * @Description User LLM usage mapper
 * @Author happyboy
 * @Date 2025/12/9 11:45
 * @Version 1.0
 */
@Mapper
public interface UserLlmUsageMapper {
    int insert(@Param("userLlmUsageDO") UserLlmUsageDO usage);

    UserLlmUsageDO selectById(@Param("id") Long id);

    List<UserLlmUsageDO> selectByUserId(@Param("userId") Long userId);

    List<UserLlmUsageDO> selectByTenantId(@Param("tenantId") Long tenantId);

    int delete(@Param("id") Long id);
}