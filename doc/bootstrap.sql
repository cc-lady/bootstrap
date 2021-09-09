 -- 创建t_user表
create table t_user(
   id INT NOT NULL AUTO_INCREMENT,
   user_name VARCHAR(20) NOT NULL,
   pwd VARCHAR(20) NOT NULL,
   mobile_phone VARCHAR(11),
   address VARCHAR(100),
   note VARCHAR(100),
   email VARCHAR(200),
   role CHAR(1),
   PRIMARY KEY ( id )
);

 -- t_user用户表增加一条数据
INSERT INTO `t_user` VALUES (1, 'cc', '123456', '18810424140', 'beijing', 'no.1', 'chenchen_hardwork@163.com', '1');


 -- 创建t_email表
create table t_email(
   id INT NOT NULL AUTO_INCREMENT,
   user_name VARCHAR(20) NOT NULL,
   email VARCHAR(200),
   role CHAR(1),
   PRIMARY KEY ( id )
);