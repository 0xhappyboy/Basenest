package com.basenest.core.service;

import com.basenest.core.dao.dos.UserRoleRelationDO;

import java.util.List;

public interface IUserRoleRelationService {
    Long addRelation(UserRoleRelationDO relation);

    UserRoleRelationDO getRelationById(Long id);

    List<UserRoleRelationDO> getRelations(Long tenantId, Long userId);

    boolean updateRelation(UserRoleRelationDO relation);

    boolean deleteRelation(Long id);
}