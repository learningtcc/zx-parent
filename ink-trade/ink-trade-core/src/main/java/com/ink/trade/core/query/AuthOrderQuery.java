package com.ink.trade.core.query;

import java.io.Serializable;

import com.ink.base.BaseQuery;

public class AuthOrderQuery extends BaseQuery implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 8876506159697110348L;
    
  //主键
    private String id;
    //订单号
    private String orderId;
    //请求流水号
    private String reqId;
    //交易码
    private String txnCode;
    //交易名称
    private String txnName;
    //签约状态
    private Integer status;
    //渠道编号
    private String channelNo;
    //版本号
    private Integer version;
    //用户id
    private String userId;
    //商户号
    private String mchId;
    //银行名称简写
    private String bankNameShort;
    //银行卡号
    private String cardNo;
    //姓名
    private String userName;
    //证件号
    private String idNo;
    //证件类型
    private String idType;
    //手机号
    private String phone;
    //cvv2
    private String cvv2;
    //有效开始日期
    private java.util.Date expireStartDate;
    //有效结束日期
    private java.util.Date expireEndDate;
    //最后更新时间
    private java.util.Date lastupdateTime;
    //创建时间
    private java.util.Date createTime;
    //repCode
    private String repCode;
    //repMsg
    private String repMsg;
    //asileRepCode
    private String asileRepCode;
    //asileRepMsg
    private String asileRepMsg;
    //columns END

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
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getReqId() {
        return reqId;
    }
    public void setReqId(String reqId) {
        this.reqId = reqId;
    }
    public String getTxnCode() {
        return txnCode;
    }
    public void setTxnCode(String txnCode) {
        this.txnCode = txnCode;
    }
    public String getTxnName() {
        return txnName;
    }
    public void setTxnName(String txnName) {
        this.txnName = txnName;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getChannelNo() {
        return channelNo;
    }
    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo;
    }
    public Integer getVersion() {
        return version;
    }
    public void setVersion(Integer version) {
        this.version = version;
    }
    public String getBankNameShort() {
        return bankNameShort;
    }
    public void setBankNameShort(String bankNameShort) {
        this.bankNameShort = bankNameShort;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getIdNo() {
        return idNo;
    }
    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }
    public String getIdType() {
        return idType;
    }
    public void setIdType(String idType) {
        this.idType = idType;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getCvv2() {
        return cvv2;
    }
    public void setCvv2(String cvv2) {
        this.cvv2 = cvv2;
    }
    public java.util.Date getExpireStartDate() {
        return expireStartDate;
    }
    public void setExpireStartDate(java.util.Date expireStartDate) {
        this.expireStartDate = expireStartDate;
    }
    public java.util.Date getExpireEndDate() {
        return expireEndDate;
    }
    public void setExpireEndDate(java.util.Date expireEndDate) {
        this.expireEndDate = expireEndDate;
    }
    public java.util.Date getLastupdateTime() {
        return lastupdateTime;
    }
    public void setLastupdateTime(java.util.Date lastupdateTime) {
        this.lastupdateTime = lastupdateTime;
    }
    public java.util.Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }
    public String getRepCode() {
        return repCode;
    }
    public void setRepCode(String repCode) {
        this.repCode = repCode;
    }
    public String getRepMsg() {
        return repMsg;
    }
    public void setRepMsg(String repMsg) {
        this.repMsg = repMsg;
    }
    public String getAsileRepCode() {
        return asileRepCode;
    }
    public void setAsileRepCode(String asileRepCode) {
        this.asileRepCode = asileRepCode;
    }
    public String getAsileRepMsg() {
        return asileRepMsg;
    }
    public void setAsileRepMsg(String asileRepMsg) {
        this.asileRepMsg = asileRepMsg;
    }
    
}
