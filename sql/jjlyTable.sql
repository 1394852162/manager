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
  BatNo        VARCHAR(20) NOT NULL,
  BatName      VARCHAR(40),
  BatBeginTime DATE        NOT NULL,
  BatEndTime   DATE        NOT NULL,
  BatTicketNum INT         NOT NULL,
  Status       INT         NOT NULL,
  BatNote      VARCHAR(100),
  CreateTime   DATE,
  UpdateTime   DATE,
  Status2      INT         ,/*1=��ְ������ȡ,0=��ְ��ְ�Ķ�����ȡ*/
)

/*���ñ�*/
CREATE TABLE Tbb_Collar (
  CollId     INT  NOT NULL PRIMARY KEY,
  CollNo     varchar(20),
  BatId      INT  NOT NULL, --����ID
  BatEndTime DATE NOT NULL, --��Ч��(���ν���ʱ��)
  CollTime   DATE, --����ʱ��
  EmpId      INT, --����Ա��ID
  CollNum    INT, --��������
  CollNote   VARCHAR(100), --��ע
  Status     INT  NOT NULL, /*-1ɾ����1δ��Ʊ��0�ѳ�Ʊ*/
  CreateTime DATE,
  UpdateTime DATE

)

/*VIP�������*/
CREATE TABLE Tbb_VipTicket (
  VipAddId   INT      NOT NULL PRIMARY KEY, --VIP ID
  VipTicNo   varchar(20), --�¼ӱ�����ʱ����ʾ
  VipAddTime DATE NOT NULL, --���VIPƱ��ʱ��
  EmpId      INT      NOT NULL, --������
  VipEmpID   INT      NOT NULL, --VIP��ȡ��
  VipAddNum  INT      NOT NULL, --VIP����ȡ������
  VipAddNote VARCHAR(100), --��ע
  Status     INT,
  CreateTime DATE,
  UpdateTime DATE
)

SELECT *
FROM Tbb_Employee;
SELECT *
FROM Tbb_Dept;

SELECT * FROM Tbb_Batch;
