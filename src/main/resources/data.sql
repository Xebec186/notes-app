INSERT INTO users (username, password, role) VALUES
('alice', '$2a$10$7QZy6Z7YhC5w5d9rM8lE8eXzN5A7t1nXz0pWwYJq8Y1z1u9xJ0F9e', 'USER'),
('bob',   '$2a$10$7QZy6Z7YhC5w5d9rM8lE8eXzN5A7t1nXz0pWwYJq8Y1z1u9xJ0F9e', 'USER');

INSERT INTO notes (title, content, created_at, user_id) VALUES
('Spring Boot Basics', 'Learn controllers, services, and repositories.', CURRENT_TIMESTAMP, 1),
('Thymeleaf Notes', 'Practice th:each, th:text, and layouts.', CURRENT_TIMESTAMP, 1),
('Security Todo', 'Implement login, logout, and password hashing.', CURRENT_TIMESTAMP, 2);