DROP DATABASE ecommerce_springboot;

CREATE DATABASE ecommerce_springboot;
USE ecommerce_springboot;

DESCRIBE admins;
DESCRIBE roles;
DESCRIBE admins_roles;
DESCRIBE categories;
DESCRIBE products;

SELECT * FROM admins;
SELECT * FROM roles;
SELECT * FROM admins_roles;
SELECT * FROM categories;
SELECT * FROM products;

SET FOREIGN_KEY_CHECKS=1; # 0 for not check
DROP TABLE products;
DROP TABLE categories;

INSERT INTO admins VALUES ();

