-- initialize database, create tables and built-in objects
drop table if exists hotels cascade;
drop table if exists users cascade;
drop table if exists services cascade;
drop table if exists hotels_services cascade;
drop table if exists catalogues cascade;
drop table if exists categories cascade;
drop table if exists rooms cascade;
drop table if exists bookings cascade;

create table hotels
(
    id      serial primary key,
    name    varchar(30),
    suburb  varchar(30),
    address varchar(50)
);

insert into hotels (name, suburb, address)
values ('Marriott Hotel', 'Melbourne CBD', 'Corner Exhibition &, Lonsdale St');
insert into hotels (name, suburb, address)
values ('Crown Hotel', 'South Melbourne', 'Crown Metropol 8 Whiteman St');


create table users
(
    id        serial primary key,
    username  varchar(30),
    password  varchar(30),
    full_name varchar(30),
    hotelId   integer references hotels (id),
    role      varchar(30)
);

insert into users (username, password, full_name, role)
values ('alice', 'admin', 'Alice Parker', 'customer');
insert into users (username, password, full_name, role)
values ('bob', 'admin', 'Bob Miles', 'customer');
insert into users (username, password, hotelId, role)
values ('chris', 'admin', 1, 'staff');
insert into users (username, password, hotelId, role)
values ('dion', 'admin', 2, 'staff');


create table services
(
    id   serial primary key,
    name varchar(30)
);

insert into services (name)
values ('Parking');
insert into services (name)
values ('Wifi');
insert into services (name)
values ('Restaurant');
insert into services (name)
values ('Massage');
insert into services (name)
values ('Swimming Pool');

create table hotels_services
(
    id        serial primary key,
    hotelId   integer references hotels (id),
    serviceId integer references services (id)
);

insert into hotels_services (hotelId, serviceId)
values (1, 1);
insert into hotels_services (hotelId, serviceId)
values (1, 2);
insert into hotels_services (hotelId, serviceId)
values (1, 3);
insert into hotels_services (hotelId, serviceId)
values (1, 4);
insert into hotels_services (hotelId, serviceId)
values (1, 5);
insert into hotels_services (hotelId, serviceId)
values (2, 2);
insert into hotels_services (hotelId, serviceId)
values (2, 3);
insert into hotels_services (hotelId, serviceId)
values (2, 4);
insert into hotels_services (hotelId, serviceId)
values (2, 5);

create table catalogues
(
    id          serial primary key,
    name        varchar(50),
    description varchar(1024),
    hotelId     integer references hotels (id),
    price       float
);

insert into catalogues (name, hotelId, description, price)
values ('Executive', 1, 'Executive lounge access, Guest room, 1 Queen', 260);
insert into catalogues (name, hotelId, description, price)
values ('Grand Deluxe', 1, 'Guest room, 1 Queen, Top floor', 300);
insert into catalogues (name, hotelId, description, price)
values ('King Suite', 1, '1 Bedroom Larger Suite, 1 King, City view, Corner room', 500);
insert into catalogues (name, hotelId, description, price)
values ('DELUXE KING OR TWIN ROOM', 2,
        'The Deluxe Room at Crown Towers Melbourne features a spacious bathroom with double marble vanities and deep soaking bath with television, separate shower, walk-in dressing room and the latest in-room technology.',
        309);
insert into catalogues (name, hotelId, description, price)
values ('CROWN DIRECT EXCLUSIVE OFFER', 2,
        'Treat yourself to an overnight stay at one of our properties and we''ll throw in buffet breakfast for two, valet parking and a cheeky bottle of sparkling just for you.',
        359);
insert into catalogues (name, hotelId, description, price)
values ('EXCLUSIVE VILLA OFFER', 2,
        'Experience the extraordinary with a stay in a magnificent Crown Towers Melbourne Villa including access to the exclusive Crystal Club, valet parking and a bottle of French Champagne.',
        970);

create table rooms
(
    id          serial primary key,
    catalogueId integer references catalogues (id),
    number      varchar(30)
);

insert into rooms (catalogueId, number)
values (1, '101');
insert into rooms (catalogueId, number)
values (1, '102');
insert into rooms (catalogueId, number)
values (1, '103');
insert into rooms (catalogueId, number)
values (1, '104');
insert into rooms (catalogueId, number)
values (1, '105');
insert into rooms (catalogueId, number)
values (2, '201');
insert into rooms (catalogueId, number)
values (2, '202');
insert into rooms (catalogueId, number)
values (2, '203');
insert into rooms (catalogueId, number)
values (2, '204');
insert into rooms (catalogueId, number)
values (2, '205');
insert into rooms (catalogueId, number)
values (2, '206');
insert into rooms (catalogueId, number)
values (3, '301');
insert into rooms (catalogueId, number)
values (3, '302');
insert into rooms (catalogueId, number)
values (3, '303');
insert into rooms (catalogueId, number)
values (3, '304');

create table bookings
(
    id        serial primary key,
    userId    integer references users (id),
    roomId      integer references rooms (id),
    startDate date,
    endDate   date,
    status    varchar(50),
    price     float,
    version   integer
);
