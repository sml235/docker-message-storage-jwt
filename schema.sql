DROP TABLE IF EXISTS messages;
DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id       int NOT NULL AUTO_INCREMENT,
    name     varchar(255) DEFAULT NULL,
    password varchar(255) DEFAULT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY idx_users_name (name)
);

CREATE TABLE messages
(
    id           int NOT NULL AUTO_INCREMENT,
    message_text varchar(255) DEFAULT NULL,
    user_id      int          DEFAULT NULL,
    PRIMARY KEY (id),
    KEY idx_user_id (user_id),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users (id)
);