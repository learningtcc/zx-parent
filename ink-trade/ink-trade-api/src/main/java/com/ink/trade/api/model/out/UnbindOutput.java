package com.ink.trade.api.model.out;

import java.io.Serializable;

public class UnbindOutput implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 612287092354128343L;
	private String merchantId;
	private String userId;
	private String cardNo;
	private String payType;
	private String reponseCode;
	private String reponseMsg;
	private String status;
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}


	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getReponseCode() {
		return reponseCode;
	}

	public void setReponseCode(String reponseCode) {
		this.reponseCode = reponseCode;
	}

	public String getReponseMsg() {
		return reponseMsg;
	}

	public void setReponseMsg(String reponseMsg) {
		this.reponseMsg = reponseMsg;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UnbindOutput [merchantId=");
		builder.append(merchantId);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", cardNo=");
		builder.append(cardNo);
		builder.append(", payType=");
		builder.append(payType);
		builder.append(", reponseCode=");
		builder.append(reponseCode);
		builder.append(", reponseMsg=");
		builder.append(reponseMsg);
		builder.append("]");
		return builder.toString();
	}

}
