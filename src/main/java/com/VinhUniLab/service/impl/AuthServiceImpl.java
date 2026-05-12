package com.VinhUniLab.service.impl;

import com.VinhUniLab.entity.User;
import com.VinhUniLab.model.request.LoginRequest;
import com.VinhUniLab.model.response.LoginResponse;
import com.VinhUniLab.repository.UserRepository;
import com.VinhUniLab.service.AuthService;
import com.VinhUniLab.utils.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider; // Class tự định nghĩa để tạo JWT

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        // 1. Kiểm tra username
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("Sai tên đăng nhập hoặc mật khẩu"));

        // 2. Kiểm tra password
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("Sai tên đăng nhập hoặc mật khẩu");
        }

        // 3. Tạo Access Token
        String token = tokenProvider.generateToken(user.getUsername(), user.getRole().name());

        return new LoginResponse(
                token,
                "Bearer",
                user.getUsername(),
                user.getRole().name()
        );
    }
}
