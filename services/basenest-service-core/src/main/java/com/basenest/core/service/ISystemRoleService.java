package com.basenest.core.service;

import com.basenest.core.dao.dos.system.SystemRoleDO;

import java.util.List;

/**
 * @ClassName ISystemRoleService
 * @Description System role service
 * @Author happyboy
 * @Date 2026/11/16
 * @Version 1.0
 */
public interface ISystemRoleService {
    Long addRole(SystemRoleDO role);

    SystemRoleDO getRoleById(Long id);

    SystemRoleDO getRoleByCode(String code);

    List<SystemRoleDO> getAllRoles();

    boolean updateRole(SystemRoleDO role);

    boolean deleteRole(Long id);

    // ----- role-permission -----
    List<String> getPermissionCodesByRoleId(Long roleId);

    boolean assignPermissions(Long roleId, List<String> permissionCodes);

    boolean addPermission(Long roleId, String permissionCode);

    boolean removePermission(Long roleId, String permissionCode);
}