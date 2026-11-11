package com.basenest.core.dao.mapper;

import com.basenest.core.dao.dos.UserMemberDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName UserMemberMapper
 * @Description User member mapper
 * @Author happyboy
 * @Date 2025/12/9 11:45
 * @Version 1.0
 */
@Mapper
public interface UserMemberMapper {
    int insert(@Param("userMemberDO") UserMemberDO userMember);

    UserMemberDO selectById(@Param("id") Long id);

    UserMemberDO selectByUserId(@Param("userId") Long userId);

    int update(@Param("userMemberDO") UserMemberDO userMember);

    int delete(@Param("id") Long id);
}