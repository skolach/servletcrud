delete from `order`;

delete from `route`;

 INSERT into `order`(`user_id`, `created_at`, `start_at`, `end_at`, `price`, `route_discount`, `user_discount`, `cash`)
 VALUES (1, now(), now(), now(), 100, 10, 20, 72);
 	insert into `route`(`order_id`, `point_order`, `point_name`)
 		values (1, 1, '111');
 	insert into `route`(`order_id`, `point_order`, `point_name`)
 		values (1, 2, '222');

 INSERT into `order`(`user_id`, `created_at`, `start_at`, `end_at`, `price`, `route_discount`, `user_discount`, `cash`)
 VALUES (2, now(), now(), now(), 40, 5, 6, 30);
 	insert into `route`(`order_id`, `point_order`, `point_name`)
 		values (2, 1, '111');
 	insert into `route`(`order_id`, `point_order`, `point_name`)
 		values (2, 2, '333');

 INSERT into `order`(`user_id`, `created_at`, `start_at`, `end_at`, `price`, `route_discount`, `user_discount`, `cash`)
 VALUES (2, now(), now(), null, null, null, null, null);
 	insert into `route`(`order_id`, `point_order`, `point_name`) values (3, 1, '111');
 	insert into `route`(`order_id`, `point_order`, `point_name`) values (3, 2, '333');