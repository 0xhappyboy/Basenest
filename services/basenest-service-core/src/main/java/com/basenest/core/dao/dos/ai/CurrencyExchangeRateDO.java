package com.basenest.core.dao.dos.ai;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @ClassName CurrencyExchangeRateDO
 * @Description Fiat currency to credit exchange rate table DO
 * @Author happyboy
 * @Date 2026/11/19
 * @Version 1.0
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyExchangeRateDO {
    private Long id;
    private String currencyCode;
    private String currencyName;
    private String symbol;
    private BigDecimal creditPerUnit;
    private LocalDateTime effectiveFrom;
    private LocalDateTime effectiveTo;
    private Integer status;
    private Integer isDeleted;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}