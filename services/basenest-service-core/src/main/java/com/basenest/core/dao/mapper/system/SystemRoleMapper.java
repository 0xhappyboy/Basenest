package com.basenest.core.dao.mapper.system;

import com.basenest.core.dao.dos.system.SystemRoleDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName SystemRoleMapper
 * @Description System role mapper
 * @Author happyboy
 * @Date 2026/11/16
 * @Version 1.0
 */
@Mapper
public interface SystemRoleMapper {
    int insert(SystemRoleDO role);

    SystemRoleDO selectById(@Param("id") Long id);

    SystemRoleDO selectByCode(@Param("code") String code);

    List<SystemRoleDO> selectAll();

    int update(SystemRoleDO role);

    int delete(@Param("id") Long id);
}