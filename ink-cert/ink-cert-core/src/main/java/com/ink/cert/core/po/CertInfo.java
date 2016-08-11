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

public class CertInfo extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "CertInfo";
//	
//	public static final String ALIAS_ID = "id";
//	
//	public static final String ALIAS_MERCHAT_CODE = "商户号";
//	
//	public static final String ALIAS_CERT_CODE = "证书编号";
//	
//	public static final String ALIAS_ENDECRY_TYPE = "加解密方式 1证书 2密钥";
//	
//	public static final String ALIAS_CERT_TYPE = "证书类型";
//	
//	public static final String ALIAS_SECRET_KEY = "密钥";
//	
//	public static final String ALIAS_ARITHMETIC_TYPE = "算法类型";
//	
//	public static final String ALIAS_CERT_ID = "证书ID";
//	
//	public static final String ALIAS_CERT_NAME = "证书文件名";
//	
//	public static final String ALIAS_STATUS = "状态 0正常1停用2删除";
//	
//	public static final String ALIAS_CREATE_USER = "创建人";
//	
//	public static final String ALIAS_CREATE_TIME = "创建时间";
//	
//	public static final String ALIAS_UPDATE_USER = "更新人";
//	
//	public static final String ALIAS_UPDATE_TIME = "更新时间";
//	
	
	//date formats
	public static final String FORMAT_CREATE_TIME = DATE_TIME_FORMAT;
	public static final String FORMAT_UPDATE_TIME = DATE_TIME_FORMAT;
	
	//columns START
	//id
	private Integer id;
	//商户号
	private String merchatCode;
	//证书编号
	private String certCode;
	//加解密方式 1证书 2密钥
	private String endecryType;
	//证书类型
	private String certType;
	//密钥
	private String secretKey;
	//算法类型
	private String arithmeticType;
	//证书文件名
	private String fileName;
	//证书名称
	private String certName;
	//状态 0正常1停用2删除
	private String status;
	//创建人
	private String createUser;
	//创建时间
	private java.util.Date createTime;
	//更新人
	private String updateUser;
	//更新时间
	private java.util.Date updateTime;
	/*证书ID*/
	private String certId;

	//columns END

	public CertInfo(){
	}

	public CertInfo(
		Integer id
	){
		this.id = id;
	}

	public void setId(Integer value) {
		this.id = value;
	}
	
	public Integer getId() {
		return this.id;
	}
	public void setMerchatCode(String value) {
		this.merchatCode = value;
	}
	
	public String getMerchatCode() {
		return this.merchatCode;
	}
	public void setCertCode(String value) {
		this.certCode = value;
	}
	
	public String getCertCode() {
		return this.certCode;
	}
	public void setEndecryType(String value) {
		this.endecryType = value;
	}
	
	public String getEndecryType() {
		return this.endecryType;
	}
	public void setCertType(String value) {
		this.certType = value;
	}
	
	public String getCertType() {
		return this.certType;
	}
	public void setSecretKey(String value) {
		this.secretKey = value;
	}
	
	public String getSecretKey() {
		return this.secretKey;
	}
	public void setArithmeticType(String value) {
		this.arithmeticType = value;
	}
	
	public String getArithmeticType() {
		return this.arithmeticType;
	}

	public void setCertName(String value) {
		this.certName = value;
	}
	
	public String getCertName() {
		return this.certName;
	}
	public void setStatus(String value) {
		this.status = value;
	}
	
	public String getStatus() {
		return this.status;
	}
	public void setCreateUser(String value) {
		this.createUser = value;
	}
	
	public String getCreateUser() {
		return this.createUser;
	}
	public String getCreateTimeString() {
		return DateConvertUtils.format(getCreateTime(), FORMAT_CREATE_TIME);
	}
	public void setCreateTimeString(String value) {
		setCreateTime(DateConvertUtils.parse(value, FORMAT_CREATE_TIME,java.util.Date.class));
	}
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	public void setUpdateUser(String value) {
		this.updateUser = value;
	}
	
	public String getUpdateUser() {
		return this.updateUser;
	}
	public String getUpdateTimeString() {
		return DateConvertUtils.format(getUpdateTime(), FORMAT_UPDATE_TIME);
	}
	public void setUpdateTimeString(String value) {
		setUpdateTime(DateConvertUtils.parse(value, FORMAT_UPDATE_TIME,java.util.Date.class));
	}
	
	public void setUpdateTime(java.util.Date value) {
		this.updateTime = value;
	}
	
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getCertId() {
		return certId;
	}

	public void setCertId(String certId) {
		this.certId = certId;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("MerchatCode", getMerchatCode())
			.append("CertCode",getCertCode())
			.append("EndecryType",getEndecryType())
			.append("CertType",getCertType())
			.append("SecretKey",getSecretKey())
			.append("ArithmeticType",getArithmeticType())
			.append("FileName", getFileName())
			.append("CertId", getCertId())
			.append("CertName", getCertName())
			.append("Status",getStatus())
			.append("CreateUser",getCreateUser())
			.append("CreateTime",getCreateTime())
			.append("UpdateUser",getUpdateUser())
			.append("UpdateTime",getUpdateTime())
			.toString();
	}
	
	public String toString(String separator) {
		StringBuffer sb = new StringBuffer();

			sb.append("id:").append(id).append(separator);
			sb.append("商户号:").append(merchatCode).append(separator);
			sb.append("证书编号:").append(certCode).append(separator);
			sb.append("加解密方式 1证书 2密钥:").append(endecryType).append(separator);
			sb.append("证书类型:").append(certType).append(separator);
			sb.append("密钥:").append(secretKey).append(separator);
			sb.append("算法类型:").append(arithmeticType).append(separator);
			sb.append("证书文件名:").append(fileName).append(separator);
			sb.append("证书名称:").append(certName).append(separator);
			sb.append("证书id:").append(certId).append(separator);
			sb.append("状态 0正常1停用2删除:").append(status).append(separator);
			sb.append("创建人:").append(createUser).append(separator);
			sb.append("创建时间:").append(getCreateTimeString()).append(separator);
			sb.append("更新人:").append(updateUser).append(separator);
			sb.append("更新时间:").append(getUpdateTimeString()).append(separator);
		
		return sb.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CertInfo == false) return false;
		if(this == obj) return true;
		CertInfo other = (CertInfo)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

