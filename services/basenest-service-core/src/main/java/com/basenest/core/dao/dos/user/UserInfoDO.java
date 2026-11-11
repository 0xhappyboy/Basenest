package com.basenest.core.dao.dos;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @ClassName UserInfoDO
 * @Description User table DO
 * @Author happyboy
 * @Date 2026/11/11
 * @Version 1.0
 */
@Data
@ToString
@NoArgsConstructor
public class UserInfoDO {
    private Long id;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String phone;
    private String avatar;
    private String wallpaper;
    private Integer gender;
    private LocalDate birthday;
    private String signature;
    private String realName;
    private String idCardNo;
    private String country;
    private String province;
    private String city;
    private String address;
    private String language;
    private String timezone;
    private String source;
    private Integer emailVerified;
    private Integer phoneVerified;
    private LocalDateTime lastLoginTime;
    private String lastLoginIp;
    private Integer status;
    private Integer isDeleted;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}