package com.basenest.core.utils;

import com.basenest.core.pojo.bo.JwtClaims;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @ClassName AuthUtil
 * @Description Extract and verify JWT from the request
 * @Author happyboy
 * @Date 2026/11/11
 * @Version 1.0
 */
public final class AuthUtil {

    private AuthUtil() {
    }

    private static String extractToken(HttpServletRequest request) {
        String auth = request.getHeader("Authorization");
        if (auth != null) {
            String trimmed = auth.trim();
            if (trimmed.startsWith("Bearer ")) {
                return trimmed.substring(7).trim();
            }
            if (trimmed.startsWith("bearer ")) {
                return trimmed.substring(7).trim();
            }
        }
        jakarta.servlet.http.Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (jakarta.servlet.http.Cookie c : cookies) {
                if ("token".equals(c.getName())) {
                    return c.getValue();
                }
            }
        }
        return null;
    }

    public static JwtClaims extractClaims(HttpServletRequest request) {
        String token = extractToken(request);
        if (token == null) {
            throw new IllegalArgumentException("missing token");
        }
        return JwtUtil.verifyToken(token);
    }
}