package com.basenest.core.dao.dos.system;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @ClassName SystemRolePermissionDO
 * @Description System role-permission relation table DO
 * @Author happyboy
 * @Date 2026/11/16
 * @Version 1.0
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SystemRolePermissionDO {
    private Long id;
    private Long roleId;
    private String permissionCode;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}