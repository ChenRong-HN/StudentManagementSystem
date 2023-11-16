--学院：（计算机学院，材料学院，商学院，外国语学院）学院编号（主键），学院名称
create  table mgr_college(
        id int primary key,
        name varchar(50)
);
--专业：（计算机学院：软件工程，物联网工程，计算机科学技术，网络工程）专业编号（主键），专业名称，所属学院编号（外键）
create table mgr_major(
       mid int primary key,
       mname varchar(50),
       id int CONSTRAINT FK_id REFERENCES mgr_college(id)
);
--班级：（每个专业一个班（计算机））班级编号（主键），班级名称，所属专业编号（外键），总人数，报道人数，未报到人数
create table mgr_class(
       cid int primary key,
       cname varchar(30),
       mid int CONSTRAINT FK_mid REFERENCES mgr_major(mid),
       cnum int,
       rnum int,
       unum int
);
--学生：学号（主键），姓名，性别，身份证，电话，班级编号（外键），
create table mgr_student(
       sid int primary key,
       sname varchar(20),
       sex varchar(10),
       idcard number(20),
       phone number(20),
       cid int CONSTRAINT FK_cid REFERENCES mgr_class(cid)
);
--管理员：姓名，密码
create table mgr_manager(
       username varchar(20),
       password number
);

insert into mgr_college values(1,'计算机学院');
insert into mgr_college values(2,'材料学院');
insert into mgr_college values(3,'商学院');
insert into mgr_college values(4,'外国语学院');


insert into mgr_major values(1,'软件工程',1);
insert into mgr_major values(2,'计算机科学技术',1);
insert into mgr_major values(3,'物联网工程',1);
insert into mgr_major values(4,'网络工程',1);
insert into mgr_major values(5,'材料力学',2);
insert into mgr_major values(6,'国际经济贸易',3);
insert into mgr_major values(7,'日语',4);


--班级：（每个专业一个班（计算机））班级编号（主键），班级名称，所属专业编号（外键），总人数，报道人数，未报到人数
insert into mgr_class values(101,'软件1班',1,4,0,4);
insert into mgr_class values(201,'计科1班',2,4,0,4);
insert into mgr_class values(301,'物联1班',3,4,0,4);
insert into mgr_class values(302,'物联2班',3,4,0,4);
insert into mgr_class values(401,'网络1班',4,4,0,4);

--学生：学号（主键），姓名，性别，身份证，电话，班级编号（外键），
insert into mgr_student values(10101,'小军','男',36736456123334362,11111111111,101);
insert into mgr_student values(10102,'小红','女',46494531327461656,12222222222,101);
insert into mgr_student values(10103,'小刚','男',16786423288677865,13333333333,101);
insert into mgr_student values(10104,'小李','男',94123453296564878,14444444444,101);

insert into mgr_student values(20101,'小陈','男',32416465334654535,15555555555,201);
insert into mgr_student values(20102,'小黄','女',23145634515465465,16666666666,201);
insert into mgr_student values(20103,'小张','女',34545631453465632,17777777777,201);
insert into mgr_student values(20104,'小赵','男',24356555545949628,18888888888,201);

insert into mgr_student values(30101,'小芳','女',34578454458677466,19999999999,301);
insert into mgr_student values(30102,'小刘','女',46945645678569564,10101010101,301);
insert into mgr_student values(30103,'小杨','女',97445613236657645,12121212121,301);
insert into mgr_student values(30104,'小亮','男',365413274564795867,13131313131,301);

insert into mgr_student values(30201,'小周','女',646893532415654698,14141414141,302);
insert into mgr_student values(30202,'小凯','男',364578963456437664,15151515151,302);
insert into mgr_student values(30203,'小小','女',544453548795398546,16161616161,302);
insert into mgr_student values(30204,'小王','男',365443645345456652,17171717171,302);

insert into mgr_student values(40101,'小谭','女',834645427456465356,18181818181,401);
insert into mgr_student values(40102,'小唐','女',542642347622355476,19191919191,401);
insert into mgr_student values(40103,'小糖','女',756845534165565326,10202020202,401);
insert into mgr_student values(40104,'小二','男',164545457625676673,11212121212,401);

insert into mgr_manager values('刘桑',111);
insert into mgr_manager values('罗桑',222);
insert into mgr_manager values('陈桑',333);



