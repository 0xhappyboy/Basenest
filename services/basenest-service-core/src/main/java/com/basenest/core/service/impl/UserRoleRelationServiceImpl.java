package com.basenest.core.service.impl;

import com.basenest.core.dao.dos.UserRoleRelationDO;
import com.basenest.core.dao.mapper.UserRoleRelationMapper;
import com.basenest.core.service.IUserRoleRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName UserRoleRelationServiceImpl
 * @Description User role relation service impl
 * @Author happyboy
 * @Date 2026/11/11
 * @Version 1.0
 */
@Service
@Transactional
public class UserRoleRelationServiceImpl implements IUserRoleRelationService {

    @Autowired
    private UserRoleRelationMapper userRoleRelationMapper;

    @Override
    public Long addRelation(UserRoleRelationDO relation) {
        userRoleRelationMapper.insert(relation);
        return relation.getId();
    }

    @Override
    public UserRoleRelationDO getRelationById(Long id) {
        return userRoleRelationMapper.selectById(id);
    }

    @Override
    public List<UserRoleRelationDO> getRelations(Long tenantId, Long userId) {
        return userRoleRelationMapper.selectByTenantAndUser(tenantId, userId);
    }

    @Override
    public boolean updateRelation(UserRoleRelationDO relation) {
        return userRoleRelationMapper.update(relation) > 0;
    }

    @Override
    public boolean deleteRelation(Long id) {
        return userRoleRelationMapper.delete(id) > 0;
    }
}