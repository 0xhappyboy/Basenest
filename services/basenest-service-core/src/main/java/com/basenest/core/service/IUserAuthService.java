package com.basenest.core.service;

import com.basenest.core.dao.dos.UserInfoDO;
import com.basenest.core.pojo.dto.LoginRequest;
import com.basenest.core.pojo.dto.RegisterRequest;

import java.util.Map;

public interface IUserAuthService {
    Map<String, Object> login(LoginRequest req, String clientIp);

    Map<String, Object> register(RegisterRequest req);

    UserInfoDO me(String token);
}