package com.basenest.core.service.impl;

import com.basenest.core.dao.dos.app.AppReleaseDO;
import com.basenest.core.dao.mapper.app.AppReleaseMapper;
import com.basenest.core.service.IAppReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName AppReleaseServiceImpl
 * @Description App release service impl
 * @Author happyboy
 * @Date 2026/11/11
 * @Version 1.0
 */
@Service
@Transactional
public class AppReleaseServiceImpl implements IAppReleaseService {

    @Autowired
    private AppReleaseMapper appReleaseMapper;

    @Override
    public Long addRelease(AppReleaseDO release) {
        appReleaseMapper.clearLatest(release.getAppId(), release.getPlatform(), release.getArch(), release.getReleaseChannel());
        release.setIsLatest(1);
        appReleaseMapper.insert(release);
        return release.getId();
    }

    @Override
    public AppReleaseDO getReleaseById(Long id) {
        return appReleaseMapper.selectById(id);
    }

    @Override
    public List<AppReleaseDO> getReleasesByAppId(Long appId) {
        return appReleaseMapper.selectByAppId(appId);
    }

    @Override
    public AppReleaseDO getLatestRelease(Long appId, String platform, String releaseChannel) {
        return appReleaseMapper.selectLatest(appId, platform, releaseChannel);
    }

    @Override
    public boolean updateRelease(AppReleaseDO release) {
        return appReleaseMapper.update(release) > 0;
    }

    @Override
    public boolean deleteRelease(Long id) {
        return appReleaseMapper.delete(id) > 0;
    }
}