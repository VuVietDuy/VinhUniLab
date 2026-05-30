package com.VinhUniLab.service.impl;

import com.VinhUniLab.entity.User;
import com.VinhUniLab.enums.UserRole;
import com.VinhUniLab.model.dto.AdminStatsDTO;
import com.VinhUniLab.model.dto.TechnicianStatsDTO;
import com.VinhUniLab.repository.StatsRepository;
import com.VinhUniLab.service.StatsService;
import com.VinhUniLab.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService {
    private final StatsRepository statsRepository;

    @Override
    @Transactional(readOnly = true)
    public AdminStatsDTO getAdminOverview() {
        AdminStatsDTO dto = new AdminStatsDTO();

        dto.setTotalUsers(statsRepository.countTotalUsers());
        dto.setTotalRooms(statsRepository.countTotalRooms());
        dto.setTotalComputers(statsRepository.countTotalComputers());
        dto.setOpenIncidents(statsRepository.countOpenIncidents());

        dto.setIncidentStatusData(statsRepository.getIncidentStatusStats());
        dto.setMonthlyUsageData(statsRepository.getMonthlyUsageStats());

        return dto;
    }

    @Override
    @Transactional(readOnly = true)
    public TechnicianStatsDTO getTechnicianOverview() {
        User currentUser = Objects.requireNonNull(SecurityUtils.getCurrentUser()).getUser();
        if (currentUser.getRole() != UserRole.TECHNICIAN) {
            throw new RuntimeException("Nguoi dung hien tai khong phai ky thuat vien");
        }

        Long technicianId = currentUser.getId();
        TechnicianStatsDTO dto = new TechnicianStatsDTO();

        dto.setAssignedIncidents(statsRepository.countAssignedIncidentsByTechnician(technicianId));
        dto.setOpenIncidents(statsRepository.countOpenIncidentsByTechnician(technicianId));
        dto.setInProgressIncidents(statsRepository.countInProgressIncidentsByTechnician(technicianId));
        dto.setResolvedIncidents(statsRepository.countResolvedIncidentsByTechnician(technicianId));
        dto.setHighPriorityIncidents(statsRepository.countHighPriorityIncidentsByTechnician(technicianId));
        dto.setIncidentStatusData(statsRepository.getIncidentStatusStatsByTechnician(technicianId));
        dto.setIncidentPriorityData(statsRepository.getIncidentPriorityStatsByTechnician(technicianId));
        dto.setRecentIncidents(statsRepository.getRecentIncidentsByTechnician(technicianId));

        return dto;
    }
}
