INSERT INTO users (username, password, role) VALUES
('alice', '$2a$12$P.u5sDYx2.j9xSTeHeKXqOYyRbVc5q8jRAKKSq93AVaA1uVUHXG/2', 'USER'),
('bob',   '$2a$12$xuTHwiyjrob7r57/qCVLzeqoEmKnQUcyKdRypRuH91N0VUfIjioJe', 'USER');

INSERT INTO notes (title, content, created_at, user_id) VALUES
('Spring Boot Basics', 'Learn controllers, services, and repositories.', CURRENT_TIMESTAMP, 1),
('Thymeleaf Notes', 'Practice th:each, th:text, and layouts.', CURRENT_TIMESTAMP, 1),
('Security Todo', 'Implement login, logout, and password hashing.', CURRENT_TIMESTAMP, 2);