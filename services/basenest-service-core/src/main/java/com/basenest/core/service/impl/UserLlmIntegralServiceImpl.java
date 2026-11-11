package com.basenest.core.service.impl;

import com.basenest.core.dao.dos.UserLlmIntegralDO;
import com.basenest.core.dao.mapper.UserLlmIntegralMapper;
import com.basenest.core.service.IUserLlmIntegralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName UserLlmIntegralServiceImpl
 * @Description User LLM integral service impl
 * @Author happyboy
 * @Date 2026/11/11
 * @Version 1.0
 */
@Service
@Transactional
public class UserLlmIntegralServiceImpl implements IUserLlmIntegralService {

    @Autowired
    private UserLlmIntegralMapper userLlmIntegralMapper;

    @Override
    public Long addIntegral(UserLlmIntegralDO integral) {
        userLlmIntegralMapper.insert(integral);
        return integral.getId();
    }

    @Override
    public UserLlmIntegralDO getIntegralById(Long id) {
        return userLlmIntegralMapper.selectById(id);
    }

    @Override
    public UserLlmIntegralDO getIntegralByUserId(Long userId) {
        return userLlmIntegralMapper.selectByUserId(userId);
    }

    @Override
    public boolean updateIntegral(UserLlmIntegralDO integral) {
        return userLlmIntegralMapper.update(integral) > 0;
    }

    @Override
    public boolean deleteIntegral(Long id) {
        return userLlmIntegralMapper.delete(id) > 0;
    }
}