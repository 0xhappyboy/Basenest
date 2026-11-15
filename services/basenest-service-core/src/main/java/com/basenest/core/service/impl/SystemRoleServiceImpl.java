package com.basenest.core.service.impl;

import com.basenest.core.dao.dos.system.SystemPermissionCodeDO;
import com.basenest.core.dao.dos.system.SystemRoleDO;
import com.basenest.core.dao.dos.system.SystemRolePermissionDO;
import com.basenest.core.dao.mapper.system.SystemPermissionCodeMapper;
import com.basenest.core.dao.mapper.system.SystemRoleMapper;
import com.basenest.core.dao.mapper.system.SystemRolePermissionMapper;
import com.basenest.core.service.ISystemRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SystemRoleServiceImpl
 * @Description System role service impl
 * @Author happyboy
 * @Date 2026/11/16
 * @Version 1.0
 */
@Service
@Transactional
public class SystemRoleServiceImpl implements ISystemRoleService {

    @Autowired
    private SystemRoleMapper systemRoleMapper;

    @Autowired
    private SystemRolePermissionMapper systemRolePermissionMapper;

    @Autowired
    private SystemPermissionCodeMapper systemPermissionCodeMapper;

    @Override
    public Long addRole(SystemRoleDO role) {
        SystemRoleDO existing = systemRoleMapper.selectByCode(role.getCode());
        if (existing != null) {
            throw new IllegalArgumentException("Role code already exists: " + role.getCode());
        }
        if (role.getIsSystem() == null) role.setIsSystem(0);
        if (role.getStatus() == null) role.setStatus(1);
        if (role.getIsDeleted() == null) role.setIsDeleted(0);
        if (role.getDataScope() == null) role.setDataScope(1);
        systemRoleMapper.insert(role);
        return role.getId();
    }

    @Override
    public SystemRoleDO getRoleById(Long id) {
        return systemRoleMapper.selectById(id);
    }

    @Override
    public SystemRoleDO getRoleByCode(String code) {
        return systemRoleMapper.selectByCode(code);
    }

    @Override
    public List<SystemRoleDO> getAllRoles() {
        return systemRoleMapper.selectAll();
    }

    @Override
    public boolean updateRole(SystemRoleDO role) {
        return systemRoleMapper.update(role) > 0;
    }

    @Override
    public boolean deleteRole(Long id) {
        SystemRoleDO role = systemRoleMapper.selectById(id);
        if (role != null && role.getIsSystem() != null && role.getIsSystem() == 1) {
            throw new IllegalStateException("Cannot delete built-in role: " + role.getCode());
        }
        return systemRoleMapper.delete(id) > 0;
    }

    // ----- role-permission -----

    @Override
    public List<String> getPermissionCodesByRoleId(Long roleId) {
        List<SystemRolePermissionDO> rows = systemRolePermissionMapper.selectByRoleId(roleId);
        List<String> codes = new ArrayList<>(rows.size());
        for (SystemRolePermissionDO r : rows) codes.add(r.getPermissionCode());
        return codes;
    }

    @Override
    public boolean assignPermissions(Long roleId, List<String> permissionCodes) {
        systemRolePermissionMapper.deleteByRoleId(roleId);
        if (permissionCodes == null || permissionCodes.isEmpty()) return true;
        for (String code : permissionCodes) {
            SystemPermissionCodeDO pc = systemPermissionCodeMapper.selectByCode(code);
            if (pc == null) {
                throw new IllegalArgumentException("Unknown permission code: " + code);
            }
            SystemRolePermissionDO rp = new SystemRolePermissionDO();
            rp.setRoleId(roleId);
            rp.setPermissionCode(code);
            rp.setStatus(1);
            systemRolePermissionMapper.insert(rp);
        }
        return true;
    }

    @Override
    public boolean addPermission(Long roleId, String permissionCode) {
        SystemPermissionCodeDO pc = systemPermissionCodeMapper.selectByCode(permissionCode);
        if (pc == null) {
            throw new IllegalArgumentException("Unknown permission code: " + permissionCode);
        }
        SystemRolePermissionDO rp = new SystemRolePermissionDO();
        rp.setRoleId(roleId);
        rp.setPermissionCode(permissionCode);
        rp.setStatus(1);
        systemRolePermissionMapper.insert(rp);
        return true;
    }

    @Override
    public boolean removePermission(Long roleId, String permissionCode) {
        return systemRolePermissionMapper.deleteByRoleIdAndCode(roleId, permissionCode) > 0;
    }
}