package com.basenest.core.dao.dos.system;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @ClassName SystemPermissionCodeDO
 * @Description System permission code enum table DO
 * @Author happyboy
 * @Date 2026/11/16
 * @Version 1.0
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SystemPermissionCodeDO {
    private Long id;
    private String code;
    private String name;
    private String module;
    private String type;
    private String description;
    private Integer sortOrder;
    private Integer status;
    private Integer isDeleted;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}