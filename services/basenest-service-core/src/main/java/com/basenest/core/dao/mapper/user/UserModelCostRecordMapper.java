package com.basenest.core.dao.mapper.user;

import com.basenest.core.dao.dos.user.UserModelCostRecordDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName UserModelCostRecordMapper
 * @Description User model cost record mapper
 * @Author happyboy
 * @Date 2026/11/19
 * @Version 1.0
 */
@Mapper
public interface UserModelCostRecordMapper {
    int insert(@Param("userModelCostRecordDO") UserModelCostRecordDO userModelCostRecord);

    UserModelCostRecordDO selectById(@Param("id") Long id);

    UserModelCostRecordDO selectByRecordNo(@Param("recordNo") String recordNo);

    List<UserModelCostRecordDO> selectAll();

    List<UserModelCostRecordDO> selectByUserId(@Param("userId") Long userId);

    List<UserModelCostRecordDO> selectByModelId(@Param("modelId") Long modelId);

    int update(@Param("userModelCostRecordDO") UserModelCostRecordDO userModelCostRecord);

    int delete(@Param("id") Long id);
}