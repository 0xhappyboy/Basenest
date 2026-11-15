package com.basenest.core.dao.dos.system;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @ClassName SystemRoleDO
 * @Description System role table DO
 * @Author happyboy
 * @Date 2026/11/16
 * @Version 1.0
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SystemRoleDO {
    private Long id;
    private String name;
    private String code;
    private String description;
    private Integer dataScope;
    private Integer isSystem;
    private Integer sortOrder;
    private Integer status;
    private Integer isDeleted;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}