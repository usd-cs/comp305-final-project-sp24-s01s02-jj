CREATE TABLE `Person`(
                         `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                         `first_name` TINYTEXT NOT NULL,
                         `last_name` TINYTEXT NOT NULL,
                         `birthdate` DATE NOT NULL,
                         `phone_number` TINYTEXT NOT NULL,
                         `username` TINYTEXT NOT NULL UNIQUE,
                         `organization_email` TINYTEXT NOT NULL UNIQUE,
                         `secondary_email` TINYTEXT NOT NULL UNIQUE,
                         `is_active` TINYINT(1) NOT NULL,
                         `department` BIGINT NOT NULL
);

CREATE TABLE `Student`(
                          `id` BIGINT UNSIGNED NOT NULL PRIMARY KEY,
                          `major` MEDIUMTEXT NOT NULL,
                          `grade` BIGINT NOT NULL
);

CREATE TABLE `Faculty`(
                          `id` BIGINT UNSIGNED NOT NULL PRIMARY KEY,
                          `office_location` BIGINT NOT NULL,
                          `has_tenure` TINYINT(1) NOT NULL
);

CREATE TABLE `Building`(
                           `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                           `name` MEDIUMTEXT NOT NULL,
                           `address` MEDIUMTEXT NOT NULL,
                           `floors` TINYINT NOT NULL,
                           `abbreviation` TINYTEXT NOT NULL UNIQUE
);

CREATE TABLE `Department`(
                             `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                             `name` MEDIUMTEXT NOT NULL
);

CREATE TABLE `Employee`(
                           `id` BIGINT UNSIGNED NOT NULL PRIMARY KEY,
                           `start_date` DATE NOT NULL,
                           `hourly_wage` DOUBLE NOT NULL,
                           `manager` BIGINT NULL
);

CREATE TABLE `Alumni`(
                         `id` BIGINT UNSIGNED NOT NULL PRIMARY KEY,
                         `graduation_date` DATE NOT NULL,
                         `degree_type` BIGINT NOT NULL
);

CREATE TABLE `Room`(
                        `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                        `building` BIGINT NOT NULL,
                        `room_number` MEDIUMINT NOT NULL
);