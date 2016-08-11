package com.ink.channel.core.minsheng.request;

public class CmbcCollectionReqBean extends CmbcBodyBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2207466901278274502L;
	private String merPlatAcctAlias;// 商户账户收款账户别名
	private String protocolNo;// 协议号
	private String bankName;// 银行名称
	private String accountNo;// 银行账号
	private String accountName;// 银行账户名称
	private String accountType;// 账户类型
	private String openProvince; // 开户行所在省
	private String openCity;// 开户行所在市
	private String openName;// 开户行名称
	private String tranAmt;// 交易金额
	private String curType;// 币种
	private String bsnType;// 业务类型(11203:虚拟账户充值)
	private String certType;// 开户证件类型
	private String certNo;// 证件号
	private String mobileNo;// 手机号
	private String prodInfo;// 商品信息
	private String msgExt;// 附加信息

	public String getMerPlatAcctAlias() {
		return merPlatAcctAlias;
	}

	public void setMerPlatAcctAlias(String merPlatAcctAlias) {
		this.merPlatAcctAlias = merPlatAcctAlias;
	}

	public String getProtocolNo() {
		return protocolNo;
	}

	public void setProtocolNo(String protocolNo) {
		this.protocolNo = protocolNo;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getOpenProvince() {
		return openProvince;
	}

	public void setOpenProvince(String openProvince) {
		this.openProvince = openProvince;
	}

	public String getOpenCity() {
		return openCity;
	}

	public void setOpenCity(String openCity) {
		this.openCity = openCity;
	}

	public String getOpenName() {
		return openName;
	}

	public void setOpenName(String openName) {
		this.openName = openName;
	}

	public String getTranAmt() {
		return tranAmt;
	}

	public void setTranAmt(String tranAmt) {
		this.tranAmt = tranAmt;
	}

	public String getCurType() {
		return curType;
	}

	public void setCurType(String curType) {
		this.curType = curType;
	}

	public String getBsnType() {
		return bsnType;
	}

	public void setBsnType(String bsnType) {
		this.bsnType = bsnType;
	}

	public String getCertType() {
		return certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getProdInfo() {
		return prodInfo;
	}

	public void setProdInfo(String prodInfo) {
		this.prodInfo = prodInfo;
	}

	public String getMsgExt() {
		return msgExt;
	}

	public void setMsgExt(String msgExt) {
		this.msgExt = msgExt;
	}

}
