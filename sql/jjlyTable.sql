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
  BatNo        VARCHAR(10) NOT NULL,
  BatName      VARCHAR(30),
  BatBeginTime DATE        NOT NULL,
  BatEndTime   DATE        NOT NULL,
  BatTicketNum INT         NOT NULL,
  Status       INT         NOT NULL,
  CreateTime   DATE,
  UpdateTime   DATE
)

/*领用表*/
CREATE TABLE Tbb_Collar (
  CollId     INT  NOT NULL PRIMARY KEY,
  BatId      INT  NOT NULL, --批次ID
  BatEndTime DATE NOT NULL, --有效期(批次结束时间)
  CollTime   DATE, --领用时间
  EmpId      INT, --领用员工ID
  CollNum    INT, --领用数量
  CollNote   VARCHAR(100), --备注
  Status     INT  NOT NULL,
  CreateTime DATE,
  UpdateTime DATE

)

/*VIP补入界面*/
CREATE TABLE Tbb_VipTicket (
  VipAddId   INT      NOT NULL PRIMARY KEY, --VIP ID
  VipAddTime DATETIME NOT NULL, --添加VIP票的时间
  EmpId      INT      NOT NULL, --操作人
  VipEMpID   INT      NOT NULL, --VIP人
  VipAddNum  INT      NOT NULL, --VIP人领取的数量
  VipAddNote VARCHAR(100), --备注
  Status     INT,
  CreateTime DATE,
  UpdateTime DATE
)

