package com.basenest.core.dao.mapper;

import com.basenest.core.dao.dos.UserInfoDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName UserInfoMapper
 * @Description User info mapper
 * @Author happyboy
 * @Date 2025/12/9 11:45
 * @Version 1.0
 */
@Mapper
public interface UserInfoMapper {
    int insert(@Param("userInfoDO") UserInfoDO userInfo);

    UserInfoDO selectById(@Param("id") Long id);

    UserInfoDO selectByUsername(@Param("username") String username);

    List<UserInfoDO> selectAll();

    int update(@Param("userInfoDO") UserInfoDO userInfo);

    int delete(@Param("id") Long id);

    List<UserInfoDO> searchByKeyword(@Param("keyword") String keyword);
}