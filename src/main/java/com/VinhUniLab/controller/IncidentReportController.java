package com.VinhUniLab.controller;

import com.VinhUniLab.entity.IncidentReport;
import com.VinhUniLab.service.IncidentReportService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/computers")
public class IncidentReportController extends BaseController<IncidentReport, IncidentReportService> {
}
