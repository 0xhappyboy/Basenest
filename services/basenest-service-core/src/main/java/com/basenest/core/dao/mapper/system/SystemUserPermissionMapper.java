package com.basenest.core.dao.mapper.system;

import com.basenest.core.dao.dos.system.SystemUserPermissionDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName SystemUserPermissionMapper
 * @Description System user-permission relation mapper
 * @Author happyboy
 * @Date 2026/11/16
 * @Version 1.0
 */
@Mapper
public interface SystemUserPermissionMapper {
    int insert(SystemUserPermissionDO userPermission);

    List<SystemUserPermissionDO> selectByUserId(@Param("userId") Long userId);

    List<SystemUserPermissionDO> selectByPermissionCode(@Param("permissionCode") String permissionCode);

    int deleteByUserId(@Param("userId") Long userId);

    int deleteByUserIdAndCode(@Param("userId") Long userId,
                              @Param("permissionCode") String permissionCode);
}