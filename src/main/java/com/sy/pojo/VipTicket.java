package com.sy.pojo;

import java.util.Date;

/**
 * Created by haswell on 2018/2/7.
 */
public class VipTicket {
    private int VipAddId;
    private String VipTicNo;
    private Date VipAddTime;
    private int EmpId;
    private int VipEmpID;
    private String EmpName;
    private int VipAddNum;
    private String VipAddNote;
    private int Status;
    private Date CreateTime;
    private Date UpdateTime;
    private String operaName;

    @Override
    public String toString() {
        return "VipTicket{" +
                "VipAddId=" + VipAddId +
                ", VipTicNo='" + VipTicNo + '\'' +
                ", VipAddTime=" + VipAddTime +
                ", EmpId=" + EmpId +
                ", VipEmpID=" + VipEmpID +
                ", EmpName='" + EmpName + '\'' +
                ", VipAddNum=" + VipAddNum +
                ", VipAddNote='" + VipAddNote + '\'' +
                ", Status=" + Status +
                ", CreateTime=" + CreateTime +
                ", UpdateTime=" + UpdateTime +
                ", operaName='" + operaName + '\'' +
                '}';
    }

    public int getVipAddId() {
        return VipAddId;
    }

    public void setVipAddId(int vipAddId) {
        VipAddId = vipAddId;
    }

    public String getVipTicNo() {
        return VipTicNo;
    }

    public void setVipTicNo(String vipTicNo) {
        VipTicNo = vipTicNo;
    }

    public Date getVipAddTime() {
        return VipAddTime;
    }

    public void setVipAddTime(Date vipAddTime) {
        VipAddTime = vipAddTime;
    }

    public int getEmpId() {
        return EmpId;
    }

    public void setEmpId(int empId) {
        EmpId = empId;
    }

    public int getVipEmpID() {
        return VipEmpID;
    }

    public void setVipEmpID(int vipEmpID) {
        VipEmpID = vipEmpID;
    }

    public String getEmpName() {
        return EmpName;
    }

    public void setEmpName(String empName) {
        EmpName = empName;
    }

    public int getVipAddNum() {
        return VipAddNum;
    }

    public void setVipAddNum(int vipAddNum) {
        VipAddNum = vipAddNum;
    }

    public String getVipAddNote() {
        return VipAddNote;
    }

    public void setVipAddNote(String vipAddNote) {
        VipAddNote = vipAddNote;
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

    public String getOperaName() {
        return operaName;
    }

    public void setOperaName(String operaName) {
        this.operaName = operaName;
    }
}
