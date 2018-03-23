/*人员表*/
CREATE TABLE Tbb_Employee (
  EmpId       INT         NOT NULL PRIMARY KEY,
  EmpNo       VARCHAR(20) NULL, /*可以为NULL*/
  EmpName     VARCHAR(40) NOT NULL,
  EmpPassword VARCHAR(20) NOT NULL,
  DeptId      INT         NULL, /*可以为NULL*/
  EmpBirth    VARCHAR(30),
  EmpStatus1  INT         NOT NULL, /*1在职 10退休 -1删除*/
  EmpStatus2  INT         NOT NULL, /*是否允许使用本系统 1.允许  0.不允许*/
  EmpNote     VARCHAR(100),
  CreateTime  DATE,
  UpdateTime  DATE,
  EmpStatus3 int,                /*是否赋予VIP劵查询权限 1.是 0.否 新加字段*/
  EmpStatus4 int,                /*是否赋予职工劵开票权限 1.是 0.否 新加字段*/
  EmpStatus5 int,                /*是否赋予批次权限 1.是 0.否 新加字段*/
  EmpStatus6 int,                /*是否赋予人员维护权限 1.是 0.否 新加字段*/
  EmpStatus7 int,                /*是否赋予部门维护权限 1.是 0.否 新加字段*/
  EmpStatus8 int,                /*是否赋予核销查询权限 1.是 0.否 新加字段*/
  EmpStatus9 int,               /*是否赋予VIP劵添加权限 1.是 0.否 新加字段*/
  EmpStatus10 int                /*是否赋予给退休员工添加领票权限*/
)

/*部门表*/
CREATE TABLE Tbb_Dept (
  DeptId     INT         NOT NULL PRIMARY KEY,
  DeptNo     VARCHAR(20) NOT NULL,
  DeptName   VARCHAR(40) NOT NULL,
  Status     INT         NOT NULL,/*1.在用 0.删除*/
  DeptNote   VARCHAR(100),
  CreateTime DATE,
  UpdateTime DATE
)

/*批次表*/
CREATE TABLE Tbb_Batch (
  BatId        INT         NOT NULL PRIMARY KEY,
  BatNo        VARCHAR(20) NOT NULL,
  BatName      VARCHAR(40),
  BatBeginTime DATE        NOT NULL,
  BatEndTime   DATE        NOT NULL,
  BatTicketNum INT         NOT NULL,
  Status       INT         NOT NULL,/*1.在用 -1.删除*/
  BatNote      VARCHAR(100),
  CreateTime   DATE,
  UpdateTime   DATE,
  Status2      INT         -- /*1=在职的能领取,0=在职离职的都能领取*/
)

/*领用表*/
CREATE TABLE Tbb_Collar (
  CollId     INT  NOT NULL PRIMARY KEY,
  CollNo     varchar(20),
  BatId      INT  NOT NULL, --批次ID
  BatEndTime DATE NOT NULL, --有效期(批次结束时间)
  CollTime   DATE, --领用时间
  EmpId      INT, --领用员工ID
  CollNum    INT, --领用数量
  CollNote   VARCHAR(100), --备注
  Status     INT  NOT NULL, /*-1删除，1未出票，0已出票,查询参数传10查询全部数据*/
  CreateTime DATE,
  UpdateTime DATE

)

/*VIP补入界面*/
CREATE TABLE Tbb_VipTicket (
  VipAddId   INT      NOT NULL PRIMARY KEY, --VIP ID
  VipTicNo   varchar(20), --新加备用暂时不显示
  VipAddTime DATE NOT NULL, --添加VIP票的时间
  EmpId      INT      NOT NULL, --操作人
  VipEmpID   INT      NOT NULL, --VIP领取人
  VipAddNum  INT      NOT NULL, --VIP人领取的数量
  VipAddNote VARCHAR(100), --备注
  Status     INT,/*1.在用 0.删除*/
  CreateTime DATE,
  UpdateTime DATE
)


insert into Tbb_Employee
  select  1, '1001', 'admin' , 'password',1, '2018-02-12' , 1 , 1 ,'增加人员' ,  '2018-2-26'  , '2018-2-26',   1

insert into Tbb_Dept select 1,'2001','Vip部门',1,'特殊账号请勿修改删除','2018-2-26',null




SELECT *
FROM Tbb_Employee;
SELECT *
FROM Tbb_Dept;

SELECT * FROM Tbb_Batch;
SELECT * FROM Tbb_VipTicket;



select x.EmpId,x.EmpNo,x.EmpName, x.BatId, x.BatTicketNum, y.Qty

from

  ( select a.BatId as BatId, b.EmpId, b.EmpNo, b.EmpName, a.BatTicketNum
    from Tbb_Batch a, Tbb_Employee b
    where BatBeginTime< getdate()
          and BatEndTime> getdate()
  ) x,

  (
    select BatId, EmpId, sum(CollNum) as Qty
    from Tbb_Collar
    group by EmpId, BatId
  ) y
where x.BatId *= y.BatId
      and x.EmpId *= y.EmpId


SELECT Tbb_Collar.BatId,Tbb_Collar.EmpId,SUM(Tbb_Collar.CollNum) FROM Tbb_Collar GROUP BY Tbb_Collar.BatId,Tbb_Collar.EmpId-- WHERE BatId=13

SELECT * FROM Tbb_Collar WHERE EmpId=2;

select x.EmpId,x.EmpNo,x.EmpName,isnull((x.BatTicketNum-y.Qty),x.BatTicketNum)  as Standbyticket
from
  ( select a.BatId as BatId, b.EmpId, b.EmpNo, b.EmpName, a.BatTicketNum
    from Tbb_Batch a, Tbb_Employee b
  ) x,
  (
    select BatId, EmpId, isnull(sum(CollNum),0)  as Qty
    from Tbb_Collar
    group by EmpId, BatId
  ) y
where x.BatId *= y.BatId
      and x.EmpId *= y.EmpId
      and x.BatId = 13
      and x.EmpId = 3

select x.EmpId,x.EmpNo,x.EmpName,isnull((x.BatTicketNum-y.Qty),x.BatTicketNum)  as Standbyticket
from
  ( select a.BatId as BatId, b.EmpId, b.EmpNo, b.EmpName, a.BatTicketNum
    from Tbb_Batch a, Tbb_Employee b
  ) x,
  (
    select BatId, EmpId, isnull(sum(CollNum),0)  as Qty
    from Tbb_Collar
    group by EmpId, BatId
  ) y
where x.BatId *= y.BatId
      and x.EmpId *= y.EmpId
      and x.BatId = #{0,jdbcType=INTEGER}
      and x.EmpId = #{1,jdbcType=INTEGER}





select x.EmpId,x.EmpNo,x.EmpName,isnull((x.BatTicketNum-y.Qty),x.BatTicketNum)  as Standbyticket
from
  ( select a.BatId as BatId, b.EmpId, b.EmpNo, b.EmpName, a.BatTicketNum
    from Tbb_Batch a, Tbb_Employee b
  ) x,
  (
    select BatId, EmpId, isnull(sum(CollNum),0)  as Qty
    from Tbb_Collar
    group by EmpId, BatId
  ) y
where x.BatId *= y.BatId
      and x.EmpId *= y.EmpId
      and x.BatId = 6
      and x.EmpId = 5



/*SELECT a.*,b.DeptName
    FROM Tbb_Employee a,Tbb_Dept b
    where EmpStatus1 !=-1
    and a.DeptId=b.DeptId
    order by EmpId*/

SELECT a.*,b.DeptName
FROM Tbb_Employee a,Tbb_Dept b
where EmpStatus1 !=-1
      and a.DeptId=b.DeptId
      and EmpName like '%'+rtrim(ltrim(#{EmpName,jdbcType=VARCHAR}))+'%' or rtrim(ltrim(#{EmpName,jdbcType=VARCHAR}))=''
order by EmpId