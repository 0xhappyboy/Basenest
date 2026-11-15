package com.basenest.core.controller.system;

import com.basenest.core.dao.dos.system.SystemMenuDO;
import com.basenest.core.service.ISystemMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName SystemMenuController
 * @Description System menu controller
 * @Author happyboy
 * @Date 2026/11/16
 * @Version 1.0
 */
@RestController
@RequestMapping("/system/menu")
public class SystemMenuController {

    @Autowired
    private ISystemMenuService systemMenuService;

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody SystemMenuDO menu) {
        return ResponseEntity.ok(systemMenuService.addMenu(menu));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SystemMenuDO> getById(@PathVariable Long id) {
        SystemMenuDO menu = systemMenuService.getMenuById(id);
        return menu != null ? ResponseEntity.ok(menu) : ResponseEntity.notFound().build();
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<SystemMenuDO> getByCode(@PathVariable String code) {
        SystemMenuDO menu = systemMenuService.getMenuByCode(code);
        return menu != null ? ResponseEntity.ok(menu) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<SystemMenuDO>> getAll() {
        return ResponseEntity.ok(systemMenuService.getAllMenus());
    }

    @GetMapping("/parent/{parentId}")
    public ResponseEntity<List<SystemMenuDO>> getByParentId(@PathVariable Long parentId) {
        return ResponseEntity.ok(systemMenuService.getMenusByParentId(parentId));
    }

    @GetMapping("/permission/{permission}")
    public ResponseEntity<List<SystemMenuDO>> getByPermission(@PathVariable String permission) {
        return ResponseEntity.ok(systemMenuService.getMenusByPermission(permission));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SystemMenuDO>> getByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(systemMenuService.getMenusByUserId(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@PathVariable Long id, @RequestBody SystemMenuDO menu) {
        menu.setId(id);
        return ResponseEntity.ok(systemMenuService.updateMenu(menu));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(systemMenuService.deleteMenu(id));
    }
}