package com.ink.balance.vo.input;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
/**
 * 
* <p>Title:CheckChannelParamVo </p>
* <p>Description:手工对账 </p>
* <p>Company: </p> 
* @author zhaojie
* @date 2016年6月17日 下午3:32:30
 */
public class CheckChannelParamVo implements Serializable {

    /**
     *
     */

    private static final long serialVersionUID = 7475220831224521325L;

   
    private Long id;

    private String channelNo;
    
    private String ChannelMerchantNo;

    private int checkStatus = -1;

    private int adjustStatus = -1;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date TradeDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date CheckDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tradeBeginTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tradeEndTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date checkBeginTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date checkEndTime;


    public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo;
    }
    public String getChannelMerchantNo() {
		return ChannelMerchantNo;
	}

	public void setChannelMerchantNo(String channelMerchantNo) {
		ChannelMerchantNo = channelMerchantNo;
	}

	public Date getTradeDate() {
		return TradeDate;
	}

	public void setTradeDate(Date tradeDate) {
		TradeDate = tradeDate;
	}

	public Date getCheckDate() {
		return CheckDate;
	}

	public void setCheckDate(Date checkDate) {
		CheckDate = checkDate;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(int checkStatus) {
        this.checkStatus = checkStatus;
    }

    public int getAdjustStatus() {
        return adjustStatus;
    }

    public void setAdjustStatus(int adjustStatus) {
        this.adjustStatus = adjustStatus;
    }

    public Date getTradeBeginTime() {
        return tradeBeginTime;
    }

    public void setTradeBeginTime(Date tradeBeginTime) {
        this.tradeBeginTime = tradeBeginTime;
    }

    public Date getTradeEndTime() {
        return tradeEndTime;
    }

    public void setTradeEndTime(Date tradeEndTime) {
        this.tradeEndTime = tradeEndTime;
    }

    public Date getCheckBeginTime() {
        return checkBeginTime;
    }

    public void setCheckBeginTime(Date checkBeginTime) {
        this.checkBeginTime = checkBeginTime;
    }

    public Date getCheckEndTime() {
        return checkEndTime;
    }

    public void setCheckEndTime(Date checkEndTime) {
        this.checkEndTime = checkEndTime;
    }

    @Override
    public String toString() {
        return "CheckChannelParamVo{" +
                "id=" + id +
                ", channelNo='" + channelNo + '\'' +
                ", ChannelMerchantNo='" + ChannelMerchantNo + '\'' +
                ", checkStatus=" + checkStatus +
                ", adjustStatus=" + adjustStatus +
                ", TradeDate=" + TradeDate +
                ", CheckDate=" + CheckDate +
                ", tradeBeginTime=" + tradeBeginTime +
                ", tradeEndTime=" + tradeEndTime +
                ", checkBeginTime=" + checkBeginTime +
                ", checkEndTime=" + checkEndTime +
                '}';
    }
}
