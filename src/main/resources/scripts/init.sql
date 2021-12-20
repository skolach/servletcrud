delete from `order`;

delete from `route`;

INSERT into `order`(
        `user_id`,
        `created_at`,
        `start_at`,
        `end_at`,
        `price`,
        `route_discount`,
        `user_discount`,
        `cash`
    )
VALUES (1, now(), now(), now(), 100, 10, 20, 72);

INSERT into `order`(
        `user_id`,
        `created_at`,
        `start_at`,
        `end_at`,
        `price`,
        `route_discount`,
        `user_discount`,
        `cash`
    )
VALUES (2, now(), now(), now(), 40, 5, 6, 30);