CREATE TABLE IF NOT EXISTS `staff_number` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `gender` varchar(255),
    `phone` varchar(255),
    `name` varchar(255),
	PRIMARY KEY(`id`),
);


CREATE TABLE IF NOT EXISTS `entrance_work` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `duration` varchar(255),
    `date` bigint DEFAULT NULL,
    `work_number` INT,
    `activity_range` varchar(255),
    `evaluation` VARCHAR (255) DEFAULT NULL
	PRIMARY KEY(`id`),
);


CREATE TABLE IF NOT EXISTS `inspection_report` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `duty_person` varchar(255),
    `inspection_person` varchar(255),
    `date` bigint DEFAULT NULL,
    `condition` varchar(255),
    `summary` varchar(255),
    `abnormal` varchar(255),
    `maintenance` varchar(255),
	PRIMARY KEY(`id`),
);