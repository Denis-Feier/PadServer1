CREATE DATABASE IF NOT EXISTS mysql;

DROP TABLE user_tbl;

CREATE TABLE IF NOT EXISTS user_tbl
(
    id       INTEGER,
    user_name varchar(16),
    password varchar(16),
    email    varchar(32)
);

INSERT INTO user_tbl(id, user_name, password, email)
VALUES (1, 'Denis', 'jmecher', 'denisfeier98@yahoo.com'),
       (2, 'Levi', 'jmecher', 'levi@yahoo.com'),
       (3, 'Alex', 'jmecher', 'alex@yahoo.com'),
       (4, 'Dan', 'jmecher', 'dan@yahoo.com');