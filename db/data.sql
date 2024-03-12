-- Users

insert into pistalibrebdd."user" (username, fullname, side_play, email, password) values
('mpuerto', 'Marta Puerto', 'R', 'mpuerto@gmail.com', '123456'),
('mboix', 'Miriam Boix', 'R', 'mboix@gmail.com', '123456'),
('jjceballos', 'Jose Jesus Ceballos', 'R', 'jjceballos@gmail.com', '123456'),
('vjfuster', 'Vicente Jose Fuster', 'R', 'vjfuster@gmail.com', '123456'),
('aquitana', 'Africa Quintana', 'L', 'aquitana@gmail.com', '123456'),
('dfrances', 'Desiree Frances', 'L', 'dfrances@gmail.com', '123456'),
('idelgado', 'Irene Delgado', 'L', 'mpuerto@gmail.com', '123456'),
('ppedraza', 'Placido Pedraza', 'L', 'ppedraza@gmail.com', '123456');

-- Clubs

insert into pistalibrebdd.club (name, location, email, password) values
('The Giants', '40.362796/-3.813375', 'thegiants@gmail.com', '123456'),
('Them Water Idols', '36.542447/-6.177998', 'themwateridols@gmail.com', '123456'),
('Da Motivated Soldiers', '41.481140/2.284622', 'damotivatedsoldiers@gmail.com', '123456');

-- Courts

insert into pistalibrebdd.court (club, number, indoor, price) values
(1, 1, true, 20.50),
(1, 2, true, 20.50),
(1, 3, true, 20.50),
(2, 1, false, 18.50),
(2, 2, false, 18.50),
(2, 3, false, 18.50),
(3, 1, true, 20.50),
(3, 2, false, 18.50),
(3, 3, true, 20.50);

-- Bookings

insert into pistalibrebdd.booking (court, date, p1, p2, p3, p4) values
(1, '12/03/2024-21:30', 1, 2, 3, 4),
(2, '12/03/2024-08:00', 5, 6, 7, 8),
(4, '12/03/2024-18:30', 1, 6, null, null),
(7, '12/03/2024-09:30', 5, 8, 1, null);

-- Socials

insert into pistalibrebdd.social (owner, date, text, photo) values
(1, '12/03/2024-21:30', 'Aqui con mis amigos!!!', 'https://www.granadahoy.com/2019/08/09/granada/partido-World-Padel-Tour-Granada_1380772621_104577678_1200x675.jpg'),
(2, '12/03/2024-21:30', 'Hoy dia de tranquis', null),
(3, '12/03/2024-21:30', 'Aqui con mis amigos!!!', null);

-- Messages

insert into pistalibrebdd.message (owner, text, date, target_user) values
(1, 'Hola, buenos dias', '12/03/2024-21:30', 2),
(2, 'Que tal?', '12/03/2024-21:31', 1),
(1, 'Bien y tu?', '12/03/2024-21:32', 2);