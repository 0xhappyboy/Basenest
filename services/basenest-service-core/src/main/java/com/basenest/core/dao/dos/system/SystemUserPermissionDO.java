package com.basenest.core.dao.dos.system;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @ClassName SystemUserPermissionDO
 * @Description System user-permission relation table DO
 * @Author happyboy
 * @Date 2026/11/16
 * @Version 1.0
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SystemUserPermissionDO {
    private Long id;
    private Long userId;
    private String permissionCode;
    private Integer grantType;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}