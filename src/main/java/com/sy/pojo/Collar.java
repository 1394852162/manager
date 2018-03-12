package com.sy.pojo;

import java.util.Date;

/**
 * Created by haswell on 2018/2/8.
 */
public class Collar {
    private int CollId;
    private String CollNo;
    private int BatId;
    private Date BatEndTime;
    private Date CollTime;
    private int EmpId;
    private int CollNum;
    private String CollNote;
    private int Status;
    private Date CreateTime;
    private Date UpdateTime;
    private String EmpName;
    private String BatNo;
    private String EmpNo;
    private String BatName;
    private int SumCollNum;

    @Override
    public String toString() {
        return "Collar{" +
                "CollId=" + CollId +
                ", CollNo='" + CollNo + '\'' +
                ", BatId=" + BatId +
                ", BatEndTime=" + BatEndTime +
                ", CollTime=" + CollTime +
                ", EmpId=" + EmpId +
                ", CollNum=" + CollNum +
                ", CollNote='" + CollNote + '\'' +
                ", Status=" + Status +
                ", CreateTime=" + CreateTime +
                ", UpdateTime=" + UpdateTime +
                ", EmpName='" + EmpName + '\'' +
                ", BatNo='" + BatNo + '\'' +
                ", EmpNo='" + EmpNo + '\'' +
                ", BatName='" + BatName + '\'' +
                ", SumCollNum=" + SumCollNum +
                '}';
    }

    public int getCollId() {
        return CollId;
    }

    public void setCollId(int collId) {
        CollId = collId;
    }

    public String getCollNo() {
        return CollNo;
    }

    public void setCollNo(String collNo) {
        CollNo = collNo;
    }

    public int getBatId() {
        return BatId;
    }

    public void setBatId(int batId) {
        BatId = batId;
    }

    public Date getBatEndTime() {
        return BatEndTime;
    }

    public void setBatEndTime(Date batEndTime) {
        BatEndTime = batEndTime;
    }

    public Date getCollTime() {
        return CollTime;
    }

    public void setCollTime(Date collTime) {
        CollTime = collTime;
    }

    public int getEmpId() {
        return EmpId;
    }

    public void setEmpId(int empId) {
        EmpId = empId;
    }

    public int getCollNum() {
        return CollNum;
    }

    public void setCollNum(int collNum) {
        CollNum = collNum;
    }

    public String getCollNote() {
        return CollNote;
    }

    public void setCollNote(String collNote) {
        CollNote = collNote;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
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

    public String getEmpName() {
        return EmpName;
    }

    public void setEmpName(String empName) {
        EmpName = empName;
    }

    public String getBatNo() {
        return BatNo;
    }

    public void setBatNo(String batNo) {
        BatNo = batNo;
    }

    public String getEmpNo() {
        return EmpNo;
    }

    public void setEmpNo(String empNo) {
        EmpNo = empNo;
    }

    public String getBatName() {
        return BatName;
    }

    public void setBatName(String batName) {
        BatName = batName;
    }

    public int getSumCollNum() {
        return SumCollNum;
    }

    public void setSumCollNum(int sumCollNum) {
        SumCollNum = sumCollNum;
    }
}
