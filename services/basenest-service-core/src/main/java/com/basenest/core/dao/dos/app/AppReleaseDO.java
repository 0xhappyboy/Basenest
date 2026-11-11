package com.basenest.core.dao.dos.app;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @ClassName AppReleaseDO
 * @Description App release/package table DO
 * @Author happyboy
 * @Date 2025/12/9 11:01
 * @Version 1.0
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AppReleaseDO {
    private Long id;
    private Long appId;
    private String platform;
    private String arch;
    private String version;
    private String releaseChannel;
    private String minSupportedVersion;
    private Integer forceUpdate;
    private String packageUrl;
    private String packageHash;
    private Long packageSize;
    private String releaseNotes;
    private Integer isLatest;
    private Integer status;
    private LocalDateTime releaseTime;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}