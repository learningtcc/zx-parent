package com.ink.channel.core.bestpay.response;
/**
 * 翼支付交易综合查询返回集合中的每条记录类
 * @author huohb
 *
 */
public class FasTransResponse {

	private String actualAmount;// 实际交易金额
	private String appName;// 应用系统名
	private String bizCode;// 业务类型编码
	private String bizDesc;// 业务类型描述
	private String channelCode;// 渠道号
	private String currencyCode;// 币种
	private String custAreaCode;// 客户区域编码
	private String custCode;// 发起客户号
	private String endTime;// 结束时间
	private String extOrderSeq;// 外部系统订单号
	private String fee;// 服务费用
	private String originAmount;// 原始金额
	private String payType;// 支付方式
	private String platCode;// 平台号
	private String reqSeq;// 请求流水号
	private String reqTime;// 请求时间
	private String stat;// 状态：000:成功 001:初始化 002:处理中 004:失败 010:冲正成功 021:退款成功
						// 025:部分成功
	private String tradeTime;// 开始时间
	private String trsCode;// 交易编码
	private String trsDesc;// 交易类型描述
	private String trsRespCode;// 交易响应码
	private String trsRespMes;// 交易响应码描述
	private String trsSeq;// 交易流水号
	private String trsType;// 交易类型
	private String updateTime;// 更新时间

	public String getActualAmount() {
		return actualAmount;
	}

	public void setActualAmount(String actualAmount) {
		this.actualAmount = actualAmount;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getBizCode() {
		return bizCode;
	}

	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}

	public String getBizDesc() {
		return bizDesc;
	}

	public void setBizDesc(String bizDesc) {
		this.bizDesc = bizDesc;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getCustAreaCode() {
		return custAreaCode;
	}

	public void setCustAreaCode(String custAreaCode) {
		this.custAreaCode = custAreaCode;
	}

	public String getCustCode() {
		return custCode;
	}

	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getExtOrderSeq() {
		return extOrderSeq;
	}

	public void setExtOrderSeq(String extOrderSeq) {
		this.extOrderSeq = extOrderSeq;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getOriginAmount() {
		return originAmount;
	}

	public void setOriginAmount(String originAmount) {
		this.originAmount = originAmount;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getPlatCode() {
		return platCode;
	}

	public void setPlatCode(String platCode) {
		this.platCode = platCode;
	}

	public String getReqSeq() {
		return reqSeq;
	}

	public void setReqSeq(String reqSeq) {
		this.reqSeq = reqSeq;
	}

	public String getReqTime() {
		return reqTime;
	}

	public void setReqTime(String reqTime) {
		this.reqTime = reqTime;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public String getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}

	public String getTrsCode() {
		return trsCode;
	}

	public void setTrsCode(String trsCode) {
		this.trsCode = trsCode;
	}

	public String getTrsDesc() {
		return trsDesc;
	}

	public void setTrsDesc(String trsDesc) {
		this.trsDesc = trsDesc;
	}

	public String getTrsRespCode() {
		return trsRespCode;
	}

	public void setTrsRespCode(String trsRespCode) {
		this.trsRespCode = trsRespCode;
	}

	public String getTrsRespMes() {
		return trsRespMes;
	}

	public void setTrsRespMes(String trsRespMes) {
		this.trsRespMes = trsRespMes;
	}

	public String getTrsSeq() {
		return trsSeq;
	}

	public void setTrsSeq(String trsSeq) {
		this.trsSeq = trsSeq;
	}

	public String getTrsType() {
		return trsType;
	}

	public void setTrsType(String trsType) {
		this.trsType = trsType;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
}