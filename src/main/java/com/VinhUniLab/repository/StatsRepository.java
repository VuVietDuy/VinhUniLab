package com.VinhUniLab.repository;

import com.VinhUniLab.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface StatsRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT COUNT(*) FROM users", nativeQuery = true)
    long countTotalUsers();

    @Query(value = "SELECT COUNT(*) FROM rooms WHERE is_active = true", nativeQuery = true)
    long countTotalRooms();

    @Query(value = "SELECT COUNT(*) FROM computers", nativeQuery = true)
    long countTotalComputers();

    @Query(value = "SELECT COUNT(*) FROM incident_reports WHERE status != 'RESOLVED'", nativeQuery = true)
    long countOpenIncidents();

    // Thống kê trạng thái sự cố cho biểu đồ tròn
    @Query(value = "SELECT status as type, COUNT(*) as value FROM incident_reports GROUP BY status", nativeQuery = true)
    List<Map<String, Object>> getIncidentStatusStats();

    // Thống kê số giờ sử dụng theo tháng (6 tháng gần nhất)
    @Query(value = "SELECT to_char(start_time, 'Month') as month, " +
            "SUM(EXTRACT(EPOCH FROM (end_time - start_time))/3600)::int as hours " +
            "FROM bookings WHERE status = 'APPROVED' " +
            "GROUP BY month ORDER BY month DESC LIMIT 6", nativeQuery = true)
    List<Map<String, Object>> getMonthlyUsageStats();

    @Query(value = "SELECT COUNT(*) FROM incident_reports WHERE technician_id = :technicianId", nativeQuery = true)
    long countAssignedIncidentsByTechnician(@Param("technicianId") Long technicianId);

    @Query(value = "SELECT COUNT(*) FROM incident_reports WHERE technician_id = :technicianId AND status = 'OPEN'", nativeQuery = true)
    long countOpenIncidentsByTechnician(@Param("technicianId") Long technicianId);

    @Query(value = "SELECT COUNT(*) FROM incident_reports WHERE technician_id = :technicianId AND status = 'IN_PROGRESS'", nativeQuery = true)
    long countInProgressIncidentsByTechnician(@Param("technicianId") Long technicianId);

    @Query(value = "SELECT COUNT(*) FROM incident_reports WHERE technician_id = :technicianId AND status = 'RESOLVED'", nativeQuery = true)
    long countResolvedIncidentsByTechnician(@Param("technicianId") Long technicianId);

    @Query(value = "SELECT COUNT(*) FROM incident_reports WHERE technician_id = :technicianId AND priority = 'HIGH' AND status != 'RESOLVED'", nativeQuery = true)
    long countHighPriorityIncidentsByTechnician(@Param("technicianId") Long technicianId);

    @Query(value = "SELECT status as type, COUNT(*) as value " +
            "FROM incident_reports WHERE technician_id = :technicianId " +
            "GROUP BY status", nativeQuery = true)
    List<Map<String, Object>> getIncidentStatusStatsByTechnician(@Param("technicianId") Long technicianId);

    @Query(value = "SELECT priority as type, COUNT(*) as value " +
            "FROM incident_reports WHERE technician_id = :technicianId " +
            "GROUP BY priority", nativeQuery = true)
    List<Map<String, Object>> getIncidentPriorityStatsByTechnician(@Param("technicianId") Long technicianId);

    @Query(value = "SELECT ir.id, ir.description, ir.priority, ir.status, ir.created_at as createdAt, " +
            "c.computer_code as computerCode, r.room_name as roomName " +
            "FROM incident_reports ir " +
            "LEFT JOIN computers c ON ir.computer_id = c.id " +
            "LEFT JOIN rooms r ON c.room_id = r.id " +
            "WHERE ir.technician_id = :technicianId " +
            "ORDER BY ir.created_at DESC LIMIT 5", nativeQuery = true)
    List<Map<String, Object>> getRecentIncidentsByTechnician(@Param("technicianId") Long technicianId);
}
