-- create schema
create schema `coupons_db_tomer`;

-- company table
create table `company`(
`id` int auto_increment,
`name` varchar(100),
`email` varchar(100),
`password` varchar(100),
primary key(id)
);

-- customer table
create table `customer`(
`id` int auto_increment,
`first_name` varchar(100),
`last_name` varchar(100),
`email` varchar(100),
`password` varchar(100),
primary key(id)
);

-- coupon table
create table `coupon`(
`id` int auto_increment,
`company_id` int,
`category` enum('FOOD', 'ELECTRONICS', 'HOME', 'CLOTHING', 'CAMPING', 'VACATION'),
`title` varchar(100),
`description` varchar(250),
`start_date` date,
`end_date` date,
`amount` int,
`price` double,
`image` varchar(100),
primary key(id),
foreign key(company_id) references company(id)
);

-- join table so we can see which customer bought which coupon
create table customer_coupon(
customer_id int,
coupon_id int,
primary key(customer_id, coupon_id),
foreign key(customer_id) references customer(id),
foreign key(coupon_id) references coupon(id)
);