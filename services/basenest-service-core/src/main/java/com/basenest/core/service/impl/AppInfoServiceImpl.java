package com.basenest.core.service.impl;

import com.basenest.core.dao.dos.app.AppInfoDO;
import com.basenest.core.dao.mapper.app.AppInfoMapper;
import com.basenest.core.service.IAppInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName AppInfoServiceImpl
 * @Description App info service impl
 * @Author happyboy
 * @Date 2026/11/11
 * @Version 1.0
 */
@Service
@Transactional
public class AppInfoServiceImpl implements IAppInfoService {

    @Autowired
    private AppInfoMapper appInfoMapper;

    @Override
    public Long addApp(AppInfoDO appInfo) {
        appInfoMapper.insert(appInfo);
        return appInfo.getId();
    }

    @Override
    public AppInfoDO getAppById(Long id) {
        return appInfoMapper.selectById(id);
    }

    @Override
    public AppInfoDO getAppByAppKey(String appKey) {
        return appInfoMapper.selectByAppKey(appKey);
    }

    @Override
    public List<AppInfoDO> getAllApps() {
        return appInfoMapper.selectAll();
    }

    @Override
    public boolean updateApp(AppInfoDO appInfo) {
        return appInfoMapper.update(appInfo) > 0;
    }

    @Override
    public boolean deleteApp(Long id) {
        return appInfoMapper.delete(id) > 0;
    }

    @Override
    public List<AppInfoDO> searchApps(String keyword) {
        return appInfoMapper.searchByName(keyword);
    }
}