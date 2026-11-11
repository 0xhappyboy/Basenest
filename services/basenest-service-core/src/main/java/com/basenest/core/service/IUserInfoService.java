package com.basenest.core.service;

import com.basenest.core.dao.dos.UserInfoDO;

import java.util.List;

public interface IUserInfoService {
    Long addUser(UserInfoDO userInfo);

    UserInfoDO getUserById(Long id);

    UserInfoDO getUserByUsername(String username);

    List<UserInfoDO> getAllUsers();

    boolean updateUser(UserInfoDO userInfo);

    boolean deleteUser(Long id);

    List<UserInfoDO> searchUsers(String keyword);
}