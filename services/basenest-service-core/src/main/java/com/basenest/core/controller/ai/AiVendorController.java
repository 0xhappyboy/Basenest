package com.basenest.core.controller.app;

import com.basenest.core.dao.dos.ai.AiVendorDO;
import com.basenest.core.service.IAiVendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName AiVendorController
 * @Description AI vendor controller
 * @Author happyboy
 * @Date 2026/11/19
 * @Version 1.0
 */
@RestController
@RequestMapping("/ai/vendor")
@CrossOrigin(origins = "*")
public class AiVendorController {

    @Autowired
    private IAiVendorService aiVendorService;

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody AiVendorDO aiVendor) {
        return ResponseEntity.ok(aiVendorService.addVendor(aiVendor));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AiVendorDO> getById(@PathVariable Long id) {
        AiVendorDO vendor = aiVendorService.getVendorById(id);
        return vendor != null ? ResponseEntity.ok(vendor) : ResponseEntity.notFound().build();
    }

    @GetMapping("/code/{vendorCode}")
    public ResponseEntity<AiVendorDO> getByVendorCode(@PathVariable String vendorCode) {
        AiVendorDO vendor = aiVendorService.getVendorByVendorCode(vendorCode);
        return vendor != null ? ResponseEntity.ok(vendor) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<AiVendorDO>> getAll() {
        return ResponseEntity.ok(aiVendorService.getAllVendors());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@PathVariable Long id, @RequestBody AiVendorDO aiVendor) {
        aiVendor.setId(id);
        return ResponseEntity.ok(aiVendorService.updateVendor(aiVendor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(aiVendorService.deleteVendor(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<AiVendorDO>> search(@RequestParam String keyword) {
        return ResponseEntity.ok(aiVendorService.searchVendors(keyword));
    }
}