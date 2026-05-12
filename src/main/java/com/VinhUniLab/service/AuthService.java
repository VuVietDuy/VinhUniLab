package com.VinhUniLab.service;

import com.VinhUniLab.model.request.LoginRequest;
import com.VinhUniLab.model.response.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);
}
