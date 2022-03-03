-- 创建 t_file_info 表
create table t_file_info (
id VARCHAR(128) NOT NULL,
user_id VARCHAR(128) NOT NULL,
file_name VARCHAR(100) NOT NULL,
file_size VARCHAR(30) ,
upload_time DATETIME ,
PRIMARY KEY ( id )
);