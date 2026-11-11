package com.basenest.core.service;

import com.basenest.core.dao.dos.TestDO;

import java.util.List;

public interface ITestService {
    void test();

    Long addTest(TestDO test);

    TestDO getTestById(Long id);

    List<TestDO> getAllTests();

    boolean updateTest(TestDO test);

    boolean deleteTest(Long id);

    List<TestDO> searchTests(String keyword);
}
