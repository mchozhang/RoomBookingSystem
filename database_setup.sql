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
    version integer,
    name    varchar(30),
    suburb  varchar(30),
    address varchar(50)
);

insert into hotels (version, name, suburb, address)
values (1, 'Marriott Hotel', 'Melbourne CBD', 'Corner Exhibition &, Lonsdale St');
insert into hotels (version, name, suburb, address)
values (1, 'Crown Hotel', 'South Melbourne', 'Crown Metropol 8 Whiteman St');


create table users
(
    id        serial primary key,
    version   integer,
    username  varchar(30),
    password  varchar(30),
    full_name varchar(30),
    hotelId   integer references hotels (id),
    role      varchar(30)
);

insert into users (version, username, password, full_name, role)
values (1, 'alice', 'admin', 'Alice Parker', 'customer');
insert into users (version, username, password, full_name, role)
values (1, 'bob', 'admin', 'Bob Miles', 'customer');
insert into users (version, username, password, hotelId, role)
values (1, 'chris', 'admin', 1, 'staff');
insert into users (version, username, password, hotelId, role)
values (1, 'dion', 'admin', 2, 'staff');


create table services
(
    id      serial primary key,
    version integer,
    name    varchar(30)
);

insert into services (version, name)
values (1, 'Parking');
insert into services (version, name)
values (1, 'Wifi');
insert into services (version, name)
values (1, 'Restaurant');
insert into services (version, name)
values (1, 'Massage');
insert into services (version, name)
values (1, 'Swimming Pool');

create table hotels_services
(
    id        serial primary key,
    version   integer,
    hotelId   integer references hotels (id),
    serviceId integer references services (id)
);

insert into hotels_services (version, hotelId, serviceId)
values (1, 1, 1);
insert into hotels_services (version, hotelId, serviceId)
values (1, 1, 2);
insert into hotels_services (version, hotelId, serviceId)
values (1, 1, 3);
insert into hotels_services (version, hotelId, serviceId)
values (1, 1, 4);
insert into hotels_services (version, hotelId, serviceId)
values (1, 1, 5);
insert into hotels_services (version, hotelId, serviceId)
values (1, 2, 2);
insert into hotels_services (version, hotelId, serviceId)
values (1, 2, 3);
insert into hotels_services (version, hotelId, serviceId)
values (1, 2, 4);
insert into hotels_services (version, hotelId, serviceId)
values (1, 2, 5);

create table catalogues
(
    id          serial primary key,
    version     integer,
    name        varchar(50),
    description varchar(1024),
    hotelId     integer references hotels (id),
    price       float
);

insert into catalogues (version, name, hotelId, description, price)
values (1, 'Executive', 1, 'Executive lounge access, Guest room, 1 Queen', 260);
insert into catalogues (version, name, hotelId, description, price)
values (1, 'Grand Deluxe', 1, 'Guest room, 1 Queen, Top floor', 300);
insert into catalogues (version, name, hotelId, description, price)
values (1, 'King Suite', 1, '1 Bedroom Larger Suite, 1 King, City view, Corner room', 500);
insert into catalogues (version, name, hotelId, description, price)
values (1, 'DELUXE KING OR TWIN ROOM', 2,
        'The Deluxe Room at Crown Towers Melbourne features a spacious bathroom with double marble vanities and deep soaking bath with television, separate shower, walk-in dressing room and the latest in-room technology.',
        309);
insert into catalogues (version, name, hotelId, description, price)
values (1, 'CROWN DIRECT EXCLUSIVE OFFER', 2,
        'Treat yourself to an overnight stay at one of our properties and we''ll throw in buffet breakfast for two, valet parking and a cheeky bottle of sparkling just for you.',
        359);
insert into catalogues (version, name, hotelId, description, price)
values (1, 'EXCLUSIVE VILLA OFFER', 2,
        'Experience the extraordinary with a stay in a magnificent Crown Towers Melbourne Villa including access to the exclusive Crystal Club, valet parking and a bottle of French Champagne.',
        970);

create table rooms
(
    id          serial primary key,
    version     integer,
    catalogueId integer references catalogues (id),
    number      varchar(30)
);

insert into rooms (version, catalogueId, number)
values (1, 1, '101');
insert into rooms (version, catalogueId, number)
values (1, 1, '102');
insert into rooms (version, catalogueId, number)
values (1, 1, '103');
insert into rooms (version, catalogueId, number)
values (1, 1, '104');
insert into rooms (version, catalogueId, number)
values (1, 1, '105');
insert into rooms (version, catalogueId, number)
values (1, 2, '201');
insert into rooms (version, catalogueId, number)
values (1, 2, '202');
insert into rooms (version, catalogueId, number)
values (1, 2, '203');
insert into rooms (version, catalogueId, number)
values (1, 2, '204');
insert into rooms (version, catalogueId, number)
values (1, 2, '205');
insert into rooms (version, catalogueId, number)
values (1, 2, '206');
insert into rooms (version, catalogueId, number)
values (1, 3, '301');
insert into rooms (version, catalogueId, number)
values (1, 3, '302');
insert into rooms (version, catalogueId, number)
values (1, 3, '303');
insert into rooms (version, catalogueId, number)
values (1, 3, '304');
insert into rooms (version, catalogueId, number)
values (1, 4, 'A101');
insert into rooms (version, catalogueId, number)
values (1, 4, 'A102');
insert into rooms (version, catalogueId, number)
values (1, 4, 'A103');
insert into rooms (version, catalogueId, number)
values (1, 5, 'B501');
insert into rooms (version, catalogueId, number)
values (1, 5, 'B502');
insert into rooms (version, catalogueId, number)
values (1, 5, 'B503');
insert into rooms (version, catalogueId, number)
values (1, 6, 'C201');
insert into rooms (version, catalogueId, number)
values (1, 6, 'C202');


create table bookings
(
    id        serial primary key,
    version   integer,
    userId    integer references users (id),
    roomId    integer references rooms (id),
    startDate date,
    endDate   date,
    status    varchar(50),
    price     float
);
