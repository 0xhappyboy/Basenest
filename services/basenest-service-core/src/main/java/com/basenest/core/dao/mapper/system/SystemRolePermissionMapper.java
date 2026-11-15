package com.basenest.core.dao.mapper.system;

import com.basenest.core.dao.dos.system.SystemRolePermissionDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName SystemRolePermissionMapper
 * @Description System role-permission relation mapper
 * @Author happyboy
 * @Date 2026/11/16
 * @Version 1.0
 */
@Mapper
public interface SystemRolePermissionMapper {
    int insert(SystemRolePermissionDO rolePermission);

    List<SystemRolePermissionDO> selectByRoleId(@Param("roleId") Long roleId);

    List<SystemRolePermissionDO> selectByPermissionCode(@Param("permissionCode") String permissionCode);

    int deleteByRoleId(@Param("roleId") Long roleId);

    int deleteByRoleIdAndCode(@Param("roleId") Long roleId,
                              @Param("permissionCode") String permissionCode);
}