DROP TABLE IF EXISTS `route`;
DROP TABLE IF EXISTS `order_`;

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
