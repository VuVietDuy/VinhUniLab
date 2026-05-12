---
name: crud-generator
type: generator
description: |
  Sinh CRUD APIs (Repository, Service, Controller) từ entity classes
  Kích hoạt khi: cần scaffold entity, user nói "tạo CRUD", "sinh API",
  "tạo repo/service/controller", "generate CRUD cho bảng X", "tạo CRUD đi",
  hoặc bất kỳ lúc nào cần sinh CRUD code cho entity trong dự án HIS 4.0.
---

# HIS4 CRUD Generator

Sinh đầy đủ 4 files CRUD (Repository, Service Interface, ServiceImpl, Controller)
từ entity class, đúng package, đúng conventions.
Sẵn sàng compile mà không cần chỉnh sửa.

## Scope

- **Thực hiện:** sinh CRUD code (Repository, Service, ServiceImpl, Controller)
- **Không thực hiện:** phân tích entity (dùng `his4-entity-lookup`), sửa entity, sửa database

