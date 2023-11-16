--ѧԺ���������ѧԺ������ѧԺ����ѧԺ�������ѧԺ��ѧԺ��ţ���������ѧԺ����
create  table mgr_college(
        id int primary key,
        name varchar(50)
);
--רҵ���������ѧԺ��������̣����������̣��������ѧ���������繤�̣�רҵ��ţ���������רҵ���ƣ�����ѧԺ��ţ������
create table mgr_major(
       mid int primary key,
       mname varchar(50),
       id int CONSTRAINT FK_id REFERENCES mgr_college(id)
);
--�༶����ÿ��רҵһ���ࣨ����������༶��ţ����������༶���ƣ�����רҵ��ţ��������������������������δ��������
create table mgr_class(
       cid int primary key,
       cname varchar(30),
       mid int CONSTRAINT FK_mid REFERENCES mgr_major(mid),
       cnum int,
       rnum int,
       unum int
);
--ѧ����ѧ�ţ����������������Ա����֤���绰���༶��ţ��������
create table mgr_student(
       sid int primary key,
       sname varchar(20),
       sex varchar(10),
       idcard number(20),
       phone number(20),
       cid int CONSTRAINT FK_cid REFERENCES mgr_class(cid)
);
--����Ա������������
create table mgr_manager(
       username varchar(20),
       password number
);

insert into mgr_college values(1,'�����ѧԺ');
insert into mgr_college values(2,'����ѧԺ');
insert into mgr_college values(3,'��ѧԺ');
insert into mgr_college values(4,'�����ѧԺ');


insert into mgr_major values(1,'�������',1);
insert into mgr_major values(2,'�������ѧ����',1);
insert into mgr_major values(3,'����������',1);
insert into mgr_major values(4,'���繤��',1);
insert into mgr_major values(5,'������ѧ',2);
insert into mgr_major values(6,'���ʾ���ó��',3);
insert into mgr_major values(7,'����',4);


--�༶����ÿ��רҵһ���ࣨ����������༶��ţ����������༶���ƣ�����רҵ��ţ��������������������������δ��������
insert into mgr_class values(101,'���1��',1,4,0,4);
insert into mgr_class values(201,'�ƿ�1��',2,4,0,4);
insert into mgr_class values(301,'����1��',3,4,0,4);
insert into mgr_class values(302,'����2��',3,4,0,4);
insert into mgr_class values(401,'����1��',4,4,0,4);

--ѧ����ѧ�ţ����������������Ա����֤���绰���༶��ţ��������
insert into mgr_student values(10101,'С��','��',36736456123334362,11111111111,101);
insert into mgr_student values(10102,'С��','Ů',46494531327461656,12222222222,101);
insert into mgr_student values(10103,'С��','��',16786423288677865,13333333333,101);
insert into mgr_student values(10104,'С��','��',94123453296564878,14444444444,101);

insert into mgr_student values(20101,'С��','��',32416465334654535,15555555555,201);
insert into mgr_student values(20102,'С��','Ů',23145634515465465,16666666666,201);
insert into mgr_student values(20103,'С��','Ů',34545631453465632,17777777777,201);
insert into mgr_student values(20104,'С��','��',24356555545949628,18888888888,201);

insert into mgr_student values(30101,'С��','Ů',34578454458677466,19999999999,301);
insert into mgr_student values(30102,'С��','Ů',46945645678569564,10101010101,301);
insert into mgr_student values(30103,'С��','Ů',97445613236657645,12121212121,301);
insert into mgr_student values(30104,'С��','��',365413274564795867,13131313131,301);

insert into mgr_student values(30201,'С��','Ů',646893532415654698,14141414141,302);
insert into mgr_student values(30202,'С��','��',364578963456437664,15151515151,302);
insert into mgr_student values(30203,'СС','Ů',544453548795398546,16161616161,302);
insert into mgr_student values(30204,'С��','��',365443645345456652,17171717171,302);

insert into mgr_student values(40101,'С̷','Ů',834645427456465356,18181818181,401);
insert into mgr_student values(40102,'С��','Ů',542642347622355476,19191919191,401);
insert into mgr_student values(40103,'С��','Ů',756845534165565326,10202020202,401);
insert into mgr_student values(40104,'С��','��',164545457625676673,11212121212,401);

insert into mgr_manager values('��ɣ',111);
insert into mgr_manager values('��ɣ',222);
insert into mgr_manager values('��ɣ',333);



