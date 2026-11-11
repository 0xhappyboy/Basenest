package com.basenest.core.dao.dos;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @ClassName UserLlmIntegralDO
 * @Description User LLM integral table DO
 * @Author happyboy
 * @Date 2026/11/11
 * @Version 1.0
 */
@Data
@ToString
@NoArgsConstructor
public class UserLlmIntegralDO {
    private Long id;
    private Long userId;
    private Long tenantId;
    private Long integral;
    private Long totalRecharge;
    private Long totalConsume;
    private LocalDateTime firstRechargeTime;
    private LocalDateTime lastRechargeTime;
    private Integer status;
    private Integer isDeleted;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}