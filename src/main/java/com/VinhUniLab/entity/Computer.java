package com.VinhUniLab.entity;

import com.VinhUniLab.enums.ComputerStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "computers")
@Data
@NoArgsConstructor
public class Computer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @Column(name = "computer_code", nullable = false, length = 20)
    private String computerCode;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "mac_address")
    private String macAddress;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "computer_status")
    private ComputerStatus status = ComputerStatus.AVAILABLE;

    @Column(name = "last_ping")
    private LocalDateTime lastPing;
}
