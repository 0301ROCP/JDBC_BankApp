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
	approved_by int references users (upi),
	is_open boolean default false,
	status varchar(30)
);

--TODO add check balance constraint, figure out foreign keys

drop table accounts;

select * from accounts;


create TABLE transfers(
	transfer_id serial primary key,
	transfer_sender int references users (upi),
	transfer_receiver int references users (upi),
	amount_in_cents int default 0,
	accepted varchar(30),
	memo varchar(30),
	date_created date,
	notified_sender boolean default false,
	sender_account int references accounts (account_id),
	receiver_account int references accounts (account_id)
);

drop table transfers;

select * from transfers;


create TABLE transactions(
	transaction_id serial primary key,
	transaction_type varchar(30), -- withdraw, deposit, send, receive
	user_id int references users (upi),
	account_id int references accounts (account_id),
	transfer_id int references transfers (transfer_id),
	transaction_date date
);

