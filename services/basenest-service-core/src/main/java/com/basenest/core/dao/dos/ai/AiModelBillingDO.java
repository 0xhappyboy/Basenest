package com.basenest.core.dao.dos.ai;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @ClassName AiModelBillingDO
 * @Description AI model billing table DO
 * @Author happyboy
 * @Date 2026/11/19
 * @Version 1.0
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AiModelBillingDO {
    private Long id;
    private Long vendorId;
    private Long modelId;
    private String billingUnit;
    private BigDecimal unitPriceUsd;
    private String currency;
    private LocalDateTime effectiveFrom;
    private LocalDateTime effectiveTo;
    private Integer status;
    private Integer isDeleted;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}