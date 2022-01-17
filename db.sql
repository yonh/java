CREATE DATABASE mybatis_start CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
use mybatis_start;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) default '' not null,
  `password` varchar(50) default '' not null,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

insert into user values(1,'admin','admin');
insert into user values(2,'user','user');
