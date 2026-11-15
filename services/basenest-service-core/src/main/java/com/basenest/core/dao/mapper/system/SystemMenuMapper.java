package com.basenest.core.dao.mapper.system;

import com.basenest.core.dao.dos.system.SystemMenuDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName SystemMenuMapper
 * @Description System menu mapper
 * @Author happyboy
 * @Date 2026/11/16
 * @Version 1.0
 */
@Mapper
public interface SystemMenuMapper {
    int insert(SystemMenuDO menu);

    SystemMenuDO selectById(@Param("id") Long id);

    SystemMenuDO selectByCode(@Param("code") String code);

    List<SystemMenuDO> selectAll();

    List<SystemMenuDO> selectByParentId(@Param("parentId") Long parentId);

    List<SystemMenuDO> selectByPermission(@Param("permission") String permission);

    int update(SystemMenuDO menu);

    int delete(@Param("id") Long id);
}