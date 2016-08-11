/**
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.cert.core.query;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Calendar;

import com.ink.base.BaseQuery;

/**
 * @author  zongtt
 * @version 1.0
 * @since 1.0
 */

public class CertLogQuery extends BaseQuery implements Serializable {
	private static final long serialVersionUID = 3148176768559230877L;

	/** id */
	private java.lang.Long id;
	/** 商户号 */
	private java.lang.String merchantCode;
	/** 证书号 */
	private java.lang.String certCode;
	/** 证书类型 */
	private java.lang.String certType;
	/** 证书名称 */
	private java.lang.String certName;
	/** 算法类型 */
	private String arithmeticType;
	/** 操作类型 */
	private java.lang.String operateType;
	/** 密钥 */
	private String secretKey;
	/** 原始消息 */
	private java.lang.String oldMsg;
	/** 响应消息 */
	private java.lang.String newMsg;
	/** 结果编号 */
	private java.lang.String resultCode;
	/** 结果信息 */
	private java.lang.String resultMsg;
	/** 请求流水ID */
	private java.lang.String requestId;
	/** 操作时间 */
	private java.util.Date operateTimeBegin;
	private java.util.Date operateTimeEnd;
	/*证书ID*/
	private String certId;
	public java.lang.Long getId() {
		return this.id;
	}

	public void setId(java.lang.Long value) {
		this.id = value;
	}

	public java.lang.String getMerchantCode() {
		return this.merchantCode;
	}

	public void setMerchantCode(java.lang.String value) {
		this.merchantCode = value;
	}

	public java.lang.String getCertCode() {
		return this.certCode;
	}

	public void setCertCode(java.lang.String value) {
		this.certCode = value;
	}

	public java.lang.String getCertType() {
		return this.certType;
	}

	public void setCertType(java.lang.String value) {
		this.certType = value;
	}

	public java.lang.String getCertName() {
		return this.certName;
	}

	public void setCertName(java.lang.String value) {
		this.certName = value;
	}

	public String getArithmeticType() {
		return this.arithmeticType;
	}

	public void setArithmeticType(String value) {
		this.arithmeticType = value;
	}

	public java.lang.String getOperateType() {
		return this.operateType;
	}

	public void setOperateType(java.lang.String value) {
		this.operateType = value;
	}

	public String getSecretKey() {
		return this.secretKey;
	}

	public void setSecretKey(String value) {
		this.secretKey = value;
	}

	public java.lang.String getOldMsg() {
		return this.oldMsg;
	}

	public void setOldMsg(java.lang.String value) {
		this.oldMsg = value;
	}

	public java.lang.String getNewMsg() {
		return this.newMsg;
	}

	public void setNewMsg(java.lang.String value) {
		this.newMsg = value;
	}

	public java.lang.String getResultCode() {
		return this.resultCode;
	}

	public void setResultCode(java.lang.String value) {
		this.resultCode = value;
	}

	public java.lang.String getResultMsg() {
		return this.resultMsg;
	}

	public void setResultMsg(java.lang.String value) {
		this.resultMsg = value;
	}

	public java.lang.String getRequestId() {
		return this.requestId;
	}

	public void setRequestId(java.lang.String value) {
		this.requestId = value;
	}

	public java.util.Date getOperateTimeBegin() {
		return this.operateTimeBegin;
	}

	public void setOperateTimeBegin(java.util.Date value) {
		this.operateTimeBegin = value;
	}

	public java.util.Date getOperateTimeEnd() {
		return this.operateTimeEnd;
	}

	public void setOperateTimeEnd(java.util.Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.operateTimeEnd = calendar.getTime();
		}else {
			this.operateTimeEnd = value;
		}
	}

	public String getCertId() {
		return certId;
	}

	public void setCertId(String certId) {
		this.certId = certId;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}

}

