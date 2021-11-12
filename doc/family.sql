 -- 创建 t_member 表
create table t_xml_node(
   id VARCHAR(128) NOT NULL,
   name VARCHAR(60) NOT NULL,
   `value` VARCHAR(30) ,
   `level` int ,
   type VARCHAR(30) NOT NULL,
   parentNode VARCHAR(128) ,
   PRIMARY KEY ( id )
);

insert into t_xml_node (id, name, value, level, type, parentNode) values ("id111","Family", null, 1, "Object", null);
insert into t_xml_node (id, name, value, level, type, parentNode) values ("id112","Members",null, 2, "List", "id111");
insert into t_xml_node (id, name, value, level, type, parentNode) values ("id113","Member",null, 3, "Object", "id112");
insert into t_xml_node (id, name, value, level, type, parentNode) values ("id114","Job",null, 4, "Object", "id113");
insert into t_xml_node (id, name, value, level, type, parentNode) values ("id115","Member",null, 3, "Object", "id111");
insert into t_xml_node (id, name, value, level, type, parentNode) values ("id116","Job",null, 4, "Object", "id115");
insert into t_xml_node (id, name, value, level, type, parentNode) values ("id117","Member",null, 3, "Object", "id112");
insert into t_xml_node (id, name, value, level, type, parentNode) values ("id118","Job",null, 4, "Object","id113");
insert into t_xml_node (id, name, value, level, type, parentNode) values ("id119","Address",null, 2, "Object", "id111");
insert into t_xml_node (id, name, value, level, type, parentNode) values("id120","familyId","111", null, "Element", "id111");
insert into t_xml_node (id, name, value, level, type, parentNode) values("id121","familyName","cc的家", null, "Element", "id111");
insert into t_xml_node (id, name, value, level, type, parentNode) values("id122","memberId","1", null, "Element", "id113");
insert into t_xml_node (id, name, value, level, type, parentNode) values("id123","name","qh", null, "Element", "id113");
insert into t_xml_node (id, name, value, level, type, parentNode) values("id124","name","工人", null, "Element", "id114");
insert into t_xml_node (id, name, value, level, type, parentNode) values("id125","company","北京工区", null, "Element", "id114");
insert into t_xml_node (id, name, value, level, type, parentNode) values("id126","salary","8000", null, "Element", "id114");
insert into t_xml_node (id, name, value, level, type, parentNode) values("id127","memberId","2", null, "Element", "id115");
insert into t_xml_node (id, name, value, level, type, parentNode) values("id128","name","yy", null, "Element", "id115");
insert into t_xml_node (id, name, value, level, type, parentNode) values("id129","name","家政中介人员", null, "Element", "id116");
insert into t_xml_node (id, name, value, level, type, parentNode) values("id130","company","北京家政", null, "Element", "id116");
insert into t_xml_node (id, name, value, level, type, parentNode) values("id131","salary","8000", null, "Element", "id116");
insert into t_xml_node (id, name, value, level, type, parentNode) values("id132","province","北京", null, "Element", "id119");
insert into t_xml_node (id, name, value, level, type, parentNode) values("id133","city","北京", null, "Element", "id119");
insert into t_xml_node (id, name, value, level, type, parentNode) values("id134","district","海淀区", null, "Element", "id119");
insert into t_xml_node (id, name, value, level, type, parentNode) values("id135","location","上地11号", null, "Element", "id119");
 