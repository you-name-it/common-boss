package com.common.service.KOLCenter.entity;

import com.baomidou.mybatisplus.annotations.TableName;

import java.util.Date;

/**
 * 〈KOL实体类〉
 *
 * @author HuangQiuRong
 * @create 2019/3/19
 */


@TableName("globalkol")
public class GlobalKOL {

    private Integer ID;    //int(11) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
    private String KOLID;    //varchar(11) NOT NULL DEFAULT '' COMMENT '网红ID',
    private String HeadUrl;  //头像地址
    private String NickName;    //varchar(255) DEFAULT '' COMMENT '昵称',
    private String BroadcastRoom;    //varchar(11) DEFAULT '' COMMENT '直播间',
    private String Signature;    //varchar(500) DEFAULT '' COMMENT '签名',
    private String OnLine;    //int(11) DEFAULT NULL COMMENT '线上',
    private String OffLine;    //int(11) DEFAULT NULL COMMENT '线下',
    private String Classify;    //varchar(255) DEFAULT NULL COMMENT '类别',
    private String Area;    //varchar(255) DEFAULT NULL COMMENT '区域',
    private String Platform;    //varchar(255) DEFAULT NULL COMMENT '平台',
    private String Fans;    //int(11) DEFAULT NULL COMMENT '粉丝数',
    private String HighestViewNumber;    //bigint(20) DEFAULT NULL COMMENT '最高观看量',
    private String AverageViewNumber;    //bigint(20) DEFAULT NULL COMMENT '平均观看量',
    private String Commentary;    //varchar(255) DEFAULT NULL COMMENT '备注说明',
    private String State;    //varchar(255) DEFAULT NULL COMMENT '国家',
    private Date CreateTime;    //datetime DEFAULT NULL COMMENT '创建时间',
    private Date UpdateTime;    //datetime DEFAULT NULL COMMENT '更新时间',
    private String Status;    //int(1) DEFAULT '1' COMMENT '网红状态，1可用，2不可用',

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getKOLID() {
        return KOLID;
    }

    public void setKOLID(String KOLID) {
        this.KOLID = KOLID;
    }

    public String getHeadUrl() {
        return HeadUrl;
    }

    public void setHeadUrl(String headUrl) {
        HeadUrl = headUrl;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public String getBroadcastRoom() {
        return BroadcastRoom;
    }

    public void setBroadcastRoom(String broadcastRoom) {
        BroadcastRoom = broadcastRoom;
    }

    public String getSignature() {
        return Signature;
    }

    public void setSignature(String signature) {
        Signature = signature;
    }

    public String getOnLine() {
        return OnLine;
    }

    public void setOnLine(String onLine) {
        OnLine = onLine;
    }

    public String getOffLine() {
        return OffLine;
    }

    public void setOffLine(String offLine) {
        OffLine = offLine;
    }

    public String getClassify() {
        return Classify;
    }

    public void setClassify(String classify) {
        Classify = classify;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public String getPlatform() {
        return Platform;
    }

    public void setPlatform(String platform) {
        Platform = platform;
    }

    public String getFans() {
        return Fans;
    }

    public void setFans(String fans) {
        Fans = fans;
    }

    public String getHighestViewNumber() {
        return HighestViewNumber;
    }

    public void setHighestViewNumber(String highestViewNumber) {
        HighestViewNumber = highestViewNumber;
    }

    public String getAverageViewNumber() {
        return AverageViewNumber;
    }

    public void setAverageViewNumber(String averageViewNumber) {
        AverageViewNumber = averageViewNumber;
    }

    public String getCommentary() {
        return Commentary;
    }

    public void setCommentary(String commentary) {
        Commentary = commentary;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
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
