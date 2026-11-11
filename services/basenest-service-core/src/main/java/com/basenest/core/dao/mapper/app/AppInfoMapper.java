package com.basenest.core.dao.mapper.app;

import com.basenest.core.dao.dos.app.AppInfoDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName AppInfoMapper
 * @Description App info mapper
 * @Author happyboy
 * @Date 2025/12/9 11:45
 * @Version 1.0
 */
@Mapper
public interface AppInfoMapper {
    int insert(@Param("appInfoDO") AppInfoDO appInfo);

    AppInfoDO selectById(@Param("id") Long id);

    AppInfoDO selectByAppKey(@Param("appKey") String appKey);

    List<AppInfoDO> selectAll();

    int update(@Param("appInfoDO") AppInfoDO appInfo);

    int delete(@Param("id") Long id);

    List<AppInfoDO> searchByName(@Param("keyword") String keyword);
}