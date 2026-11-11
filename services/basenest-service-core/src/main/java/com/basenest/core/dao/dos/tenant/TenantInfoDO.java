package com.basenest.core.dao.dos.tenant;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @ClassName TenantInfoDO
 * @Description Tenant table DO
 * @Author happyboy
 * @Date 2025/12/9 11:01
 * @Version 1.0
 */
@Data
@ToString
@NoArgsConstructor
public class TenantInfoDO {
    private Long id;
    private String name;
    private String code;
    private String contact;
    private String phone;
    private Integer status;
    private Long planId;
    private LocalDateTime expireTime;
    private Integer isDeleted;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}