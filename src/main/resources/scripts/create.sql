CREATE TABLE IF NOT EXISTS `order` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `user_id` INT,
    `created_at` DATETIME DEFAULT NOW(),
    `start_at` DATETIME DEFAULT NOW(),
    -- `end_at` aproximate calculated time of end of trip
    `end_at` DATETIME,
    `price` DECIMAL(10, 2),
    `route_discount` SMALLINT DEFAULT 0,
    `user_discount` SMALLINT DEFAULT 0,
    -- `cach` > 0 determine trip completed
    `cash` DECIMAL(10, 2),
    --
    CONSTRAINT `pk_order` PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `route` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `order_id` INT,
    `point_order` INT,
    `point_name` VARCHAR(10),
    --
    CONSTRAINT `pk_route` PRIMARY KEY (`id`),
    CONSTRAINT `fk_route_order` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`) ON DELETE CASCADE
);
