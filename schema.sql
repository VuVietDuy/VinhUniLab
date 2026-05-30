-- Khởi tạo Database cho Quản lý Phòng máy VinhUniLab
-- Hệ quản trị: PostgreSQL

-- 1. Định nghĩa các kiểu dữ liệu ENUM để quản lý trạng thái
CREATE TYPE user_role AS ENUM ('ADMIN', 'TEACHER', 'TECHNICIAN');
CREATE TYPE computer_status AS ENUM ('AVAILABLE', 'IN_USE', 'FAULTY', 'MAINTENANCE');
CREATE TYPE booking_status AS ENUM ('PENDING', 'APPROVED', 'CANCELLED', 'REJECTED');
CREATE TYPE incident_status AS ENUM ('OPEN', 'IN_PROGRESS', 'RESOLVED');
CREATE TYPE incident_priority AS ENUM ('LOW', 'NORMAL', 'HIGH');

-- 2. Bảng Người dùng
CREATE TABLE users
(
    id            SERIAL PRIMARY KEY,
    username      VARCHAR(50) UNIQUE NOT NULL,
    password_hash TEXT               NOT NULL,
    full_name     VARCHAR(100)       NOT NULL,
    email         VARCHAR(100) UNIQUE,
    role          user_role   DEFAULT 'TEACHER',
    created_at    TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
);

-- 3. Bảng Phòng máy
CREATE TABLE rooms
(
    id          SERIAL PRIMARY KEY,
    room_code   VARCHAR(50) NOT NULL,
    room_name   VARCHAR(50) NOT NULL,
    location    VARCHAR(100),
    total_seats INT     DEFAULT 0 CHECK (total_seats >= 0),
    is_active   BOOLEAN DEFAULT TRUE
);

-- 4. Bảng Máy tính
CREATE TABLE computers
(
    id            SERIAL PRIMARY KEY,
    room_id       INT REFERENCES rooms (id) ON DELETE CASCADE,
    computer_code VARCHAR(20) NOT NULL,
    ip_address    INET,    -- Sử dụng kiểu dữ liệu mạng của Postgres
    mac_address   MACADDR, -- Sử dụng kiểu macaddr chuyên dụng
    status        computer_status DEFAULT 'AVAILABLE',
    last_ping     TIMESTAMPTZ,
    UNIQUE (room_id, computer_code)
);

-- 5. Bảng Đăng ký phòng máy
CREATE TABLE bookings
(
    id         SERIAL PRIMARY KEY,
    user_id    INT REFERENCES users (id),
    room_id    INT REFERENCES rooms (id),
    start_time TIMESTAMPTZ NOT NULL,
    end_time   TIMESTAMPTZ NOT NULL,
    purpose    TEXT,
    status     booking_status DEFAULT 'PENDING',
    created_at TIMESTAMPTZ    DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT check_booking_time CHECK (end_time > start_time)
);

-- 6. Bảng Báo cáo sự cố
CREATE TABLE incident_reports
(
    id            SERIAL PRIMARY KEY,
    computer_id   INT REFERENCES computers (id),
    reported_by   INT REFERENCES users (id),
    technician_id INT REFERENCES users (id),
    description   TEXT NOT NULL,
    image_url     TEXT,
    priority      incident_priority DEFAULT 'NORMAL',
    status        incident_status   DEFAULT 'OPEN',
    created_at    TIMESTAMPTZ       DEFAULT CURRENT_TIMESTAMP,
    resolved_at   TIMESTAMPTZ
);


-- Tạo Index để tăng tốc độ truy vấn cho Dashboard và Báo cáo
CREATE INDEX idx_computers_room_status ON computers (room_id, status);
CREATE INDEX idx_bookings_range ON bookings USING btree (start_time, end_time);
CREATE INDEX idx_incidents_active ON incident_reports (status) WHERE status != 'RESOLVED';