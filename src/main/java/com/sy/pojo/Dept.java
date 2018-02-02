package com.sy.pojo;

import java.util.Date;

/**
 * Created by haswell on 2018/1/25.
 */
public class Dept {
    private int DeptId;
    private String DeptNo;
    private String DeptName;
    private int Status;
    private String DeptNote;
    private Date CreateTime;
    private Date UpdateTime;

    @Override
    public String toString() {
        return "Dept{" +
                "DeptId=" + DeptId +
                ", DeptNo='" + DeptNo + '\'' +
                ", DeptName='" + DeptName + '\'' +
                ", Status=" + Status +
                ", DeptNote='" + DeptNote + '\'' +
                ", CreateTime=" + CreateTime +
                ", UpdateTime=" + UpdateTime +
                '}';
    }

    public int getDeptId() {
        return DeptId;
    }

    public void setDeptId(int deptId) {
        DeptId = deptId;
    }

    public String getDeptNo() {
        return DeptNo;
    }

    public void setDeptNo(String deptNo) {
        DeptNo = deptNo;
    }

    public String getDeptName() {
        return DeptName;
    }

    public void setDeptName(String deptName) {
        DeptName = deptName;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getDeptNote() {
        return DeptNote;
    }

    public void setDeptNote(String deptNote) {
        DeptNote = deptNote;
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
}
