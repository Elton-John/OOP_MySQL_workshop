

CREATE DATABASE workshop2 CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
 USE workshop2;

CREATE TABLE users (
    id INT(11) AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL UNIQUE,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);
#
# dodawanie użytkownika,
INSERT INTO users (email, username, password) VALUES (?,?,?);

# zmiana danych,
UPDATE users SET email=?, username=?, password=? WHERE id=?;

# pobieranie po id,
SELECT email, username, password FROM users WHERE id = ?;

# usuwanie po id,
DELETE FROM users WHERE id = ?;

# pobieranie wszystkich użytkowników.
SELECT * FROM users;

