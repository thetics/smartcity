USE  smart_city;

-- -----------------------------------------------------
-- INSERT ROLES
-- -----------------------------------------------------

INSERT into `roles` (`id`, `name`)
values ('id1', 'ADMIN');
INSERT into `roles` (`id`, `name`)
values ('id12', 'MODERATOR');
INSERT into `roles` (`id`, `name`)
values ('id123', 'USER');
-- -----------------------------------------------------
-- INSERT USERS
-- -----------------------------------------------------

INSERT into `users` (`id`, `password`, `username`)
values ('firstUser', '$2a$12$iXzjdB0QPGuhY/nJYlJOO.c5nR7TNCitnfnqq4XKNgcaFmmn1um56', 'user');
-- -----------------------------------------------------
-- insert USERS_AUTHORITIES
-- -----------------------------------------------------

INSERT INTO  users_roles
values ('firstUser','id1');


-- -----------------------------------------------------
-- insert CITIES
-- -----------------------------------------------------
INSERT INTO city
values ('city1','Plovdiv','Great Historical city' );
INSERT INTO city
values ('city2','Varna','City on the cost of Black sea' );
INSERT INTO city
values ('city3','Sofia','The capital of Bulgaria' );



-- -----------------------------------------------------
-- insert HOTELS
-- -----------------------------------------------------
INSERT INTO hotel
values ('hotel1','Pravda','Great Historical hotel','random adress','city3','3' );
INSERT INTO hotel
values ('hotel2','Vega','Hotel on the cost of Black sea','random adress','city2','5' );
INSERT INTO hotel
values ('hotel3','Neptun','The hotel of Bulgaria','random adress','city1','1' );
-- -----------------------------------------------------
-- insert REVIEWS FOR HOTELS
INSERT INTO review
values ('review1','Great Historical review','HOTEL','hotel3' );
INSERT INTO review
values ('review2','Great Historical review','HOTEL','hotel2' );
INSERT INTO review
values ('review3','Great Historical review','HOTEL','hotel1' );
-- -----------------------------------------------------
-- insert RESERVATIONS FOR HOTELS
INSERT INTO reservation
values ('reservation1','Great Historical reservation','1998-02-03','1997-02-03','HOTEL','hotel3','CONFIRMED' );
INSERT INTO reservation
values ('reservation2','Great Historical reservation','1998-02-03','1997-02-03','HOTEL','hotel2','CONFIRMED' );
INSERT INTO reservation
values ('reservation3','Great Historical reservation','1998-02-03','1997-02-03','HOTEL','hotel1','CONFIRMED' );





-- -----------------------------------------------------
-- insert HOSPITALS
-- -----------------------------------------------------
INSERT INTO hospital
values ('hospital1','Tokuda','Great Historical hospital','random adress','city3');
INSERT INTO hospital
values ('hospital2','St Cherkezov','Hospital on the cost of Black sea','random adress','city2');
INSERT INTO hospital
values ('hospital3','Aleksandrovska','The hospital of Bulgaria','random adress','city1' );
-- -----------------------------------------------------
-- insert REVIEWS FOR HOSPITALS
INSERT INTO review
values ('review4','Great Historical review','HOSPITAL','hospital3' );
INSERT INTO review
values ('review5','Great Historical review','HOSPITAL','hospital2' );
INSERT INTO review
values ('review6','Great Historical review','HOSPITAL','hospital1' );
-- -----------------------------------------------------
-- insert RESERVATIONS FOR HOSPITALS
INSERT INTO reservation
values ('reservation4','Great Historical reservation','1998-02-03','1997-02-03','HOSPITAL','hospital3','CONFIRMED' );
INSERT INTO reservation
values ('reservation5','Great Historical reservation','1998-02-03','1997-02-03','HOSPITAL','hospital2','CONFIRMED' );
INSERT INTO reservation
values ('reservation6','Great Historical reservation','1998-02-03','1997-02-03','HOSPITAL','hospital1','CONFIRMED' );






-- -----------------------------------------------------
-- insert BUSINESSES
-- -----------------------------------------------------
INSERT INTO business
values ('business1','Techtopia','Great Historical business','random adress','city3');
INSERT INTO business
values ('business2','Walltopia','Business on the cost of Black sea','random adress','city2');
INSERT INTO business
values ('business3','ET Yonko Panayotov','The business of Bulgaria','random adress','city1' );
-- -----------------------------------------------------
-- insert REVIEWS FOR BUSINESSES
INSERT INTO review
values ('review7','Great Historical review','BUSINESS','business3' );
INSERT INTO review
values ('review8','Great Historical review','BUSINESS','business2' );
INSERT INTO review
values ('review9','Great Historical review','BUSINESS','business1' );
