package com.basenest.core.dao.mapper.system;

import com.basenest.core.dao.dos.system.SystemUserRoleDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName SystemUserRoleMapper
 * @Description System user-role relation mapper
 * @Author happyboy
 * @Date 2026/11/16
 * @Version 1.0
 */
@Mapper
public interface SystemUserRoleMapper {
    int insert(SystemUserRoleDO userRole);

    List<SystemUserRoleDO> selectByUserId(@Param("userId") Long userId);

    List<SystemUserRoleDO> selectByRoleId(@Param("roleId") Long roleId);

    int deleteByUserId(@Param("userId") Long userId);

    int deleteByUserIdAndRoleId(@Param("userId") Long userId, @Param("roleId") Long roleId);
}