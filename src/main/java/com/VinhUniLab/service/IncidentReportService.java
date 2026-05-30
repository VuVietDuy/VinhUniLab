package com.VinhUniLab.service;

import com.VinhUniLab.entity.IncidentReport;
import com.VinhUniLab.model.request.AssignTechnicianReq;
import com.VinhUniLab.model.request.SearchReq;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IncidentReportService extends BaseService<IncidentReport> {
    Page<IncidentReport> getMyIncidents(Pageable pageable);
    IncidentReport changeStatus(IncidentReport req);
    IncidentReport assignTechnician(AssignTechnicianReq req);
    Page<IncidentReport> getAssignedToCurrentTechnician(SearchReq req);
}
