package com.basenest.core.service;


import com.basenest.core.dao.dos.user.UserModelCostRecordDO;

import java.util.List;

public interface IUserModelCostRecordService {
    Long addCostRecord(UserModelCostRecordDO userModelCostRecord);

    UserModelCostRecordDO getCostRecordById(Long id);

    UserModelCostRecordDO getCostRecordByRecordNo(String recordNo);

    List<UserModelCostRecordDO> getAllCostRecords();

    List<UserModelCostRecordDO> getCostRecordsByUserId(Long userId);

    List<UserModelCostRecordDO> getCostRecordsByModelId(Long modelId);

    boolean updateCostRecord(UserModelCostRecordDO userModelCostRecord);

    boolean deleteCostRecord(Long id);
}