create TABLE users(
	upi serial primary key,
	username varchar(30) not null unique,
	first_name text,
	last_name text,
	is_customer boolean default false,
	is_employee boolean default false,
	user_password varchar(30) not null
);

drop table users;

select * from users;


create TABLE accounts(
	account_id serial primary key,
	account_type varchar(30),
	primary_owner int references users (upi),
	nickname varchar(30),
	joint_account boolean default false,
	date_created date,
	balance_in_cents int default 0,
	approved boolean default false,
	approved_by int references users (upi),
	is_open boolean default false,
	status varchar(30)
);

--TODO add check balance constraint, figure out foreign keys

drop table accounts;

select * from accounts;

alter table accounts add column status varchar(30);

