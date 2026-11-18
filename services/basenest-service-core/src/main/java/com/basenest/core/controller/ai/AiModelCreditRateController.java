package com.basenest.core.controller.ai;

import com.basenest.core.dao.dos.ai.AiModelCreditRateDO;
import com.basenest.core.service.IAiModelCreditRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName AiModelCreditRateController
 * @Description AI model credit rate controller
 * @Author happyboy
 * @Date 2026/11/19
 * @Version 1.0
 */
@RestController
@RequestMapping("/ai/credit-rate")
@CrossOrigin(origins = "*")
public class AiModelCreditRateController {

    @Autowired
    private IAiModelCreditRateService aiModelCreditRateService;

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody AiModelCreditRateDO aiModelCreditRate) {
        return ResponseEntity.ok(aiModelCreditRateService.addCreditRate(aiModelCreditRate));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AiModelCreditRateDO> getById(@PathVariable Long id) {
        AiModelCreditRateDO creditRate = aiModelCreditRateService.getCreditRateById(id);
        return creditRate != null ? ResponseEntity.ok(creditRate) : ResponseEntity.notFound().build();
    }

    @GetMapping("/active/{modelId}")
    public ResponseEntity<AiModelCreditRateDO> getActiveByModelId(@PathVariable Long modelId) {
        AiModelCreditRateDO creditRate = aiModelCreditRateService.getActiveCreditRateByModelId(modelId);
        return creditRate != null ? ResponseEntity.ok(creditRate) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<AiModelCreditRateDO>> getAll() {
        return ResponseEntity.ok(aiModelCreditRateService.getAllCreditRates());
    }

    @GetMapping("/model/{modelId}")
    public ResponseEntity<List<AiModelCreditRateDO>> getByModelId(@PathVariable Long modelId) {
        return ResponseEntity.ok(aiModelCreditRateService.getCreditRatesByModelId(modelId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@PathVariable Long id, @RequestBody AiModelCreditRateDO aiModelCreditRate) {
        aiModelCreditRate.setId(id);
        return ResponseEntity.ok(aiModelCreditRateService.updateCreditRate(aiModelCreditRate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(aiModelCreditRateService.deleteCreditRate(id));
    }
}