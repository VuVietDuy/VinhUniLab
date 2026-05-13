package com.VinhUniLab.controller;

import com.VinhUniLab.entity.IncidentReport;
import com.VinhUniLab.service.IncidentReportService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/incident-reports")
public class IncidentReportController extends BaseController<IncidentReport, IncidentReportService> {
    @GetMapping("/my")
    public ResponseEntity<?> getMyIncidents(Pageable pageable) {
        return ResponseEntity.ok(this.service.getMyIncidents(pageable));
    }

}
