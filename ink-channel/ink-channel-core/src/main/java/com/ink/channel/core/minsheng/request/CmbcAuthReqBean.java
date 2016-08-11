package com.ink.channel.core.minsheng.request;
/**
 * 民生电商认证请求消息类
 * @author huohb
 *
 */
public class CmbcAuthReqBean extends CmbcBodyBean{

	private static final long serialVersionUID = 1L;
	
	private String bankName;// 银行名称
	
	private String accountNo;// 银行卡号
	
	private String accountName;//账户名称
	
	private String tranAmt;//交易金额
	
	private String certType;//证件类型
	
	private String certNo;// 身份证号
	
	private String mobileNo;// 手机号码
	
	private String merOrderId;// 订单号

    private String custId;//客户号
    
    private String phoneToken;//令牌信息
    
    private String phoneVerCode;//手机验证码
    
    private String expiredDate;
    
    private String cvv2;

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

	public String getTranAmt() {
		return tranAmt;
	}

	public void setTranAmt(String tranAmt) {
		this.tranAmt = tranAmt;
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

	public String getPhoneToken() {
		return phoneToken;
	}

	public void setPhoneToken(String phoneToken) {
		this.phoneToken = phoneToken;
	}

	public String getPhoneVerCode() {
		return phoneVerCode;
	}

	public void setPhoneVerCode(String phoneVerCode) {
		this.phoneVerCode = phoneVerCode;
	}

	public String getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(String expiredDate) {
		this.expiredDate = expiredDate;
	}

	public String getCvv2() {
		return cvv2;
	}

	public void setCvv2(String cvv2) {
		this.cvv2 = cvv2;
	}
	
    
}
