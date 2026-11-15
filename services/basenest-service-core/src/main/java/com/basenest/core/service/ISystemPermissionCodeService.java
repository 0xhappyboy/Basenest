package com.basenest.core.service;

import com.basenest.core.dao.dos.system.SystemPermissionCodeDO;

import java.util.List;

/**
 * @ClassName ISystemPermissionCodeService
 * @Description System permission code service
 * @Author happyboy
 * @Date 2026/11/16
 * @Version 1.0
 */
public interface ISystemPermissionCodeService {
    Long addPermissionCode(SystemPermissionCodeDO permissionCode);

    SystemPermissionCodeDO getPermissionCodeById(Long id);

    SystemPermissionCodeDO getPermissionCodeByCode(String code);

    List<SystemPermissionCodeDO> getAllPermissionCodes();

    List<SystemPermissionCodeDO> getPermissionCodesByModule(String module);

    List<SystemPermissionCodeDO> getPermissionCodesByType(String type);

    boolean updatePermissionCode(SystemPermissionCodeDO permissionCode);

    boolean deletePermissionCode(Long id);
}