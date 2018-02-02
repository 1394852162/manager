/*人员表*/
create table Tbb_Employee(
EmpId int  not null primary key,
EmpNo varchar(20) not null,
EmpName varchar(40) not null,
EmpPassword varchar(20) not null,
DeptId int not null,
EmpBirth varchar(30),
EmpStatus1 int not null,/*1在职 10离职 2删除*/
EmpStatus2 int not null,
EmpNote varchar(100),
CreateTime date ,
UpdateTime date 
)

/*部门表*/
create table Tbb_Dept(
DeptId int   not null primary key,
DeptNo varchar(20) not null,
DeptName varchar(40) not null,
Status int not null,
DeptNote varchar(100),
CreateTime date,
UpdateTime date
)

/*批次表*/

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

/*领用表*/
create table Tbb_Collar (
CollId int  not null primary key,
BatId int not null, --批次ID
BatEndTime date not null,--有效期(批次结束时间)
CollTime date,  --领用时间
EmpId int  ,  --领用员工ID
CollNum int , --领用数量
CollNote varchar(100),  --备注
Status int not null,
CreateTime date,
UpdateTime date

)

/*VIP补入界面*/
create table Tbb_VipTicket(
VipAddId int  not null primary key, --VIP ID
VipAddTime datetime not null, --添加VIP票的时间
EmpId int not null, --操作人
VipEMpID int not null,  --VIP人
VipAddNum int not null, --VIP人领取的数量
VipAddNote varchar(100),  --备注
Status int,
CreateTime date,
UpdateTime date
)

