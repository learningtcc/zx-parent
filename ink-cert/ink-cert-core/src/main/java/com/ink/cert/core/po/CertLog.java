/**
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.cert.core.po;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ink.base.util.DateConvertUtils;
import com.ink.base.BaseEntity;

/**
 * @author  zongtt
 * @version 1.0
 * @since 1.0
 */

public class CertLog extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;

	//alias
	public static final String TABLE_ALIAS = "CertLog";
//	
//	public static final String ALIAS_ID = "id";
//	
//	public static final String ALIAS_MERCHANT_CODE = "商户号";
//	
//	public static final String ALIAS_CERT_CODE = "证书号";
//	
//	public static final String ALIAS_CERT_TYPE = "证书类型";
//	
//	public static final String ALIAS_CERT_ID = "证书ID";
//	
//	public static final String ALIAS_CERT_NAME = "证书名称";
//	
//	public static final String ALIAS_ARITHMETIC_TYPE = "算法类型";
//	
//	public static final String ALIAS_OPERATE_TYPE = "操作类型";
//	
//	public static final String ALIAS_SECRET_KEY = "密钥";
//	
//	public static final String ALIAS_OLD_MSG = "原始消息";
//	
//	public static final String ALIAS_NEW_MSG = "响应消息";
//	
//	public static final String ALIAS_RESULT_CODE = "结果编号";
//	
//	public static final String ALIAS_RESULT_MSG = "结果信息";
//	
//	public static final String ALIAS_REQUEST_ID = "请求流水ID";
//	
//	public static final String ALIAS_OPERATE_TIME = "操作时间";
//	

	//date formats
	public static final String FORMAT_OPERATE_TIME = DATE_TIME_FORMAT;

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
			java.lang.Long id
	){
		this.id = id;
	}

	public void setId(java.lang.Long value) {
		this.id = value;
	}

	public java.lang.Long getId() {
		return this.id;
	}
	public void setMerchantCode(java.lang.String value) {
		this.merchantCode = value;
	}

	public java.lang.String getMerchantCode() {
		return this.merchantCode;
	}
	public void setCertCode(java.lang.String value) {
		this.certCode = value;
	}

	public java.lang.String getCertCode() {
		return this.certCode;
	}
	public void setCertType(java.lang.String value) {
		this.certType = value;
	}

	public java.lang.String getCertType() {
		return this.certType;
	}

	public void setCertName(java.lang.String value) {
		this.certName = value;
	}

	public java.lang.String getCertName() {
		return this.certName;
	}
	public void setArithmeticType(String value) {
		this.arithmeticType = value;
	}

	public String getArithmeticType() {
		return this.arithmeticType;
	}
	public void setOperateType(java.lang.String value) {
		this.operateType = value;
	}

	public java.lang.String getOperateType() {
		return this.operateType;
	}
	public void setSecretKey(String value) {
		this.secretKey = value;
	}

	public String getSecretKey() {
		return this.secretKey;
	}
	public void setOldMsg(java.lang.String value) {
		this.oldMsg = value;
	}

	public java.lang.String getOldMsg() {
		return this.oldMsg;
	}
	public void setNewMsg(java.lang.String value) {
		this.newMsg = value;
	}

	public java.lang.String getNewMsg() {
		return this.newMsg;
	}
	public void setResultCode(java.lang.String value) {
		this.resultCode = value;
	}

	public java.lang.String getResultCode() {
		return this.resultCode;
	}
	public void setResultMsg(java.lang.String value) {
		this.resultMsg = value;
	}

	public java.lang.String getResultMsg() {
		return this.resultMsg;
	}
	public void setRequestId(java.lang.String value) {
		this.requestId = value;
	}

	public java.lang.String getRequestId() {
		return this.requestId;
	}
	public String getOperateTimeString() {
		return DateConvertUtils.format(getOperateTime(), FORMAT_OPERATE_TIME);
	}
	public void setOperateTimeString(String value) {
		setOperateTime(DateConvertUtils.parse(value, FORMAT_OPERATE_TIME,java.util.Date.class));
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
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("Id",getId())
				.append("MerchantCode", getMerchantCode())
				.append("CertCode",getCertCode())
				.append("CertType",getCertType())
				.append("CertName",getCertName())
				.append("CertId", getCertId())
				.append("ArithmeticType", getArithmeticType())
				.append("OperateType",getOperateType())
				.append("SecretKey",getSecretKey())
				.append("OldMsg",getOldMsg())
				.append("NewMsg",getNewMsg())
				.append("ResultCode",getResultCode())
				.append("ResultMsg",getResultMsg())
				.append("RequestId",getRequestId())
				.append("OperateTime",getOperateTime())
				.toString();
	}

	public String toString(String separator) {
		StringBuffer sb = new StringBuffer();

		sb.append("id:").append(id).append(separator);
		sb.append("商户号:").append(merchantCode).append(separator);
		sb.append("证书号:").append(certCode).append(separator);
		sb.append("证书类型:").append(certType).append(separator);
		sb.append("证书名称:").append(certName).append(separator);
		sb.append("证书id:").append(certId).append(separator);
		sb.append("算法类型:").append(arithmeticType).append(separator);
		sb.append("操作类型:").append(operateType).append(separator);
		sb.append("密钥:").append(secretKey).append(separator);
		sb.append("原始消息:").append(oldMsg).append(separator);
		sb.append("响应消息:").append(newMsg).append(separator);
		sb.append("结果编号:").append(resultCode).append(separator);
		sb.append("结果信息:").append(resultMsg).append(separator);
		sb.append("请求流水:").append(requestId).append(separator);
		sb.append("操作时间:").append(getOperateTimeString()).append(separator);

		return sb.toString();
	}

	public int hashCode() {
		return new HashCodeBuilder()
				.append(getId())
				.toHashCode();
	}

	public boolean equals(Object obj) {
		if(obj instanceof CertLog == false) return false;
		if(this == obj) return true;
		CertLog other = (CertLog)obj;
		return new EqualsBuilder()
				.append(getId(),other.getId())
				.isEquals();
	}
}

