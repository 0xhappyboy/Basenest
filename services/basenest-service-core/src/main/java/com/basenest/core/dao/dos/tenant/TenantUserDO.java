package com.basenest.core.dao.dos.tenant;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @ClassName TenantUserDO
 * @Description Tenant-user relation table DO
 * @Author happyboy
 * @Date 2025/12/9 11:01
 * @Version 1.0
 */
@Data
@ToString
@NoArgsConstructor
public class TenantUserDO {
    private Long id;
    private Long tenantId;
    private Long userId;
    private Integer isOwner;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}