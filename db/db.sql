CREATE schema pistalibrebdd;

CREATE TABLE pistalibrebdd.USER (
    id serial,
    username varchar(255) not null UNIQUE,
    fullname varchar(500) not null,
    photo varchar(500),
    side_play char(1),
    email varchar(255) not null,
    password varchar(255) not null,
    PRIMARY KEY (id)
);

CREATE TABLE pistalibrebdd.CLUB (
    id serial,
    name varchar(255) not null,
    location varchar(255) not null,
    photo varchar(500),
    email varchar(255) not null,
    password varchar(255) not null,
    PRIMARY KEY (id)
);

CREATE TABLE pistalibrebdd.COURT (
    id serial,
    club int not null,
    number int not null,
    indoor bool, 
    price decimal(10, 2),
    PRIMARY KEY (id),
    CONSTRAINT UN_CLUB UNIQUE (number, club),
    FOREIGN KEY (club) REFERENCES pistalibrebdd.CLUB(id)
);

CREATE TABLE pistalibrebdd.BOOKING (
    id serial,
    court int not null,
    date varchar(300) not null,
    p1 int not null,
    p2 int,
    p3 int,
    p4 int,
    PRIMARY KEY (id),
    CONSTRAINT UN_BOOKING UNIQUE (date, court),
    FOREIGN KEY (court) REFERENCES pistalibrebdd.COURT(id),
    FOREIGN KEY (p1) REFERENCES pistalibrebdd.USER(id),
    FOREIGN KEY (p2) REFERENCES pistalibrebdd.USER(id),
    FOREIGN KEY (p3) REFERENCES pistalibrebdd.USER(id),
    FOREIGN KEY (p4) REFERENCES pistalibrebdd.USER(id)
);

CREATE TABLE pistalibrebdd.SOCIAL (
    id serial,
    owner int not null,
    date varchar(300) not null,
    text varchar(300) not null,
    photo varchar(500),
    PRIMARY KEY (id),
    FOREIGN KEY (owner) REFERENCES pistalibrebdd.USER(id)
);

CREATE TABLE pistalibrebdd.MESSAGE (
	id serial,
	owner int NOT NULL,
	text varchar(300) NOT NULL,
	date varchar(300) NOT NULL,
	target_user int NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (owner) REFERENCES pistalibrebdd.USER(id),
	FOREIGN KEY (target_user) REFERENCES pistalibrebdd.USER(id)
);