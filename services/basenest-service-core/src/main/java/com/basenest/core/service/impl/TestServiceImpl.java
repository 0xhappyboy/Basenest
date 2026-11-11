package com.basenest.core.service.impl;

import com.basenest.core.dao.mapper.TestMapper;
import com.basenest.core.service.ITestService;
import com.basenest.core.dao.dos.TestDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName TestServiceImpl
 * @Description
 * @Author happyboy
 * @Date 2025/12/9 10:55
 * @Version 1.0
 */
@Service
@Transactional
public class TestServiceImpl implements ITestService {

    @Autowired
    private TestMapper testMapper;

    @Override
    public void test() {
    }

    public Long addTest(TestDO test) {
        testMapper.insert(test);
        return test.getId();
    }

    public TestDO getTestById(Long id) {
        return testMapper.selectById(id);
    }

    public List<TestDO> getAllTests() {
        return testMapper.selectAll();
    }

    public boolean updateTest(TestDO test) {
        return testMapper.update(test) > 0;
    }

    public boolean deleteTest(Long id) {
        return testMapper.delete(id) > 0;
    }

    public List<TestDO> searchTests(String keyword) {
        return testMapper.searchByT1(keyword);
    }
}
