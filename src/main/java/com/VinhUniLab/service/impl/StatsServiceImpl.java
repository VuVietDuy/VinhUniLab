package com.VinhUniLab.service.impl;

import com.VinhUniLab.model.dto.AdminStatsDTO;
import com.VinhUniLab.repository.StatsRepository;
import com.VinhUniLab.service.StatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
