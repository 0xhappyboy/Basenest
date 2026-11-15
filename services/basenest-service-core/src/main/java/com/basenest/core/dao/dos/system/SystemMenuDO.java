package com.basenest.core.dao.dos.system;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @ClassName SystemMenuDO
 * @Description System menu table DO.
 * Path and component are removed: React/Next.js uses file-system
 * routing and maps menu code to its own page in frontend code.
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
    private String icon;
    private String permission;
    private Integer sortOrder;
    private Integer visible;
    private Integer status;
    private Integer isDeleted;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}