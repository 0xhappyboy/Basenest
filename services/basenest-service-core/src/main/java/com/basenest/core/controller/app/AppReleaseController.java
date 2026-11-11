package com.basenest.core.controller.app;

import com.basenest.core.dao.dos.app.AppReleaseDO;
import com.basenest.core.service.IAppReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName AppReleaseController
 * @Description App release controller
 * @Author happyboy
 * @Date 2026/11/11
 * @Version 1.0
 */
@RestController
@RequestMapping("/app/release")
public class AppReleaseController {

    @Autowired
    private IAppReleaseService appReleaseService;

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody AppReleaseDO release) {
        return ResponseEntity.ok(appReleaseService.addRelease(release));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppReleaseDO> getById(@PathVariable Long id) {
        AppReleaseDO release = appReleaseService.getReleaseById(id);
        return release != null ? ResponseEntity.ok(release) : ResponseEntity.notFound().build();
    }

    @GetMapping("/app/{appId}")
    public ResponseEntity<List<AppReleaseDO>> getByAppId(@PathVariable Long appId) {
        return ResponseEntity.ok(appReleaseService.getReleasesByAppId(appId));
    }

    @GetMapping("/latest")
    public ResponseEntity<AppReleaseDO> getLatest(@RequestParam Long appId,
                                                  @RequestParam String platform,
                                                  @RequestParam(defaultValue = "stable") String channel) {
        AppReleaseDO release = appReleaseService.getLatestRelease(appId, platform, channel);
        return release != null ? ResponseEntity.ok(release) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@PathVariable Long id, @RequestBody AppReleaseDO release) {
        release.setId(id);
        return ResponseEntity.ok(appReleaseService.updateRelease(release));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(appReleaseService.deleteRelease(id));
    }
}