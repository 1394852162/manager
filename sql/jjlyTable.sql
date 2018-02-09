/*人员表*/
CREATE TABLE Tbb_Employee (
  EmpId       INT         NOT NULL PRIMARY KEY,
  EmpNo       VARCHAR(20) NOT NULL,
  EmpName     VARCHAR(40) NOT NULL,
  EmpPassword VARCHAR(20) NOT NULL,
  DeptId      INT         NOT NULL,
  EmpBirth    VARCHAR(30),
  EmpStatus1  INT         NOT NULL, /*1在职 10离职 2删除*/
  EmpStatus2  INT         NOT NULL,
  EmpNote     VARCHAR(100),
  CreateTime  DATE,
  UpdateTime  DATE
)

/*部门表*/
CREATE TABLE Tbb_Dept (
  DeptId     INT         NOT NULL PRIMARY KEY,
  DeptNo     VARCHAR(20) NOT NULL,
  DeptName   VARCHAR(40) NOT NULL,
  Status     INT         NOT NULL,
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
  Status       INT         NOT NULL,
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
  Status     INT  NOT NULL, /*-1删除，1未出票，0已出票*/
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
  Status     INT,
  CreateTime DATE,
  UpdateTime DATE
)

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


SELECT * FROM Tbb_Collar

