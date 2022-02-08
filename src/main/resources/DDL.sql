
drop table if exists review cascade;
drop table if exists credit_card_info cascade;
drop table if exists shipping_information cascade;
drop table if exists transaction_keeper cascade;
drop table if exists ordr cascade;
drop table if exists cart cascade;
drop table if exists usr cascade;
drop table if exists book_to_buy cascade;
drop table if exists book_price_data cascade;
drop table if exists book cascade;
drop table if exists genre cascade;



create table if not exists genre (
     id serial unique not null primary key,
      genre varchar
);

create table if not exists book (
     book_id serial unique not null primary key,
     ISBN varchar not null,
     book_name varchar not null,
     synopsis varchar not null,
     author varchar not null,
     genre_id integer references genre,
     year integer not null,
     publisher varchar not null,
	 book_image varchar not null
);

create table if not exists book_price_data (
	book_price_data_id serial unique not null primary key,
    sale_is_active boolean not null,
	sale_discount_rate float8,
	book_price float8,
	quantity_on_hand integer,
	featured boolean,
	book_id integer references book
);

create table if not exists book_to_buy (
     book_to_buy_id serial unique not null primary key,
     book_id integer references book,
     quantity_to_buy integer not null
 
);

create table if not exists usr (
	user_id serial unique not null primary key,
	username varchar, 
	passwrd varchar, 
	first_name varchar, 
	last_name varchar, 
	email varchar, 
	birthday date, 
	user_role varchar
);

create table if not exists cart (
     cart_id serial unique not null primary key, 
     user_id integer references usr,
     book_to_buy_id integer references book_to_buy  
);

create table if not exists ordr (
     order_id serial unique not null primary key,
     cc_info_id integer ,
     cart_id integer references cart 
);

create table if not exists transaction_keeper (
	transaction_id serial unique not null primary key, 
	order_id integer references ordr, 
	total_price float8, 
	transaction_date timestamp
);

create table if not exists shipping_information (
    shipping_info_id serial unique not null primary key, 
	first_name varchar not null,
	last_name varchar not null,
	street_name varchar not null,
	city varchar not null,
	state varchar not null,
	zip_code varchar not null,
	delivery_date varchar not null
);

create table if not exists credit_card_info (
    cc_info_id serial unique not null primary key,
	name_on_card varchar, 
	card_number integer, 
	cvv integer, 
	expiration_month integer, 
	expiration_year integer, 
	billing_zip integer, 
	credit_card_type varchar, 
	shipping_info_id integer references shipping_information 
);


create table if not exists review (
	review_id serial unique not null primary key,
	book_id integer references book, 
	user_id integer references usr, 
	review_title varchar,
	review_text varchar(500),
	rating_one integer,
	rating_two integer,
	rating_three integer,
	sent_at timestamp 
);


alter table if exists ordr
add foreign key(cc_info_id) references credit_card_info(cc_info_id);


