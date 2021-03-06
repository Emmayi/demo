CREATE TABLE IF NOT EXISTS `staff_number` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `gender` varchar(255),
    `phone` varchar(255),
    `name` varchar(255),
    `email` VARCHAR(20),
	PRIMARY KEY(`id`)
);

CREATE TABLE IF NOT EXISTS `entrance_work` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `duration` varchar(255),
    `date` bigint DEFAULT NULL,
    `work_number` INT,
    `activity_range` varchar(255),
    `evaluation` VARCHAR (255) DEFAULT NULL,
	PRIMARY KEY(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `emergency_plan` (
    `emergency_id` INT NOT NULL AUTO_INCREMENT,
    `name` varchar(255) NOT NULL,
    `category` varchar(255),
    `level` INT,
    `associated_event_type` varchar(255),
    `content` varchar(255),
    `department` varchar(255),
    `release_date` bigint,
    `release_number` varchar(255),
    `issued` varchar(255),
    `signer` varchar(255),
    `file` varchar(255),
    PRIMARY KEY(`emergency_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `emergency_supplies` (
    `supply_id` INT NOT NULL AUTO_INCREMENT,
    `name` varchar(255),
    `category` varchar(255),
    `quantity` INT,
    `model` varchar(255),
    `purchase_date` bigint,
    `manufacturer` varchar(255),
    `manufacture_date` bigint,
    `valid_until` bigint,
    `use_description` varchar(255),
    `performance_description` varchar(255),
    `affiliation` varchar(255),
    `location` varchar(255),
    PRIMARY KEY(`supply_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `inspection_report` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `duty_person` varchar(255),
    `inspection_person` varchar(255),
    `create_date` bigint DEFAULT NULL,
    `calendar_date` bigint DEFAULT NULL,
    `state` varchar(255),
    `summary` varchar(255),
    `abnormal` varchar(255),
    `maintenance` varchar(255),
    `image` varchar(255),
    `video` varchar(255),
	PRIMARY KEY(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `inspection_plan` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `inspection_person` varchar(255),
    `create_date` bigint DEFAULT NULL,
    `inspection_date` bigint DEFAULT NULL,
    PRIMARY KEY(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `inspection_daily` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `inspection_person` varchar(255),
    `time` bigint DEFAULT NULL,
    `work` varchar(255) DEFAULT NULL,
    `path` VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `inspection_equip` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    `last_time` bigint DEFAULT NULL,
    `content` VARCHAR(255) DEFAULT NULL,
    `period` VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;