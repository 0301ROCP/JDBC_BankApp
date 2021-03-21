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

create TABLE accounts(
	account_id serial primary key,
	primary_owner int references users (upi),
	joint_account boolean default false,
	date_created date,
	balance money default 0,
	approved boolean default false,
	approved_by int references users (upi),
	is_open boolean default false
);

--TODO add check balance constraint, figure out foreign keys

drop table accounts;