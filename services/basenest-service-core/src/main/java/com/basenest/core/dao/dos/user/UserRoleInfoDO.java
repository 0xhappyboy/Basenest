package com.basenest.core.dao.dos;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @ClassName UserRoleInfoDO
 * @Description User role table DO
 * @Author happyboy
 * @Date 2025/12/9 11:01
 * @Version 1.0
 */
@Data
@ToString
@NoArgsConstructor
public class UserRoleInfoDO {
    private Long id;
    private Long tenantId;
    private String name;
    private String code;
    private String description;
    private Integer isSystem;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}