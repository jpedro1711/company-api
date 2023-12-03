create table tb_customer (
	id bigint auto_increment, 
	address varchar(255),
	phone_number varchar(255),
	email varchar(255) not null unique, 
	name varchar(255),
	gender varchar(255),
	country varchar(255),
	city varchar(255),
	credit_card_type varchar(255),
	children_count varchar(255),
	is_married BOOLEAN,
	salary FLOAT,
	primary key (id)
) engine=InnoDB;




