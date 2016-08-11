package com.ink.channel.core.minsheng.response;

import com.ink.channel.core.minsheng.request.CmbcBodyBean;

public class CmbcQuickTradeQueryResBean extends CmbcBodyBean {

	/**  
	 * @since JDK 1.7  
	 */  
	
	private static final long serialVersionUID = -4705558395697938299L;

	private String txnType;// 交易类型

	private String txnStat;// 交易状态0-处理中 1-成功 2-失败

	private String amount;// 金额 元为单位，100代表100元

	private String merTransTime;// 商户交易时间 格式为yyyyMMddHHmmss，例如：20140825010101

	private String merOrderId;// 商户订单号

	private String custId;// 如有值通过数据密钥加密

	private String transTime;// 交易传输时间 格式为yyyyMMddHHmmss，例如：20140825010101

	private String voidFlag;// 撤销标志

	private String refNo;// 系统参考号

	private String tranRespMsg;// 应答码文本信息

	private String bankId;// 支付银行号

	private String bankName;// 银行名称

	private String storableCardNo;// 短卡号:由卡号前六位后四位组成

	public CmbcQuickTradeQueryResBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getTxnType() {
		return txnType;
	}

	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}

	public String getTxnStat() {
		return txnStat;
	}

	public void setTxnStat(String txnStat) {
		this.txnStat = txnStat;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getMerTransTime() {
		return merTransTime;
	}

	public void setMerTransTime(String merTransTime) {
		this.merTransTime = merTransTime;
	}

	public String getMerOrderId() {
		return merOrderId;
	}

	public void setMerOrderId(String merOrderId) {
		this.merOrderId = merOrderId;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getTransTime() {
		return transTime;
	}

	public void setTransTime(String transTime) {
		this.transTime = transTime;
	}

	public String getVoidFlag() {
		return voidFlag;
	}

	public void setVoidFlag(String voidFlag) {
		this.voidFlag = voidFlag;
	}

	public String getRefNo() {
		return refNo;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	public String getTranRespMsg() {
		return tranRespMsg;
	}

	public void setTranRespMsg(String tranRespMsg) {
		this.tranRespMsg = tranRespMsg;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getStorableCardNo() {
		return storableCardNo;
	}

	public void setStorableCardNo(String storableCardNo) {
		this.storableCardNo = storableCardNo;
	}

	@Override
	public String toString() {
		return "CmbcQuickTradeQueryResBean [txnType=" + txnType + ", txnStat=" + txnStat + ", amount=" + amount
				+ ", merTransTime=" + merTransTime + ", merOrderId=" + merOrderId + ", custId=" + custId
				+ ", transTime=" + transTime + ", voidFlag=" + voidFlag + ", refNo=" + refNo + ", tranRespMsg="
				+ tranRespMsg + ", bankId=" + bankId + ", bankName=" + bankName + ", storableCardNo=" + storableCardNo
				+ "]";
	}
	
	

}
