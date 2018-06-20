-- permissions
insert into permission (id, name) values (1, 'CREATE');
insert into permission (id, name) values (2, 'READ');

-- roles
insert into roles (id, name) values (1, 'ROLE_ADMIN');
insert into roles (id, name) values (2, 'ROLE_AGENT');
insert into roles (id, name) values (3, 'ROLE_USER');

-- users
insert into users (id, email, enabled, password) 
values (UUID(), 'bokih.95@gmail.com', 1, '$2a$10$8bH9N7jBJo6XNlfR.mKhYeD9yoWGJJXJ.rOaCgftdn5SdSwZaOlfq');
insert into users (id, email, enabled, password) 
values (UUID(), 'darkokirin@gmail.com', 1, '$2a$10$uZzqU5v.G10pFySkUUIbXuNDukV//c19dBD/hiqbYSgyP8pbhTxh2');


-- role-permissions
insert into role_permissions (permission_id, role_id) values (1, 1);
insert into role_permissions (permission_id, role_id) values (2, 1);

-- user-roles
insert into user_roles (user_id, role_id) 
values ((select id from users where email = 'bokih.95@gmail.com'), 1);
insert into user_roles (user_id, role_id) 
values ((select id from users where email = 'darkokirin@gmail.com'), 1);

-- accomodation types
insert into accomodation_type (type) values ('apartment');
insert into accomodation_type (type) values ('bed&breakfast');
insert into accomodation_type (type) values ('hotel');

-- additional services
insert into additional_service (name) values ('Parking');
insert into additional_service (name) values ('Wifi');
insert into additional_service (name) values ('Breakfast');
insert into additional_service (name) values ('Polupansion');
insert into additional_service (name) values ('Pansion');
insert into additional_service (name) values ('TV');
insert into additional_service (name) values ('Mini kuhinja/kuhinja');
insert into additional_service (name) values ('Privatno kupatilo');
insert into additional_service (name) values ('Helipad');

-- category
insert into category (category) values ('*');
insert into category (category) values ('**');
insert into category (category) values ('***');
insert into category (category) values ('****');
insert into category (category) values ('*****');
insert into category (category) values ('uncategorized');

-- test data
insert into accomodation (id, address, capacity, city, country, description, name, type_id, category_id, agent)
values (1, 'Lozionicka 12', 4, 'Novi Sad', 'Srbija', 'Opis', 'Sheraton', 1, 4, 'CN=a1') ;
insert into accomodation_image values (1, 'url1', 1);
insert into accomodation_image values (2, 'url2', 1);
insert into term(id, end_date, price, start_date, accomodation_id, reserved, visited) values (1,'2018-08-02',120,'2018-06-15', 1, 0, 0);
insert into term(id, end_date, price, start_date, accomodation_id, reserved, visited) values (2,'2018-09-03',250,'2018-08-10',1, 0, 0);
insert into term(id, end_date, price, start_date, accomodation_id, reserved, visited) values (3,'2018-10-02',99,'2018-09-20',1, 0, 0);
insert into accomodation_services values (1,1);
insert into accomodation_services values (1,3);
insert into accomodation_services values (1,4);
insert into accomodation_services values (1,7);

insert into accomodation (id, address, capacity, city, country, description, name, type_id, category_id)
values (2, 'Radnicka 41', 10, 'Chicago', 'USA', 'jako lepo', 'Hilton', 2, 2) ;
insert into accomodation_image values (3, 'url1', 2);
insert into accomodation_image values (4, 'url2', 2);
insert into term(id, end_date, price, start_date, accomodation_id, reserved, visited) values (4,'2018-07-13',322,'2018-06-23',2, 0, 0);
insert into term(id, end_date, price, start_date, accomodation_id, reserved, visited) values (5,'2018-12-12',400,'2018-10-20',2, 0, 0);
insert into term(id, end_date, price, start_date, accomodation_id, reserved, visited) values (6,'2018-09-02',215,'2018-08-25',2, 0, 0);
insert into accomodation_services values (2,2);
insert into accomodation_services values (2,3);
insert into accomodation_services values (2,8);
insert into accomodation_services values (2,5);

insert into accomodation (id, address, capacity, city, country, description, name, type_id, category_id)
values (3, 'Highbury 12', 6, 'London', 'UK', 'kisa pada trava raste', 'Shack', 3, 1) ;
insert into accomodation_image values (5, 'url1', 3);
insert into accomodation_image values (6, 'url2', 3);
insert into term(id, end_date, price, start_date, accomodation_id, reserved, visited) values (7,'2018-06-29',100,'2018-06-16',3, 0, 0);
insert into term(id, end_date, price, start_date, accomodation_id, reserved, visited) values (8,'2018-10-01',320,'2018-09-22',3, 0, 0);
insert into term(id, end_date, price, start_date, accomodation_id, reserved, visited) values (9,'2018-07-12',277,'2018-07-02',3, 0, 0);
insert into accomodation_services values (3,1);
insert into accomodation_services values (3,2);
insert into accomodation_services values (3,3);
insert into accomodation_services values (3,4);

insert into accomodation (id, address, capacity, city, country, description, name, type_id, category_id)
values (4, 'Campes 12', 4, 'Mallorca', 'Spain', 'more kokteli poljupci vreli', 'Espana paradise', 1, 4) ;
insert into accomodation_image values (7, 'url1', 4);
insert into accomodation_image values (8, 'url2', 4);
insert into term(id, end_date, price, start_date, accomodation_id, reserved, visited) values (10,'2018-06-22',205,'2018-06-10',4, 0, 0);
insert into term(id, end_date, price, start_date, accomodation_id, reserved, visited) values (11,'2018-09-03',350,'2018-08-25',4, 0, 0);
insert into term(id, end_date, price, start_date, accomodation_id, reserved, visited) values (12,'2018-10-12',198,'2018-10-01',4, 0, 0);
insert into accomodation_services values (4,8);
insert into accomodation_services values (4,1);
insert into accomodation_services values (4,7);
insert into accomodation_services values (4,2);

insert into accomodation (id, address, capacity, city, country, description, name, type_id, category_id,agent)
values (5, 'Šonzelize', 2, 'Paris', 'France', 'skupo ali kratko', 'French connection', 2, 5, 'CN=a1') ;
insert into accomodation_image values (9, 'url1', 5);
insert into accomodation_image values (10, 'url2', 5);
insert into term(id, end_date, price, start_date, accomodation_id, reserved, visited) values (13,'2018-09-15',500,'2018-09-05',5, 0, 0);
insert into term(id, end_date, price, start_date, accomodation_id, reserved, visited) values (14,'2018-11-07',620,'2018-10-28',5, 0, 0);
insert into term(id, end_date, price, start_date, accomodation_id, reserved, visited) values (15,'2018-12-12',542,'2018-12-01',5, 0, 0);
insert into accomodation_services values (5,5);
insert into accomodation_services values (5,3);
insert into accomodation_services values (5,1);
insert into accomodation_services values (5,8);

insert into accomodation (id, address, capacity, city, country, description, name, type_id, category_id,agent)
values (6, 'Sirtaki 13', 6, 'Paralia', 'Greece', 'grcka za sirotinju', 'Vila Janos', 3, 2, 'CN=a1') ;
insert into accomodation_image values (11, 'url1', 6);
insert into accomodation_image values (12, 'url2', 6);
insert into term(id, end_date, price, start_date, accomodation_id, reserved, visited) values (16,'2018-06-22',85,'2018-06-10',6, 0, 0);
insert into term(id, end_date, price, start_date, accomodation_id, reserved, visited) values (17,'2018-08-14',125,'2018-08-01',6, 0, 0);
insert into term(id, end_date, price, start_date, accomodation_id, reserved, visited) values (18,'2018-09-20',99,'2018-09-10',6, 0, 0);
insert into accomodation_services values (6,8);
insert into accomodation_services values (6,4);
insert into accomodation_services values (6,6);
insert into accomodation_services values (6,2);