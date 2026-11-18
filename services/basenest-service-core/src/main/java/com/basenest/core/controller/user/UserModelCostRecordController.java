package com.basenest.core.controller.user;

import com.basenest.core.dao.dos.user.UserModelCostRecordDO;
import com.basenest.core.service.IUserModelCostRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName UserModelCostRecordController
 * @Description User model cost record controller
 * @Author happyboy
 * @Date 2026/11/19
 * @Version 1.0
 */
@RestController
@RequestMapping("/user/cost-record")
@CrossOrigin(origins = "*")
public class UserModelCostRecordController {

    @Autowired
    private IUserModelCostRecordService userModelCostRecordService;

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody UserModelCostRecordDO userModelCostRecord) {
        return ResponseEntity.ok(userModelCostRecordService.addCostRecord(userModelCostRecord));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModelCostRecordDO> getById(@PathVariable Long id) {
        UserModelCostRecordDO record = userModelCostRecordService.getCostRecordById(id);
        return record != null ? ResponseEntity.ok(record) : ResponseEntity.notFound().build();
    }

    @GetMapping("/no/{recordNo}")
    public ResponseEntity<UserModelCostRecordDO> getByRecordNo(@PathVariable String recordNo) {
        UserModelCostRecordDO record = userModelCostRecordService.getCostRecordByRecordNo(recordNo);
        return record != null ? ResponseEntity.ok(record) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<UserModelCostRecordDO>> getAll() {
        return ResponseEntity.ok(userModelCostRecordService.getAllCostRecords());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserModelCostRecordDO>> getByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(userModelCostRecordService.getCostRecordsByUserId(userId));
    }

    @GetMapping("/model/{modelId}")
    public ResponseEntity<List<UserModelCostRecordDO>> getByModelId(@PathVariable Long modelId) {
        return ResponseEntity.ok(userModelCostRecordService.getCostRecordsByModelId(modelId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@PathVariable Long id, @RequestBody UserModelCostRecordDO userModelCostRecord) {
        userModelCostRecord.setId(id);
        return ResponseEntity.ok(userModelCostRecordService.updateCostRecord(userModelCostRecord));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(userModelCostRecordService.deleteCostRecord(id));
    }
}