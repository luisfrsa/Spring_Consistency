drop database if exists sd_database_1;
drop database if exists sd_database_2;
drop database if exists sd_database_3;

create database sd_database_1;
create database sd_database_2;
create database sd_database_3;


use sd_database_1;
CREATE TABLE IF NOT EXISTS `user` (
	`id` int(10) NOT NULL auto_increment,
	`nome` varchar(255),
	PRIMARY KEY( `id` )
);

use sd_database_2;
CREATE TABLE IF NOT EXISTS `user` (
	`id` int(10) NOT NULL auto_increment,
	`nome` varchar(255),
	PRIMARY KEY( `id` )
);

use sd_database_3;
CREATE TABLE IF NOT EXISTS `user` (
	`id` int(10) NOT NULL auto_increment,
	`nome` varchar(255),
	PRIMARY KEY( `id` )
);

