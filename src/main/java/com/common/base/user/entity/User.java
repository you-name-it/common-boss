package com.common.base.user.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.common.base.role.entity.TbRole;

import java.io.Serializable;
import java.util.Date;

/**
 * 〈用户实体类〉
 *
 * @author HuangQiuRong
 * @create 2019/1/3
 */
@TableName("USER")
public class User implements Serializable{

    @TableId(value = "ID" , type = IdType.INPUT)
    private Integer ID;                          //记录ID

    @TableField(value = "USER_NAME")
    private String userName;                    //用户名

    @TableField(value = "EMAIL")
    private String email;                       //邮箱

    @TableField(value = "PHONE")
    private String phone;                       //手机

    @TableField(value = "PASSWORD")
    private String password;                    //密码

    @TableField(value = "CREDENTIALS_SALT")
    private String credentialsSalt;             //密码盐

    @TableField(value = "LOCKED")
    private String locked;             //密码盐

    @TableField(value = "VIP_STATUS")
    private String vipStatus;                   //会员状态         是否为会员：0  不是   1  是

    @TableField(value = "VIP_ID")
    private String vipID;                       //会员ID    不是会员可以为空

    @TableField(value = "GENDER")
    private String gender;                      //性别

    @TableField(value = "BIRTHDAY")
    private Date birthDay;                      //生日

    @TableField(value = "CREATE_TIME")
    private Date createTime;                    //创建时间

    @TableField(value = "UPDATE_TIME")
    private Date updateTime;                    //更新时间

    @TableField(value = "LOGIN_TIME")
    private Date loginTime;                     //登录时间

    @TableField(exist=false)
    private TbRole role;                        //角色

    @TableField(value = "PROVINCE")
    private String province;                    //省份

    @TableField(value = "CITY")
    private String city;                        //城市

    @TableField(value = "COUNTIES")
    private String counties;                    //县区

    @TableField(value = "SITE")
    private String site;                        //地址

    @TableField(value = "COMPANYID")
    private String companyID;                   //企业ID

    @TableField(value = "COMPANY_STATUS")
    private String companyStatus;               //企业认证状态    未认证：0   已认证：1

    @TableField(value = "AIRWALLEX_ACCOUNT_ID")
    private String airwallexAccountId;               //AirWallex 账号 ID   未注册则为空

    @TableField(value = "Solution_Service_ID")
    private String solutionServiceId;               //建站服务记录

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVipStatus() {
        return vipStatus;
    }

    public void setVipStatus(String vipStatus) {
        this.vipStatus = vipStatus;
    }

    public String getVipID() {
        return vipID;
    }

    public void setVipID(String vipID) {
        this.vipID = vipID;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getCredentialsSalt() {
        return credentialsSalt;
    }

    public void setCredentialsSalt(String credentialsSalt) {
        this.credentialsSalt = credentialsSalt;
    }

    public TbRole getRole() {
        return role;
    }

    public void setRole(TbRole role) {
        this.role = role;
    }

    public String getLocked() {
        return locked;
    }

    public void setLocked(String locked) {
        this.locked = locked;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounties() {
        return counties;
    }

    public void setCounties(String counties) {
        this.counties = counties;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public String getCompanyStatus() {
        return companyStatus;
    }

    public void setCompanyStatus(String companyStatus) {
        this.companyStatus = companyStatus;
    }

    public String getAirwallexAccountId() {
        return airwallexAccountId;
    }

    public void setAirwallexAccountId(String airwallexAccountId) {
        this.airwallexAccountId = airwallexAccountId;
    }

    public String getSolutionServiceId() {
        return solutionServiceId;
    }

    public void setSolutionServiceId(String solutionServiceId) {
        this.solutionServiceId = solutionServiceId;
    }
}
