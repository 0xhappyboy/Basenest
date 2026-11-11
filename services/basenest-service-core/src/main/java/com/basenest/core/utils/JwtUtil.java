package com.basenest.core.utils;

import com.basenest.core.pojo.bo.JwtClaims;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @ClassName JwtUtil
 * @Description JWT issue / verify helper
 * @Author happyboy
 * @Date 2026/11/11
 * @Version 1.0
 */
@Component
public final class JwtUtil {

    private static final Logger log = LoggerFactory.getLogger(JwtUtil.class);

    private static String secret;
    private static long expireDays;
    private static volatile SecretKey key;

    private JwtUtil() {
    }

    @Value("${app.auth.jwt-secret}")
    public void setSecret(String value) {
        JwtUtil.secret = value;
        initKey();
    }

    @Value("${app.auth.jwt-expire-days}")
    public void setExpireDays(long value) {
        JwtUtil.expireDays = value;
    }

    private static void initKey() {
        if (secret == null || secret.getBytes(StandardCharsets.UTF_8).length < 32) {
            log.warn("app.auth.jwt-secret is missing or shorter than 32 bytes; "
                    + "HS256 requires at least 32 bytes.");
        }
        key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    private static SecretKey key() {
        if (key == null) {
            synchronized (JwtUtil.class) {
                if (key == null) {
                    initKey();
                }
            }
        }
        return key;
    }

    public static String issueToken(Long userId, String username, Long tenantId, String role) {
        long now = System.currentTimeMillis();
        long exp = now + expireDays * 24 * 60 * 60 * 1000;
        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .claim("username", username)
                .claim("tenant_id", tenantId)
                .claim("role", role)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(exp))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    public static JwtClaims verifyToken(String token) {
        Claims c = Jwts.parserBuilder().setSigningKey(key()).build()
                .parseClaimsJws(token).getBody();
        JwtClaims claims = new JwtClaims();
        claims.setSub(c.getSubject());
        claims.setUsername(c.get("username", String.class));
        Object tenant = c.get("tenant_id");
        claims.setTenantId(tenant == null ? 0L : ((Number) tenant).longValue());
        claims.setRole(c.get("role", String.class));
        claims.setIat(c.getIssuedAt() == null ? null : c.getIssuedAt().getTime());
        claims.setExp(c.getExpiration() == null ? null : c.getExpiration().getTime());
        return claims;
    }
}