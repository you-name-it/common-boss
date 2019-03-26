package com.common.service.Order.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 〈购买会员订单〉
 *
 * @author HuangQiuRong
 * @create 2019/3/5
 */
@TableName("BuyVipOrder")
public class BuyVipOrder implements Serializable{

    @TableField("ORDERID")
    private String orderID;         //订单ID

    @TableField("PRODUCTTYPE")
    private String productType;     //商品类型

    @TableField("ORDERTOTAL")
    private Integer orderTotal;      //订单总价

    @TableField("PRODUCTID")
    private String productID;       //商品ID

    @TableField("USERID")
    private String userID;          //用户ID

    @TableField("COMPANYID")
    private String companyID;       //企业ID

    @TableField("ORDERSTATUS")
    private String orderStatus;     //订单状态

    @TableField("CTIME")
    private Date cTime;             //创建时间

    @TableField("UTIME")
    private Date uTime;             //更新时间


    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Integer getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(Integer orderTotal) {
        this.orderTotal = orderTotal;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
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
}
