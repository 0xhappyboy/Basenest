package com.basenest.core.dao.dos.app;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @ClassName AppMachineDO
 * @Description App machine environment table DO
 * @Author happyboy
 * @Date 2025/12/9 11:01
 * @Version 1.0
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AppMachineDO {
    private Long id;
    private Long appId;
    private Long userId;
    private Long tenantId;
    private String machineKey;
    private String machineName;
    private String osName;
    private String osVersion;
    private String osArch;
    private String kernelVersion;
    private String cpuModel;
    private Integer cpuCores;
    private Integer cpuFreqMhz;
    private Long memoryTotal;
    private Long diskTotal;
    private Long diskFree;
    private String gpuModel;
    private String screenResolution;
    private BigDecimal screenScale;
    private String hostname;
    private String macAddress;
    private String ipLocal;
    private String ipPublic;
    private String timezone;
    private String language;
    private String appVersion;
    private String runtimeVersion;
    private String installPath;
    private Integer isVirtual;
    private String extraJson;
    private LocalDateTime firstSeenTime;
    private LocalDateTime lastSeenTime;
    private Integer status;
    private Integer isDeleted;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}