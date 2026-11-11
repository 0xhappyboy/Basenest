package com.basenest.core.controller;

import com.basenest.core.dao.dos.TestDO;
import com.basenest.core.service.ITestService;
import com.basenest.core.pojo.dto.TestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName TestController
 * @Description
 * @Author happyboy
 * @Date 2026/11/11
 * @Version 1.0
 */
@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    private ITestService iTestService;

    @GetMapping("/test2")
    public String test2() {
        return "test2";
    }

    @GetMapping("/test")
    public TestDTO test() {
        iTestService.test();
        TestDTO testDTO = new TestDTO();
        TestDO t = new TestDO();
        t.setT1("test");
        iTestService.addTest(t);
        System.out.println(iTestService.getTestById(1L));
        return testDTO;
    }

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody TestDO test) {
        Long id = iTestService.addTest(test);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestDO> getById(@PathVariable Long id) {
        TestDO test = iTestService.getTestById(id);
        return test != null ? ResponseEntity.ok(test) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<TestDO>> getAll() {
        return ResponseEntity.ok(iTestService.getAllTests());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@PathVariable Long id, @RequestBody TestDO test) {
        test.setId(id);
        boolean success = iTestService.updateTest(test);
        return ResponseEntity.ok(success);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        boolean success = iTestService.deleteTest(id);
        return ResponseEntity.ok(success);
    }

    @GetMapping("/search")
    public ResponseEntity<List<TestDO>> search(@RequestParam String keyword) {
        return ResponseEntity.ok(iTestService.searchTests(keyword));
    }
}