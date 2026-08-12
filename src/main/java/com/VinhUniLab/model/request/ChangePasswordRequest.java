package com.VinhUniLab.model.request;

import lombok.Data;

@Data
public class ChangePasswordRequest {
    private String currentPassword;
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;

    public String getOldPassword() {
        return oldPassword != null && !oldPassword.isBlank() ? oldPassword : currentPassword;
    }
}
