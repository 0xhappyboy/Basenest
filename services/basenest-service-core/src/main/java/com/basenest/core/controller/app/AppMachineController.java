package com.basenest.core.controller.app;

import com.basenest.core.dao.dos.app.AppMachineDO;
import com.basenest.core.service.IAppMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName AppMachineController
 * @Description App machine controller
 * @Author happyboy
 * @Date 2026/11/11
 * @Version 1.0
 */
@RestController
@RequestMapping("/app/machine")
public class AppMachineController {

    @Autowired
    private IAppMachineService appMachineService;

    @PostMapping("/report")
    public ResponseEntity<Long> report(@RequestBody AppMachineDO machine) {
        return ResponseEntity.ok(appMachineService.reportMachine(machine));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppMachineDO> getById(@PathVariable Long id) {
        AppMachineDO machine = appMachineService.getMachineById(id);
        return machine != null ? ResponseEntity.ok(machine) : ResponseEntity.notFound().build();
    }

    @GetMapping("/app/{appId}")
    public ResponseEntity<List<AppMachineDO>> getByAppId(@PathVariable Long appId) {
        return ResponseEntity.ok(appMachineService.getMachinesByAppId(appId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AppMachineDO>> getByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(appMachineService.getMachinesByUserId(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@PathVariable Long id, @RequestBody AppMachineDO machine) {
        machine.setId(id);
        return ResponseEntity.ok(appMachineService.updateMachine(machine));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(appMachineService.deleteMachine(id));
    }
}