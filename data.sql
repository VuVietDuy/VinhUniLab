INSERT INTO users (username, password_hash, full_name, email, role)
VALUES ('admin',
        '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.7uKCQq2',
        'System Administrator',
        'admin@vinhunilab.edu.vn',
        'ADMIN');

select *
from users;