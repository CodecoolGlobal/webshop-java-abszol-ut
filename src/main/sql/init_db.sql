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
ALTER TABLE IF EXISTS ONLY public.product_category DROP CONSTRAINT IF EXISTS pk_product_category_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.supplier DROP CONSTRAINT IF EXISTS pk_supplier_id CASCADE;


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
    description varchar,
    url varchar NOT NULL,
    price float NOT NULL,
    currency varchar NOT NULL,
    supplier_id integer NOT NULL,
    category_id integer NOT NULL

);

DROP TABLE IF EXISTS public.orders;
DROP SEQUENCE IF EXISTS public.orders_id_seq;
CREATE TABLE orders (
    id serial NOT NULL,
    user_id INTEGER NOT NULL,
    product_id integer NOT NULL,
    qty integer

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
    id serial NOT NULL,
    name varchar NOT NULL,
    department varchar NOT NULL,
    description varchar NOT NULL
);

DROP TABLE IF EXISTS public.suppliers;
DROP SEQUENCE IF EXISTS public.suppliers_id_seq;
CREATE TABLE suppliers (
    id serial NOT NULL,
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

INSERT INTO suppliers VALUES (1, 'ABeszállító', 'desc');
SELECT pg_catalog.setval('suppliers_id_seq', 1, true);

INSERT INTO product_categories VALUES (1, 'AKategória','Depi', 'desc');
SELECT pg_catalog.setval('product_categories_id_seq', 1, true);

INSERT INTO products VALUES (1, 'Varázspálca', 'desc', '/1.png', 11, 'USD' ,1 ,1 );
SELECT pg_catalog.setval('products_id_seq', 1, true);

INSERT INTO orders VALUES (1, 1, 1, 11);
SELECT pg_catalog.setval('orders_id_seq', 1, true);

INSERT INTO address_info VALUES (1,1,'1','address','city','state',121);
SELECT pg_catalog.setval('address_info_id_seq', 1, true);

INSERT INTO payment_info VALUES (1,1,'fullname',12345,2,21);
SELECT pg_catalog.setval('payment_info_id_seq', 1, true);

