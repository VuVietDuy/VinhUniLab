package com.VinhUniLab.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherStatsDTO {
    private long totalBookings;
    private long pendingBookings;
    private long approvedBookings;
    private long rejectedBookings;
    private long cancelledBookings;
    private long upcomingBookings;
    private long reportedIncidents;
    private long openIncidents;
    private long inProgressIncidents;
    private long resolvedIncidents;
    private long highPriorityOpenIncidents;
    private List<Map<String, Object>> bookingStatusData;
    private List<Map<String, Object>> incidentStatusData;
    private List<Map<String, Object>> upcomingBookingData;
    private List<Map<String, Object>> recentIncidentData;
}
