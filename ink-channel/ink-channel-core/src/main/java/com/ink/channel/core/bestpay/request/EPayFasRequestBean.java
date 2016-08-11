package com.ink.channel.core.bestpay.request;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.ink.channel.core.utils.MyJaxbDateAdapter;

/**
 * 翼支付代扣支付请求消息类
 * @author huohb
 *
 */
@XmlRootElement(name = "data")
public class EPayFasRequestBean extends EPayBaseRequestBean {

	private static final long serialVersionUID = 1L;
	
	private Date reqTime = new Date(); // 请求格式：yyyyMMddHHmmss
	
	private String extOrderSeq;// 外部订单
	
	private String currencyCode;// 币种码
	
	private String amount;// 金额
	
	private String signId;// 签约号
	
	private String accountCode;// 银行卡号
	
	private PayeeAccount payeeAccount;// 支付账户

	@XmlJavaTypeAdapter(value = MyJaxbDateAdapter.class)
	public Date getReqTime() {
		return reqTime;
	}

	public void setReqTime(Date reqTime) {
		this.reqTime = reqTime;
	}

	public String getExtOrderSeq() {
		return extOrderSeq;
	}

	public void setExtOrderSeq(String extOrderSeq) {
		this.extOrderSeq = extOrderSeq;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getSignId() {
		return signId;
	}

	public void setSignId(String signId) {
		this.signId = signId;
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public PayeeAccount getPayeeAccount() {
		return payeeAccount;
	}

	public void setPayeeAccount(PayeeAccount payeeAccount) {
		this.payeeAccount = payeeAccount;
	}

}
