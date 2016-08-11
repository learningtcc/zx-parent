package com.ink.trade.api.platform.trade.model.in;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ink.base.BaseQuery;

public class TradeOrderQueryInput extends BaseQuery implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -41416320700767126L;
	//主键id
    private Long id;
    //请求流水号
    private String reqId;
    //商户号
    private String mchId;
    //交易名称
    private String txnName;
    //交易码
    private String txnCode;
    //订单日期
    private java.util.Date orderTime;
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
    //操作人
    private String operator;
    //备注
    private String remark;
    //绑卡信息表主键
    private Long cid;
    //最后修改时间
    private java.util.Date lastupdateTime;
    //渠道号，支付成功后更新订单状态时，插入渠道号信息
    private String channelNo;
    //银行卡号
    private String cardNo;
    //用户id
    private String userId;
    //商户订单号
    private String orderId;
    //交易时间-记录商户传过来的交易时间
    private java.util.Date tradeDate;
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
    private Date orderTimeBegin;
    private Date orderTimeEnd;
    private Date createTimeBegin;
    private Date createTimeEnd;
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
	public String getMchId() {
		return mchId;
	}
	public void setMchId(String mchId) {
		this.mchId = mchId;
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
	public Date getOrderTimeBegin() {
		return orderTimeBegin;
	}
	public void setOrderTimeBegin(Date orderTimeBegin) {
		this.orderTimeBegin = orderTimeBegin;
	}
	public Date getOrderTimeEnd() {
		return orderTimeEnd;
	}
	public void setOrderTimeEnd(Date orderTimeEnd) {
		this.orderTimeEnd = orderTimeEnd;
	}
	public Date getCreateTimeBegin() {
		return createTimeBegin;
	}
	public void setCreateTimeBegin(Date createTimeBegin) {
		this.createTimeBegin = createTimeBegin;
	}
	public Date getCreateTimeEnd() {
		return createTimeEnd;
	}
	public void setCreateTimeEnd(Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}
    
}
