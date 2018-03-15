package com.sy.pojo;

import java.util.Date;

/**
 * Created by haswell on 2018/1/29.
 */
public class Employee {
    private int EmpId;
    private String EmpNo;
    private String EmpName;
    private String EmpPassword;
    private int DeptId;
    private String EmpBirth;
    private int EmpStatus1;
    private int EmpStatus2;
    private Date CreateTime;
    private Date UpdateTime;
    private String DeptName;
    private int Standbyticket;
    private int EmpStatus3;
    private int EmpStatus4;
    private int EmpStatus5;
    private int EmpStatus6;
    private int EmpStatus7;
    private int EmpStatus8;

    public int getEmpId() {
        return EmpId;
    }

    public void setEmpId(int empId) {
        EmpId = empId;
    }

    public String getEmpNo() {
        return EmpNo;
    }

    public void setEmpNo(String empNo) {
        EmpNo = empNo;
    }

    public String getEmpName() {
        return EmpName;
    }

    public void setEmpName(String empName) {
        EmpName = empName;
    }

    public String getEmpPassword() {
        return EmpPassword;
    }

    public void setEmpPassword(String empPassword) {
        EmpPassword = empPassword;
    }

    public int getDeptId() {
        return DeptId;
    }

    public void setDeptId(int deptId) {
        DeptId = deptId;
    }

    public String getEmpBirth() {
        return EmpBirth;
    }

    public void setEmpBirth(String empBirth) {
        EmpBirth = empBirth;
    }

    public int getEmpStatus1() {
        return EmpStatus1;
    }

    public void setEmpStatus1(int empStatus1) {
        EmpStatus1 = empStatus1;
    }

    public int getEmpStatus2() {
        return EmpStatus2;
    }

    public void setEmpStatus2(int empStatus2) {
        EmpStatus2 = empStatus2;
    }

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date createTime) {
        CreateTime = createTime;
    }

    public Date getUpdateTime() {
        return UpdateTime;
    }

    public void setUpdateTime(Date updateTime) {
        UpdateTime = updateTime;
    }

    public String getDeptName() {
        return DeptName;
    }

    public void setDeptName(String deptName) {
        DeptName = deptName;
    }

    public int getStandbyticket() {
        return Standbyticket;
    }

    public void setStandbyticket(int standbyticket) {
        Standbyticket = standbyticket;
    }

    public int getEmpStatus3() {
        return EmpStatus3;
    }

    public void setEmpStatus3(int empStatus3) {
        EmpStatus3 = empStatus3;
    }

    public int getEmpStatus4() {
        return EmpStatus4;
    }

    public void setEmpStatus4(int empStatus4) {
        EmpStatus4 = empStatus4;
    }

    public int getEmpStatus5() {
        return EmpStatus5;
    }

    public void setEmpStatus5(int empStatus5) {
        EmpStatus5 = empStatus5;
    }

    public int getEmpStatus6() {
        return EmpStatus6;
    }

    public void setEmpStatus6(int empStatus6) {
        EmpStatus6 = empStatus6;
    }

    public int getEmpStatus7() {
        return EmpStatus7;
    }

    public void setEmpStatus7(int empStatus7) {
        EmpStatus7 = empStatus7;
    }

    public int getEmpStatus8() {
        return EmpStatus8;
    }

    public void setEmpStatus8(int empStatus8) {
        EmpStatus8 = empStatus8;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "EmpId=" + EmpId +
                ", EmpNo='" + EmpNo + '\'' +
                ", EmpName='" + EmpName + '\'' +
                ", EmpPassword='" + EmpPassword + '\'' +
                ", DeptId=" + DeptId +
                ", EmpBirth='" + EmpBirth + '\'' +
                ", EmpStatus1=" + EmpStatus1 +
                ", EmpStatus2=" + EmpStatus2 +
                ", CreateTime=" + CreateTime +
                ", UpdateTime=" + UpdateTime +
                ", DeptName='" + DeptName + '\'' +
                ", Standbyticket=" + Standbyticket +
                ", EmpStatus3=" + EmpStatus3 +
                ", EmpStatus4=" + EmpStatus4 +
                ", EmpStatus5=" + EmpStatus5 +
                ", EmpStatus6=" + EmpStatus6 +
                ", EmpStatus7=" + EmpStatus7 +
                ", EmpStatus8=" + EmpStatus8 +
                '}';
    }
}
