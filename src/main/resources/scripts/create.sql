DROP TABLE IF EXISTS `route`;
DROP TABLE IF EXISTS `order_`;
DROP TABLE IF EXISTS `user_role`;
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `role`;

CREATE TABLE IF NOT EXISTS `order_` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `user_id` INT,
    `created_at` DATETIME DEFAULT NOW(),
    `start_at` DATETIME DEFAULT NOW(),
    `end_at` DATETIME,
    `price` DECIMAL(10, 2),
    `route_discount` SMALLINT DEFAULT 0,
    `user_discount` SMALLINT DEFAULT 0,
    `cash` DECIMAL(10, 2)
);

CREATE TABLE IF NOT EXISTS `route` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `order_id` INT,
    `point_order` INT,
    `point_name` VARCHAR(10),

    CONSTRAINT `FK_ORDER_ID` FOREIGN KEY (`order_id`) REFERENCES `order_`(`id`) ON DELETE CASCADE
);


--  SECURITY --

CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL UNIQUE,
  `password` varchar(64) NOT NULL,
  `enabled` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`)
);
 
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
);
 
CREATE TABLE `user_role` (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  KEY `idx_user_id` (`user_id`),
  KEY `idx_role_id` (`role_id`),
  CONSTRAINT `fk_role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
);