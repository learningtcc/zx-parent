package com.ink.balance.api.model.in;

import java.io.Serializable;

/**
 * @author xuguoqi
 * @Description 驻留分页查询条件参数
 * @date 2016年4月7日 上午10:41:39
 */
public class ResidentQueryParamInput implements Serializable {

    private static final long serialVersionUID = 8775313715916251970L;

    private int channelNo;

    private int payType;

    private int residentType;

    private int redidentTime;//单位天

    public int getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(int channelNo) {
        this.channelNo = channelNo;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public int getResidentType() {
        return residentType;
    }

    public void setResidentType(int residentType) {
        this.residentType = residentType;
    }

    public int getRedidentTime() {
        return redidentTime;
    }

    public void setRedidentTime(int redidentTime) {
        this.redidentTime = redidentTime;
    }

    public ResidentQueryParamInput() {
        super();
    }

    public ResidentQueryParamInput(int channelNo, int payType, int residentType, int redidentTime) {
        super();
        this.channelNo = channelNo;
        this.payType = payType;
        this.residentType = residentType;
        this.redidentTime = redidentTime;
    }

    @Override
    public String toString() {
        return "ResidentQueryParamInput [channelNo=" + channelNo + ", payType=" + payType + ", residentType="
                + residentType + ", redidentTime=" + redidentTime + "]";
    }


}
