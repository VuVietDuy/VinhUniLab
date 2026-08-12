package com.VinhUniLab.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Entity
@Table(name = "time_slots")
@Data
@NoArgsConstructor
public class TimeSlot extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "slot_name")
    private String slotName;

    @NotNull(message = "Giờ bắt đầu không được để trống")
    @Column(name = "start_time", nullable = false)
    private LocalTime startTime; // ví dụ: 07:00

    @NotNull(message = "Giờ kết thúc không được để trống")
    @Column(name = "end_time", nullable = false)
    private LocalTime endTime; // ví dụ: 07:45
}
