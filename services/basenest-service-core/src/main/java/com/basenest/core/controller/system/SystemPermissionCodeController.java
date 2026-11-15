package com.basenest.core.controller.system;

import com.basenest.core.dao.dos.system.SystemPermissionCodeDO;
import com.basenest.core.service.ISystemPermissionCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName SystemPermissionCodeController
 * @Description System permission code controller
 * @Author happyboy
 * @Date 2026/11/16
 * @Version 1.0
 */
@RestController
@RequestMapping("/system/permission-code")
@CrossOrigin(origins = "*")
public class SystemPermissionCodeController {

    @Autowired
    private ISystemPermissionCodeService systemPermissionCodeService;

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody SystemPermissionCodeDO permissionCode) {
        return ResponseEntity.ok(systemPermissionCodeService.addPermissionCode(permissionCode));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SystemPermissionCodeDO> getById(@PathVariable Long id) {
        SystemPermissionCodeDO pc = systemPermissionCodeService.getPermissionCodeById(id);
        return pc != null ? ResponseEntity.ok(pc) : ResponseEntity.notFound().build();
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<SystemPermissionCodeDO> getByCode(@PathVariable String code) {
        SystemPermissionCodeDO pc = systemPermissionCodeService.getPermissionCodeByCode(code);
        return pc != null ? ResponseEntity.ok(pc) : ResponseEntity.notFound().build();
    }

    @GetMapping("/module/{module}")
    public ResponseEntity<List<SystemPermissionCodeDO>> getByModule(@PathVariable String module) {
        return ResponseEntity.ok(systemPermissionCodeService.getPermissionCodesByModule(module));
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<SystemPermissionCodeDO>> getByType(@PathVariable String type) {
        return ResponseEntity.ok(systemPermissionCodeService.getPermissionCodesByType(type));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@PathVariable Long id,
                                          @RequestBody SystemPermissionCodeDO permissionCode) {
        permissionCode.setId(id);
        return ResponseEntity.ok(systemPermissionCodeService.updatePermissionCode(permissionCode));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(systemPermissionCodeService.deletePermissionCode(id));
    }
}