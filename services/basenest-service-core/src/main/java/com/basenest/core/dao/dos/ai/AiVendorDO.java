package com.basenest.core.dao.dos.ai;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @ClassName AiVendorDO
 * @Description AI vendor table DO
 * @Author happyboy
 * @Date 2026/11/19
 * @Version 1.0
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AiVendorDO {
    private Long id;
    private String vendorCode;
    private String nameCn;
    private String nameEn;
    private String description;
    private String website;
    private Integer sortOrder;
    private Integer status;
    private Integer isDeleted;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}