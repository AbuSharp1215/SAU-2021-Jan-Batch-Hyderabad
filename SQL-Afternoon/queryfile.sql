-- author Sheik Abudhahir K
create schema markets;

create table category(
	category_code int primary key,
    category_name varchar(30) not null
);

insert into category values(1,'mobile phones');
insert into category values(2,'laptops');
insert into category values(3,'mobile accessories');
insert into category values(4,'laptop accessories');

select * from category;

create table product(
	product_code int primary key,
    product_name varchar(30) not null,
    unit_price int not null,
    category_code int,
    foreign key (category_code) references category (category_code)
);

insert into product values(1,'One plus node',28000,1);
insert into product values(2,'Samsung galaxy m21',20000,1);
insert into product values(3,'Redmi 9 prime',15000,1);
insert into product values(4,'Dell inspiron 15',45000,2);
insert into product values(5,'Lenovo ideapad',40000,2);
insert into product values(6,'Power bank',3000,3);
insert into product values(7,'Headset',2000,3);
insert into product values(8,'USB cable',100,3);
insert into product values(9,'Keyboard',500,4);
insert into product values(10,'Mouse',350,4);
insert into product values(11,'External harddisk',3000,4);
insert into product values(12,'Speaker',1000,4);

select * from product;

create table customer(
	customer_id int not null,
    customer_name varchar(30) not null,
    location_code int not null,
    dob date not null,
    gender char not null,
    mobile_number bigint(10),
    primary key (customer_id),
    check (gender in('M','F')),
    foreign key (location_code) references location(location_code)
);

insert into customer values(1,'ravi',1,'1997-10-10','M',9872362811);
insert into customer values(2,'ram',1,'1995-05-21','M',9871361822);
insert into customer values(3,'nazriya',2,'2000-11-12','F',9887263411);
insert into customer values(4,'sri divya',2,'1997-10-12','F',9972462811);
insert into customer values(5,'harish',3,'2002-01-09','M',7872562811);
insert into customer values(6,'suresh',3,'1999-03-10','M',8972364671);

select * from customer;

create table sales_executive(
	executive_id int not null,
    executive_name varchar(30) not null,
    location_code int not null,
    dob date not null,
    gender char not null,
    mobile_number bigint(10),
    primary key (executive_id),
    check (gender in('M','F')),
    foreign key (location_code) references location (location_code)
);

insert into sales_executive values(1,'ramesh',1,'1992-11-01','M',9872362827);
insert into sales_executive values(2,'ganesh',1,'1991-02-02','M',8877362827);
insert into sales_executive values(3,'rajesh',2,'1991-02-28','M',7872365127);
insert into sales_executive values(4,'siva',3,'1992-01-11','M',9872362872);

select * from sales_executive;



create table location(
	location_code int not null,
    location_name varchar(30),
    primary key(location_code)
);

insert into location values(1,'chennai');
insert into location values(2,'banglore');
insert into location values(3,'hyderabad');

select * from location;

create table sales(
	product_code int not null,
    customer_id int not null,
    executive_id int not null,
    date_of_purchase date not null,
    no_of_units int not null,
    primary key(product_code, customer_id, executive_id, date_of_purchase),
    foreign key (product_code) references product(product_code),
    foreign key (customer_id) references customer(customer_id),
    foreign key (executive_id) references sales_executive(executive_id)
);

truncate table sales;

select count(*) from sales;

insert into sales values(7,1,1,'2021-01-02',3);
insert into sales values(8,1,1,'2021-01-02',20);

insert into sales values(1,1,1,'2021-01-03',4);
insert into sales values(5,2,2,'2021-01-03',9);
insert into sales values(6,4,3,'2021-01-03',10);
insert into sales values(1,6,4,'2021-01-03',2);

insert into sales values(1,2,2,'2021-01-04',5);
insert into sales values(8,4,3,'2021-01-04',7);
insert into sales values(10,5,4,'2021-01-04',8);
insert into sales values(4,6,4,'2021-01-04',3);

insert into sales values(6,2,2,'2021-01-05',9);
insert into sales values(2,4,3,'2021-01-05',6);
insert into sales values(1,3,3,'2021-01-05',5);

insert into sales values(7,3,3,'2021-01-06',7);
insert into sales values(10,1,2,'2021-01-06',12);
insert into sales values(4,5,4,'2021-01-06',3);

insert into sales values(5,1,2,'2021-01-07',9);
insert into sales values(7,2,2,'2021-01-07',6);

insert into sales values(8,3,3,'2021-01-08',7);
insert into sales values(2,4,3,'2021-01-08',8);
insert into sales values(6,5,4,'2021-01-08',3);

insert into sales values(10,1,1,'2021-01-09',7);
insert into sales values(12,2,2,'2021-01-09',10);
insert into sales values(4,3,3,'2021-01-09',10);
insert into sales values(2,5,4,'2021-01-09',10);


select * from sales;

SET SQL_SAFE_UPDATES=0;

-- sold products in specific location datewise
select p.product_code,p.product_name,customer_name, date_of_purchase, sum(no_of_units) as total from 
sales s,product p,customer c
where s.product_code=p.product_code and s.customer_id=c.customer_id and location_code=3
group by s.product_code,date_of_purchase order by date_of_purchase;

-- Write a query to retrieve the most sold product per day in a specific location (take any location) in last week.

select product_code,product_name,date_of_purchase,max(total) from 
(select p.product_code,p.product_name,customer_name, date_of_purchase, sum(no_of_units) as total from 
sales s,product p,customer c
where s.product_code=p.product_code and s.customer_id=c.customer_id and location_code=3 and 
date_of_purchase between date_sub("2021-01-09",interval 6 day) and current_date()
group by s.product_code,date_of_purchase order by date_of_purchase, total desc
 ) product_sold group by date_of_purchase;

-- Write a query to list all the sales persons details along with the count of products sold by them (if any) till current date.

select se.* , count(s.executive_id) as product_sold_count from sales_executive se, sales s 
where se.executive_id = s.executive_id
group by s.executive_id;



