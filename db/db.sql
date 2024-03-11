CREATE DATABASE pistalibrebdd;

USE pistalibrebdd;

CREATE TABLE USER (
    id int not null auto_increment,
    username varchar(255) not null UNIQUE,
    fullname varchar(500) not null,
    side_play char(1),
    password varchar(255) not null,
    PRIMARY KEY (id)
);

CREATE TABLE CLUB (
    id int not null auto_increment,
    name varchar(255) not null,
    location varchar(255) not null,
    password varchar(255) not null,
    PRIMARY KEY (id)
);

CREATE TABLE COURT (
    id int not null auto_increment,
    club int not null,
    number int not null,
    indoor bool, 
    price decimal(10, 2),
    PRIMARY KEY (id),
    CONSTRAINT UN_CLUB UNIQUE (id, club),
    FOREIGN KEY (club) REFERENCES CLUB(id)
);

CREATE TABLE BOOKING (
    id int not null auto_increment,
    court int not null,
    date varchar(300) not null,
    p1 int not null,
    p2 int,
    p3 int,
    p4 int,
    PRIMARY KEY (id),
    CONSTRAINT UN_BOOKING UNIQUE (id, court),
    FOREIGN KEY (court) REFERENCES COURT(id),
    FOREIGN KEY (p1) REFERENCES USER(id),
    FOREIGN KEY (p2) REFERENCES USER(id),
    FOREIGN KEY (p3) REFERENCES USER(id),
    FOREIGN KEY (p4) REFERENCES USER(id)
);

CREATE TABLE SOCIAL (
    id int not null auto_increment,
    user int not null,
    date varchar(300) not null,
    text varchar(300) not null,
    photo varchar(500),
    PRIMARY KEY (id),
    FOREIGN KEY (user) REFERENCES USER(id)
);

CREATE TABLE MESSAGE (
	id int not null auto_increment,
	USER int NOT NULL,
	text varchar(300) NOT NULL,
	date varchar(300) NOT NULL,
	target_user int NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (USER) REFERENCES USER(id),
	FOREIGN KEY (target_user) REFERENCES USER(id)
);