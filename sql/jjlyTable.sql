/*��Ա��*/
CREATE TABLE Tbb_Employee (
  EmpId       INT         NOT NULL PRIMARY KEY,
  EmpNo       VARCHAR(20) NOT NULL,
  EmpName     VARCHAR(40) NOT NULL,
  EmpPassword VARCHAR(20) NOT NULL,
  DeptId      INT         NOT NULL,
  EmpBirth    VARCHAR(30),
  EmpStatus1  INT         NOT NULL, /*1��ְ 10��ְ 2ɾ��*/
  EmpStatus2  INT         NOT NULL,
  EmpNote     VARCHAR(100),
  CreateTime  DATE,
  UpdateTime  DATE
)

/*���ű�*/
CREATE TABLE Tbb_Dept (
  DeptId     INT         NOT NULL PRIMARY KEY,
  DeptNo     VARCHAR(20) NOT NULL,
  DeptName   VARCHAR(40) NOT NULL,
  Status     INT         NOT NULL,
  DeptNote   VARCHAR(100),
  CreateTime DATE,
  UpdateTime DATE
)

/*���α�*/
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

/*���ñ�*/
CREATE TABLE Tbb_Collar (
  CollId     INT  NOT NULL PRIMARY KEY,
  BatId      INT  NOT NULL, --����ID
  BatEndTime DATE NOT NULL, --��Ч��(���ν���ʱ��)
  CollTime   DATE, --����ʱ��
  EmpId      INT, --����Ա��ID
  CollNum    INT, --��������
  CollNote   VARCHAR(100), --��ע
  Status     INT  NOT NULL,
  CreateTime DATE,
  UpdateTime DATE

)

/*VIP�������*/
CREATE TABLE Tbb_VipTicket (
  VipAddId   INT      NOT NULL PRIMARY KEY, --VIP ID
  VipAddTime DATETIME NOT NULL, --���VIPƱ��ʱ��
  EmpId      INT      NOT NULL, --������
  VipEMpID   INT      NOT NULL, --VIP��
  VipAddNum  INT      NOT NULL, --VIP����ȡ������
  VipAddNote VARCHAR(100), --��ע
  Status     INT,
  CreateTime DATE,
  UpdateTime DATE
)

