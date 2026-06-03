package com.VinhUniLab.service;

import com.VinhUniLab.model.dto.AdminStatsDTO;
import com.VinhUniLab.model.dto.TeacherStatsDTO;
import com.VinhUniLab.model.dto.TechnicianStatsDTO;

public interface StatsService {
    AdminStatsDTO getAdminOverview();
    TeacherStatsDTO getTeacherOverview();
    TechnicianStatsDTO getTechnicianOverview();
}
