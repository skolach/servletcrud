delete from `order_`;

delete from `route`;

INSERT into `order_`(
        `user_id`,
        `created_at`,
        `start_at`,
        `end_at`,
        `price`,
        `route_discount`,
        `user_discount`,
        `cash`
    )
VALUES
(1, now(), now(), now(), 100, 10, 20, 72),
(2, now(), now(), now(), 40, 5, 6, 30);

insert into `route`(`order_id`, `point_order`, `point_name`)
values
	(1,1,'1_1'), (1,2,'1_2'), (1,3,'1_3'),
    (2,1,'2_1'), (2,2,'2_2'), (2,3,'2_3');