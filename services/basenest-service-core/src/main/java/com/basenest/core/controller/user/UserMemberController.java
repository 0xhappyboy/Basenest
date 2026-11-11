package com.basenest.core.controller.user;

import com.basenest.core.dao.dos.UserMemberDO;
import com.basenest.core.service.IUserMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName UserMemberController
 * @Description User member controller
 * @Author happyboy
 * @Date 2026/11/11
 * @Version 1.0
 */
@RestController
@RequestMapping("/user/member")
public class UserMemberController {

    @Autowired
    private IUserMemberService userMemberService;

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody UserMemberDO userMember) {
        return ResponseEntity.ok(userMemberService.addMember(userMember));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserMemberDO> getById(@PathVariable Long id) {
        UserMemberDO member = userMemberService.getMemberById(id);
        return member != null ? ResponseEntity.ok(member) : ResponseEntity.notFound().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserMemberDO> getByUserId(@PathVariable Long userId) {
        UserMemberDO member = userMemberService.getMemberByUserId(userId);
        return member != null ? ResponseEntity.ok(member) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@PathVariable Long id, @RequestBody UserMemberDO userMember) {
        userMember.setId(id);
        return ResponseEntity.ok(userMemberService.updateMember(userMember));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(userMemberService.deleteMember(id));
    }
}