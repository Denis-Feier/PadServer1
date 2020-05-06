CREATE DATABASE IF NOT EXISTS mysql;

DROP TABLE user_tbl;

CREATE TABLE IF NOT EXISTS user_tbl
(
    id        INTEGER PRIMARY KEY AUTO_INCREMENT,
    user_name varchar(16),
    password  varchar(256),
    email     varchar(32),
    role      varchar(10),
    user_pic  varchar(256)
);

INSERT INTO user_tbl(id, user_name, password, email, role, user_pic)
VALUES (1, 'Denis', '$2a$10$5KdOiO1s/fIWqFuZxNH.LuQVvTgGUuXkLfyJigMuIVTvHElyQW34u',
        'denisfeier98@yahoo.com', 'admin', 'https://thumbs.dreamstime.com/z/isolated-artistic-drawing-cat-profile-color-sketch-white-background-135822849.jpg'),
       (2, 'David', '$2a$10$ONx7mnYXBK1TzAHHBZGhgey6JlIass1dpg8CIsC12hKkczGeXhCpy',
        'david@yahoo.com', 'user', null),
       (3, 'Levi', '$2a$10$ug8pwEWTkFO4sID37p5MDOu8c42x9wxqvFrJL9NQRXyK4WlRvYaOq',
        'Levi@yahoo.com', 'admin', 'https://cdn3.iconfinder.com/data/icons/avatars-9/145/Avatar_Dog-512.png');

DROP TABLE IF EXISTS token_tbl;

CREATE TABLE token_tbl
(
    tid    INTEGER PRIMARY KEY AUTO_INCREMENT,
    token  varchar(256),
    uid_fk INTEGER
);

# SELECT * FROM user_tbl JOIN token_tbl t WHERE id = t.uid_fk AND t.token = ''eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJEZW5pcyIsImV4cCI6MTU4NjY2OTk1NSwiaWF0IjoxNTg2NjMzOTU1fQ.UKAWjjEhoiQzLIJm9qxKBScV4TSKm0F_y7SFK9w7Q4M'';

DROP TABLE IF EXISTS product_tbl;

CREATE TABLE product_tbl
(
    pid          INTEGER PRIMARY KEY AUTO_INCREMENT,
    product_name VARCHAR(32),
    price        FLOAT,
    ingredients  VARCHAR(256),
    quantity     INTEGER,
    is_food       TINYINT(1),
    pic          VARCHAR(256)
);

INSERT INTO product_tbl(pid, product_name, price, ingredients, quantity, is_food, pic)
VALUES (1, 'Apa Plata', 4.5, null, 500, 0, 'https://lamasa.auchan.ro/get_image.php?&width=240&skin=cs_zenfashion&type=product&pic=izv.-alb-apa-plata-0.5l_2893_1_1517561954.jpg'),
       (2, 'Apa Minerala', 4.5, null, 500, 0, 'https://www.cora.ro/images/products/2362956/gallery/2362956_hd_1.jpg'),
       (3, 'Coca Cola', 6.0, null, 500, 0, 'https://www.cora.ro/images/products/2502817/gallery/2502817_hd_1.jpg'),
       (4, 'Pizza Diavola', 21,
        'Salam picant, sos de rosii (vezi reteta de Sos de Rosii la Pizza Calzone), ciuperci, ozzarella Gourmet, masline, ardei iute (pepperoni)'
           , 500, 1, 'https://www.bucatareselevesele.ro/wp-content/uploads/2016/09/pizza-diavolo-11.jpg'),
       (5, 'Prosciutto e Funghi', 25,
        'aluat de pizza, sos de rosii 5 linguri, mozzarella 70g, prosciutto sau sunca 6 felii, ciuperci 60g, masline 4bucati - optional, oregano uscat 1 lingurita',
        450, 1,
        'http://urgentpizza.ro/blog/wp-content/uploads/2014/05/prosciutto-e-funghi-urgenpizza-575x262.png'),
       (6, 'Pizza Vegetariana', 24.5, 'rosii taiate cubulete, pasta de tomate, ceapa, oregano uscat, ardei gras verde',
        600, 1, 'https://www.lalena.ro/images/uploaded/600x_Pizza-Vegetariana-ca-la-pizzerie-632.jpg'),
       (7, 'Pizza Fructe De Mare', 26.0, 'faina alba, drojdie de bere uscata, lingurita de zahar, praf de pier negru, sare fina',
        600, 1, 'https://atelieruldepizza.ro/wp-content/uploads/2017/02/REC_PRO_0033.png'),
       (8, 'Pizza Cu Ton', 20, 'faina, drojdie, ulei de masline, sare, ceapa rosie, mozzarella pentru pizza, masline negre, oregano uscat',
        600, 1, 'https://2.bp.blogspot.com/-vQGXL1viC70/UjAh5mZMM9I/AAAAAAAAUG0/6csGimdiLTo/s1600/DSCF8750.jpg'),
       (9, 'Burger De Pui', 25, 'piept de pui dezosat, ceapa rosie, ulei de masline, piper, sare ',
        600, 1, 'https://pizzahugo.ro/wp-content/uploads/2018/11/burger-de-pui-detaliu-min.jpg'),
       (10, 'Burger Vegetarian', 20, 'usturoi, ceapa rosie tocata, zeama de lamaie, chifle din faina integrala, sare si piper',
        600, 1, 'https://bobulverde.eu/wp-content/uploads/2018/06/burger-vegan-1440x960.jpg');

DROP TABLE IF EXISTS order_items_tbl;

CREATE TABLE order_items_tbl (
    iid INTEGER PRIMARY KEY AUTO_INCREMENT,
    quantity INTEGER,
    pid INTEGER,
    o_id_fik INTEGER
);

DROP TABLE IF EXISTS order_tbl;

CREATE TABLE order_tbl (
    o_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    total_price FLOAT,
    nr_items INTEGER,
    state VARCHAR(16),
    u_id INTEGER,
    data TIMESTAMP
);