package com.basenest.core.service;

import com.basenest.core.dao.dos.UserRoleInfoDO;

import java.util.List;

public interface IUserRoleInfoService {
    Long addRole(UserRoleInfoDO role);

    UserRoleInfoDO getRoleById(Long id);

    UserRoleInfoDO getRoleByTenantAndCode(Long tenantId, String code);

    List<UserRoleInfoDO> getRolesByTenantId(Long tenantId);

    boolean updateRole(UserRoleInfoDO role);

    boolean deleteRole(Long id);
}