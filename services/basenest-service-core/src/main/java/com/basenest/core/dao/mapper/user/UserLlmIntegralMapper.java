package com.basenest.core.dao.mapper;

import com.basenest.core.dao.dos.UserLlmIntegralDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName UserLlmIntegralMapper
 * @Description User LLM integral mapper
 * @Author happyboy
 * @Date 2025/12/9 11:45
 * @Version 1.0
 */
@Mapper
public interface UserLlmIntegralMapper {
    int insert(@Param("userLlmIntegralDO") UserLlmIntegralDO integral);

    UserLlmIntegralDO selectById(@Param("id") Long id);

    UserLlmIntegralDO selectByUserId(@Param("userId") Long userId);

    int update(@Param("userLlmIntegralDO") UserLlmIntegralDO integral);

    int delete(@Param("id") Long id);
}