package com.VinhUniLab.model.dto;

import com.VinhUniLab.enums.UserRole;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String password; // Dùng khi tạo/cập nhật
    private String fullName;
    private String email;
    private UserRole role;
    private LocalDateTime createdAt;
}