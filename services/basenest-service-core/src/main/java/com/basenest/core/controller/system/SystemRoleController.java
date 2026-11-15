package com.basenest.core.controller.system;

import com.basenest.core.dao.dos.system.SystemRoleDO;
import com.basenest.core.service.ISystemRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName SystemRoleController
 * @Description System role controller
 * @Author happyboy
 * @Date 2026/11/16
 * @Version 1.0
 */
@RestController
@RequestMapping("/system/role")
@CrossOrigin(origins = "*")
public class SystemRoleController {

    @Autowired
    private ISystemRoleService systemRoleService;

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody SystemRoleDO role) {
        return ResponseEntity.ok(systemRoleService.addRole(role));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SystemRoleDO> getById(@PathVariable Long id) {
        SystemRoleDO role = systemRoleService.getRoleById(id);
        return role != null ? ResponseEntity.ok(role) : ResponseEntity.notFound().build();
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<SystemRoleDO> getByCode(@PathVariable String code) {
        SystemRoleDO role = systemRoleService.getRoleByCode(code);
        return role != null ? ResponseEntity.ok(role) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<SystemRoleDO>> getAll() {
        return ResponseEntity.ok(systemRoleService.getAllRoles());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@PathVariable Long id, @RequestBody SystemRoleDO role) {
        role.setId(id);
        return ResponseEntity.ok(systemRoleService.updateRole(role));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(systemRoleService.deleteRole(id));
    }

    // ----- role-permission -----

    @GetMapping("/{id}/permissions")
    public ResponseEntity<List<String>> getPermissionCodes(@PathVariable Long id) {
        return ResponseEntity.ok(systemRoleService.getPermissionCodesByRoleId(id));
    }

    @PutMapping("/{id}/permissions")
    public ResponseEntity<Boolean> assignPermissions(@PathVariable Long id,
                                                     @RequestBody List<String> permissionCodes) {
        return ResponseEntity.ok(systemRoleService.assignPermissions(id, permissionCodes));
    }

    @PostMapping("/{id}/permissions")
    public ResponseEntity<Boolean> addPermission(@PathVariable Long id,
                                                 @RequestParam String permissionCode) {
        return ResponseEntity.ok(systemRoleService.addPermission(id, permissionCode));
    }

    @DeleteMapping("/{id}/permissions")
    public ResponseEntity<Boolean> removePermission(@PathVariable Long id,
                                                    @RequestParam String permissionCode) {
        return ResponseEntity.ok(systemRoleService.removePermission(id, permissionCode));
    }
}