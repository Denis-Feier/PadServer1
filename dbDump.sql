CREATE DATABASE IF NOT EXISTS mysql;

DROP TABLE user_tbl;

CREATE TABLE IF NOT EXISTS user_tbl
(
    id       INTEGER PRIMARY KEY AUTO_INCREMENT,
    user_name varchar(16),
    password varchar(256),
    email    varchar(32),
    role varchar(10)
);

INSERT INTO user_tbl(id, user_name, password, email, role)
VALUES (1, 'Denis', '$2a$10$5KdOiO1s/fIWqFuZxNH.LuQVvTgGUuXkLfyJigMuIVTvHElyQW34u', 'denisfeier98@yahoo.com', 'admin'),
       (2, 'David', '$2a$10$ONx7mnYXBK1TzAHHBZGhgey6JlIass1dpg8CIsC12hKkczGeXhCpy', 'david@yahoo.com', 'user'),
       (3, 'Levi', '$2a$10$ug8pwEWTkFO4sID37p5MDOu8c42x9wxqvFrJL9NQRXyK4WlRvYaOq', 'Levi@yahoo.com', 'admin');

DROP TABLE IF EXISTS token_tbl;

CREATE TABLE token_tbl(
    tid INTEGER PRIMARY KEY AUTO_INCREMENT,
    token varchar(256),
    uid_fk INTEGER
);

# SELECT * FROM user_tbl JOIN token_tbl t WHERE id = t.uid_fk AND t.token = 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJEZW5pcyIsImV4cCI6MTU4NjY2OTk1NSwiaWF0IjoxNTg2NjMzOTU1fQ.UKAWjjEhoiQzLIJm9qxKBScV4TSKm0F_y7SFK9w7Q4M';

DROP TABLE IF EXISTS product_tbl;

CREATE TABLE product_tbl(
    pid INTEGER PRIMARY KEY AUTO_INCREMENT,
    product_name VARCHAR(32),
    price FLOAT,
    ingredients VARCHAR(256),
    quantity INTEGER,
    isFood TINYINT(1),
    pic VARCHAR(256)
);

INSERT INTO product_tbl(pid, product_name, price, ingredients, quantity, isFood, pic)
VALUES
       (1, 'Apa Plata', 4.5, null, 500, 0, 'https://lamasa.auchan.ro/get_image.php?&width=240&skin=cs_zenfashion&type=product&pic=izv.-alb-apa-plata-0.5l_2893_1_1517561954.jpg'),
       (2, 'Apa Minerala', 4.5, null, 500, 0, 'https://www.cora.ro/images/products/2362956/gallery/2362956_hd_1.jpg'),
       (3, 'Coca Cola', 6.0, null, 500, 0, 'https://www.cora.ro/images/products/2502817/gallery/2502817_hd_1.jpg'),
       (4, 'Pizza Diavola', 21,
        'Salam picant,sos de rosii (vezi reteta de Sos de Rosii la Pizza Calzone),ciuperci,ozzarella Gourmet,masline,ardei iute (pepperoni)'
        , 500, 1, 'https://www.bucatareselevesele.ro/wp-content/uploads/2016/09/pizza-diavolo-11.jpg'),
       (5, 'Prosciutto e Funghi', 25,
        'aluat de pizza,sos de rosii 5 linguri,mozzarella 70g,prosciutto sau sunca 6 felii,ciuperci 60g,masline 4bucati - optional,oregano uscat 1 lingurita',
        450, 1,
        'http://urgentpizza.ro/blog/wp-content/uploads/2014/05/prosciutto-e-funghi-urgenpizza-575x262.png');