package com.basenest.core.dao.mapper.ai;

import com.basenest.core.dao.dos.ai.CurrencyExchangeRateDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName CurrencyExchangeRateMapper
 * @Description Currency exchange rate mapper
 * @Author happyboy
 * @Date 2026/11/19
 * @Version 1.0
 */
@Mapper
public interface CurrencyExchangeRateMapper {
    int insert(@Param("currencyExchangeRateDO") CurrencyExchangeRateDO currencyExchangeRate);

    CurrencyExchangeRateDO selectById(@Param("id") Long id);

    CurrencyExchangeRateDO selectActiveByCurrencyCode(@Param("currencyCode") String currencyCode);

    List<CurrencyExchangeRateDO> selectAll();

    int update(@Param("currencyExchangeRateDO") CurrencyExchangeRateDO currencyExchangeRate);

    int delete(@Param("id") Long id);
}