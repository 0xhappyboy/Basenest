package com.basenest.core.service;

import com.basenest.core.dao.dos.system.SystemMenuDO;

import java.util.List;

/**
 * @ClassName ISystemMenuService
 * @Description System menu service
 * @Author happyboy
 * @Date 2026/11/16
 * @Version 1.0
 */
public interface ISystemMenuService {
    Long addMenu(SystemMenuDO menu);

    SystemMenuDO getMenuById(Long id);

    SystemMenuDO getMenuByCode(String code);

    List<SystemMenuDO> getAllMenus();

    List<SystemMenuDO> getMenusByParentId(Long parentId);

    List<SystemMenuDO> getMenusByPermission(String permission);

    List<SystemMenuDO> getMenusByUserId(Long userId);

    boolean updateMenu(SystemMenuDO menu);

    boolean deleteMenu(Long id);
}