package com.basenest.core.controller.ai;

import com.basenest.core.dao.dos.ai.CurrencyExchangeRateDO;
import com.basenest.core.service.ICurrencyExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName CurrencyExchangeRateController
 * @Description Currency exchange rate controller
 * @Author happyboy
 * @Date 2026/11/19
 * @Version 1.0
 */
@RestController
@RequestMapping("/ai/currency-rate")
@CrossOrigin(origins = "*")
public class CurrencyExchangeRateController {

    @Autowired
    private ICurrencyExchangeRateService currencyExchangeRateService;

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody CurrencyExchangeRateDO currencyExchangeRate) {
        return ResponseEntity.ok(currencyExchangeRateService.addCurrencyRate(currencyExchangeRate));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CurrencyExchangeRateDO> getById(@PathVariable Long id) {
        CurrencyExchangeRateDO rate = currencyExchangeRateService.getCurrencyRateById(id);
        return rate != null ? ResponseEntity.ok(rate) : ResponseEntity.notFound().build();
    }

    @GetMapping("/code/{currencyCode}")
    public ResponseEntity<CurrencyExchangeRateDO> getActiveByCode(@PathVariable String currencyCode) {
        CurrencyExchangeRateDO rate = currencyExchangeRateService.getActiveCurrencyRateByCode(currencyCode);
        return rate != null ? ResponseEntity.ok(rate) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<CurrencyExchangeRateDO>> getAll() {
        return ResponseEntity.ok(currencyExchangeRateService.getAllCurrencyRates());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@PathVariable Long id, @RequestBody CurrencyExchangeRateDO currencyExchangeRate) {
        currencyExchangeRate.setId(id);
        return ResponseEntity.ok(currencyExchangeRateService.updateCurrencyRate(currencyExchangeRate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(currencyExchangeRateService.deleteCurrencyRate(id));
    }
}