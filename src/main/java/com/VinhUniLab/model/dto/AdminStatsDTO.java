package com.VinhUniLab.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminStatsDTO {
    private long totalUsers;
    private long totalRooms;
    private long totalComputers;
    private long openIncidents;
    private List<Map<String, Object>> incidentStatusData; // Dữ liệu biểu đồ tròn
    private List<Map<String, Object>> monthlyUsageData;  // Dữ liệu biểu đồ cột
}