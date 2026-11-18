package com.basenest.core.service.impl;

import com.basenest.core.dao.dos.ai.AiVendorDO;
import com.basenest.core.dao.mapper.ai.AiVendorMapper;
import com.basenest.core.service.IAiVendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName AiVendorServiceImpl
 * @Description AI vendor service impl
 * @Author happyboy
 * @Date 2026/11/19
 * @Version 1.0
 */
@Service
@Transactional
public class AiVendorServiceImpl implements IAiVendorService {

    @Autowired
    private AiVendorMapper aiVendorMapper;

    @Override
    public Long addVendor(AiVendorDO aiVendor) {
        aiVendorMapper.insert(aiVendor);
        return aiVendor.getId();
    }

    @Override
    public AiVendorDO getVendorById(Long id) {
        return aiVendorMapper.selectById(id);
    }

    @Override
    public AiVendorDO getVendorByVendorCode(String vendorCode) {
        return aiVendorMapper.selectByVendorCode(vendorCode);
    }

    @Override
    public List<AiVendorDO> getAllVendors() {
        return aiVendorMapper.selectAll();
    }

    @Override
    public boolean updateVendor(AiVendorDO aiVendor) {
        return aiVendorMapper.update(aiVendor) > 0;
    }

    @Override
    public boolean deleteVendor(Long id) {
        return aiVendorMapper.delete(id) > 0;
    }

    @Override
    public List<AiVendorDO> searchVendors(String keyword) {
        return aiVendorMapper.searchByName(keyword);
    }
}