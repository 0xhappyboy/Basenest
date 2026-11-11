package com.basenest.core.controller.tenant;

import com.basenest.core.dao.dos.tenant.TenantUserDO;
import com.basenest.core.service.ITenantUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName TenantUserController
 * @Description Tenant-user relation controller
 * @Author happyboy
 * @Date 2026/11/11
 * @Version 1.0
 */
@RestController
@RequestMapping("/tenant/user")
public class TenantUserController {

    @Autowired
    private ITenantUserService tenantUserService;

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody TenantUserDO tenantUser) {
        return ResponseEntity.ok(tenantUserService.addRelation(tenantUser));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TenantUserDO> getById(@PathVariable Long id) {
        TenantUserDO relation = tenantUserService.getRelationById(id);
        return relation != null ? ResponseEntity.ok(relation) : ResponseEntity.notFound().build();
    }

    @GetMapping("/tenant/{tenantId}")
    public ResponseEntity<List<TenantUserDO>> getByTenantId(@PathVariable Long tenantId) {
        return ResponseEntity.ok(tenantUserService.getByTenantId(tenantId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TenantUserDO>> getByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(tenantUserService.getByUserId(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@PathVariable Long id, @RequestBody TenantUserDO tenantUser) {
        tenantUser.setId(id);
        return ResponseEntity.ok(tenantUserService.updateRelation(tenantUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(tenantUserService.deleteRelation(id));
    }
}