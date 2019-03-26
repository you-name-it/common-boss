package com.common.base.index.web;

import com.common.base.common.bean.AbstractBean;
import com.common.base.common.exception.EnumSvrResult;
import com.common.base.user.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.SecurityUtils;

import java.io.IOException;

/**
 *
 */
public abstract class BaseController {

    /**
     * 获取登录用户信息
     * @return
     */
    public User getUserEntity() {
        return (User) SecurityUtils.getSubject().getPrincipal();
    }

    /**
     * 成功,返回状态
     * @return
     */
    public AbstractBean success() {
        return new AbstractBean();
    }

    /**
     * 失败,返回状态及原因
     * @param result 消息
     * @return
     */
    public AbstractBean fail(EnumSvrResult result) {
        AbstractBean bean = new AbstractBean();
        bean.setMessage(result.getName());
        bean.setStatus(result.getVal());
        return bean;
    }

    public AbstractBean fail(String message,String status) {
        AbstractBean bean = new AbstractBean();
        bean.setMessage(message);
        bean.setStatus(status);
        return bean;
    }

    public AbstractBean error() {
        AbstractBean bean = new AbstractBean();
        bean.setMessage(EnumSvrResult.ERROR.getName());
        bean.setStatus(EnumSvrResult.ERROR.getVal());
        return bean;
    }


    /**
     * 成功，返回json数据
     * @param data 消息
     * @return
     */
    public AbstractBean json(Object data) {
        AbstractBean bean = new AbstractBean();
        bean.setData(data);
        return bean;
    }

    public String toJson(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

    public <T> T getJson(String json,Class<T> valueType) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES , false);
        return mapper.readValue(json,valueType);
    }


}

