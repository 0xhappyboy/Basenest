package com.basenest.core.dao.dos;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @ClassName UserLlmUsageDO
 * @Description User LLM usage table DO
 * @Author happyboy
 * @Date 2025/12/9 11:01
 * @Version 1.0
 */
@Data
@ToString
@NoArgsConstructor
public class UserLlmUsageDO {
    private Long id;
    private Long userId;
    private Long tenantId;
    private String kind;
    private String providerCode;
    private String modelId;
    private Long tokens;
    private String usageType;
    private Integer durationMs;
    private Integer success;
    private String requestId;
    private LocalDateTime createdAt;
}