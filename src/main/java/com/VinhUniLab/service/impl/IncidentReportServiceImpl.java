package com.VinhUniLab.service.impl;

import com.VinhUniLab.entity.IncidentReport;
import com.VinhUniLab.repository.IncidentReportRepository;
import com.VinhUniLab.service.IncidentReportService;
import com.VinhUniLab.utils.SecurityUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class IncidentReportServiceImpl extends BaseServiceImpl<IncidentReport, IncidentReportRepository> implements IncidentReportService {
    @Override
    public Page<IncidentReport> getMyIncidents(Pageable pageable) {
        Long userId = Objects.requireNonNull(SecurityUtils.getCurrentUser()).getUserId();
        return this.repository.findAllByReportedById(userId, pageable);
    }

    @Override
    public IncidentReport changeStatus(IncidentReport req) {
        IncidentReport e = repository.findById(req.getId()).orElse(null);
        if (Objects.isNull(e)) {
            return null;
        }

        e.setStatus(req.getStatus());
        return repository.save(e);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IncidentReport create(IncidentReport t) {
        t.setReportedBy(Objects.requireNonNull(SecurityUtils.getCurrentUser()).getUser());
        return this.repository.save(t);
    }
}
