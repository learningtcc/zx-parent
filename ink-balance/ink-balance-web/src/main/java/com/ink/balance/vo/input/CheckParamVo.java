package com.ink.balance.vo.input;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class CheckParamVo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 7475220831224521325L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Long id;

    private String no;
    private String name;
    private Integer type;
    private String channelMerchantNo;
    private int checkStatus = -1;

    private int adjustStatus = -1;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tradeBeginTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tradeEndTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date checkBeginTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date checkEndTime;


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
        return "CheckParamVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", channelMerchantNo='" + channelMerchantNo + '\'' +
                ", no='" + no + '\'' +
                ", checkStatus=" + checkStatus +
                ", adjustStatus=" + adjustStatus +
                ", tradeBeginTime=" + tradeBeginTime +
                ", tradeEndTime=" + tradeEndTime +
                ", checkBeginTime=" + checkBeginTime +
                ", checkEndTime=" + checkEndTime +
                '}';
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getChannelMerchantNo() {
		return channelMerchantNo;
	}

	public void setChannelMerchantNo(String channelMerchantNo) {
		this.channelMerchantNo = channelMerchantNo;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}
}
