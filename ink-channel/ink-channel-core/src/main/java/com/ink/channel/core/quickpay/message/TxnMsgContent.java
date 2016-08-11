package com.ink.channel.core.quickpay.message;

import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.lang.builder.ToStringBuilder;
/**
 * 快钱支付请求类
 * @author huohb
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class TxnMsgContent {

	@XmlElement
	private String interactiveStatus;// 消息状态
	@XmlElement
	private String txnType;// 交易类型
	@XmlElement
	private String merchantId;// 商户号
	@XmlElement
	private String terminalId;// 终端号
	@XmlElement
	private String tr3Url;// tr3回调地址
	@XmlElement
	private String entryTime;//商户端交易时间
	@XmlElement
	private String cardNo;// 卡号
	@XmlElement
	private String amount;// 交易金额
	@XmlElement
	private String externalRefNumber;// 外部跟踪号
	@XmlElement
	private String customerId;// 客户号
	@XmlElement
	private String spFlag;// 特殊交易标识
	@XmlJavaTypeAdapter(MapAdapter.class)
	@XmlElement(name = "extMap")
	private Map<String, String> extMap;// 扩展数据
	@XmlElement
	private String responseCode;// 返回码
	@XmlElement
	private String responseTextMessage;// 返回信息
	@XmlElement
	private String cardOrg;// 卡组织编号
	@XmlElement
	private String issuer;// 发卡银行名称
	@XmlElement
	private String authorizationCode;//授权码
	@XmlElement
	private String refNumber;// 系统参考号
	@XmlElement
	private String transTime;// 交易时间
	@XmlElement
	private String storableCardNo;// 缩略卡号
	@XmlElement
	private String voidFlag;//撤销标志’1’表示本交易已经被撤销，’0’表示本交易正常
	@XmlElement
	private String txnStatus;//交易状态 :S－交易成功,F－交易失败,P－交易挂起
	@XmlElement
	private String cardHolderName;// 持卡人姓名
	@XmlElement
	private String idType;// 证件类型
	@XmlElement
	private String cardHolderId;// 持卡人身份证号
	@XmlElement
	private String bankId;// 银行编码
	@XmlElement
	private String origRefNumber;// 原系统参考号
	@XmlElement
	private String orignalTxnType;// 原交易类型

	public String getOrignalTxnType() {
		return orignalTxnType;
	}

	public void setOrignalTxnType(String orignalTxnType) {
		this.orignalTxnType = orignalTxnType;
	}

	public String getOrigRefNumber() {
		return origRefNumber;
	}

	public void setOrigRefNumber(String origRefNumber) {
		this.origRefNumber = origRefNumber;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getCardHolderId() {
		return cardHolderId;
	}

	public void setCardHolderId(String cardHolderId) {
		this.cardHolderId = cardHolderId;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getInteractiveStatus() {
		return interactiveStatus;
	}

	public void setInteractiveStatus(String interactiveStatus) {
		this.interactiveStatus = interactiveStatus;
	}

	public String getTxnType() {
		return txnType;
	}

	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getTr3Url() {
		return tr3Url;
	}

	public void setTr3Url(String tr3Url) {
		this.tr3Url = tr3Url;
	}

	public String getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(String entryTime) {
		this.entryTime = entryTime;
	}


	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getExternalRefNumber() {
		return externalRefNumber;
	}

	public void setExternalRefNumber(String externalRefNumber) {
		this.externalRefNumber = externalRefNumber;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getSpFlag() {
		return spFlag;
	}

	public void setSpFlag(String spFlag) {
		this.spFlag = spFlag;
	}

	public Map<String, String> getExtMap() {
		return extMap;
	}

	public void setExtMap(Map<String, String> extMap) {
		this.extMap = extMap;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseTextMessage() {
		return responseTextMessage;
	}

	public void setResponseTextMessage(String responseTextMessage) {
		this.responseTextMessage = responseTextMessage;
	}

	public String getCardOrg() {
		return cardOrg;
	}

	public String getStorableCardNo() {
		return storableCardNo;
	}

	public void setStorableCardNo(String storableCardNo) {
		this.storableCardNo = storableCardNo;
	}

	public void setCardOrg(String cardOrg) {
		this.cardOrg = cardOrg;
	}

	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	public String getAuthorizationCode() {
		return authorizationCode;
	}

	public void setAuthorizationCode(String authorizationCode) {
		this.authorizationCode = authorizationCode;
	}

	public String getRefNumber() {
		return refNumber;
	}

	public void setRefNumber(String refNumber) {
		this.refNumber = refNumber;
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

	public String getTxnStatus() {
		return txnStatus;
	}

	public void setTxnStatus(String txnStatus) {
		this.txnStatus = txnStatus;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	
}
