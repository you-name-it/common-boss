package com.common.service.loans.entity;

import com.baomidou.mybatisplus.annotations.TableName;

import java.util.Date;

/**
 * 〈企业贷款实体〉
 *
 * @author HuangQiuRong
 * @create 2019/3/19
 */
@TableName("enterpriseloanrecord")
public class EnterpriseLoans {

    private Integer ID;                     //
    private String EnterpriseLoanID;        // '企业贷款订单ID'
    private String CompanyID;               // '企业ID'
    private String ExpectMoney;             // '期望资金'
    private String NeedTime;                //  '需要时间'
    private String Purpose;                 // '如何使用'
    private String CompanyName;                 // '如何使用'
    private String CreditCode;              // '企业信用代码'
    private String Province;                //  '省'
    private String City;                    //  '市'
    private String Address;                 //  '地址'
    private String CompanyCreateTime;       // '企业创建时间'
    private String CompanyType;             //  '企业类型'
    private String IsOtherFinancing;        // '是否有其他短期融资'
    private String NickName;                //'昵称'
    private String Email;                   // '电子邮箱'
    private String Phone;                   // '手机号码'
    private Long AnnualIncome;              // '估计的年度总收入'
    private Long BanksLimit;                // '平均银行额度'
    private Date CreateTime;                //  '创建时间'
    private Date UpdateTime;                // '更新时间'
    private String Status;                  //'状态  1已处理  2处理中 3未处理'

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getEnterpriseLoanID() {
        return EnterpriseLoanID;
    }

    public void setEnterpriseLoanID(String enterpriseLoanID) {
        EnterpriseLoanID = enterpriseLoanID;
    }

    public String getCompanyID() {
        return CompanyID;
    }

    public void setCompanyID(String companyID) {
        CompanyID = companyID;
    }

    public String getExpectMoney() {
        return ExpectMoney;
    }

    public void setExpectMoney(String expectMoney) {
        ExpectMoney = expectMoney;
    }

    public String getNeedTime() {
        return NeedTime;
    }

    public void setNeedTime(String needTime) {
        NeedTime = needTime;
    }

    public String getPurpose() {
        return Purpose;
    }

    public void setPurpose(String purpose) {
        Purpose = purpose;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getCreditCode() {
        return CreditCode;
    }

    public void setCreditCode(String creditCode) {
        CreditCode = creditCode;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCompanyCreateTime() {
        return CompanyCreateTime;
    }

    public void setCompanyCreateTime(String companyCreateTime) {
        CompanyCreateTime = companyCreateTime;
    }

    public String getCompanyType() {
        return CompanyType;
    }

    public void setCompanyType(String companyType) {
        CompanyType = companyType;
    }

    public String getIsOtherFinancing() {
        return IsOtherFinancing;
    }

    public void setIsOtherFinancing(String isOtherFinancing) {
        IsOtherFinancing = isOtherFinancing;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public Long getAnnualIncome() {
        return AnnualIncome;
    }

    public void setAnnualIncome(Long annualIncome) {
        AnnualIncome = annualIncome;
    }

    public Long getBanksLimit() {
        return BanksLimit;
    }

    public void setBanksLimit(Long banksLimit) {
        BanksLimit = banksLimit;
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
