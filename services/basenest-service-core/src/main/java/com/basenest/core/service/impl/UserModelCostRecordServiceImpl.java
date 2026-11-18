package com.basenest.core.service.impl;

import com.basenest.core.dao.dos.user.UserModelCostRecordDO;
import com.basenest.core.dao.mapper.user.UserModelCostRecordMapper;
import com.basenest.core.service.IUserModelCostRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName UserModelCostRecordServiceImpl
 * @Description User model cost record service impl
 * @Author happyboy
 * @Date 2026/11/19
 * @Version 1.0
 */
@Service
@Transactional
public class UserModelCostRecordServiceImpl implements IUserModelCostRecordService {

    @Autowired
    private UserModelCostRecordMapper userModelCostRecordMapper;

    @Override
    public Long addCostRecord(UserModelCostRecordDO userModelCostRecord) {
        userModelCostRecordMapper.insert(userModelCostRecord);
        return userModelCostRecord.getId();
    }

    @Override
    public UserModelCostRecordDO getCostRecordById(Long id) {
        return userModelCostRecordMapper.selectById(id);
    }

    @Override
    public UserModelCostRecordDO getCostRecordByRecordNo(String recordNo) {
        return userModelCostRecordMapper.selectByRecordNo(recordNo);
    }

    @Override
    public List<UserModelCostRecordDO> getAllCostRecords() {
        return userModelCostRecordMapper.selectAll();
    }

    @Override
    public List<UserModelCostRecordDO> getCostRecordsByUserId(Long userId) {
        return userModelCostRecordMapper.selectByUserId(userId);
    }

    @Override
    public List<UserModelCostRecordDO> getCostRecordsByModelId(Long modelId) {
        return userModelCostRecordMapper.selectByModelId(modelId);
    }

    @Override
    public boolean updateCostRecord(UserModelCostRecordDO userModelCostRecord) {
        return userModelCostRecordMapper.update(userModelCostRecord) > 0;
    }

    @Override
    public boolean deleteCostRecord(Long id) {
        return userModelCostRecordMapper.delete(id) > 0;
    }
}