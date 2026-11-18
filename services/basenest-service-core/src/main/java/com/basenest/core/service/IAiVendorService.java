package com.basenest.core.service;

import com.basenest.core.dao.dos.ai.AiVendorDO;

import java.util.List;

public interface IAiVendorService {
    Long addVendor(AiVendorDO aiVendor);

    AiVendorDO getVendorById(Long id);

    AiVendorDO getVendorByVendorCode(String vendorCode);

    List<AiVendorDO> getAllVendors();

    boolean updateVendor(AiVendorDO aiVendor);

    boolean deleteVendor(Long id);

    List<AiVendorDO> searchVendors(String keyword);
}