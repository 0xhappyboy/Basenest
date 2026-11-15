package com.basenest.core.dao.dos.system;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @ClassName SystemUserDO
 * @Description System user table DO
 * @Author happyboy
 * @Date 2026/11/16
 * @Version 1.0
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SystemUserDO {
    private Long id;
    private String username;
    private String password;
    private String nickname;
    private String realName;
    private String email;
    private String phone;
    private String avatar;
    private Integer gender;
    private String dept;
    private String jobTitle;
    private LocalDateTime lastLoginTime;
    private String lastLoginIp;
    private Integer loginFailCount;
    private LocalDateTime lockedUntil;
    private Integer isSuper;
    private Integer status;
    private Integer isDeleted;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}