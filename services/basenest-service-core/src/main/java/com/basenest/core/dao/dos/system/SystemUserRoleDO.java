package com.basenest.core.dao.dos.system;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @ClassName SystemUserRoleDO
 * @Description System user-role relation table DO
 * @Author happyboy
 * @Date 2026/11/16
 * @Version 1.0
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SystemUserRoleDO {
    private Long id;
    private Long userId;
    private Long roleId;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}