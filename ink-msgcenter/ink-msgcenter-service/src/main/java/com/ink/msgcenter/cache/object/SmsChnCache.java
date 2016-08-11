package com.ink.msgcenter.cache.object;

import java.io.Serializable;

/**
 * 短信通道缓存
 * Created by aiyungui on 2016/5/18.
 */
public class SmsChnCache implements Serializable {

    private static final long serialVersionUID = -7796730049763559894L;
    //通道ID
    private Long id;
    //通道名称
    private String name;
    //通道代码
    private String chnCode;
    //通道类型
    private String chnType;
    //通道参数
    private String chnParam;
    //通道优先级
    private Integer priorityLevel;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChnCode() {
        return chnCode;
    }

    public void setChnCode(String chnCode) {
        this.chnCode = chnCode;
    }

    public String getChnType() {
        return chnType;
    }

    public void setChnType(String chnType) {
        this.chnType = chnType;
    }

    public String getChnParam() {
        return chnParam;
    }

    public void setChnParam(String chnParam) {
        this.chnParam = chnParam;
    }

    public Integer getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(Integer priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
