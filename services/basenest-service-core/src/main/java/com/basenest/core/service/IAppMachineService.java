package com.basenest.core.service;

import com.basenest.core.dao.dos.app.AppMachineDO;

import java.util.List;

public interface IAppMachineService {
    Long reportMachine(AppMachineDO machine);

    AppMachineDO getMachineById(Long id);

    AppMachineDO getMachineByAppAndKey(Long appId, String machineKey);

    List<AppMachineDO> getMachinesByAppId(Long appId);

    List<AppMachineDO> getMachinesByUserId(Long userId);

    boolean updateMachine(AppMachineDO machine);

    boolean deleteMachine(Long id);
}