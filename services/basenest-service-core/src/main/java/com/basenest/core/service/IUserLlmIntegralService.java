package com.basenest.core.service;

import com.basenest.core.dao.dos.UserLlmIntegralDO;

public interface IUserLlmIntegralService {
    Long addIntegral(UserLlmIntegralDO integral);

    UserLlmIntegralDO getIntegralById(Long id);

    UserLlmIntegralDO getIntegralByUserId(Long userId);

    boolean updateIntegral(UserLlmIntegralDO integral);

    boolean deleteIntegral(Long id);
}