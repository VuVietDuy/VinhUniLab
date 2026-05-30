package com.VinhUniLab.controller;

import com.VinhUniLab.entity.IncidentReport;
import com.VinhUniLab.model.request.AssignTechnicianReq;
import com.VinhUniLab.model.request.SearchReq;
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

    @PatchMapping("/assign")
    public ResponseEntity<?> assignTechnician(@RequestBody AssignTechnicianReq req) {
        return ResponseEntity.ok(this.service.assignTechnician(req));
    }

    @GetMapping("/assigned-to-me")
    public ResponseEntity<?> getAssignedToMe(SearchReq req) {
        return ResponseEntity.ok(this.service.getAssignedToCurrentTechnician(req));
    }

}
