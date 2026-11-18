package com.basenest.core.service.impl;

import com.basenest.core.dao.dos.ai.CurrencyExchangeRateDO;
import com.basenest.core.dao.mapper.ai.CurrencyExchangeRateMapper;
import com.basenest.core.service.ICurrencyExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName CurrencyExchangeRateServiceImpl
 * @Description Currency exchange rate service impl
 * @Author happyboy
 * @Date 2026/11/19
 * @Version 1.0
 */
@Service
@Transactional
public class CurrencyExchangeRateServiceImpl implements ICurrencyExchangeRateService {

    @Autowired
    private CurrencyExchangeRateMapper currencyExchangeRateMapper;

    @Override
    public Long addCurrencyRate(CurrencyExchangeRateDO currencyExchangeRate) {
        currencyExchangeRateMapper.insert(currencyExchangeRate);
        return currencyExchangeRate.getId();
    }

    @Override
    public CurrencyExchangeRateDO getCurrencyRateById(Long id) {
        return currencyExchangeRateMapper.selectById(id);
    }

    @Override
    public CurrencyExchangeRateDO getActiveCurrencyRateByCode(String currencyCode) {
        return currencyExchangeRateMapper.selectActiveByCurrencyCode(currencyCode);
    }

    @Override
    public List<CurrencyExchangeRateDO> getAllCurrencyRates() {
        return currencyExchangeRateMapper.selectAll();
    }

    @Override
    public boolean updateCurrencyRate(CurrencyExchangeRateDO currencyExchangeRate) {
        return currencyExchangeRateMapper.update(currencyExchangeRate) > 0;
    }

    @Override
    public boolean deleteCurrencyRate(Long id) {
        return currencyExchangeRateMapper.delete(id) > 0;
    }
}