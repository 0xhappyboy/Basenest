package com.basenest.core.pojo.bo;

import lombok.Data;

/**
 * @ClassName JwtClaims
 * @Description JWT claims carried inside the token
 * @Author happyboy
 * @Date 2026/11/11
 * @Version 1.0
 */
@Data
public class JwtClaims {
    private String sub;
    private String username;
    private Long tenantId;
    private String role;
    private Long iat;
    private Long exp;
}