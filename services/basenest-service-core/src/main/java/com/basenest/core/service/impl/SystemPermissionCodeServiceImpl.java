package com.basenest.core.service.impl;

import com.basenest.core.dao.dos.system.SystemPermissionCodeDO;
import com.basenest.core.dao.mapper.system.SystemPermissionCodeMapper;
import com.basenest.core.service.ISystemPermissionCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName SystemPermissionCodeServiceImpl
 * @Description System permission code service impl
 * @Author happyboy
 * @Date 2026/11/16
 * @Version 1.0
 */
@Service
@Transactional
public class SystemPermissionCodeServiceImpl implements ISystemPermissionCodeService {

    @Autowired
    private SystemPermissionCodeMapper systemPermissionCodeMapper;

    @Override
    public Long addPermissionCode(SystemPermissionCodeDO permissionCode) {
        SystemPermissionCodeDO existing = systemPermissionCodeMapper.selectByCode(permissionCode.getCode());
        if (existing != null) {
            throw new IllegalArgumentException("Permission code already exists: " + permissionCode.getCode());
        }
        if (permissionCode.getStatus() == null) permissionCode.setStatus(1);
        if (permissionCode.getIsDeleted() == null) permissionCode.setIsDeleted(0);
        systemPermissionCodeMapper.insert(permissionCode);
        return permissionCode.getId();
    }

    @Override
    public SystemPermissionCodeDO getPermissionCodeById(Long id) {
        return systemPermissionCodeMapper.selectById(id);
    }

    @Override
    public SystemPermissionCodeDO getPermissionCodeByCode(String code) {
        return systemPermissionCodeMapper.selectByCode(code);
    }

    @Override
    public List<SystemPermissionCodeDO> getAllPermissionCodes() {
        return systemPermissionCodeMapper.selectAll();
    }

    @Override
    public List<SystemPermissionCodeDO> getPermissionCodesByModule(String module) {
        return systemPermissionCodeMapper.selectByModule(module);
    }

    @Override
    public List<SystemPermissionCodeDO> getPermissionCodesByType(String type) {
        return systemPermissionCodeMapper.selectByType(type);
    }

    @Override
    public boolean updatePermissionCode(SystemPermissionCodeDO permissionCode) {
        return systemPermissionCodeMapper.update(permissionCode) > 0;
    }

    @Override
    public boolean deletePermissionCode(Long id) {
        return systemPermissionCodeMapper.delete(id) > 0;
    }
}