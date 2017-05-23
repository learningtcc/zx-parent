package com.ink.route.api.model;

import java.util.Date;

/**
 * Created by YKDZ075 on 2017/5/21.
 */
public class Channel {
    private Integer id;
    private String name;
    private Integer payType;
    private Date unUseableTimeBeginTemp;
    private Date unUseableTimeEndTemp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getUnUseableTimeEndTemp() {
        return unUseableTimeEndTemp;
    }

    public void setUnUseableTimeEndTemp(Date unUseableTimeEndTemp) {
        this.unUseableTimeEndTemp = unUseableTimeEndTemp;
    }

    public Date getUnUseableTimeBeginTemp() {
        return unUseableTimeBeginTemp;
    }

    public void setUnUseableTimeBeginTemp(Date unUseableTimeBeginTemp) {
        this.unUseableTimeBeginTemp = unUseableTimeBeginTemp;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
