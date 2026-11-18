package com.basenest.core.controller.app;

import com.basenest.core.dao.dos.ai.AiModelDO;
import com.basenest.core.service.IAiModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName AiModelController
 * @Description AI model controller
 * @Author happyboy
 * @Date 2026/11/19
 * @Version 1.0
 */
@RestController
@RequestMapping("/ai/model")
@CrossOrigin(origins = "*")
public class AiModelController {

    @Autowired
    private IAiModelService aiModelService;

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody AiModelDO aiModel) {
        return ResponseEntity.ok(aiModelService.addModel(aiModel));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AiModelDO> getById(@PathVariable Long id) {
        AiModelDO model = aiModelService.getModelById(id);
        return model != null ? ResponseEntity.ok(model) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<AiModelDO>> getAll() {
        return ResponseEntity.ok(aiModelService.getAllModels());
    }

    @GetMapping("/vendor/{vendorId}")
    public ResponseEntity<List<AiModelDO>> getByVendorId(@PathVariable Long vendorId) {
        return ResponseEntity.ok(aiModelService.getModelsByVendorId(vendorId));
    }

    @GetMapping("/modality/{modality}")
    public ResponseEntity<List<AiModelDO>> getByModality(@PathVariable String modality) {
        return ResponseEntity.ok(aiModelService.getModelsByModality(modality));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@PathVariable Long id, @RequestBody AiModelDO aiModel) {
        aiModel.setId(id);
        return ResponseEntity.ok(aiModelService.updateModel(aiModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(aiModelService.deleteModel(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<AiModelDO>> search(@RequestParam String keyword) {
        return ResponseEntity.ok(aiModelService.searchModels(keyword));
    }
}