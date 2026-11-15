package com.basenest.core.service;

import com.basenest.core.dao.dos.system.SystemRoleDO;
import com.basenest.core.dao.dos.system.SystemUserDO;

import java.util.List;

/**
 * @ClassName ISystemUserService
 * @Description System user service
 * @Author happyboy
 * @Date 2026/11/16
 * @Version 1.0
 */
public interface ISystemUserService {
    Long addUser(SystemUserDO user);

    SystemUserDO getUserById(Long id);

    SystemUserDO getUserByUsername(String username);

    List<SystemUserDO> getAllUsers();

    List<SystemUserDO> searchUsers(String keyword);

    boolean updateUser(SystemUserDO user);

    boolean changePassword(Long id, String rawPassword);

    boolean deleteUser(Long id);

    // ----- user-role -----
    List<Long> getRoleIdsByUserId(Long userId);

    boolean assignRoles(Long userId, List<Long> roleIds);

    // ----- user-permission -----
    List<String> getDirectPermissionCodes(Long userId);

    List<String> getUserPermissionCodes(Long userId);

    boolean grantPermission(Long userId, String permissionCode);

    boolean denyPermission(Long userId, String permissionCode);

    boolean removeUserPermission(Long userId, String permissionCode);

    List<SystemRoleDO> getRolesByUserId(Long userId);
}