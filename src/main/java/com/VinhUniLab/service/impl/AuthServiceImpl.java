package com.VinhUniLab.service.impl;

import com.VinhUniLab.config.CustomUserDetails;
import com.VinhUniLab.entity.User;
import com.VinhUniLab.exception.UnSuccessException;
import com.VinhUniLab.model.request.ChangePasswordRequest;
import com.VinhUniLab.model.request.LoginRequest;
import com.VinhUniLab.model.response.LoginResponse;
import com.VinhUniLab.repository.UserRepository;
import com.VinhUniLab.service.AuthService;
import com.VinhUniLab.service.JwtService;
import com.VinhUniLab.utils.JwtTokenProvider;
import com.VinhUniLab.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider; // Class tự định nghĩa để tạo JWT
    private final JwtService jwtService; // Class tự định nghĩa để tạo JWT

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        // 1. Kiểm tra username
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new UnSuccessException("Sai tên đăng nhập hoặc mật khẩu", 400));

        // 2. Kiểm tra password
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPasswordHash())) {
            throw new UnSuccessException("Sai tên đăng nhập hoặc mật khẩu", 400);
        }

        // 3. Tạo Access Token
//        String token = tokenProvider.generateToken(user.getUsername(), user.getRole().name());
        var jwtToken = jwtService.generateToken(new CustomUserDetails(user));

        return new LoginResponse(
                jwtToken,
                "Bearer",
                user.getUsername(),
                user.getRole().name()
        );
    }

    @Override
    @Transactional
    public void changePassword(ChangePasswordRequest request) {
        CustomUserDetails currentUser = SecurityUtils.getCurrentUser();
        if (currentUser == null) {
            throw new UnSuccessException("Vui lòng đăng nhập để thực hiện chức năng này", 401);
        }

        User user = userRepository.findById(currentUser.getUserId())
                .orElseThrow(() -> new UnSuccessException("Không tìm thấy người dùng", 404));

        String oldPass = request.getOldPassword();
        if (oldPass == null || oldPass.isBlank()) {
            throw new UnSuccessException("Mật khẩu hiện tại không được để trống", 400);
        }

        if (!passwordEncoder.matches(oldPass, user.getPasswordHash())) {
            throw new UnSuccessException("Mật khẩu hiện tại không chính xác", 400);
        }

        if (request.getNewPassword() == null || request.getNewPassword().isBlank()) {
            throw new UnSuccessException("Mật khẩu mới không được để trống", 400);
        }

        if (request.getNewPassword().length() < 6) {
            throw new UnSuccessException("Mật khẩu mới phải có ít nhất 6 ký tự", 400);
        }

        if (request.getConfirmPassword() != null && !request.getConfirmPassword().isBlank()
                && !request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new UnSuccessException("Mật khẩu xác nhận không khớp", 400);
        }

        if (passwordEncoder.matches(request.getNewPassword(), user.getPasswordHash())) {
            throw new UnSuccessException("Mật khẩu mới không được trùng với mật khẩu cũ", 400);
        }

        user.setPasswordHash(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }
}

