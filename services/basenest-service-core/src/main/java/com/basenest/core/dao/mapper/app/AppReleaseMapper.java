package com.basenest.core.dao.mapper.app;

import com.basenest.core.dao.dos.app.AppReleaseDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName AppReleaseMapper
 * @Description App release mapper
 * @Author happyboy
 * @Date 2025/12/9 11:45
 * @Version 1.0
 */
@Mapper
public interface AppReleaseMapper {
    int insert(@Param("appReleaseDO") AppReleaseDO appRelease);

    AppReleaseDO selectById(@Param("id") Long id);

    List<AppReleaseDO> selectByAppId(@Param("appId") Long appId);

    AppReleaseDO selectLatest(@Param("appId") Long appId,
                              @Param("platform") String platform,
                              @Param("releaseChannel") String releaseChannel);

    int update(@Param("appReleaseDO") AppReleaseDO appRelease);

    int delete(@Param("id") Long id);

    int clearLatest(@Param("appId") Long appId,
                    @Param("platform") String platform,
                    @Param("arch") String arch,
                    @Param("releaseChannel") String releaseChannel);
}