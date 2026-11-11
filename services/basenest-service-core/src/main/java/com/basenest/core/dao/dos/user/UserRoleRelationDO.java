package com.basenest.core.dao.dos;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @ClassName UserRoleRelationDO
 * @Description User role relation table DO
 * @Author happyboy
 * @Date 2026/11/11
 * @Version 1.0
 */
@Data
@ToString
@NoArgsConstructor
public class UserRoleRelationDO {
    private Long id;
    private Long tenantId;
    private Long userId;
    private Long roleId;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}