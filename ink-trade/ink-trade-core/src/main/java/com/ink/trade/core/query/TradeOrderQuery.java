package com.ink.trade.core.query;

import java.io.Serializable;
import java.math.BigDecimal;

import com.ink.base.BaseQuery;

public class TradeOrderQuery extends BaseQuery implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 4107706858392096551L;
    //columns START
    //商户号
    private String mchId;
    //卡号
    private String cardNo;
    //用户号
    private String userId;
    //订单号
    private String orderId;
    //主键id
    private Long id;
    //请求流水号
    private String reqId;
    //交易名称
    private String txnName;
    //交易码
    private String txnCode;
    //订单日期
    private java.util.Date orderTime;
    private java.util.Date orderTimeBegin;
    private java.util.Date orderTimeEnd;
    //手机号
    private String phoneNo;
    //姓名
    private String userName;
    //订单金额
    private BigDecimal amt;
    //订单状态
    private Integer status;
    //版本号
    private Integer version;
    //创建时间
    private java.util.Date createTime;
    private java.util.Date createTimeBegin;
    private java.util.Date createTimeEnd;
    //操作人
    private String operator;
    //备注
    private String remark;
    //绑卡信息表主键
    private Long cid;
    //最后修改时间
    private java.util.Date lastupdateTime;
    private java.util.Date lastupdateTimeBegin;
    private java.util.Date lastupdateTimeEnd;
    //渠道号，支付成功后更新订单状态时，插入渠道号信息
    private String channelNo;
    //交易时间-记录商户传过来的交易时间
    private java.util.Date tradeDate;
    
    private java.util.Date tradeDateBegin;
    private java.util.Date tradeDateEnd;
    //账户类型
    private String accountType;
    //银行简码
    private String bankShort;
    //渠道token
    private String token;
    //支付方式
    private String payType;
    //回调地址
    private String noticeUrl;
    private String routeBusinessType;
    public String getMchId() {
        return mchId;
    }
    public void setMchId(String mchId) {
        this.mchId = mchId;
    }
    public String getCardNo() {
        return cardNo;
    }
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getReqId() {
		return reqId;
	}
	public void setReqId(String reqId) {
		this.reqId = reqId;
	}
	public String getTxnName() {
		return txnName;
	}
	public void setTxnName(String txnName) {
		this.txnName = txnName;
	}
	public String getTxnCode() {
		return txnCode;
	}
	public void setTxnCode(String txnCode) {
		this.txnCode = txnCode;
	}
	public java.util.Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(java.util.Date orderTime) {
		this.orderTime = orderTime;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public BigDecimal getAmt() {
		return amt;
	}
	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public java.util.Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Long getCid() {
		return cid;
	}
	public void setCid(Long cid) {
		this.cid = cid;
	}
	public java.util.Date getLastupdateTime() {
		return lastupdateTime;
	}
	public void setLastupdateTime(java.util.Date lastupdateTime) {
		this.lastupdateTime = lastupdateTime;
	}
	public String getChannelNo() {
		return channelNo;
	}
	public void setChannelNo(String channelNo) {
		this.channelNo = channelNo;
	}
	public java.util.Date getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(java.util.Date tradeDate) {
		this.tradeDate = tradeDate;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getBankShort() {
		return bankShort;
	}
	public void setBankShort(String bankShort) {
		this.bankShort = bankShort;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getNoticeUrl() {
		return noticeUrl;
	}
	public void setNoticeUrl(String noticeUrl) {
		this.noticeUrl = noticeUrl;
	}
	public String getRouteBusinessType() {
		return routeBusinessType;
	}
	public void setRouteBusinessType(String routeBusinessType) {
		this.routeBusinessType = routeBusinessType;
	}
	public java.util.Date getOrderTimeBegin() {
		return orderTimeBegin;
	}
	public void setOrderTimeBegin(java.util.Date orderTimeBegin) {
		this.orderTimeBegin = orderTimeBegin;
	}
	public java.util.Date getOrderTimeEnd() {
		return orderTimeEnd;
	}
	public void setOrderTimeEnd(java.util.Date orderTimeEnd) {
		this.orderTimeEnd = orderTimeEnd;
	}
	public java.util.Date getCreateTimeBegin() {
		return createTimeBegin;
	}
	public void setCreateTimeBegin(java.util.Date createTimeBegin) {
		this.createTimeBegin = createTimeBegin;
	}
	public java.util.Date getCreateTimeEnd() {
		return createTimeEnd;
	}
	public void setCreateTimeEnd(java.util.Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}
	public java.util.Date getLastupdateTimeBegin() {
		return lastupdateTimeBegin;
	}
	public void setLastupdateTimeBegin(java.util.Date lastupdateTimeBegin) {
		this.lastupdateTimeBegin = lastupdateTimeBegin;
	}
	public java.util.Date getLastupdateTimeEnd() {
		return lastupdateTimeEnd;
	}
	public void setLastupdateTimeEnd(java.util.Date lastupdateTimeEnd) {
		this.lastupdateTimeEnd = lastupdateTimeEnd;
	}
	public java.util.Date getTradeDateBegin() {
		return tradeDateBegin;
	}
	public void setTradeDateBegin(java.util.Date tradeDateBegin) {
		this.tradeDateBegin = tradeDateBegin;
	}
	public java.util.Date getTradeDateEnd() {
		return tradeDateEnd;
	}
	public void setTradeDateEnd(java.util.Date tradeDateEnd) {
		this.tradeDateEnd = tradeDateEnd;
	}
    
    
}
