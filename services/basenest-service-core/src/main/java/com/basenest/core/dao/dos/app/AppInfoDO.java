package com.basenest.core.dao.dos.app;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @ClassName AppInfoDO
 * @Description Application registry main table DO
 * @Author happyboy
 * @Date 2026/11/11
 * @Version 1.0
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AppInfoDO {
    private Long id;
    private String appKey;
    private String code;
    private String name;
    private String description;
    private String platform;
    private String appType;
    private String runtime;
    private String bundleId;
    private String iconUrl;
    private String baseUrl;
    private String callbackUrl;
    private String appSecret;
    private String configJson;
    private Long ownerUserId;
    private Long ownerTenantId;
    private String category;
    private Integer sortOrder;
    private Integer isPublic;
    private Integer status;
    private Integer isDeleted;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}