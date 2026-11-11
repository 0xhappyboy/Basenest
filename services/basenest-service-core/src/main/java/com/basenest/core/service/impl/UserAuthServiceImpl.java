package com.basenest.core.service.impl;

import com.basenest.core.dao.dos.UserInfoDO;
import com.basenest.core.dao.dos.UserRoleInfoDO;
import com.basenest.core.dao.dos.UserRoleRelationDO;
import com.basenest.core.dao.mapper.UserInfoMapper;
import com.basenest.core.dao.mapper.UserRoleInfoMapper;
import com.basenest.core.dao.mapper.UserRoleRelationMapper;
import com.basenest.core.pojo.dto.LoginRequest;
import com.basenest.core.pojo.dto.RegisterRequest;
import com.basenest.core.service.IUserAuthService;
import com.basenest.core.utils.JwtUtil;
import com.basenest.core.utils.LoginGuard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UserAuthServiceImpl
 * @Description Login / register / me
 * @Author happyboy
 * @Date 2026/11/11
 * @Version 1.0
 */
@Service
@Transactional
public class UserAuthServiceImpl implements IUserAuthService {

    private static final long SYSTEM_TENANT_ID = 0L;
    private static final long NO_TENANT_ID = 0L;

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private UserRoleInfoMapper userRoleInfoMapper;
    @Autowired
    private UserRoleRelationMapper userRoleRelationMapper;
    @Autowired
    private LoginGuard loginGuard;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public Map<String, Object> login(LoginRequest req, String clientIp) {
        Long remain = loginGuard.checkAllowed(req.getUsername());
        if (remain != null) {
            throw new IllegalStateException("too many failed attempts, try again in " + remain + " seconds");
        }
        UserInfoDO user = userInfoMapper.selectByUsername(req.getUsername());
        if (user == null) {
            loginGuard.recordFailure(req.getUsername());
            throw new IllegalArgumentException("invalid username or password");
        }
        if (!encoder.matches(req.getPassword(), user.getPassword())) {
            loginGuard.recordFailure(req.getUsername());
            throw new IllegalArgumentException("invalid username or password");
        }
        if (user.getStatus() == null || user.getStatus() != 1) {
            throw new IllegalStateException("user is disabled");
        }
        String role = "TENANT_USER";
        List<UserRoleRelationDO> relations =
                userRoleRelationMapper.selectByTenantAndUser(SYSTEM_TENANT_ID, user.getId());
        if (relations != null && !relations.isEmpty()) {
            UserRoleInfoDO roleInfo = userRoleInfoMapper.selectById(relations.get(0).getRoleId());
            if (roleInfo != null) {
                role = roleInfo.getCode();
            }
        }
        loginGuard.recordSuccess(req.getUsername());
        user.setLastLoginTime(LocalDateTime.now());
        user.setLastLoginIp(clientIp);
        userInfoMapper.update(user);
        String token = JwtUtil.issueToken(user.getId(), user.getUsername(), NO_TENANT_ID, role);
        Map<String, Object> resp = new HashMap<>();
        resp.put("token", token);
        resp.put("user", user);
        resp.put("tenant_id", NO_TENANT_ID);
        resp.put("role", role);
        return resp;
    }

    @Override
    public Map<String, Object> register(RegisterRequest req) {
        if (userInfoMapper.selectByUsername(req.getUsername()) != null) {
            throw new IllegalStateException("username already exists");
        }
        UserInfoDO user = new UserInfoDO();
        user.setUsername(req.getUsername());
        user.setPassword(encoder.encode(req.getPassword()));
        user.setNickname(req.getNickname());
        user.setEmail(req.getEmail());
        user.setPhone(req.getPhone());
        user.setStatus(1);
        user.setIsDeleted(0);
        userInfoMapper.insert(user);

        UserRoleInfoDO role = userRoleInfoMapper.selectByTenantAndCode(SYSTEM_TENANT_ID, "TENANT_USER");
        if (role == null) {
            throw new IllegalStateException("default role TENANT_USER not found");
        }
        UserRoleRelationDO relation = new UserRoleRelationDO();
        relation.setTenantId(SYSTEM_TENANT_ID);
        relation.setUserId(user.getId());
        relation.setRoleId(role.getId());
        relation.setStatus(1);
        userRoleRelationMapper.insert(relation);

        Map<String, Object> resp = new HashMap<>();
        resp.put("id", user.getId());
        resp.put("username", user.getUsername());
        resp.put("role", "TENANT_USER");
        return resp;
    }

    @Override
    public UserInfoDO me(String token) {
        var claims = JwtUtil.verifyToken(token);
        Long userId = Long.parseLong(claims.getSub());
        UserInfoDO user = userInfoMapper.selectById(userId);
        if (user == null) {
            throw new IllegalArgumentException("user not found");
        }
        return user;
    }
}