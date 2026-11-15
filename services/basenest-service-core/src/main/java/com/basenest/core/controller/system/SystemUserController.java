package com.basenest.core.controller.system;

import com.basenest.core.dao.dos.system.SystemRoleDO;
import com.basenest.core.dao.dos.system.SystemUserDO;
import com.basenest.core.service.ISystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName SystemUserController
 * @Description System user controller
 * @Author happyboy
 * @Date 2026/11/16
 * @Version 1.0
 */
@RestController
@RequestMapping("/system/user")
public class SystemUserController {

    @Autowired
    private ISystemUserService systemUserService;

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody SystemUserDO user) {
        return ResponseEntity.ok(systemUserService.addUser(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SystemUserDO> getById(@PathVariable Long id) {
        SystemUserDO user = systemUserService.getUserById(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<SystemUserDO> getByUsername(@PathVariable String username) {
        SystemUserDO user = systemUserService.getUserByUsername(username);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<SystemUserDO>> getAll() {
        return ResponseEntity.ok(systemUserService.getAllUsers());
    }

    @GetMapping("/search")
    public ResponseEntity<List<SystemUserDO>> search(@RequestParam String keyword) {
        return ResponseEntity.ok(systemUserService.searchUsers(keyword));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@PathVariable Long id, @RequestBody SystemUserDO user) {
        user.setId(id);
        return ResponseEntity.ok(systemUserService.updateUser(user));
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<Boolean> changePassword(@PathVariable Long id,
                                                  @RequestParam String password) {
        return ResponseEntity.ok(systemUserService.changePassword(id, password));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(systemUserService.deleteUser(id));
    }

    // ----- user-role -----

    @GetMapping("/{id}/roles")
    public ResponseEntity<List<Long>> getRoleIds(@PathVariable Long id) {
        return ResponseEntity.ok(systemUserService.getRoleIdsByUserId(id));
    }

    @GetMapping("/{id}/role-details")
    public ResponseEntity<List<SystemRoleDO>> getRoleDetails(@PathVariable Long id) {
        return ResponseEntity.ok(systemUserService.getRolesByUserId(id));
    }

    @PutMapping("/{id}/roles")
    public ResponseEntity<Boolean> assignRoles(@PathVariable Long id,
                                               @RequestBody List<Long> roleIds) {
        return ResponseEntity.ok(systemUserService.assignRoles(id, roleIds));
    }

    // ----- user-permission -----

    @GetMapping("/{id}/permissions")
    public ResponseEntity<List<String>> getPermissionCodes(@PathVariable Long id) {
        return ResponseEntity.ok(systemUserService.getUserPermissionCodes(id));
    }

    @GetMapping("/{id}/permissions/direct")
    public ResponseEntity<List<String>> getDirectPermissionCodes(@PathVariable Long id) {
        return ResponseEntity.ok(systemUserService.getDirectPermissionCodes(id));
    }

    @PostMapping("/{id}/permissions/grant")
    public ResponseEntity<Boolean> grantPermission(@PathVariable Long id,
                                                   @RequestParam String permissionCode) {
        return ResponseEntity.ok(systemUserService.grantPermission(id, permissionCode));
    }

    @PostMapping("/{id}/permissions/deny")
    public ResponseEntity<Boolean> denyPermission(@PathVariable Long id,
                                                  @RequestParam String permissionCode) {
        return ResponseEntity.ok(systemUserService.denyPermission(id, permissionCode));
    }

    @DeleteMapping("/{id}/permissions")
    public ResponseEntity<Boolean> removePermission(@PathVariable Long id,
                                                    @RequestParam String permissionCode) {
        return ResponseEntity.ok(systemUserService.removeUserPermission(id, permissionCode));
    }
}