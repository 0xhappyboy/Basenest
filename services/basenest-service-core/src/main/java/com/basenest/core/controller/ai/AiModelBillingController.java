package com.basenest.core.controller.app;

import com.basenest.core.dao.dos.ai.AiModelBillingDO;
import com.basenest.core.service.IAiModelBillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName AiModelBillingController
 * @Description AI model billing controller
 * @Author happyboy
 * @Date 2026/11/19
 * @Version 1.0
 */
@RestController
@RequestMapping("/ai/billing")
@CrossOrigin(origins = "*")
public class AiModelBillingController {

    @Autowired
    private IAiModelBillingService aiModelBillingService;

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody AiModelBillingDO aiModelBilling) {
        return ResponseEntity.ok(aiModelBillingService.addBilling(aiModelBilling));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AiModelBillingDO> getById(@PathVariable Long id) {
        AiModelBillingDO billing = aiModelBillingService.getBillingById(id);
        return billing != null ? ResponseEntity.ok(billing) : ResponseEntity.notFound().build();
    }

    @GetMapping("/active/{modelId}")
    public ResponseEntity<AiModelBillingDO> getActiveByModelId(@PathVariable Long modelId) {
        AiModelBillingDO billing = aiModelBillingService.getActiveBillingByModelId(modelId);
        return billing != null ? ResponseEntity.ok(billing) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<AiModelBillingDO>> getAll() {
        return ResponseEntity.ok(aiModelBillingService.getAllBillings());
    }

    @GetMapping("/model/{modelId}")
    public ResponseEntity<List<AiModelBillingDO>> getByModelId(@PathVariable Long modelId) {
        return ResponseEntity.ok(aiModelBillingService.getBillingsByModelId(modelId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@PathVariable Long id, @RequestBody AiModelBillingDO aiModelBilling) {
        aiModelBilling.setId(id);
        return ResponseEntity.ok(aiModelBillingService.updateBilling(aiModelBilling));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(aiModelBillingService.deleteBilling(id));
    }
}