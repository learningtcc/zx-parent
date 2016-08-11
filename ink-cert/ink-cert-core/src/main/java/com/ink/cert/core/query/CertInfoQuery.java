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

public class CertInfoQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    
	/** id */
	private Integer id;
	/** 商户号 */
	private String merchatCode;
	/** 证书编号 */
	private String certCode;
	/** 加解密方式 1证书 2密钥 */
	private String endecryType;
	/** 证书类型 */
	private String certType;
	/** 密钥 */
	private String secretKey;
	/** 算法类型 */
	private String arithmeticType;
	/** 证书文件名 */
	private String fileName;
	/**证书名称*/
	private String certName;
	/** 状态 0正常1停用2删除 */
	private String status;
	/** 创建人 */
	private String createUser;
	/** 创建时间 */
	private java.util.Date createTimeBegin;
	private java.util.Date createTimeEnd;
	/** 更新人 */
	private String updateUser;
	/*证书ID*/
	private String certId;
	/** 更新时间 */
	private java.util.Date updateTimeBegin;
	private java.util.Date updateTimeEnd;
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer value) {
		this.id = value;
	}
	
	public String getMerchatCode() {
		return this.merchatCode;
	}
	
	public void setMerchatCode(String value) {
		this.merchatCode = value;
	}
	
	public String getCertCode() {
		return this.certCode;
	}
	
	public void setCertCode(String value) {
		this.certCode = value;
	}
	
	public String getEndecryType() {
		return this.endecryType;
	}
	
	public void setEndecryType(String value) {
		this.endecryType = value;
	}
	
	public String getCertType() {
		return this.certType;
	}
	
	public void setCertType(String value) {
		this.certType = value;
	}
	
	public String getSecretKey() {
		return this.secretKey;
	}
	
	public void setSecretKey(String value) {
		this.secretKey = value;
	}
	
	public String getArithmeticType() {
		return this.arithmeticType;
	}
	
	public void setArithmeticType(String value) {
		this.arithmeticType = value;
	}

	
	public String getCertName() {
		return this.certName;
	}
	
	public void setCertName(String value) {
		this.certName = value;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public void setStatus(String value) {
		this.status = value;
	}
	
	public String getCreateUser() {
		return this.createUser;
	}
	
	public void setCreateUser(String value) {
		this.createUser = value;
	}
	
	public java.util.Date getCreateTimeBegin() {
		return this.createTimeBegin;
	}
	
	public void setCreateTimeBegin(java.util.Date value) {
		this.createTimeBegin = value;
	}	
	
	public java.util.Date getCreateTimeEnd() {
		return this.createTimeEnd;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setCreateTimeEnd(java.util.Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.createTimeEnd = calendar.getTime();
		}else {
			this.createTimeEnd = value;
		}
	}
	
	public String getUpdateUser() {
		return this.updateUser;
	}
	
	public void setUpdateUser(String value) {
		this.updateUser = value;
	}
	
	public java.util.Date getUpdateTimeBegin() {
		return this.updateTimeBegin;
	}
	
	public void setUpdateTimeBegin(java.util.Date value) {
		this.updateTimeBegin = value;
	}	
	
	public java.util.Date getUpdateTimeEnd() {
		return this.updateTimeEnd;
	}
	
	public void setUpdateTimeEnd(java.util.Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.updateTimeEnd = calendar.getTime();
		}else {
			this.updateTimeEnd = value;
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

