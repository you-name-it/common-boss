package com.common.base.loginfo.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 *
 */
@TableName("tb_log_info")
public class TbLogInfo extends Model<TbLogInfo> {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private Long id;
    /**
     *
     */
    @TableField(value="user_id")
    private Integer userId;
    /**
     *
     */
    @TableField(value="account_name")
    private String accountName;
    /**
     *
     */
    private String method;
    /**
     *
     */
    @TableField(value="method_args")
    private String methodArgs;
    /**
     *
     */
    private String remark;
    /**
     *
     */
    private String status;
    /**
     *
     */
    @TableField(value="operate_ip")
    private String operateIp;
    /**
     * 创建日期
     */
    @TableField(value="operate_time")
    private Date operateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getMethodArgs() {
        return methodArgs;
    }

    public void setMethodArgs(String methodArgs) {
        this.methodArgs = methodArgs;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOperateIp() {
        return operateIp;
    }

    public void setOperateIp(String operateIp) {
        this.operateIp = operateIp;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

