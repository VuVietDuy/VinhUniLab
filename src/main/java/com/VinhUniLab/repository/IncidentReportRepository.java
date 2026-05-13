package com.VinhUniLab.repository;

import com.VinhUniLab.entity.IncidentReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IncidentReportRepository extends BaseRepository<IncidentReport> {
    Page<IncidentReport> findAllByReportedById(Long reportedById, Pageable pageable);
}
