package com.ink.balance.core.ret;

import java.util.List;

import com.ink.balance.core.po.CheckChannel;

/**
 * @author 作者 :bo.chen
 * @version 创建时间：2016年3月31日 上午11:17:41
 * @description 描述：总对账数据汇总
 */
public class Result {
    //返回码
    private String retCode;
    //返回信息
    private String retMsg;
    //是否存在差异标识
    private boolean diffFlag=false;
    //汇总对象
    private CheckChannel checkChannel;
    //渠道差异数据的订单号
    private List<String> channelSdiff;
    //平台差异数据的订单号
    private List<String> platformSdiff;
    //渠道单边数据的订单号
    private List<String> channelSide;
    //平台单边数据的订单号
    private List<String> platformSide;

    public boolean isDiffFlag() {
        return diffFlag;
    }

    public void setDiffFlag(boolean diffFlag) {
        this.diffFlag = diffFlag;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public CheckChannel getCheckChannel() {
        return checkChannel;
    }

    public void setCheckChannel(CheckChannel checkChannel) {
        this.checkChannel = checkChannel;
    }

    public List<String> getChannelSdiff() {
        return channelSdiff;
    }

    public void setChannelSdiff(List<String> channelSdiff) {
        this.channelSdiff = channelSdiff;
    }

    public List<String> getPlatformSdiff() {
        return platformSdiff;
    }

    public void setPlatformSdiff(List<String> platformSdiff) {
        this.platformSdiff = platformSdiff;
    }

    public List<String> getChannelSide() {
        return channelSide;
    }

    public void setChannelSide(List<String> channelSide) {
        this.channelSide = channelSide;
    }

    public List<String> getPlatformSide() {
        return platformSide;
    }

    public void setPlatformSide(List<String> platformSide) {
        this.platformSide = platformSide;
    }

    @Override
    public String toString() {
        return "Result{" +
                "retCode='" + retCode + '\'' +
                ", retMsg='" + retMsg + '\'' +
                ", diffFlag=" + diffFlag +
                ", checkChannel=" + checkChannel +
                ", channelSdiff=" + channelSdiff +
                ", platformSdiff=" + platformSdiff +
                ", channelSide=" + channelSide +
                ", platformSide=" + platformSide +
                '}';
    }
}
