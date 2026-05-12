package com.VinhUniLab.model.dto;

import com.VinhUniLab.entity.Room;
import com.VinhUniLab.enums.ComputerStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

public class ComputerDTO {
    private Long id;

    private Room room;

    private String computerCode;

    private String ipAddress;

    private String macAddress;

    private ComputerStatus status = ComputerStatus.AVAILABLE;

    private LocalDateTime lastPing;
}
