create database 'warehouse_manger';

create table products(id int not null auto_increment, product_name varchar(255), product_individual_number varchar(20) ,product_description varchar(255), product_manufacturer varchar(50), base_price DECIMAL(12,2), primary key (id));
create table clients(id int not null auto_increment, name varchar(255), short_name varchar(30), country varchar(60), city varchar(60), address varchar(60), primary key (id));
create table suppliers(id int not null auto_increment, name varchar(255), short_name varchar(30), country varchar(60), city varchar(60), address varchar(60), primary key (id));

create table deliveries(id int not null auto_increment, product_id int not null, supplier_id int not null, product_individual_number varchar(20), unit_price DECIMAL(12,2), units bigint, transaction_value DECIMAL(12,2), primary key (id), foreign key (product_id) references products(id), foreign key (supplier_id) references suppliers(id));
create table orders(id int not null auto_increment, product_id int not null, client_id int not null, product_individual_number varchar(20), unit_price DECIMAL(12,2), units bigint, transaction_value DECIMAL(12,2), primary key (id), foreign key (product_id) references products(id), foreign key (client_id) references clients(id));
create table discounts(id int not null auto_increment, product_id int not null, product_individual_number varchar(20), description varchar(20), since date, until date, discount_size DECIMAL(4,3), primary key(id), foreign key(product_id) references products(id));

create table warehouse(id int not null auto_increment, product_id int not null, product_individual_number varchar(20), units bigint, primary key(id), foreign key(product_id) references products(id));