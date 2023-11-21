INSERT INTO pizzas(name, description, photo_url, price)VALUES('Pizza Margherita', 'Pomodoro e mozzarella','https://primochef.it/wp-content/uploads/2019/08/SH_pizza_fatta_in_casa-1200x800.jpg.webp', 8.99);
INSERT INTO pizzas(name, description, photo_url, price)VALUES('Pizza Pepperoni', 'Peperoni, cipolla, olive nere', 'https://www.ilovevegan.it/wp-content/uploads/2017/06/vegpizza-e1498805143460-1300x967.jpg', 10.99);
INSERT INTO pizzas(name, description, photo_url, price)VALUES('Pizza Diavola', 'Salsiccia piccante, peperoncino,formaggio', 'https://s3.eu-central-1.amazonaws.com/quomi/media/133057/conversions/Pizza-Diavola-thumb-big.jpg',  11.99);
INSERT INTO pizzas(name, description, photo_url, price)VALUES('Pizza Vegetariana', 'Melanzane, zucchine, peperoni', 'https://www.guardini.com/images/guardinispa/ricette/full/pizza_set_2021_full.jpg', 9.99);
INSERT INTO pizzas(name, description, photo_url, price)VALUES('Pizza Quattro Stagioni', 'Prosciutto, funghi, carciofi e olive.', 'https://www.petitchef.it/imgupl/recipe/pizza-quattro-stagioni--327908p533338.jpg', 10.99);
INSERT INTO pizzas(name, description, photo_url, price)VALUES('Pizza Prosciutto e Funghi', 'Prosciutto crudo, funghi, rucola', 'https://www.pizzeriasenzatempo.it/wp-content/uploads/2020/04/PROSCIUTTO-E-FUNGHI.jpg', 12.99);
INSERT INTO pizzas(name, description, photo_url, price)VALUES('Pizza Gorgonzola e Pere', 'Gorgonzola, noci, pere', 'https://www.dolcidee.it/media/uploads/recipe/7609280.jpg', 11.99);

INSERT INTO ingredients(name) VALUES('Peperoni');
INSERT INTO ingredients(name) VALUES('Funghi');
INSERT INTO ingredients(name) VALUES('Mozzarella');
INSERT INTO ingredients(name) VALUES('Pomodoro');
INSERT INTO ingredients(name) VALUES('Cipolla');
INSERT INTO ingredients(name) VALUES('Peperoncino');
INSERT INTO ingredients(name) VALUES('Zucchine');
INSERT INTO ingredients(name) VALUES('Noci');
INSERT INTO ingredients(name) VALUES('Prosciutto crudo');
INSERT INTO ingredients(name) VALUES('Rucola');
INSERT INTO ingredients(name) VALUES('Formaggio');
INSERT INTO ingredients(name) VALUES('Olive nere');
INSERT INTO ingredients(name) VALUES('Pere');
INSERT INTO ingredients(name) VALUES('Salsiccia');
INSERT INTO ingredients(name) VALUES('Salsiccia piccante');
INSERT INTO ingredients(name) VALUES('Gorgonzola');

INSERT INTO roles (id, name) VALUES(1, 'ADMIN');
INSERT INTO roles (id, name) VALUES(2, 'USER');

INSERT INTO users (email, first_name, last_name, registered_at, password) VALUES('antonio@email.com', 'Antonio', 'Rossi', '2023-11-20 16:00', '{noop}antonio');
INSERT INTO users (email, first_name, last_name, registered_at, password) VALUES('valeria@email.com', 'Valeria', 'Bianchi', '2023-11-20 16:20','{noop}jane');

INSERT INTO pizzas_ingredients(pizza_id, ingredients_id) VALUES(1,1);
INSERT INTO pizzas_ingredients(pizza_id, ingredients_id) VALUES(1,3);

INSERT INTO users_roles (user_id, roles_id) VALUES(1, 1);
INSERT INTO users_roles (user_id, roles_id) VALUES(1, 2);
INSERT INTO users_roles (user_id, roles_id) VALUES(2, 2);