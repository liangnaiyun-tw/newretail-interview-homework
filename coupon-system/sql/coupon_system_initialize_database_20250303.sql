create database if not exists coupon_system;

create table if not exists coupon (
	id int primary key auto_increment,
    name nvarchar(200) not null,
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