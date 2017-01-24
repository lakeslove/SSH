#mysql数据库
create database mybatis;
use mybatis;
CREATE TABLE users(id INT PRIMARY KEY AUTO_INCREMENT, NAME VARCHAR(20), age INT);
INSERT INTO users(NAME, age) VALUES('孤傲苍狼', 27);
INSERT INTO users(NAME, age) VALUES('白虎神皇', 27);