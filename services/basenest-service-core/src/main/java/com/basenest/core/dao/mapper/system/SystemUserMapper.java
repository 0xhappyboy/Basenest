package com.basenest.core.dao.mapper.system;

import com.basenest.core.dao.dos.system.SystemUserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName SystemUserMapper
 * @Description System user mapper
 * @Author happyboy
 * @Date 2026/11/16
 * @Version 1.0
 */
@Mapper
public interface SystemUserMapper {
    int insert(SystemUserDO user);

    SystemUserDO selectById(@Param("id") Long id);

    SystemUserDO selectByUsername(@Param("username") String username);

    List<SystemUserDO> selectAll();

    List<SystemUserDO> search(@Param("keyword") String keyword);

    int update(SystemUserDO user);

    int updatePassword(@Param("id") Long id, @Param("password") String password);

    int updateLastLogin(@Param("id") Long id,
                        @Param("ip") String ip,
                        @Param("time") java.time.LocalDateTime time);

    int delete(@Param("id") Long id);
}