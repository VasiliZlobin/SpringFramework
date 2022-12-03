use goods;

CREATE TABLE IF NOT EXISTS `products` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(500) NOT NULL,
  `price` decimal(15,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `customers` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(500) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `purchases` (
  `customer_id` bigint NOT NULL,
  `product_id` bigint NOT NULL,
  `amount` bigint NOT NULL DEFAULT '1',
  `sum` decimal(15,2) DEFAULT NULL,
  PRIMARY KEY (`customer_id`,`product_id`),
  KEY `fk_product_purchases_idx` (`product_id`),
  CONSTRAINT `fk_customer_purchases` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`),
  CONSTRAINT `fk_product_purchases` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO products (title, price) VALUES ("Книга", 517.23);
INSERT INTO products (title, price) VALUES ("Карандаш", 34);
INSERT INTO products (title, price) VALUES ("Ручка", 145.11);
INSERT INTO products (title, price) VALUES ("Пальто", 19000);

INSERT INTO customers (name) VALUES ("Библиотека");
INSERT INTO customers (name) VALUES ("Канцелярия");
INSERT INTO customers (name) VALUES ("Администрация");
INSERT INTO customers (name) VALUES ("Хозяйство");

INSERT INTO purchases (customer_id, product_id) VALUES (1, 1);
INSERT INTO purchases (customer_id, product_id, amount, sum) VALUES (1, 3, 10, 700);
INSERT INTO purchases (customer_id, product_id, amount) VALUES (2, 2, 4);
INSERT INTO purchases (customer_id, product_id, amount) VALUES (2, 3, 3);
INSERT INTO purchases (customer_id, product_id) VALUES (3, 1);
INSERT INTO purchases (customer_id, product_id, amount) VALUES (3, 4, 2);
INSERT INTO purchases (customer_id, product_id, amount) VALUES (4, 4, 1);
INSERT INTO purchases (customer_id, product_id, amount) VALUES (4, 2, 5);
INSERT INTO purchases (customer_id, product_id, amount) VALUES (4, 3, 2);
