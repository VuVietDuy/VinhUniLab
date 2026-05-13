package com.VinhUniLab.controller;

import com.VinhUniLab.model.dto.AdminStatsDTO;
import com.VinhUniLab.service.StatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stats")
@RequiredArgsConstructor
public class StatsController {
    private final StatsService statsService;

    @GetMapping("/admin")
    public ResponseEntity<AdminStatsDTO> getStats() {
        return ResponseEntity.ok(statsService.getAdminOverview());
    }
}
