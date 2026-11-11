package com.basenest.core.dao.mapper;

import com.basenest.core.dao.dos.TestDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName TestMapper
 * @Description
 * @Author happyboy
 * @Date 2025/12/9 11:45
 * @Version 1.0
 */
@Mapper
public interface TestMapper {
    int insert(@Param("testDO") TestDO test);

    TestDO selectById(@Param("id") Long id);

    List<TestDO> selectAll();

    int update(@Param("testDO") TestDO test);

    int delete(@Param("id") Long id);

    List<TestDO> searchByT1(@Param("keyword") String keyword);
}