package com.basenest.core.dao.dos;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @ClassName UserMemberDO
 * @Description User member table DO
 * @Author happyboy
 * @Date 2026/11/11
 * @Version 1.0
 */
@Data
@ToString
@NoArgsConstructor
public class UserMemberDO {
    private Long id;
    private Long userId;
    private Long tenantId;
    private Long expireAt;
    private Integer status;
    private Integer isDeleted;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}