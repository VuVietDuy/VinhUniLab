package com.VinhUniLab.entity;

import com.VinhUniLab.enums.IncidentPriority;
import com.VinhUniLab.enums.IncidentStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;

@Entity
@Table(name = "incident_reports")
@Data
@NoArgsConstructor
public class IncidentReport extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "computer_id")
    private Computer computer;

    @Column(name = "computer_id", insertable = false, updatable = false)
    private Long computerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reported_by")
    private User reportedBy;

    @Column(name = "reported_by", insertable = false, updatable = false)
    private Long reportedById;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "technician_id")
    private User technician;

    @Column(nullable = false)
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "incident_priority")
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private IncidentPriority priority = IncidentPriority.NORMAL;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "incident_status")
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private IncidentStatus status = IncidentStatus.OPEN;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "resolved_at")
    private LocalDateTime resolvedAt;
}