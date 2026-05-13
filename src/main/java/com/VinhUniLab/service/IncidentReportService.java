package com.VinhUniLab.service;

import com.VinhUniLab.entity.IncidentReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IncidentReportService extends BaseService<IncidentReport> {
    Page<IncidentReport> getMyIncidents(Pageable pageable);
}
