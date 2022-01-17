CREATE DATABASE IF NOT EXISTS mybatis_mapper_examples CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
use mybatis_mapper_examples;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) default '' not null,
  `password` varchar(50) default '' not null,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

insert into user values(1,'admin','admin');
insert into user values(2,'user','user');

DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderTime` varchar(255) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `uid` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

insert into orders values(1, "2022-01-01", 1, 1);
insert into orders values(2, "2022-01-02", 1, 1);
insert into orders values(3, "2022-01-03", 1, 2);
insert into orders values(4, "2022-01-04", 1, 2);
