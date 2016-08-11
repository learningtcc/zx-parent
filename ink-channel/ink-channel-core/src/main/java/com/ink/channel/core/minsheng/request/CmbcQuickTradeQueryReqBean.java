package com.ink.channel.core.minsheng.request;

public class CmbcQuickTradeQueryReqBean extends CmbcBodyBean{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1672150315060584834L;
	
	private String refNo;//系统参考号  非必填
	
	private String merOderNo;//需要查询的商户订单号
	
	private String merTransDate; //商户端交易日期 原消费交易商户端日期YYYYMMDDHHMMSS
	
	private String txnType;//必填  交易类型,原交易类型

	public CmbcQuickTradeQueryReqBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getRefNo() {
		return refNo;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	public String getMerOderNo() {
		return merOderNo;
	}

	public void setMerOderNo(String merOderNo) {
		this.merOderNo = merOderNo;
	}

	public String getMerTransDate() {
		return merTransDate;
	}

	public void setMerTransDate(String merTransDate) {
		this.merTransDate = merTransDate;
	}

	public String getTxnType() {
		return txnType;
	}

	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}

	@Override
	public String toString() {
		return "CmbcQuickTradeQueryReqBean [refNo=" + refNo + ", merOderNo=" + merOderNo + ", merTransDate="
				+ merTransDate + ", txnType=" + txnType + "]";
	}
	
	
	
	
}
