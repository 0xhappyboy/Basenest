package com.basenest.core.dao.mapper.system;

import com.basenest.core.dao.dos.system.SystemPermissionCodeDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName SystemPermissionCodeMapper
 * @Description System permission code mapper
 * @Author happyboy
 * @Date 2026/11/16
 * @Version 1.0
 */
@Mapper
public interface SystemPermissionCodeMapper {
    int insert(SystemPermissionCodeDO permissionCode);

    SystemPermissionCodeDO selectById(@Param("id") Long id);

    SystemPermissionCodeDO selectByCode(@Param("code") String code);

    List<SystemPermissionCodeDO> selectAll();

    List<SystemPermissionCodeDO> selectByModule(@Param("module") String module);

    List<SystemPermissionCodeDO> selectByType(@Param("type") String type);

    int update(SystemPermissionCodeDO permissionCode);

    int delete(@Param("id") Long id);
}