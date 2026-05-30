package com.VinhUniLab.service.impl;

import com.VinhUniLab.entity.IncidentReport;
import com.VinhUniLab.entity.User;
import com.VinhUniLab.enums.IncidentStatus;
import com.VinhUniLab.enums.UserRole;
import com.VinhUniLab.model.request.AssignTechnicianReq;
import com.VinhUniLab.model.request.SearchReq;
import com.VinhUniLab.repository.IncidentReportRepository;
import com.VinhUniLab.repository.UserRepository;
import com.VinhUniLab.service.IncidentReportService;
import com.VinhUniLab.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class IncidentReportServiceImpl extends BaseServiceImpl<IncidentReport, IncidentReportRepository> implements IncidentReportService {
    private final UserRepository userRepository;

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
        if (req.getStatus().equals(IncidentStatus.RESOLVED)) {
            e.setResolvedAt(LocalDateTime.now());
        }
        return repository.save(e);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IncidentReport assignTechnician(AssignTechnicianReq req) {
        IncidentReport incidentReport = repository.findById(req.getId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy báo cáo sự cố"));
        User technician = userRepository.findById(req.getTechnicianId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy kỹ thuật viên"));

        if (technician.getRole() != UserRole.TECHNICIAN) {
            throw new RuntimeException("Người dùng được chọn không phải kỹ thuật viên");
        }

        incidentReport.setTechnician(technician);
        incidentReport.setStatus(IncidentStatus.IN_PROGRESS);
        return repository.save(incidentReport);
    }

    @Override
    public Page<IncidentReport> getAssignedToCurrentTechnician(SearchReq req) {
        User currentUser = Objects.requireNonNull(SecurityUtils.getCurrentUser()).getUser();
        if (currentUser.getRole() != UserRole.TECHNICIAN) {
            throw new RuntimeException("Nguoi dung hien tai khong phai ky thuat vien");
        }

        SearchReq searchReq = normalizeSearchReq(req);
        String assignedFilter = "technician.id==" + currentUser.getId();
        if (searchReq.getFilter() == null || searchReq.getFilter().isBlank()) {
            searchReq.setFilter(assignedFilter);
        } else {
            searchReq.setFilter("(" + searchReq.getFilter() + ");" + assignedFilter);
        }

        return search(searchReq);
    }

    private SearchReq normalizeSearchReq(SearchReq req) {
        SearchReq searchReq = Objects.requireNonNullElseGet(req, SearchReq::new);
        if (searchReq.getPage() == null) {
            searchReq.setPage(0);
        }
        if (searchReq.getSize() == null) {
            searchReq.setSize(20);
        }
        if (searchReq.getSort() == null || searchReq.getSort().isBlank()) {
            searchReq.setSort("createdAt,desc");
        }
        return searchReq;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IncidentReport create(IncidentReport t) {
        t.setReportedBy(Objects.requireNonNull(SecurityUtils.getCurrentUser()).getUser());
        return this.repository.save(t);
    }
}
