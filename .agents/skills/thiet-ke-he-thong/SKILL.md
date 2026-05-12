# Tài liệu Đặc tả Hệ thống Quản lý Phòng máy Tập trung (Web-based)

## 1. Tổng quan Dự án (Project Overview)
Hệ thống được thiết kế dưới dạng nền tảng ứng dụng Web nhằm mục đích quản lý tập trung các phòng máy tính trong một tổ chức (trường học, trung tâm đào tạo). Hệ thống cho phép truy cập linh hoạt từ mạng nội bộ hoặc từ xa, giúp tối ưu hóa việc vận hành mà không cần cài đặt phần mềm trực tiếp trên từng máy trạm.

## 2. Đối tượng người dùng (User Personas)
* **Quản trị viên (Admin):** Theo dõi toàn diện tình trạng hạ tầng, xử lý báo cáo hỏng hóc và quản lý thống kê.
* **Giáo viên (Teacher):** Thực hiện đăng ký lịch sử dụng phòng máy trực tuyến.
* **Kỹ thuật viên (Technician):** Cập nhật trạng thái sửa chữa thiết bị.

## 3. Danh sách Chức năng Chi tiết (Functional Requirements)

### 3.1. Quản lý và Giám sát Tập trung (Centralized Monitoring)
* **Dashboard trực quan:** Hiển thị sơ đồ lưới (Grid View) đại diện cho các máy tính trong từng phòng.
* **Trạng thái thời gian thực:** Cập nhật trạng thái máy (Đang hoạt động, Đang hỏng, Đang bảo trì, Tắt máy) thông qua giao thức truyền tải dữ liệu thời gian thực.
* **Truy cập từ xa:** Giao diện Web responsive hỗ trợ theo dõi qua thiết bị di động hoặc máy tính cá nhân ngoài mạng nội bộ.

### 3.2. Đăng ký Sử dụng Phòng máy Online (Online Booking)
* **Lịch biểu tương tác:** Cho phép giáo viên xem các khung giờ còn trống của từng phòng máy.
* **Đặt lịch:** Thực hiện đăng ký mượn phòng theo tiết học, ngày học hoặc theo định kỳ.
* **Xác nhận tự động:** Hệ thống tự động kiểm tra xung đột lịch và gửi thông báo xác nhận.

### 3.3. Quản lý Sự cố và Bảo trì (Incident Management)
* **Báo hỏng thiết bị:** Người dùng có thể gửi yêu cầu sửa chữa ngay trên Web kèm mô tả chi tiết vị trí máy và lỗi gặp phải.
* **Theo dõi quy trình:** Cập nhật tiến độ sửa chữa từ khi tiếp nhận lỗi đến khi hoàn thành.
* **Lịch sử bảo trì:** Lưu trữ dữ liệu các lần hỏng hóc của từng linh kiện/thiết bị để phục vụ việc đánh giá chất lượng phần cứng.

### 3.4. Thống kê và Báo cáo (Analytics & Reporting)
* **Xuất dữ liệu:** Hỗ trợ xuất báo cáo dưới dạng file Excel, PDF cho các mục đích hành chính.
* **Biểu đồ thống kê:** * Tần suất sử dụng phòng máy theo tuần/tháng.
    * Thống kê các loại lỗi thường gặp nhất.
    * Tỷ lệ máy hoạt động ổn định trên tổng số máy hiện có.

**## 5. Mục tiêu của Hệ thống (System Goals)
* Giảm thiểu thời gian quản lý thủ công.
* Tăng tính minh bạch trong việc sử dụng tài sản công.
* Đảm bảo phòng máy luôn trong tình trạng sẵn sàng phục vụ giảng dạy thông qua việc phát hiện và xử lý sự cố kịp thời.**