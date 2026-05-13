INSERT INTO users (username, password_hash, full_name, email, role)
VALUES ('admin',
        '$2a$10$AJPeTf8RhLSxzUoLoB7U5uwOzyK7mq4fGAhCfD8C3qJmM3b1ji1.y',
        'System Administrator',
        'admin@vinhunilab.edu.vn',
        'ADMIN');

-- 1. Dữ liệu cho bảng users (Mật khẩu giả định: 123456)
INSERT INTO users (username, password_hash, full_name, email, role)
VALUES ('kthuat01', '$2a$10$xyz', 'Nguyễn Kỹ Thuật', 'tech01@vinhuni.edu.vn', 'TECHNICIAN'),
       ('kthuat02', '$2a$10$xyz', 'Trần Kỹ Thuật', 'tech02@vinhuni.edu.vn', 'TECHNICIAN'),
       ('giaovien01', '$2a$10$xyz', 'Lê Văn Giáo', 'gv01@vinhuni.edu.vn', 'TEACHER'),
       ('giaovien02', '$2a$10$xyz', 'Phạm Thị Viên', 'gv02@vinhuni.edu.vn', 'TEACHER'),
       ('giaovien03', '$2a$10$xyz', 'Hoàng Xuân A', 'gv03@vinhuni.edu.vn', 'TEACHER'),
       ('giaovien04', '$2a$10$xyz', 'Đặng Thu B', 'gv04@vinhuni.edu.vn', 'TEACHER'),
       ('giaovien05', '$2a$10$xyz', 'Mai Văn C', 'gv05@vinhuni.edu.vn', 'TEACHER'),
       ('giaovien06', '$2a$10$xyz', 'Bùi Thị D', 'gv06@vinhuni.edu.vn', 'TEACHER'),
       ('giaovien07', '$2a$10$xyz', 'Ngô Văn E', 'gv07@vinhuni.edu.vn', 'TEACHER');

-- 2. Dữ liệu cho bảng rooms (Phòng máy)
INSERT INTO rooms (room_name, location, total_seats)
VALUES ('Lab 101', 'Tầng 1 - Tòa nhà A', 40),
       ('Lab 102', 'Tầng 1 - Tòa nhà A', 40),
       ('Lab 201', 'Tầng 2 - Tòa nhà B', 35),
       ('Lab 202', 'Tầng 2 - Tòa nhà B', 35),
       ('Lab 301', 'Tầng 3 - Tòa nhà C', 50),
       ('Lab 302', 'Tầng 3 - Tòa nhà C', 50),
       ('Phòng Chuyên đề 1', 'Tầng 4 - Thư viện', 20),
       ('Phòng Chuyên đề 2', 'Tầng 4 - Thư viện', 20),
       ('Lab AI & Robotics', 'Tầng 5 - Trung tâm số', 30),
       ('Phòng Đồ họa', 'Tầng 2 - Tòa nhà A', 25);

-- 3. Dữ liệu cho bảng computers (Máy tính - Mỗi phòng vài máy mẫu)
INSERT INTO computers (room_id, computer_code, ip_address, mac_address, status)
VALUES (1, 'L101-PC01', '192.168.1.1', '00:1A:2B:3C:4D:01', 'AVAILABLE'),
       (1, 'L101-PC02', '192.168.1.2', '00:1A:2B:3C:4D:02', 'FAULTY'),
       (2, 'L102-PC01', '192.168.2.1', '00:1A:2B:3C:4D:03', 'AVAILABLE'),
       (3, 'L201-PC05', '192.168.3.5', '00:1A:2B:3C:4D:04', 'MAINTENANCE'),
       (5, 'L301-PC10', '192.168.5.10', '00:1A:2B:3C:4D:05', 'IN_USE'),
       (9, 'AI-PC01', '10.0.0.1', '00:1A:2B:3C:4D:06', 'AVAILABLE'),
       (9, 'AI-PC02', '10.0.0.2', '00:1A:2B:3C:4D:07', 'AVAILABLE'),
       (10, 'GRAPHIC-01', '172.16.0.1', '00:1A:2B:3C:4D:08', 'AVAILABLE'),
       (10, 'GRAPHIC-02', '172.16.0.2', '00:1A:2B:3C:4D:09', 'IN_USE'),
       (4, 'L202-PC12', '192.168.4.12', '00:1A:2B:3C:4D:10', 'AVAILABLE');

-- 4. Dữ liệu cho bảng bookings (Đăng ký phòng)
INSERT INTO bookings (user_id, room_id, start_time, end_time, purpose, status)
VALUES (4, 1, '2026-05-15 07:00:00', '2026-05-15 09:30:00', 'Dạy môn Lập trình Java', 'APPROVED'),
       (5, 2, '2026-05-15 13:00:00', '2026-05-15 16:00:00', 'Dạy môn Cơ sở dữ liệu', 'PENDING'),
       (6, 3, '2026-05-16 08:00:00', '2026-05-16 11:00:00', 'Thực hành mạng máy tính', 'APPROVED'),
       (7, 5, '2026-05-16 14:00:00', '2026-05-16 17:00:00', 'Thi học kỳ môn Tin đại cương', 'PENDING'),
       (8, 9, '2026-05-17 09:00:00', '2026-05-17 11:00:00', 'Hội thảo AI cho SV', 'APPROVED'),
       (4, 10, '2026-05-17 13:00:00', '2026-05-17 15:00:00', 'Dạy bù Đồ họa nâng cao', 'REJECTED'),
       (9, 4, '2026-05-18 07:00:00', '2026-05-18 09:00:00', 'Dạy môn Hệ điều hành', 'PENDING'),
       (10, 6, '2026-05-18 10:00:00', '2026-05-18 12:00:00', 'Thực hành Python', 'APPROVED'),
       (4, 1, '2026-05-19 07:00:00', '2026-05-19 09:30:00', 'Dạy môn Lập trình Java ca sau', 'PENDING'),
       (5, 2, '2026-05-20 13:00:00', '2026-05-20 16:00:00', 'Họp khoa CNTT', 'CANCELLED');

-- 5. Dữ liệu cho bảng incident_reports (Báo cáo sự cố)
INSERT INTO incident_reports (computer_id, reported_by, technician_id, description, priority, status)
VALUES (2, 4, NULL, 'Máy tính L101-PC02 không lên màn hình', 'HIGH', 'IN_PROGRESS'),
       (4, 5, NULL, 'Máy L201-PC05 bị lỏng bàn phím', 'LOW', 'OPEN'),
       (5, 6, NULL, 'Máy L301-PC10 treo khi chạy Android Studio', 'NORMAL', 'RESOLVED'),
       (1, 4, NULL, 'Mất kết nối internet tại máy PC01', 'NORMAL', 'OPEN'),
       (9, 8, NULL, 'Card đồ họa kêu to bất thường', 'HIGH', 'IN_PROGRESS'),
       (8, 7, NULL, 'Chuột bị hỏng nút cuộn', 'LOW', 'OPEN'),
       (3, 10, NULL, 'Lỗi phần mềm thi trắc nghiệm', 'NORMAL', 'RESOLVED'),
       (7, 9, NULL, 'Màn hình bị sọc ngang', 'NORMAL', 'OPEN'),
       (10, 4, NULL, 'Không đăng nhập được vào domain', 'NORMAL', 'IN_PROGRESS'),
       (2, 5, NULL, 'Lỗi ổ cứng SSD', 'HIGH', 'OPEN');

-- 6. Dữ liệu cho bảng usage_logs (Nhật ký sử dụng)
INSERT INTO usage_logs (computer_id, user_id, login_time, logout_time)
VALUES (1, 4, '2026-05-10 07:00:00', '2026-05-10 09:00:00'),
       (5, 5, '2026-05-10 08:30:00', '2026-05-10 11:30:00'),
       (9, 8, '2026-05-11 13:00:00', '2026-05-11 15:00:00'),
       (3, 9, '2026-05-11 14:00:00', '2026-05-11 16:30:00'),
       (10, 4, '2026-05-12 09:00:00', '2026-05-12 11:00:00'),
       (1, 6, '2026-05-12 10:00:00', '2026-05-12 12:00:00'),
       (2, 7, '2026-05-13 07:30:00', '2026-05-13 09:30:00'),
       (4, 10, '2026-05-13 13:00:00', '2026-05-13 15:00:00'),
       (5, 8, '2026-05-14 08:00:00', '2026-05-14 10:00:00'),
       (8, 5, '2026-05-14 14:00:00', '2026-05-14 16:00:00');