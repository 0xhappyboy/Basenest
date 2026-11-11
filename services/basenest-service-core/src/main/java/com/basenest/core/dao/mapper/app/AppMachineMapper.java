package com.basenest.core.dao.mapper.app;

import com.basenest.core.dao.dos.app.AppMachineDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName AppMachineMapper
 * @Description App machine mapper
 * @Author happyboy
 * @Date 2025/12/9 11:45
 * @Version 1.0
 */
@Mapper
public interface AppMachineMapper {
    int insert(@Param("appMachineDO") AppMachineDO appMachine);

    AppMachineDO selectById(@Param("id") Long id);

    AppMachineDO selectByAppAndKey(@Param("appId") Long appId,
                                   @Param("machineKey") String machineKey);

    List<AppMachineDO> selectByAppId(@Param("appId") Long appId);

    List<AppMachineDO> selectByUserId(@Param("userId") Long userId);

    int update(@Param("appMachineDO") AppMachineDO appMachine);

    int delete(@Param("id") Long id);
}