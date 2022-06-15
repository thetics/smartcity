USE smart_city;


-- -----------------------------------------------------
-- Table `city`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `city`;


CREATE TABLE IF NOT EXISTS `city`
(
    `id`           VARCHAR(255) UNIQUE NOT NULL ,
    `name`         VARCHAR(255),
    `general_info` text,
#     `hospital_id`  INT        NULL DEFAULT NULL,
#     `hotel_id`     INT        NULL DEFAULT NULL,
#     `business_id`  INT        NULL DEFAULT NULL,

    PRIMARY KEY (`id`)

#     CONSTRAINT `fk_hospital_city`
#         FOREIGN KEY (`hospital_id`)
#             REFERENCES `hospital` (`id`),
#     CONSTRAINT `fk_hotel_city`
#         FOREIGN KEY (`hotel_id`)
#             REFERENCES `hotel` (`id`),
#     CONSTRAINT `fk_business_city`
#         FOREIGN KEY (`business_id`)
#             REFERENCES `business` (`id`)

);


-- -----------------------------------------------------
-- Table `business`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `business`;;

CREATE TABLE IF NOT EXISTS `business`
(
    `id`          VARCHAR(255) UNIQUE NOT NULL ,
    `name`        VARCHAR(255),
    `information` text,
    `address`     VARCHAR(255),
    `city_id`     VARCHAR(255)        NULL DEFAULT NULL,

    PRIMARY KEY (`id`),
    CONSTRAINT `fk_city_business`
        FOREIGN KEY (`city_id`)
            REFERENCES `city` (`id`)

);
-- -----------------------------------------------------
-- Table `hotel`
-- -----------------------------------------------------

DROP TABLE IF EXISTS `hotel`;;

CREATE TABLE IF NOT EXISTS `hotel`
(

    `id`          VARCHAR(255) UNIQUE NOT NULL ,
    `name`        VARCHAR(255),
    `information` text,
    `address`     VARCHAR(255),
    `city_id`     VARCHAR(255)        NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_city_hotel`
        FOREIGN KEY (`city_id`)
            REFERENCES `city` (`id`)
);

-- -----------------------------------------------------
-- Table `hospital`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hospital`;

CREATE TABLE IF NOT EXISTS `hospital`
(
    `id`          VARCHAR(255) UNIQUE NOT NULL ,
    `name`        VARCHAR(255),
    `information` text,
    `address`     VARCHAR(255),
    `city_id`     VARCHAR(255)        NULL DEFAULT NULL,

    PRIMARY KEY (`id`),
    CONSTRAINT `fk_city_hospital`
        FOREIGN KEY (`city_id`)
            REFERENCES `city` (`id`)


);


# -----------------------------------------------------
# Table `review`
# -----------------------------------------------------
DROP TABLE IF EXISTS `review`;;

CREATE TABLE IF NOT EXISTS `review`
(
    `id`             VARCHAR(255) UNIQUE                             NOT NULL ,
    `description`    VARCHAR(255),
#     `business_id`    INT                                    NULL DEFAULT NULL,
#     `hotel_id`       INT                                    NULL DEFAULT NULL,
#     `hospital_id`    INT                                    NULL DEFAULT NULL,
    `institute_type` ENUM ('HOTEL', 'HOSPITAL', 'BUSINESS') NOT NULL,
    `institute_id`   VARCHAR(255)                                    NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
#     CONSTRAINT `fk_business_review`
#         FOREIGN KEY (`business_id`)
#             REFERENCES `business` (`id`),
#     CONSTRAINT `fk_hotel_review`
#         FOREIGN KEY (`hotel_id`)
#             REFERENCES `hotel` (`id`),
#     CONSTRAINT `fk_hospital_review`
#         FOREIGN KEY (`hospital_id`)
#             REFERENCES `hospital` (`id`)

);

-- -----------------------------------------------------
-- Table `reservation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `reservation`;

CREATE TABLE IF NOT EXISTS `reservation`
(
    `id`          VARCHAR(255) UNIQUE NOT NULL ,
    `description` VARCHAR(255),
    `date_to`      DATE,
    `date_from`    DATE,
#     `hospital_id` VARCHAR(255)        NULL DEFAULT NULL,
#     `hotel_id`    VARCHAR(255)        NULL DEFAULT NULL,
    `institute_type` ENUM ('HOTEL', 'HOSPITAL', 'BUSINESS') NOT NULL,
    `institute_id`   VARCHAR(255)                                    NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
#     CONSTRAINT `fk_hospital_reservation`
#         FOREIGN KEY (`hospital_id`)
#             REFERENCES `hospital` (`id`),
#     CONSTRAINT `fk_hotel_reservation`
#         FOREIGN KEY (`hotel_id`)
#             REFERENCES `hotel` (`id`)

);

-- -----------------------------------------------------
-- Table `users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `users`;

CREATE TABLE IF NOT EXISTS `users`
(
    id       VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,

    PRIMARY KEY (`id`)

);

-- -----------------------------------------------------
-- Table `roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `roles`;

CREATE TABLE IF NOT EXISTS `roles`
(
    id        VARCHAR(255) NOT NULL,
    name VARCHAR(255) NULL ,

        PRIMARY KEY (`id`)
);
-- -----------------------------------------------------
-- Table `users_authorities`
-- -----------------------------------------------------

DROP TABLE IF EXISTS `users_roles`;

CREATE TABLE IF NOT EXISTS `users_roles`
(
    user_id        varchar(255) not null,
    role_id varchar(255) not null,

    PRIMARY KEY (user_id,role_id),
    CONSTRAINT key_role_id
        FOREIGN KEY (role_id) REFERENCES smart_city.roles (id),
        CONSTRAINT  key_user_id
            FOREIGN KEY (user_id) REFERENCES smart_city.users (id)
);
-- -----------------------------------------------------
-- Table `permissions`
-- -----------------------------------------------------
-- -----------------------------------------------------
DROP TABLE IF EXISTS `permissions`;

CREATE TABLE IF NOT EXISTS `permissions`
(
    id        VARCHAR(255) NOT NULL ,
    name VARCHAR(255) NULL ,

    PRIMARY KEY (`id`)
);




-- -----------------------------------------------------
-- Table `authorities_permissions`
-- -----------------------------------------------------

DROP TABLE IF EXISTS `roles_permissions`;

CREATE TABLE IF NOT EXISTS `roles_permissions`
(
    permission_id        varchar(255) not null,
    role_id varchar(255) not null,

    PRIMARY KEY (permission_id,role_id),
    CONSTRAINT key_role_id_num2
        FOREIGN KEY (role_id) REFERENCES smart_city.roles (id),
    CONSTRAINT  key_permission_id
        FOREIGN KEY (permission_id) REFERENCES smart_city.permissions (id)
);