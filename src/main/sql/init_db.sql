--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.6
-- Dumped by pg_dump version 9.5.6

ALTER TABLE IF EXISTS ONLY public.users DROP CONSTRAINT IF EXISTS pk_users_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.products DROP CONSTRAINT IF EXISTS pk_products_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.orders DROP CONSTRAINT IF EXISTS pk_orders_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.address_info DROP CONSTRAINT IF EXISTS pk_address_info_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.payment_info DROP CONSTRAINT IF EXISTS pk_payment_info_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.product_category DROP CONSTRAINT IF EXISTS pk_product_categories_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.supplier DROP CONSTRAINT IF EXISTS pk_suppliers_id CASCADE;


DROP TABLE IF EXISTS public.users;
DROP SEQUENCE IF EXISTS public.users_id_seq;
CREATE TABLE users (
    id serial NOT NULL,
    username varchar UNIQUE NOT NULL,
    email varchar UNIQUE NOT NULL,
    password varchar NOT NULL

);

DROP TABLE IF EXISTS public.products;
DROP SEQUENCE IF EXISTS public.products_id_seq;
CREATE TABLE products (
    id serial NOT NULL,
    name varchar NOT NULL,
    price float NOT NULL,
    currency varchar NOT NULL,
    description varchar,
    category_id integer NOT NULL,
    supplier_id integer NOT NULL

);

DROP TABLE IF EXISTS public.orders;
DROP SEQUENCE IF EXISTS public.orders_id_seq;
CREATE TABLE orders (
    id serial NOT NULL,
    user_id INTEGER NOT NULL,
    product_id integer NOT NULL,
    qty integer,
    payed boolean

);

DROP TABLE IF EXISTS public.address_info;
DROP SEQUENCE IF EXISTS public.address_info_id_seq;
CREATE TABLE address_info (
    id serial NOT NULL,
    user_id integer NOT NULL,
    full_name varchar,
    address varchar NOT NULL,
    city varchar NOT NULL,
    state varchar NOT NULL,
    zip integer NOT NULL


);

DROP TABLE IF EXISTS public.payment_info;
DROP SEQUENCE IF EXISTS public.payment_info_id_seq;
CREATE TABLE payment_info (
    id serial NOT NULL,
    user_id integer NOT NULL,
    name varchar NOT NULL,
    card_number integer NOT NULL,
    month integer NOT NULL,
    year integer NOT NULL
);

DROP TABLE IF EXISTS public.product_categories;
DROP SEQUENCE IF EXISTS public.product_categories_id_seq;
CREATE TABLE product_categories (
    id serial UNIQUE NOT NULL,
    name varchar NOT NULL,
    department varchar NOT NULL,
    description varchar NOT NULL
);

DROP TABLE IF EXISTS public.suppliers;
DROP SEQUENCE IF EXISTS public.suppliers_id_seq;
CREATE TABLE suppliers (
    id serial UNIQUE NOT NULL,
    name varchar NOT NULL,
    description varchar NOT NULL
);


ALTER TABLE ONLY users
    ADD CONSTRAINT pk_users_id PRIMARY KEY (id);

ALTER TABLE ONLY products
    ADD CONSTRAINT pk_products_id PRIMARY KEY (id);

ALTER TABLE ONLY orders
    ADD CONSTRAINT pk_orders_id PRIMARY KEY (id);

ALTER TABLE ONLY address_info
    ADD CONSTRAINT pk_address_info_id PRIMARY KEY (id);

ALTER TABLE ONLY payment_info
    ADD CONSTRAINT pk_payment_info_id PRIMARY KEY (id);





ALTER TABLE ONLY orders
    ADD CONSTRAINT fk_orders_user_id FOREIGN KEY (user_id) REFERENCES users(id);

ALTER TABLE ONLY orders
    ADD CONSTRAINT fk_orders_product_id FOREIGN KEY (product_id) REFERENCES products(id);

ALTER TABLE ONLY address_info
    ADD CONSTRAINT fk_address_info_id FOREIGN KEY (user_id) REFERENCES users(id);

ALTER TABLE ONLY payment_info
    ADD CONSTRAINT fk_payment_info_id FOREIGN KEY (user_id) REFERENCES users(id);

ALTER TABLE ONLY products
    ADD CONSTRAINT fk_products_supplier_id FOREIGN KEY (supplier_id) REFERENCES suppliers(id);

ALTER TABLE ONLY products
    ADD CONSTRAINT fk_products_category_id FOREIGN KEY (category_id) REFERENCES product_categories(id);





INSERT INTO users VALUES (1, 'HarryPoootr', 'harry@gmail.com', 'ILoveHagridhashas');
INSERT INTO users VALUES (2, 'Hermione', 'Hermione@gmail.com', 'ILoveHagridhashas');
INSERT INTO users VALUES (3, 'harrmione', 'asdad@gmail.com', 'ILoveHagridhashas');
SELECT pg_catalog.setval('users_id_seq', 3, true);

INSERT INTO suppliers VALUES (1, 'Wanna have some drugs? Corp', 'Supported by Daniel Radcliffe');
INSERT INTO suppliers VALUES (2, 'Olliwander ''s Magic Wands', 'Very best wands in town, where the size doesn''t matter');
INSERT INTO suppliers VALUES (3, 'Weasley''s Tutoring', 'Most effective spells, from experts');
INSERT INTO suppliers VALUES (4, 'Cheap Slaves LTD', 'From beasts to humans, for weddings, stags, birthdays, funerals, etc.');
SELECT pg_catalog.setval('suppliers_id_seq', 4, true);

INSERT INTO product_categories VALUES (1, 'Magic tool','Tools', 'Utilities for magic');
INSERT INTO product_categories VALUES (2, 'Magic spell','Spells', 'Most effective words against enemies');
INSERT INTO product_categories VALUES (3, 'Rental','Rentals', 'Various species around the world');
INSERT INTO product_categories VALUES (4, '18+','Adults only', 'BEWARE! Adults only! :O');
SELECT pg_catalog.setval('product_categories_id_seq', 4, true);

INSERT INTO products VALUES (1, 'Magic Dust', 10, 'USD' , 'Pure happiness and joy', 1 ,1 );
INSERT INTO products VALUES (2, 'Cloak of invisibility', 50, 'USD', 'Watch out!' ,1 ,1 );
INSERT INTO products VALUES (3, 'Magic wand - phoenix feather',20 , 'USD', 'Optimal for fiery wizards and witches' ,1 ,2 );
INSERT INTO products VALUES (4, 'Magic wand - dragon heart string',20 , 'USD', 'Optimal for fiery wizards and witches' ,1 ,2 );
INSERT INTO products VALUES (5, 'Magic wand - unicorn hair',20 , 'USD', 'Optimal for fiery wizards and witches' ,1 ,2 );
INSERT INTO products VALUES (6, 'Invito',30 , 'USD', 'Don''t be so distant - pull your loved ones closer' ,2 ,3 );
INSERT INTO products VALUES (7, 'Expecto Patronum(with custom animal type)',30 , 'USD', 'Good defender against dementors' ,2 ,3 );
INSERT INTO products VALUES (8, 'To be an animagus(with custom animal type',40 , 'USD', 'Why just wonder wildlife if you can be a part of it?' ,2 ,3 );
INSERT INTO products VALUES (9, 'Vingardium leviosa',30 , 'USD', 'Not leviosaaa!' ,2 ,3 );
INSERT INTO products VALUES (10, 'Hungarian Horntail',200 , 'USD', 'Coolest dragon ever' ,3 ,4 );
INSERT INTO products VALUES (11, 'Hippogriff',200 , 'USD', 'Known flying object' ,3 ,4 );
INSERT INTO products VALUES (12, 'Phoenix',300 , 'USD', 'Pet for life' ,3 ,4 );
INSERT INTO products VALUES (13, 'Rubeus Hagrid',300 , 'USD', 'Happi birthdae, Hary!' ,3 ,4 );
INSERT INTO products VALUES (14, 'Random human pack',50 , 'USD', 'Surpriiiise!' ,3 ,4 );
INSERT INTO products VALUES (15, 'Phoenix tear',600 , 'USD', 'Wounds are for the weak' ,4 ,3 );
INSERT INTO products VALUES (16, 'Magic mushrooms',600 , 'USD', 'Original magic' ,4 ,1 );
INSERT INTO products VALUES (17, 'Mandragora root',600 , 'USD', 'Solution for blue screen of death' ,4 ,1 );
INSERT INTO products VALUES (18, 'Basilisc fang',600 , 'USD', 'If you''re bored of the book...' ,4 ,1 );
INSERT INTO products VALUES (19, 'Unicorn blood',600 , 'USD', 'Stay alive - drink blood!' ,4 ,1 );
SELECT pg_catalog.setval('products_id_seq', 19, true);

INSERT INTO orders VALUES (1, 1, 1, 11, true);
SELECT pg_catalog.setval('orders_id_seq', 1, true);

INSERT INTO address_info VALUES (1,1,'1','address','city','state',121);
SELECT pg_catalog.setval('address_info_id_seq', 1, true);

INSERT INTO payment_info VALUES (1,1,'fullname',12345,2,21);
SELECT pg_catalog.setval('payment_info_id_seq', 1, true);
