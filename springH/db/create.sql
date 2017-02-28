#mysql数据库
DROP DATABASE IF EXISTS springMVC;
create database springMVC DEFAULT CHARACTER SET utf8 ;
use springMVC;

DROP TABLE IF EXISTS user;
CREATE TABLE user(
id BIGINT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(20) NOT NULL,
password VARCHAR(20) NOT NULL,
slogan VARCHAR(200),
create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
update_date TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS user_type;
CREATE TABLE user_type(
id INT PRIMARY KEY AUTO_INCREMENT,
user_type VARCHAR(20) NOT NULL
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS user_message;
CREATE TABLE user_message(
id BIGINT PRIMARY KEY AUTO_INCREMENT,
user_id BIGINT,
user_type_id INT,
interest VARCHAR(20),
create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
update_date TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
FOREIGN KEY(user_type_id) REFERENCES user_type(id),
FOREIGN KEY(user_id) REFERENCES user(id) ON DELETE CASCADE
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS essay;
CREATE TABLE essay(
id BIGINT PRIMARY KEY AUTO_INCREMENT,
user_id BIGINT,
title VARCHAR(20) NOT NULL,
content text,
flag INT DEFAULT 0,
create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
update_date TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
FOREIGN KEY(user_id) REFERENCES user(id) ON DELETE CASCADE
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

#插入数据
INSERT INTO `springMVC`.`user` (`name`, `password`, `slogan`) VALUES ('曲1', 'password', 'sb的宣言');
INSERT INTO `springMVC`.`user` (`name`, `password`, `slogan`) VALUES ('曲2', 'password', 'sb的宣言');
INSERT INTO `springMVC`.`user` (`name`, `password`, `slogan`) VALUES ('曲3', 'password', 'sb的宣言');
INSERT INTO `springMVC`.`user` (`name`, `password`, `slogan`) VALUES ('曲4', 'password', 'sb的宣言');
INSERT INTO `springMVC`.`user` (`name`, `password`, `slogan`) VALUES ('曲5', 'password', 'sb的宣言');
INSERT INTO `springMVC`.`user` (`name`, `password`, `slogan`) VALUES ('曲6', 'password', 'sb的宣言');
INSERT INTO `springMVC`.`user` (`name`, `password`, `slogan`) VALUES ('曲7', 'password', 'sb的宣言');
INSERT INTO `springMVC`.`user` (`name`, `password`, `slogan`) VALUES ('曲1', 'password', 'sb的宣言');
INSERT INTO `springMVC`.`user` (`name`, `password`, `slogan`) VALUES ('曲2', 'password', 'sb的宣言');
INSERT INTO `springMVC`.`user` (`name`, `password`, `slogan`) VALUES ('曲3', 'password', 'sb的宣言');
INSERT INTO `springMVC`.`user` (`name`, `password`, `slogan`) VALUES ('曲4', 'password', 'sb的宣言');
INSERT INTO `springMVC`.`user` (`name`, `password`, `slogan`) VALUES ('曲5', 'password', 'sb的宣言');
INSERT INTO `springMVC`.`user` (`name`, `password`, `slogan`) VALUES ('曲6', 'password', 'sb的宣言');
INSERT INTO `springMVC`.`user` (`name`, `password`, `slogan`) VALUES ('曲7', 'password', 'sb的宣言');
INSERT INTO `springMVC`.`user` (`name`, `password`, `slogan`) VALUES ('曲1', 'password', 'sb的宣言');
INSERT INTO `springMVC`.`user` (`name`, `password`, `slogan`) VALUES ('曲2', 'password', 'sb的宣言');
INSERT INTO `springMVC`.`user` (`name`, `password`, `slogan`) VALUES ('曲3', 'password', 'sb的宣言');
INSERT INTO `springMVC`.`user` (`name`, `password`, `slogan`) VALUES ('曲4', 'password', 'sb的宣言');
INSERT INTO `springMVC`.`user` (`name`, `password`, `slogan`) VALUES ('曲5', 'password', 'sb的宣言');
INSERT INTO `springMVC`.`user` (`name`, `password`, `slogan`) VALUES ('曲6', 'password', 'sb的宣言');
INSERT INTO `springMVC`.`user` (`name`, `password`, `slogan`) VALUES ('曲7', 'password', 'sb的宣言');
