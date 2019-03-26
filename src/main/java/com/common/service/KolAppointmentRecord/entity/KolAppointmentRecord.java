package com.common.service.KolAppointmentRecord.entity;

import com.baomidou.mybatisplus.annotations.TableName;

import java.util.Date;

/**
 * 〈KOL预约实体〉
 *
 * @author HuangQiuRong
 * @create 2019/3/19
 */
@TableName("kolappointmentrecord")
public class KolAppointmentRecord {

    private Integer ID;  // int(11) NOT NULL AUTO_INCREMENT,
    private String RecordID;  // varchar(255) DEFAULT NULL,
    private String Type;  // varchar(255) DEFAULT NULL,
    private String Remark;  // varchar(255) DEFAULT NULL,
    private Date CreateTime;  // datetime DEFAULT NULL,
    private Date UpdateTime;  // datetime DEFAULT NULL,
    private String Status;  // varchar(11) DEFAULT NULL,

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getRecordID() {
        return RecordID;
    }

    public void setRecordID(String recordID) {
        RecordID = recordID;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
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

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
