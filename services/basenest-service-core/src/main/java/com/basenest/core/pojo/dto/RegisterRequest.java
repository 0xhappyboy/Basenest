package com.basenest.core.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName RegisterRequest
 * @Description Register request body
 * @Author happyboy
 * @Date 2026/11/11
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String phone;
}