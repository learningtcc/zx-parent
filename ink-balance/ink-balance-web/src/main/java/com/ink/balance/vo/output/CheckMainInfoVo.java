package com.ink.balance.vo.output;

import java.io.Serializable;
import java.math.BigDecimal;

public class CheckMainInfoVo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -7992724664110273490L;

    private String channelName;//渠道名称

    private String channelMerchantNo;//支付渠道商户号

    private String checkDate;//对账日期

    private String tradeDate;//交易日期

    private int channelSuccTradeCount;//渠道成功交易

    private BigDecimal channelSuccTradeMoney;//渠道成功交易金额

    private int channelTradeCount;//渠道总交易数量

    private int platTradeCount;//平台交易总数

    private int platsuccTradeCount;//平台成功交易总数

    private BigDecimal platSuccTradeMoney;//成功交易金额

    private int diffCount;//差异总笔数

    private int checkSuccCount;//对账成功笔数

    private BigDecimal checkSuccMoney;//对账成功金额

    private int platResidentCount;//平台驻留笔数

    private int bankResidentCount;//银行驻留笔数

    public CheckMainInfoVo() {
        super();
        // TODO Auto-generated constructor stub
    }

    public CheckMainInfoVo(String channelName, String channelMerchantNo, String checkDate, String tradeDate,
                           int channelSuccTradeCount, BigDecimal channelSuccTradeMoney, int channelTradeCount, int platTradeCount,
                           int platsuccTradeCount, BigDecimal platSuccTradeMoney, int diffCount, int checkSuccCount,
                           BigDecimal checkSuccMoney, int platResidentCount, int bankResidentCount) {
        super();
        this.channelName = channelName;
        this.channelMerchantNo = channelMerchantNo;
        this.checkDate = checkDate;
        this.tradeDate = tradeDate;
        this.channelSuccTradeCount = channelSuccTradeCount;
        this.channelSuccTradeMoney = channelSuccTradeMoney;
        this.channelTradeCount = channelTradeCount;
        this.platTradeCount = platTradeCount;
        this.platsuccTradeCount = platsuccTradeCount;
        this.platSuccTradeMoney = platSuccTradeMoney;
        this.diffCount = diffCount;
        this.checkSuccCount = checkSuccCount;
        this.checkSuccMoney = checkSuccMoney;
        this.platResidentCount = platResidentCount;
        this.bankResidentCount = bankResidentCount;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelMerchantNo() {
        return channelMerchantNo;
    }

    public void setChannelMerchantNo(String channelMerchantNo) {
        this.channelMerchantNo = channelMerchantNo;
    }

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }

    public int getChannelSuccTradeCount() {
        return channelSuccTradeCount;
    }

    public void setChannelSuccTradeCount(int channelSuccTradeCount) {
        this.channelSuccTradeCount = channelSuccTradeCount;
    }

    public BigDecimal getChannelSuccTradeMoney() {
        return channelSuccTradeMoney;
    }

    public void setChannelSuccTradeMoney(BigDecimal channelSuccTradeMoney) {
        this.channelSuccTradeMoney = channelSuccTradeMoney;
    }

    public int getChannelTradeCount() {
        return channelTradeCount;
    }

    public void setChannelTradeCount(int channelTradeCount) {
        this.channelTradeCount = channelTradeCount;
    }

    public int getPlatTradeCount() {
        return platTradeCount;
    }

    public void setPlatTradeCount(int platTradeCount) {
        this.platTradeCount = platTradeCount;
    }

    public int getPlatsuccTradeCount() {
        return platsuccTradeCount;
    }

    public void setPlatsuccTradeCount(int platsuccTradeCount) {
        this.platsuccTradeCount = platsuccTradeCount;
    }

    public BigDecimal getPlatSuccTradeMoney() {
        return platSuccTradeMoney;
    }

    public void setPlatSuccTradeMoney(BigDecimal platSuccTradeMoney) {
        this.platSuccTradeMoney = platSuccTradeMoney;
    }

    public int getDiffCount() {
        return diffCount;
    }

    public void setDiffCount(int diffCount) {
        this.diffCount = diffCount;
    }

    public int getCheckSuccCount() {
        return checkSuccCount;
    }

    public void setCheckSuccCount(int checkSuccCount) {
        this.checkSuccCount = checkSuccCount;
    }

    public BigDecimal getCheckSuccMoney() {
        return checkSuccMoney;
    }

    public void setCheckSuccMoney(BigDecimal checkSuccMoney) {
        this.checkSuccMoney = checkSuccMoney;
    }

    public int getPlatResidentCount() {
        return platResidentCount;
    }

    public void setPlatResidentCount(int platResidentCount) {
        this.platResidentCount = platResidentCount;
    }

    public int getBankResidentCount() {
        return bankResidentCount;
    }

    public void setBankResidentCount(int bankResidentCount) {
        this.bankResidentCount = bankResidentCount;
    }

    @Override
    public String toString() {
        return "CheckMainInfoVo [channelName=" + channelName + ", channelMerchantNo=" + channelMerchantNo
                + ", checkDate=" + checkDate + ", tradeDate=" + tradeDate + ", channelSuccTradeCount="
                + channelSuccTradeCount + ", channelSuccTradeMoney=" + channelSuccTradeMoney + ", channelTradeCount="
                + channelTradeCount + ", platTradeCount=" + platTradeCount + ", platsuccTradeCount="
                + platsuccTradeCount + ", platSuccTradeMoney=" + platSuccTradeMoney + ", diffCount=" + diffCount
                + ", checkSuccCount=" + checkSuccCount + ", checkSuccMoney=" + checkSuccMoney + ", platResidentCount="
                + platResidentCount + ", bankResidentCount=" + bankResidentCount + "]";
    }


}
