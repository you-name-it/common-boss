package com.common.enterprise.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * 〈企业信息实体类〉
 *
 * @author HuangQiuRong
 * @create 2019/1/11
 */

@TableName("Company")
public class Company implements Serializable {

    @TableId(value = "ID", type = IdType.INPUT)
    private Integer ID;                 //记录ID

    @TableField("NAME")
    private String name;                //企业名称

    @TableField("CREDITCODE")
    private String creditCode;          //企业信用代码

    @TableField("ADDRESS")
    private String address;             //企业地址

    @TableField("EMAIL")
    private String email;               //企业邮箱

    @TableField("TELEPHONE")
    private String telephone;           //企业电话

    @TableField("BUSINESSLICENSE_IMGURL")
    private String businessLicenseImgUrl;     //营业执照

    @TableField("REVIEWTHEMESSAGE")
    private String reviewTheMessage;     //认证消息

    @TableField("STATUS")
    private String status;              //状态    0表示未认证    1表示已认证     2表示已拒绝
    
    @TableField("CTIME")
    private Date cTime;               //创建时间

    @TableField("UTIME")
    private Date uTime;               //更新时间

    @TableField("AIRWALLEX_ACCOUNT_ID")
    private String airWallexAccountID;      //airwallex账号ID


    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getBusinessLicenseImgUrl() {
        return businessLicenseImgUrl;
    }

    public void setBusinessLicenseImgUrl(String businessLicenseImgUrl) {
        this.businessLicenseImgUrl = businessLicenseImgUrl;
    }

    public String getReviewTheMessage() {
        return reviewTheMessage;
    }

    public void setReviewTheMessage(String reviewTheMessage) {
        this.reviewTheMessage = reviewTheMessage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getcTime() {
        return cTime;
    }

    public void setcTime(Date cTime) {
        this.cTime = cTime;
    }

    public Date getuTime() {
        return uTime;
    }

    public void setuTime(Date uTime) {
        this.uTime = uTime;
    }

    public String getAirWallexAccountID() {
        return airWallexAccountID;
    }

    public void setAirWallexAccountID(String airWallexAccountID) {
        this.airWallexAccountID = airWallexAccountID;
    }
}
