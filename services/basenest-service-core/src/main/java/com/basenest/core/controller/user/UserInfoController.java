package com.basenest.core.controller.user;

import com.basenest.core.dao.dos.UserInfoDO;
import com.basenest.core.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName UserInfoController
 * @Description User info controller
 * @Author happyboy
 * @Date 2026/11/11
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    private IUserInfoService userInfoService;

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody UserInfoDO userInfo) {
        return ResponseEntity.ok(userInfoService.addUser(userInfo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserInfoDO> getById(@PathVariable Long id) {
        UserInfoDO user = userInfoService.getUserById(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserInfoDO> getByUsername(@PathVariable String username) {
        UserInfoDO user = userInfoService.getUserByUsername(username);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<UserInfoDO>> getAll() {
        return ResponseEntity.ok(userInfoService.getAllUsers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@PathVariable Long id, @RequestBody UserInfoDO userInfo) {
        userInfo.setId(id);
        return ResponseEntity.ok(userInfoService.updateUser(userInfo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(userInfoService.deleteUser(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserInfoDO>> search(@RequestParam String keyword) {
        return ResponseEntity.ok(userInfoService.searchUsers(keyword));
    }
}