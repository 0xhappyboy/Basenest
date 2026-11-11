package com.basenest.core.service.impl;

import com.basenest.core.dao.dos.UserInfoDO;
import com.basenest.core.dao.mapper.UserInfoMapper;
import com.basenest.core.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName UserInfoServiceImpl
 * @Description User info service impl
 * @Author happyboy
 * @Date 2026/11/11
 * @Version 1.0
 */
@Service
@Transactional
public class UserInfoServiceImpl implements IUserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public Long addUser(UserInfoDO userInfo) {
        userInfoMapper.insert(userInfo);
        return userInfo.getId();
    }

    @Override
    public UserInfoDO getUserById(Long id) {
        return userInfoMapper.selectById(id);
    }

    @Override
    public UserInfoDO getUserByUsername(String username) {
        return userInfoMapper.selectByUsername(username);
    }

    @Override
    public List<UserInfoDO> getAllUsers() {
        return userInfoMapper.selectAll();
    }

    @Override
    public boolean updateUser(UserInfoDO userInfo) {
        return userInfoMapper.update(userInfo) > 0;
    }

    @Override
    public boolean deleteUser(Long id) {
        return userInfoMapper.delete(id) > 0;
    }

    @Override
    public List<UserInfoDO> searchUsers(String keyword) {
        return userInfoMapper.searchByKeyword(keyword);
    }
}