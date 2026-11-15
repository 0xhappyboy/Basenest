package com.basenest.core.dao.dos.system;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @ClassName SystemMenuDO
 * @Description System menu table DO
 * @Author happyboy
 * @Date 2026/11/16
 * @Version 1.0
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SystemMenuDO {
    private Long id;
    private Long parentId;
    private Integer level;
    private String name;
    private String code;
    private String type;
    private String path;
    private String component;
    private String icon;
    private String permission;
    private Integer sortOrder;
    private Integer visible;
    private Integer status;
    private Integer isDeleted;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}