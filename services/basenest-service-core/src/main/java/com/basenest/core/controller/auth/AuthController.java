package com.basenest.core.controller.auth;

import com.basenest.core.dao.dos.UserInfoDO;
import com.basenest.core.pojo.bo.JwtClaims;
import com.basenest.core.pojo.dto.LoginRequest;
import com.basenest.core.pojo.dto.RegisterRequest;
import com.basenest.core.service.IUserAuthService;
import com.basenest.core.utils.AuthUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @ClassName UserAuthController
 * @Description Login / register / me
 * @Author happyboy
 * @Date 2026/11/11
 * @Version 1.0
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IUserAuthService userAuthService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req, HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty()) {
            ip = request.getRemoteAddr();
        }
        try {
            return ResponseEntity.ok(userAuthService.login(req, ip));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(401).body(Map.of("message", e.getMessage()));
        } catch (IllegalStateException e) {
            String msg = e.getMessage() == null ? "" : e.getMessage();
            if (msg.startsWith("too many")) {
                return ResponseEntity.status(429).body(Map.of("message", msg));
            }
            return ResponseEntity.status(403).body(Map.of("message", msg));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
        try {
            return ResponseEntity.ok(userAuthService.register(req));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(409).body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping("/me")
    public ResponseEntity<?> me(HttpServletRequest request) {
        try {
            JwtClaims claims = AuthUtil.extractClaims(request);
            UserInfoDO user = userAuthService.me(JwtUtilTokenOf(claims));
            return ResponseEntity.ok(user);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(401).body(Map.of("message", e.getMessage()));
        }
    }

    private String JwtUtilTokenOf(JwtClaims claims) {
        return claims.getSub();
    }
}