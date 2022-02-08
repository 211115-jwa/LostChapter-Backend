

insert into usr 
(username, passwrd, first_name, last_name, email, birthday, user_role) 
values 
('kstra', 'pass', 'Kalila', 'Straneo', 'kstra0@washington.edu', '12/26/1967', 'admin'),					--1
('tbot1', 'pass', 'Tony', 'Botti', 'tbotti1@mozilla.com', '7/16/1996', 'customer'),						--2
('gdenziloe2', 'pass', 'Guido', 'Denziloe', 'gdenziloe2@reuters.com', '5/22/1968', 'customer'),			--3
('cdanza3', 'pass', 'Cate', 'Danza', 'cdanza3@google.cn', '1/6/1992', 'customer'),						--4
('lrambadt4', 'pass', 'Livy', 'Rambadt', 'lrambadt4@whitehouse.gov', '3/29/1965', 'customer'),			--5
('avobes5', 'pass', 'Arluene', 'Vobes', 'avobes5@addtoany.com', '10/25/1982', 'customer'),				--6
('bmaltster6', 'pass', 'Benedick', 'Maltster', 'bmaltster6@123.com', '6/13/1973', 'customer'),			--7
('epigny7', 'pass', 'Ellie', 'Pigny', 'epigny7@businessinsider.com', '3/19/1978', 'customer'),			--8
('fauger8', 'pass', 'Federico', 'Auger', 'fauger8@sfgate.com', '11/26/1980', 'customer'),				--9
('rboole9', 'pass', 'Raphael', 'Boole', 'rboole9@omniture.com', '11/6/1970', 'customer'),				--10
('jscarda', 'pass', 'Jasper', 'Scard', 'jscarda@weebly.com', '3/8/1970', 'customer'),					--11
('mlodekeb', 'pass', 'Melesa', 'Lodeke', 'mlodekeb@amazon.co.jp', '8/27/1974', 'customer'),				--12
('odull', 'pass', 'Orv', 'Dullingham', 'odullinghamc@mtv.com', '5/22/1995', 'customer'),				--13
('ashutlerd', 'pass', 'Adams', 'Shutler', 'ashutlerd@wikipedia.org', '12/6/1962', 'customer'),			--14
('beverlye', 'pass', 'Bert', 'Everly', 'beverlye@msn.com', '9/20/1973', 'customer'),					--15
('walvarad', 'pass', 'Wilie', 'Alvarado', 'walvarad@upenn.edu', '6/15/1993', 'customer'),				--16
('tbulleyg', 'pass', 'Tobey', 'Bulley', 'tbulleyg@posterous.com', '3/29/1962', 'customer'),				--17
('amel', 'pass', 'Alexandra', 'Melanaphy', 'amelanaphyh@umn.edu', '7/10/1988', 'customer'),				--18
('dcluetti', 'pass', 'Derron', 'Cluett', 'dcluetti@google.ru', '10/5/1994', 'customer'),				--19
('bpruej', 'pass', 'Basile', 'Prue', 'bpruej@bing.com', '9/25/1990', 'customer');						--20
​

insert into genre 
(genre)
values 
('unknown');


insert into book_price_data 
(sale_is_active, sale_discount_rate, book_price, quantity_on_hand, featured, book_id)
values 
( true, .05, 30.00, 4, false, 1),		--1
( false, null, 28.97, 3, true, 2),		--2
( true, .1, 43.99, 10, true, 3),		--3
( false, null, 15.01, 7, false, 4),		--4
( true, .15, 74.88, 2, true, 5);		--5



insert into book_to_buy 
(book_id, quantity_to_buy)
values 
( 2, 1),			--1
( 4, 3),			--2
( 1, 1),			--3
( 1, 1),			--4
( 3, 2);			--5



insert into cart 
(user_id, book_to_buy_id)
values 
( 5, 6),			--1
( 5, 7),			--2
( 18, 8),			--3
( 18, 9),			--4
( 18, 10);			--5




insert into ordr ​
(cc_info_id, cart_id)
values 
( 12, 11),			--1
( 11, 12),			--2
( 10, 13),			--3
( 9, 14),			--4
( 8, 15);			--5




​
insert into transaction_keeper 
(order_id, total_price,  transaction_date) 
values 
(2, '22.47', '5/13/2021'),		--1
(4, '22.82', '9/23/2021'),		--2
(5, '37.55', '11/19/2021');		--3

​
​
​
​
insert into shipping_information 
(first_name, last_name, street_name, city, state, zip_code, delivery_date) 
values 
('Giulietta', 'Leaves', '6 Fulton Place', 'Pasadena', 'California', '91186', 'express'), 				--1
('Sammy', 'Dorie', '7 Scott Parkway', 'Los Angeles', 'California', '90015', 'express'), 				--2
('Gerome', 'Pavlenko', '103 Garrison Street', 'Troy', 'Michigan', '48098', 'express'), 					--3
('Hyacinthie', 'Orteaux', '168 New Castle Parkway', 'Orange', 'California', '92867', 'priority'), 		--4
('Jareb', 'Greenhaugh', '7249 Spenser Alley', 'Van Nuys', 'California', '91411', 'express'); 			--5
​
​
​
insert into credit_card_info 
(name_on_card, card_number, cvv, expiration_month, expiration_year, billing_zip, credit_card_type, shipping_info_id) 
values 
('Brandy Faas', '3537697616419643', 372, 11, 22, '43521', 'jcb', 1),					--1
('Neda Pindell', '4508561723705067', 671, 12, 27, '07821', 'visa-electron', 2),			--2
('Ariella Hagergham', '337941395956401', 224, 5, 27, '57428', 'americanexpress', 3),	--3
('Orelie Kirley', '3543324220284485', 154, 5, 25, '34567', 'jcb', 4),					--4
('Shandra Traher', '3531873960626747', 256, 8, 27, '98765', 'jcb', 5),					--5
('Yard Barson', '560221220805483518', 381, 3, 24, '67584', 'china-unionpay', 1),		--6
('Red Thebe', '3583340671027372', 391, 1, 27, '34213', 'jcb', 2),						--7
('Corrie Boor', '3567124100953170', 876, 8, 25, '09123', 'jcb', 3),						--8
('Egor Trickey', '349201821192048', 747, 8, 24, '90210', 'americanexpress', 4),			--9
('Jamaal Aloshechkin', '3569773663535358', 832, 6, 23, '12345', 'jcb', 5),				--10
('Lexine Heaton', '4175007610054716', 475, 2, 22, '56445', 'visa-electron', 1),			--11
('Gare Pohlke', '3587164396636325', 939, 2, 24, '87768', 'jcb', 2),						--12
('Holden Suggey', '201855565263203', 686, 4, 23, '69455', 'diners-club-enroute', 3),	--13
('Feodora McQuaid', '3585420940317602', 494, 8, 27, '09909', 'jcb', 4),					--14
('Broderic Noell', '3539337111971990', 221, 5, 27, '98765', 'jcb', 5);					--15
​
​	
insert into review 
(​book_id, user_id, review_title, review_text, rating_one, rating_two, rating_three, sent_at)
values 
(1, 17, 'The Hobbit Sucks!', 'What can I say, I hated it.', 1, 1, 1, null),
(33, 6, 'The Return Rocks', 'Dude its the best book Ive ever read', 5, 5, 5, null);
	
	
	
​
​
​