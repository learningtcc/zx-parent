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

public class CertInfo implements java.io.Serializable {

	private static final long serialVersionUID = -220818100061466885L;
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
	/*证书ID*/
	private String certId;

	public CertInfo(){
	}

	public String getMerchatCode() {
		return merchatCode;
	}

	public void setMerchatCode(String merchatCode) {
		this.merchatCode = merchatCode;
	}

	public String getCertCode() {
		return certCode;
	}

	public void setCertCode(String certCode) {
		this.certCode = certCode;
	}

	public String getEndecryType() {
		return endecryType;
	}

	public void setEndecryType(String endecryType) {
		this.endecryType = endecryType;
	}

	public String getCertType() {
		return certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getArithmeticType() {
		return arithmeticType;
	}

	public void setArithmeticType(String arithmeticType) {
		this.arithmeticType = arithmeticType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getCertName() {
		return certName;
	}

	public void setCertName(String certName) {
		this.certName = certName;
	}

	public String getCertId() {
		return certId;
	}

	public void setCertId(String certId) {
		this.certId = certId;
	}

	@Override
	public String toString() {
		return "CertInfo{" +
				"merchatCode='" + merchatCode + '\'' +
				", certCode='" + certCode + '\'' +
				", endecryType='" + endecryType + '\'' +
				", certType='" + certType + '\'' +
				", secretKey='" + secretKey + '\'' +
				", arithmeticType='" + arithmeticType + '\'' +
				", fileName='" + fileName + '\'' +
				", certName='" + certName + '\'' +
				", certId='" + certId + '\'' +
				'}';
	}
}

