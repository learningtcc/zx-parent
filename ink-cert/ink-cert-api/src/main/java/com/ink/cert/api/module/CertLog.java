/**
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.cert.api.module;

/**
 * @author  zongtt
 * @version 1.0
 * @since 1.0
 */

public class CertLog implements java.io.Serializable {


	private static final long serialVersionUID = 9188752800928448312L;
	//columns START
	//id
	private Long id;
	//商户号
	private String merchantCode;
	//证书号
	private String certCode;
	//证书类型
	private String certType;
	//证书名称
	private String certName;
	//算法类型
	private String arithmeticType;
	//操作类型
	private String operateType;
	//密钥
	private String secretKey;
	//原始消息
	private String oldMsg;
	//响应消息
	private String newMsg;
	//结果编号
	private String resultCode;
	//结果信息
	private String resultMsg;
	//请求流水ID
	private String requestId;
	//操作时间
	private java.util.Date operateTime;
	/*证书ID*/
	private String certId;
	//columns END

	public CertLog(){
	}

	public CertLog(
			Long id
	){
		this.id = id;
	}

	public void setId(Long value) {
		this.id = value;
	}

	public Long getId() {
		return this.id;
	}
	public void setMerchantCode(String value) {
		this.merchantCode = value;
	}

	public String getMerchantCode() {
		return this.merchantCode;
	}
	public void setCertCode(String value) {
		this.certCode = value;
	}

	public String getCertCode() {
		return this.certCode;
	}
	public void setCertType(String value) {
		this.certType = value;
	}

	public String getCertType() {
		return this.certType;
	}

	public void setCertName(String value) {
		this.certName = value;
	}

	public String getCertName() {
		return this.certName;
	}
	public void setArithmeticType(String value) {
		this.arithmeticType = value;
	}

	public String getArithmeticType() {
		return this.arithmeticType;
	}
	public void setOperateType(String value) {
		this.operateType = value;
	}

	public String getOperateType() {
		return this.operateType;
	}
	public void setSecretKey(String value) {
		this.secretKey = value;
	}

	public String getSecretKey() {
		return this.secretKey;
	}
	public void setOldMsg(String value) {
		this.oldMsg = value;
	}

	public String getOldMsg() {
		return this.oldMsg;
	}
	public void setNewMsg(String value) {
		this.newMsg = value;
	}

	public String getNewMsg() {
		return this.newMsg;
	}
	public void setResultCode(String value) {
		this.resultCode = value;
	}

	public String getResultCode() {
		return this.resultCode;
	}
	public void setResultMsg(String value) {
		this.resultMsg = value;
	}

	public String getResultMsg() {
		return this.resultMsg;
	}
	public void setRequestId(String value) {
		this.requestId = value;
	}

	public String getRequestId() {
		return this.requestId;
	}

	public String getCertId() {
		return certId;
	}

	public void setCertId(String certId) {
		this.certId = certId;
	}

	public void setOperateTime(java.util.Date value) {
		this.operateTime = value;
	}

	public java.util.Date getOperateTime() {
		return this.operateTime;
	}

	@Override
	public String toString() {
		return "CertLog{" +
				"id=" + id +
				", merchantCode='" + merchantCode + '\'' +
				", certCode='" + certCode + '\'' +
				", certType='" + certType + '\'' +
				", certName='" + certName + '\'' +
				", arithmeticType='" + arithmeticType + '\'' +
				", operateType='" + operateType + '\'' +
				", secretKey='" + secretKey + '\'' +
				", oldMsg='" + oldMsg + '\'' +
				", newMsg='" + newMsg + '\'' +
				", resultCode='" + resultCode + '\'' +
				", resultMsg='" + resultMsg + '\'' +
				", requestId='" + requestId + '\'' +
				", operateTime=" + operateTime +
				", certId='" + certId + '\'' +
				'}';
	}
}

