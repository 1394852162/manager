/*��Ա��*/
CREATE TABLE Tbb_Employee (
  EmpId       INT         NOT NULL PRIMARY KEY,
  EmpNo       VARCHAR(20) NULL, /*����ΪNULL*/
  EmpName     VARCHAR(40) NOT NULL,
  EmpPassword VARCHAR(20) NOT NULL,
  DeptId      INT         NULL, /*����ΪNULL*/
  EmpBirth    VARCHAR(30),
  EmpStatus1  INT         NOT NULL, /*1��ְ 10���� -1ɾ��*/
  EmpStatus2  INT         NOT NULL, /*�Ƿ�����ʹ�ñ�ϵͳ 1.����  0.������*/
  EmpNote     VARCHAR(100),
  CreateTime  DATE,
  UpdateTime  DATE,
  EmpStatus3 int,                /*�Ƿ���VIP����ѯȨ�� 1.�� 0.�� �¼��ֶ�*/
  EmpStatus4 int,                /*�Ƿ���ְ������ƱȨ�� 1.�� 0.�� �¼��ֶ�*/
  EmpStatus5 int,                /*�Ƿ�������Ȩ�� 1.�� 0.�� �¼��ֶ�*/
  EmpStatus6 int,                /*�Ƿ�����Աά��Ȩ�� 1.�� 0.�� �¼��ֶ�*/
  EmpStatus7 int,                /*�Ƿ��貿��ά��Ȩ�� 1.�� 0.�� �¼��ֶ�*/
  EmpStatus8 int,                /*�Ƿ��������ѯȨ�� 1.�� 0.�� �¼��ֶ�*/
  EmpStatus9 int,               /*�Ƿ���VIP�����Ȩ�� 1.�� 0.�� �¼��ֶ�*/
  EmpStatus10 int                /*�Ƿ��������Ա�������ƱȨ��*/
)

/*���ű�*/
CREATE TABLE Tbb_Dept (
  DeptId     INT         NOT NULL PRIMARY KEY,
  DeptNo     VARCHAR(20) NOT NULL,
  DeptName   VARCHAR(40) NOT NULL,
  Status     INT         NOT NULL,/*1.���� 0.ɾ��*/
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
  Status       INT         NOT NULL,/*1.���� -1.ɾ��*/
  BatNote      VARCHAR(100),
  CreateTime   DATE,
  UpdateTime   DATE,
  Status2      INT         -- /*1=��ְ������ȡ,0=��ְ��ְ�Ķ�����ȡ*/
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
  Status     INT  NOT NULL, /*-1ɾ����1δ��Ʊ��0�ѳ�Ʊ,��ѯ������10��ѯȫ������*/
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
  Status     INT,/*1.���� 0.ɾ��*/
  CreateTime DATE,
  UpdateTime DATE
)


insert into Tbb_Employee
  select  1, '1001', 'admin' , 'password',1, '2018-02-12' , 1 , 1 ,'������Ա' ,  '2018-2-26'  , '2018-2-26',   1

insert into Tbb_Dept select 1,'2001','Vip����',1,'�����˺������޸�ɾ��','2018-2-26',null




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