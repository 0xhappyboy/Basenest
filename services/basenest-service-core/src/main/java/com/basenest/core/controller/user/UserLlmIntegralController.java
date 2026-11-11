package com.basenest.core.controller.user;

import com.basenest.core.dao.dos.UserLlmIntegralDO;
import com.basenest.core.service.IUserLlmIntegralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName UserLlmIntegralController
 * @Description User LLM integral controller
 * @Author happyboy
 * @Date 2026/11/11
 * @Version 1.0
 */
@RestController
@RequestMapping("/user/llmintegral")
public class UserLlmIntegralController {

    @Autowired
    private IUserLlmIntegralService userLlmIntegralService;

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody UserLlmIntegralDO integral) {
        return ResponseEntity.ok(userLlmIntegralService.addIntegral(integral));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserLlmIntegralDO> getById(@PathVariable Long id) {
        UserLlmIntegralDO integral = userLlmIntegralService.getIntegralById(id);
        return integral != null ? ResponseEntity.ok(integral) : ResponseEntity.notFound().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserLlmIntegralDO> getByUserId(@PathVariable Long userId) {
        UserLlmIntegralDO integral = userLlmIntegralService.getIntegralByUserId(userId);
        return integral != null ? ResponseEntity.ok(integral) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@PathVariable Long id, @RequestBody UserLlmIntegralDO integral) {
        integral.setId(id);
        return ResponseEntity.ok(userLlmIntegralService.updateIntegral(integral));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(userLlmIntegralService.deleteIntegral(id));
    }
}