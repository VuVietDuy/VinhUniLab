package com.VinhUniLab.controller;

import com.VinhUniLab.entity.IncidentReport;
import com.VinhUniLab.service.IncidentReportService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/incident-reports")
public class IncidentReportController extends BaseController<IncidentReport, IncidentReportService> {
    @GetMapping("/my")
    public ResponseEntity<?> getMyIncidents(Pageable pageable) {
        return ResponseEntity.ok(this.service.getMyIncidents(pageable));
    }

    @PatchMapping("/status")
    public ResponseEntity<?> changeStatus(@RequestBody IncidentReport incidentReport) {
        return ResponseEntity.ok(this.service.changeStatus(incidentReport));
    }

}
