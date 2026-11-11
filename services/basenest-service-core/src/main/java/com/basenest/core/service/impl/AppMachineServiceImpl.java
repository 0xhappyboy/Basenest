package com.basenest.core.service.impl;

import com.basenest.core.dao.dos.app.AppMachineDO;
import com.basenest.core.dao.mapper.app.AppMachineMapper;
import com.basenest.core.service.IAppMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName AppMachineServiceImpl
 * @Description App machine service impl
 * @Author happyboy
 * @Date 2026/11/11
 * @Version 1.0
 */
@Service
@Transactional
public class AppMachineServiceImpl implements IAppMachineService {

    @Autowired
    private AppMachineMapper appMachineMapper;

    @Override
    public Long reportMachine(AppMachineDO machine) {
        AppMachineDO existing = appMachineMapper.selectByAppAndKey(machine.getAppId(), machine.getMachineKey());
        LocalDateTime now = LocalDateTime.now();
        if (existing == null) {
            machine.setFirstSeenTime(now);
            machine.setLastSeenTime(now);
            appMachineMapper.insert(machine);
            return machine.getId();
        }
        machine.setId(existing.getId());
        machine.setFirstSeenTime(existing.getFirstSeenTime());
        machine.setLastSeenTime(now);
        appMachineMapper.update(machine);
        return existing.getId();
    }

    @Override
    public AppMachineDO getMachineById(Long id) {
        return appMachineMapper.selectById(id);
    }

    @Override
    public AppMachineDO getMachineByAppAndKey(Long appId, String machineKey) {
        return appMachineMapper.selectByAppAndKey(appId, machineKey);
    }

    @Override
    public List<AppMachineDO> getMachinesByAppId(Long appId) {
        return appMachineMapper.selectByAppId(appId);
    }

    @Override
    public List<AppMachineDO> getMachinesByUserId(Long userId) {
        return appMachineMapper.selectByUserId(userId);
    }

    @Override
    public boolean updateMachine(AppMachineDO machine) {
        return appMachineMapper.update(machine) > 0;
    }

    @Override
    public boolean deleteMachine(Long id) {
        return appMachineMapper.delete(id) > 0;
    }
}