package com.ink.trade.core.po;

import java.io.Serializable;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ink.base.BaseEntity;
import com.ink.base.util.DateConvertUtils;

/**
 * 
 * @Description 鉴权信息[用户绑卡关系表]
 * @author xuguoqi
 * @date 2016年5月3日 下午3:31:06
 */
public class Auth extends BaseEntity implements Serializable,Comparable<Auth> {
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "Auth";
//	
//	public static final String ALIAS_ID = "主键";
//	
//	public static final String ALIAS_CARD_NO = "银行卡号";
//	
//	public static final String ALIAS_CARD_TYPE = "银行卡类型";
//	
//	public static final String ALIAS_PHONE_NO = "手机号";
//	
//	public static final String ALIAS_ID_TYPE = "证件类型";
//	
//	public static final String ALIAS_ID_NO = "身份证号";
//	
//	public static final String ALIAS_USER_NAME = "姓名";
//	
//	public static final String ALIAS_BANK_SHORT = "所属银行简码";
//	
//	public static final String ALIAS_BANK_NAME = "所属银行";
//	
//	public static final String ALIAS_STATUS = "状态";
//	
//	public static final String ALIAS_VERSION = "版本号";
//	
//	public static final String ALIAS_IS_DELETE = "删除标识";
//	
//	public static final String ALIAS_REMARK = "备注";
//	
//	public static final String ALIAS_MCH_ID = "商户号";
//	
//	public static final String ALIAS_LASTUPDATE_TIME = "最后修改时间";
//	
//	public static final String ALIAS_REQ_ID = "请求流水号";
//	
//	public static final String ALIAS_USER_ID = "客户号";
//	
//	public static final String ALIAS_CREATE_TIME = "createTime";
//	
//	public static final String ALIAS_PAY_TYPE = "支付类型";
//	
	
	//date formats
	public static final String FORMAT_LASTUPDATE_TIME = DATE_FORMAT;
	public static final String FORMAT_CREATE_TIME = DATE_FORMAT;
	
	//columns START
	//主键
	private Long id;
	//银行卡号
	private String cardNo;
	//银行卡类型
	private String cardType;
	//手机号
	private String phoneNo;
	//证件类型
	private String idType;
	//身份证号
	private String idNo;
	//姓名
	private String userName;
	//所属银行简码
	private String bankShort;
	//所属银行
	private String bankName;
	//状态
	private Integer status;
	//版本号
	private Integer version;
	//删除标识
	private Integer isDelete;
	//备注
	private String remark;
	//商户号
	private String mchId;
	//最后修改时间
	private java.util.Date lastupdateTime;
	//请求流水号
	private String reqId;
	//客户号
	private String userId;
	//createTime
	private java.util.Date createTime;
	//支付类型
	private String payType;
	//columns END

	public Auth(){
	}

	public Auth(
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
	public void setCardNo(java.lang.String value) {
		this.cardNo = value;
	}
	
	public java.lang.String getCardNo() {
		return this.cardNo;
	}
	public void setCardType(java.lang.String value) {
		this.cardType = value;
	}
	
	public java.lang.String getCardType() {
		return this.cardType;
	}
	public void setPhoneNo(java.lang.String value) {
		this.phoneNo = value;
	}
	
	public java.lang.String getPhoneNo() {
		return this.phoneNo;
	}
	public void setIdType(java.lang.String value) {
		this.idType = value;
	}
	
	public java.lang.String getIdType() {
		return this.idType;
	}
	public void setIdNo(java.lang.String value) {
		this.idNo = value;
	}
	
	public java.lang.String getIdNo() {
		return this.idNo;
	}
	public void setUserName(java.lang.String value) {
		this.userName = value;
	}
	
	public java.lang.String getUserName() {
		return this.userName;
	}
	public void setBankShort(java.lang.String value) {
		this.bankShort = value;
	}
	
	public java.lang.String getBankShort() {
		return this.bankShort;
	}
	public void setBankName(java.lang.String value) {
		this.bankName = value;
	}
	
	public java.lang.String getBankName() {
		return this.bankName;
	}
	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}
	
	public java.lang.Integer getStatus() {
		return this.status;
	}
	public void setVersion(java.lang.Integer value) {
		this.version = value;
	}
	
	public java.lang.Integer getVersion() {
		return this.version;
	}
	public void setIsDelete(java.lang.Integer value) {
		this.isDelete = value;
	}
	
	public java.lang.Integer getIsDelete() {
		return this.isDelete;
	}
	public void setRemark(java.lang.String value) {
		this.remark = value;
	}
	
	public java.lang.String getRemark() {
		return this.remark;
	}
	public void setMchId(java.lang.String value) {
		this.mchId = value;
	}
	
	public java.lang.String getMchId() {
		return this.mchId;
	}
	public String getLastupdateTimeString() {
		return DateConvertUtils.format(getLastupdateTime(), FORMAT_LASTUPDATE_TIME);
	}
	public void setLastupdateTimeString(String value) {
		setLastupdateTime(DateConvertUtils.parse(value, FORMAT_LASTUPDATE_TIME,java.util.Date.class));
	}
	
	public void setLastupdateTime(java.util.Date value) {
		this.lastupdateTime = value;
	}
	
	public java.util.Date getLastupdateTime() {
		return this.lastupdateTime;
	}
	public void setReqId(java.lang.String value) {
		this.reqId = value;
	}
	
	public java.lang.String getReqId() {
		return this.reqId;
	}
	public void setUserId(java.lang.String value) {
		this.userId = value;
	}
	
	public java.lang.String getUserId() {
		return this.userId;
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
	public void setPayType(java.lang.String value) {
		this.payType = value;
	}
	
	public java.lang.String getPayType() {
		return this.payType;
	}
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("CardNo",getCardNo())
			.append("CardType",getCardType())
			.append("PhoneNo",getPhoneNo())
			.append("IdType",getIdType())
			.append("IdNo",getIdNo())
			.append("UserName",getUserName())
			.append("BankShort",getBankShort())
			.append("BankName",getBankName())
			.append("Status",getStatus())
			.append("Version",getVersion())
			.append("IsDelete",getIsDelete())
			.append("Remark",getRemark())
			.append("MchId",getMchId())
			.append("LastupdateTime",getLastupdateTime())
			.append("ReqId",getReqId())
			.append("UserId",getUserId())
			.append("CreateTime",getCreateTime())
			.append("PayType",getPayType())
			.toString();
	}
	
	public String toString(String separator) {
		StringBuffer sb = new StringBuffer();

			sb.append("主键:").append(id).append(separator);
			sb.append("银行卡号:").append(cardNo).append(separator);
			sb.append("银行卡类型:").append(cardType).append(separator);
			sb.append("手机号:").append(phoneNo).append(separator);
			sb.append("证件类型:").append(idType).append(separator);
			sb.append("身份证号:").append(idNo).append(separator);
			sb.append("姓名:").append(userName).append(separator);
			sb.append("所属银行简码:").append(bankShort).append(separator);
			sb.append("所属银行:").append(bankName).append(separator);
			sb.append("状态:").append(status).append(separator);
			sb.append("版本号:").append(version).append(separator);
			sb.append("删除标识:").append(isDelete).append(separator);
			sb.append("备注:").append(remark).append(separator);
			sb.append("商户号:").append(mchId).append(separator);
			sb.append("最后修改时间:").append(getLastupdateTimeString()).append(separator);
			sb.append("请求流水号:").append(reqId).append(separator);
			sb.append("客户号:").append(userId).append(separator);
			sb.append("createTime:").append(getCreateTimeString()).append(separator);
			sb.append("支付类型:").append(payType).append(separator);
		
		return sb.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	
	public String getPkValue() {
		if(getId() != null){
			return String.valueOf(getId());
		}
		
		return "";
	}

	/**
	 * @Description TODO
	 * @author xuguoqi
	 * @date 2016年7月27日 下午3:12:13
	 * @param o
	 * @return  
	 */
	@Override
	public int compareTo(Auth o) {
		return this.getLastupdateTime().compareTo(o.getLastupdateTime());
	}}