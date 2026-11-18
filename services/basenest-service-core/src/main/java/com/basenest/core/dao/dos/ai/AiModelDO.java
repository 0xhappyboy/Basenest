package com.basenest.core.dao.dos.ai;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @ClassName AiModelDO
 * @Description AI model table DO
 * @Author happyboy
 * @Date 2026/11/19
 * @Version 1.0
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AiModelDO {
    private Long id;
    private Long vendorId;
    private String modelCode;
    private String modelName;
    private String modality;
    private String providerCode;
    private String description;
    private Integer sortOrder;
    private Integer status;
    private Integer isDeleted;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}