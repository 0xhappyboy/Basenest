package com.basenest.core.controller.tenant;

import com.basenest.core.dao.dos.tenant.TenantInfoDO;
import com.basenest.core.service.ITenantInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName TenantInfoController
 * @Description Tenant info controller
 * @Author happyboy
 * @Date 2026/11/11
 * @Version 1.0
 */
@RestController
@RequestMapping("/tenant")
public class TenantInfoController {

    @Autowired
    private ITenantInfoService tenantInfoService;

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody TenantInfoDO tenantInfo) {
        return ResponseEntity.ok(tenantInfoService.addTenant(tenantInfo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TenantInfoDO> getById(@PathVariable Long id) {
        TenantInfoDO tenant = tenantInfoService.getTenantById(id);
        return tenant != null ? ResponseEntity.ok(tenant) : ResponseEntity.notFound().build();
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<TenantInfoDO> getByCode(@PathVariable String code) {
        TenantInfoDO tenant = tenantInfoService.getTenantByCode(code);
        return tenant != null ? ResponseEntity.ok(tenant) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<TenantInfoDO>> getAll() {
        return ResponseEntity.ok(tenantInfoService.getAllTenants());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@PathVariable Long id, @RequestBody TenantInfoDO tenantInfo) {
        tenantInfo.setId(id);
        return ResponseEntity.ok(tenantInfoService.updateTenant(tenantInfo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(tenantInfoService.deleteTenant(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<TenantInfoDO>> search(@RequestParam String keyword) {
        return ResponseEntity.ok(tenantInfoService.searchTenants(keyword));
    }
}