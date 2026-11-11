package com.basenest.core.controller.app;

import com.basenest.core.dao.dos.app.AppInfoDO;
import com.basenest.core.service.IAppInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName AppInfoController
 * @Description App info controller
 * @Author happyboy
 * @Date 2026/11/11
 * @Version 1.0
 */
@RestController
@RequestMapping("/app/info")
public class AppInfoController {

    @Autowired
    private IAppInfoService appInfoService;

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody AppInfoDO appInfo) {
        return ResponseEntity.ok(appInfoService.addApp(appInfo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppInfoDO> getById(@PathVariable Long id) {
        AppInfoDO app = appInfoService.getAppById(id);
        return app != null ? ResponseEntity.ok(app) : ResponseEntity.notFound().build();
    }

    @GetMapping("/key/{appKey}")
    public ResponseEntity<AppInfoDO> getByAppKey(@PathVariable String appKey) {
        AppInfoDO app = appInfoService.getAppByAppKey(appKey);
        return app != null ? ResponseEntity.ok(app) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<AppInfoDO>> getAll() {
        return ResponseEntity.ok(appInfoService.getAllApps());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@PathVariable Long id, @RequestBody AppInfoDO appInfo) {
        appInfo.setId(id);
        return ResponseEntity.ok(appInfoService.updateApp(appInfo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(appInfoService.deleteApp(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<AppInfoDO>> search(@RequestParam String keyword) {
        return ResponseEntity.ok(appInfoService.searchApps(keyword));
    }
}