package com.basenest.core.service.impl;

import com.basenest.core.dao.dos.UserRoleInfoDO;
import com.basenest.core.dao.mapper.UserRoleInfoMapper;
import com.basenest.core.service.IUserRoleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName UserRoleInfoServiceImpl
 * @Description User role info service impl
 * @Author happyboy
 * @Date 2026/11/11
 * @Version 1.0
 */
@Service
@Transactional
public class UserRoleInfoServiceImpl implements IUserRoleInfoService {

    @Autowired
    private UserRoleInfoMapper userRoleInfoMapper;

    @Override
    public Long addRole(UserRoleInfoDO role) {
        userRoleInfoMapper.insert(role);
        return role.getId();
    }

    @Override
    public UserRoleInfoDO getRoleById(Long id) {
        return userRoleInfoMapper.selectById(id);
    }

    @Override
    public UserRoleInfoDO getRoleByTenantAndCode(Long tenantId, String code) {
        return userRoleInfoMapper.selectByTenantAndCode(tenantId, code);
    }

    @Override
    public List<UserRoleInfoDO> getRolesByTenantId(Long tenantId) {
        return userRoleInfoMapper.selectByTenantId(tenantId);
    }

    @Override
    public boolean updateRole(UserRoleInfoDO role) {
        return userRoleInfoMapper.update(role) > 0;
    }

    @Override
    public boolean deleteRole(Long id) {
        return userRoleInfoMapper.delete(id) > 0;
    }
}