create database if not exists coupon_system;

use coupon_system;
create table if not exists coupon (
	id int primary key auto_increment,
    name nvarchar(200) not null,
    quantity int not null,
    type int not null,
    start_date datetime not null,
    end_date datetime not null,
    created_at timestamp default current_timestamp not null
);

create table if not exists `user` (
	id int primary key auto_increment,
    name nvarchar(50) not null
);

create table if not exists user_coupon (
	id int primary key auto_increment,
    user_id int not null,
    coupon_id int not null,
    status int not null,
    used_at datetime,
    claimed_at timestamp default current_timestamp,
    foreign key (coupon_id) references coupon(id) on delete cascade,
    foreign key (user_id) references `user`(id) on delete cascade
);

create table if not exists coupon_log (
	id int primary key auto_increment,
    user_id int not null,
    coupon_id int not null,
    operation_type int not null,
    description text,
    created_at timestamp default current_timestamp,
    foreign key (coupon_id) references coupon(id),
    foreign key (user_id) references `user`(id)
);


INSERT INTO coupon (name, quantity, type, start_date, end_date)
VALUES 
('滿減券 A', 100, 1, '2025-03-01 00:00:00', '2025-03-31 23:59:59'),
('折扣券 B', 200, 2, '2025-03-05 00:00:00', '2025-04-05 23:59:59'),
('優惠券 C', 150, 3, '2025-03-10 00:00:00', '2025-03-20 23:59:59');