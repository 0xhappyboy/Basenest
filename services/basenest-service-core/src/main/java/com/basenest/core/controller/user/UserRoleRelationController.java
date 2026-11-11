package com.basenest.core.controller.user;

import com.basenest.core.dao.dos.UserRoleRelationDO;
import com.basenest.core.service.IUserRoleRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName UserRoleRelationController
 * @Description User role relation controller
 * @Author happyboy
 * @Date 2026/11/11
 * @Version 1.0
 */
@RestController
@RequestMapping("/user/rolerelation")
public class UserRoleRelationController {

    @Autowired
    private IUserRoleRelationService userRoleRelationService;

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody UserRoleRelationDO relation) {
        return ResponseEntity.ok(userRoleRelationService.addRelation(relation));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRoleRelationDO> getById(@PathVariable Long id) {
        UserRoleRelationDO relation = userRoleRelationService.getRelationById(id);
        return relation != null ? ResponseEntity.ok(relation) : ResponseEntity.notFound().build();
    }

    @GetMapping("/tenant/{tenantId}/user/{userId}")
    public ResponseEntity<List<UserRoleRelationDO>> getRelations(@PathVariable Long tenantId,
                                                                 @PathVariable Long userId) {
        return ResponseEntity.ok(userRoleRelationService.getRelations(tenantId, userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@PathVariable Long id, @RequestBody UserRoleRelationDO relation) {
        relation.setId(id);
        return ResponseEntity.ok(userRoleRelationService.updateRelation(relation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(userRoleRelationService.deleteRelation(id));
    }
}