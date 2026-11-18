package com.basenest.core.dao.dos.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @ClassName UserModelCostRecordDO
 * @Description User model cost record table DO
 * @Author happyboy
 * @Date 2026/11/19
 * @Version 1.0
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserModelCostRecordDO {
    private Long id;
    private String recordNo;
    private Long userId;
    private Long tenantId;
    private Long vendorId;
    private Long modelId;
    private String modality;
    private Integer status;
    private Integer isDeleted;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}