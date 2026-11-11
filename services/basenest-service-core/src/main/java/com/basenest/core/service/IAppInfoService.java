package com.basenest.core.service;

import com.basenest.core.dao.dos.app.AppInfoDO;

import java.util.List;

public interface IAppInfoService {
    Long addApp(AppInfoDO appInfo);

    AppInfoDO getAppById(Long id);

    AppInfoDO getAppByAppKey(String appKey);

    List<AppInfoDO> getAllApps();

    boolean updateApp(AppInfoDO appInfo);

    boolean deleteApp(Long id);

    List<AppInfoDO> searchApps(String keyword);
}