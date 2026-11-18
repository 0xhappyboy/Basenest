package com.basenest.core.service;

import com.basenest.core.dao.dos.ai.CurrencyExchangeRateDO;

import java.util.List;

public interface ICurrencyExchangeRateService {
    Long addCurrencyRate(CurrencyExchangeRateDO currencyExchangeRate);

    CurrencyExchangeRateDO getCurrencyRateById(Long id);

    CurrencyExchangeRateDO getActiveCurrencyRateByCode(String currencyCode);

    List<CurrencyExchangeRateDO> getAllCurrencyRates();

    boolean updateCurrencyRate(CurrencyExchangeRateDO currencyExchangeRate);

    boolean deleteCurrencyRate(Long id);
}