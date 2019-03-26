package com.common.base.common.model;

import java.util.Map;

/**
 *
 */
public class PageParams {

    private Integer current=0;

    private Integer size=10;

    private Map<String,Object> params;

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
