package com.VinhUniLab.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TechnicianStatsDTO {
    private long assignedIncidents;
    private long openIncidents;
    private long inProgressIncidents;
    private long resolvedIncidents;
    private long highPriorityIncidents;
    private List<Map<String, Object>> incidentStatusData;
    private List<Map<String, Object>> incidentPriorityData;
    private List<Map<String, Object>> recentIncidents;
}
