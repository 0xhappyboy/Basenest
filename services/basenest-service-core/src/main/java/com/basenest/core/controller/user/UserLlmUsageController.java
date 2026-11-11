package com.basenest.core.controller.user;

import com.basenest.core.dao.dos.UserLlmUsageDO;
import com.basenest.core.service.IUserLlmUsageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName UserLlmUsageController
 * @Description User LLM usage controller
 * @Author happyboy
 * @Date 2026/11/11
 * @Version 1.0
 */
@RestController
@RequestMapping("/user/llmusage")
public class UserLlmUsageController {

    @Autowired
    private IUserLlmUsageService userLlmUsageService;

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody UserLlmUsageDO usage) {
        return ResponseEntity.ok(userLlmUsageService.addUsage(usage));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserLlmUsageDO> getById(@PathVariable Long id) {
        UserLlmUsageDO usage = userLlmUsageService.getUsageById(id);
        return usage != null ? ResponseEntity.ok(usage) : ResponseEntity.notFound().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserLlmUsageDO>> getByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(userLlmUsageService.getUsagesByUserId(userId));
    }

    @GetMapping("/tenant/{tenantId}")
    public ResponseEntity<List<UserLlmUsageDO>> getByTenantId(@PathVariable Long tenantId) {
        return ResponseEntity.ok(userLlmUsageService.getUsagesByTenantId(tenantId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(userLlmUsageService.deleteUsage(id));
    }
}