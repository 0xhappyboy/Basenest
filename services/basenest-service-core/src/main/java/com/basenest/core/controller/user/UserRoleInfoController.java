package com.basenest.core.controller.user;

import com.basenest.core.dao.dos.UserRoleInfoDO;
import com.basenest.core.service.IUserRoleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName UserRoleInfoController
 * @Description User role info controller
 * @Author happyboy
 * @Date 2026/11/11
 * @Version 1.0
 */
@RestController
@RequestMapping("/user/role")
public class UserRoleInfoController {

    @Autowired
    private IUserRoleInfoService userRoleInfoService;

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody UserRoleInfoDO role) {
        return ResponseEntity.ok(userRoleInfoService.addRole(role));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRoleInfoDO> getById(@PathVariable Long id) {
        UserRoleInfoDO role = userRoleInfoService.getRoleById(id);
        return role != null ? ResponseEntity.ok(role) : ResponseEntity.notFound().build();
    }

    @GetMapping("/tenant/{tenantId}")
    public ResponseEntity<List<UserRoleInfoDO>> getByTenantId(@PathVariable Long tenantId) {
        return ResponseEntity.ok(userRoleInfoService.getRolesByTenantId(tenantId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@PathVariable Long id, @RequestBody UserRoleInfoDO role) {
        role.setId(id);
        return ResponseEntity.ok(userRoleInfoService.updateRole(role));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(userRoleInfoService.deleteRole(id));
    }
}