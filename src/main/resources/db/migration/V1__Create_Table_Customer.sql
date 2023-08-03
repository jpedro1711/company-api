create table tb_customer (
	id bigint auto_increment, 
	address varchar(255), 
	email varchar(255) not null unique, 
	name varchar(255), 
	phone_number varchar(255), 
	primary key (id)
) engine=InnoDB;




