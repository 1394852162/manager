/*��Ա��*/
create table Tbb_Employee(
EmpId int  not null primary key,
EmpNo varchar(20) not null,
EmpName varchar(40) not null,
EmpPassword varchar(20) not null,
DeptId int not null,
EmpBirth varchar(30),
EmpStatus1 int not null,/*1��ְ 10��ְ 2ɾ��*/
EmpStatus2 int not null,
EmpNote varchar(100),
CreateTime date ,
UpdateTime date 
)

/*���ű�*/
create table Tbb_Dept(
DeptId int   not null primary key,
DeptNo varchar(20) not null,
DeptName varchar(40) not null,
Status int not null,
DeptNote varchar(100),
CreateTime date,
UpdateTime date
)

/*���α�*/

create table Tbb_Batch (
BatId int    not null primary key,
BatNo varchar(10) not null,
BatName varchar(30),
BatBeginTime date not null,
BatEndTime date not null,
BatTicketNum int not null,
Status int not null,
CreateTime date ,
UpdateTime date 
)

/*���ñ�*/
create table Tbb_Collar (
CollId int  not null primary key,
BatId int not null, --����ID
BatEndTime date not null,--��Ч��(���ν���ʱ��)
CollTime date,  --����ʱ��
EmpId int  ,  --����Ա��ID
CollNum int , --��������
CollNote varchar(100),  --��ע
Status int not null,
CreateTime date,
UpdateTime date

)

/*VIP�������*/
create table Tbb_VipTicket(
VipAddId int  not null primary key, --VIP ID
VipAddTime datetime not null, --���VIPƱ��ʱ��
EmpId int not null, --������
VipEMpID int not null,  --VIP��
VipAddNum int not null, --VIP����ȡ������
VipAddNote varchar(100),  --��ע
Status int,
CreateTime date,
UpdateTime date
)

