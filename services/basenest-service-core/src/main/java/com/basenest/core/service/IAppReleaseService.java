package com.basenest.core.service;


import com.basenest.core.dao.dos.app.AppReleaseDO;

import java.util.List;

public interface IAppReleaseService {
    Long addRelease(AppReleaseDO release);

    AppReleaseDO getReleaseById(Long id);

    List<AppReleaseDO> getReleasesByAppId(Long appId);

    AppReleaseDO getLatestRelease(Long appId, String platform, String releaseChannel);

    boolean updateRelease(AppReleaseDO release);

    boolean deleteRelease(Long id);
}