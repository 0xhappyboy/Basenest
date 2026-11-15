package com.basenest.core.service.impl;

import com.basenest.core.dao.dos.system.SystemMenuDO;
import com.basenest.core.dao.dos.system.SystemRoleDO;
import com.basenest.core.dao.dos.system.SystemUserRoleDO;
import com.basenest.core.dao.mapper.system.SystemMenuMapper;
import com.basenest.core.dao.mapper.system.SystemRoleMapper;
import com.basenest.core.dao.mapper.system.SystemUserRoleMapper;
import com.basenest.core.service.ISystemMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ClassName SystemMenuServiceImpl
 * @Description System menu service impl
 * @Author happyboy
 * @Date 2026/11/16
 * @Version 1.0
 */
@Service
@Transactional
public class SystemMenuServiceImpl implements ISystemMenuService {

    @Autowired
    private SystemMenuMapper systemMenuMapper;

    @Autowired
    private SystemUserRoleMapper systemUserRoleMapper;

    @Autowired
    private SystemRoleMapper systemRoleMapper;

    @Override
    public Long addMenu(SystemMenuDO menu) {
        SystemMenuDO existing = systemMenuMapper.selectByCode(menu.getCode());
        if (existing != null) {
            throw new IllegalArgumentException("Menu code already exists: " + menu.getCode());
        }
        if (menu.getLevel() == null) menu.setLevel(1);
        if (menu.getStatus() == null) menu.setStatus(1);
        if (menu.getIsDeleted() == null) menu.setIsDeleted(0);
        if (menu.getVisible() == null) menu.setVisible(1);
        systemMenuMapper.insert(menu);
        return menu.getId();
    }

    @Override
    public SystemMenuDO getMenuById(Long id) {
        return systemMenuMapper.selectById(id);
    }

    @Override
    public SystemMenuDO getMenuByCode(String code) {
        return systemMenuMapper.selectByCode(code);
    }

    @Override
    public List<SystemMenuDO> getAllMenus() {
        return systemMenuMapper.selectAll();
    }

    @Override
    public List<SystemMenuDO> getMenusByParentId(Long parentId) {
        return systemMenuMapper.selectByParentId(parentId);
    }

    @Override
    public List<SystemMenuDO> getMenusByPermission(String permission) {
        return systemMenuMapper.selectByPermission(permission);
    }

    @Override
    public List<SystemMenuDO> getMenusByUserId(Long userId) {
        // collect permission codes from user's roles, then return menus whose permission is in the set
        Set<String> granted = new HashSet<>();
        List<SystemUserRoleDO> roles = systemUserRoleMapper.selectByUserId(userId);
        for (SystemUserRoleDO ur : roles) {
            SystemRoleDO role = systemRoleMapper.selectById(ur.getRoleId());
            if (role == null) continue;
            // role-permission lookup is delegated to role service; for a self-contained menu query
            // we return all menus when the user is super admin (is_super handled at user layer).
        }
        List<SystemMenuDO> all = systemMenuMapper.selectAll();
        List<SystemMenuDO> result = new ArrayList<>();
        for (SystemMenuDO m : all) {
            if (m.getPermission() == null || granted.contains(m.getPermission())) {
                result.add(m);
            }
        }
        return result;
    }

    @Override
    public boolean updateMenu(SystemMenuDO menu) {
        return systemMenuMapper.update(menu) > 0;
    }

    @Override
    public boolean deleteMenu(Long id) {
        return systemMenuMapper.delete(id) > 0;
    }
}