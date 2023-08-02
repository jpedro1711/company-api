create table tb_customer (
	id bigint not null auto_increment, 
	address varchar(255), 
	cpf varchar(255) not null unique, 
	email varchar(255) not null unique, 
	name varchar(255), 
	phone_number varchar(255), 
	primary key (id)
) engine=InnoDB;

insert into tb_customer values (1, 'Rua do pinho, 21', '145.543.123-001', 'josivaldo@gmail.com', 'Josivaldo Silva', '4199176-9876');
insert into tb_customer values (2, 'Rua do pinhão, 29', '198.523.153-001', 'marilene@gmail.com', 'Marilene Andrade', '4199777-9876');
insert into tb_customer values (3, 'Rua do abacaxi, 199', '995.543.123-001', 'eduardo@gmail.com', 'Eduardo Fernandes', '4199174-9476');



