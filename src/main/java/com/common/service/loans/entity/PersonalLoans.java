package com.common.service.loans.entity;

import com.baomidou.mybatisplus.annotations.TableName;

import java.util.Date;

/**
 * 〈个人贷款实体类〉
 *
 * @author HuangQiuRong
 * @create 2019/3/19
 */
@TableName("personalloanrecord")
public class PersonalLoans {

    private Integer ID;                 //int(11) NOT NULL COMMENT '记录ID',
    private String PersonalLoanID;      //varchar(255) NOT NULL,
    private Integer ExpectMoney;        //期望资金
    private String NeedTime;              //datetime DEFAULT NULL COMMENT '什么时候需要',
    private String Purpose; //varchar(255) DEFAULT NULL COMMENT '如何使用这笔资金',
    private String NikeName; //varchar(255) DEFAULT NULL COMMENT '昵称',
    private String IdentityCardNumber; //varchar(18) DEFAULT NULL COMMENT '身份证号码',
    private String Phone; //varchar(11) DEFAULT NULL COMMENT '手机号码',
    private String Email; //varchar(100) DEFAULT NULL COMMENT '邮箱',
    private String Address; //varchar(100) DEFAULT NULL COMMENT '地址',
    private String CompanyName; //varchar(100) DEFAULT NULL COMMENT '单位名称',
    private String Certifier; //varchar(100) DEFAULT NULL COMMENT '单位证明人',
    private String CertifierPhone; //varchar(11) DEFAULT NULL COMMENT '单位证明人电话',
    private Date CreateTime; //datetime DEFAULT NULL,
    private Date UpdateTime; //datetime DEFAULT NULL,
    private String Status; //varchar(20) DEFAULT NULL COMMENT '状态  1已处理  2处理中 3未处理',

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getPersonalLoanID() {
        return PersonalLoanID;
    }

    public void setPersonalLoanID(String personalLoanID) {
        PersonalLoanID = personalLoanID;
    }

    public String getNeedTime() {
        return NeedTime;
    }

    public void setNeedTime(String needTime) {
        NeedTime = needTime;
    }

    public Integer getExpectMoney() {
        return ExpectMoney;
    }

    public void setExpectMoney(Integer expectMoney) {
        ExpectMoney = expectMoney;
    }

    public String getPurpose() {
        return Purpose;
    }

    public void setPurpose(String purpose) {
        Purpose = purpose;
    }

    public String getNikeName() {
        return NikeName;
    }

    public void setNikeName(String nikeName) {
        NikeName = nikeName;
    }

    public String getIdentityCardNumber() {
        return IdentityCardNumber;
    }

    public void setIdentityCardNumber(String identityCardNumber) {
        IdentityCardNumber = identityCardNumber;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getCertifier() {
        return Certifier;
    }

    public void setCertifier(String certifier) {
        Certifier = certifier;
    }

    public String getCertifierPhone() {
        return CertifierPhone;
    }

    public void setCertifierPhone(String certifierPhone) {
        CertifierPhone = certifierPhone;
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
