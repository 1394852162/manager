package com.sy.pojo;

import java.util.Date;

/**
 * Created by haswell on 2018/2/6.
 */
public class Batch {
    private int BatId;
    private String BatNo;
    private String BatName;
    private int  BatTicketNum;
    private int Status;
    private String BatNote;
    private Date BatBeginTime;
    private Date BatEndTime;
    private Date CreateTime;
    private Date UpdateTime;
    private int Status2;

    @Override
    public String toString() {
        return "Batch{" +
                "BatId=" + BatId +
                ", BatNo='" + BatNo + '\'' +
                ", BatName='" + BatName + '\'' +
                ", BatTicketNum=" + BatTicketNum +
                ", Status=" + Status +
                ", BatNote='" + BatNote + '\'' +
                ", BatBeginTime=" + BatBeginTime +
                ", BatEndTime=" + BatEndTime +
                ", CreateTime=" + CreateTime +
                ", UpdateTime=" + UpdateTime +
                ", Status2=" + Status2 +
                '}';
    }

    public int getBatId() {
        return BatId;
    }

    public void setBatId(int batId) {
        BatId = batId;
    }

    public String getBatNo() {
        return BatNo;
    }

    public void setBatNo(String batNo) {
        BatNo = batNo;
    }

    public String getBatName() {
        return BatName;
    }

    public void setBatName(String batName) {
        BatName = batName;
    }

    public int getBatTicketNum() {
        return BatTicketNum;
    }

    public void setBatTicketNum(int batTicketNum) {
        BatTicketNum = batTicketNum;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getBatNote() {
        return BatNote;
    }

    public void setBatNote(String batNote) {
        BatNote = batNote;
    }

    public Date getBatBeginTime() {
        return BatBeginTime;
    }

    public void setBatBeginTime(Date batBeginTime) {
        BatBeginTime = batBeginTime;
    }

    public Date getBatEndTime() {
        return BatEndTime;
    }

    public void setBatEndTime(Date batEndTime) {
        BatEndTime = batEndTime;
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

    public int getStatus2() {
        return Status2;
    }

    public void setStatus2(int status2) {
        Status2 = status2;
    }
}
