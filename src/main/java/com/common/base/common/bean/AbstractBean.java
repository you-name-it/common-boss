package com.common.base.common.bean;

import com.common.base.common.exception.EnumSvrResult;

import java.io.Serializable;

/**
 *
 */
public class AbstractBean implements Serializable{

    /** */
    private static final long serialVersionUID = 1L;

    private String status = EnumSvrResult.OK.getVal();

    private String message= EnumSvrResult.OK.getName();

    private Object data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
