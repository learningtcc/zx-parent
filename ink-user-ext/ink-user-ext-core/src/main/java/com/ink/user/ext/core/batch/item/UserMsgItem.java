package com.ink.user.ext.core.batch.item;

import java.io.Serializable;

/**
 * 批量文件的一行数据对应的bean
 * 
 * @author yangchen
 * @date 2016年6月22日 下午2:41:58
 */
public class UserMsgItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6041465838470769278L;

	// 商户号
	private Long mchId;
	// 手机号
	private String phone;
	// 客户号
	private Long custId;
	// 名称
	private String name;
	// 昵称
	private String nickName;
	// 短信通道
	private String msgChannel;
	// 短信模板
	private String msgTemplate;
	// 活动信息
	private String eventInfo;
	// 证件类型
	private String idType;
	// 证件号
	private String idNo;
	// 地址
	private String address;
	// 性别
	private String sex;
	// 邮箱
	private String email;
	// 邮编
	private String zipcode;

	public Long getMchId() {
		return mchId;
	}

	public void setMchId(Long mchId) {
		this.mchId = mchId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getMsgChannel() {
		return msgChannel;
	}

	public void setMsgChannel(String msgChannel) {
		this.msgChannel = msgChannel;
	}

	public String getMsgTemplate() {
		return msgTemplate;
	}

	public void setMsgTemplate(String msgTemplate) {
		this.msgTemplate = msgTemplate;
	}

	public String getEventInfo() {
		return eventInfo;
	}

	public void setEventInfo(String eventInfo) {
		this.eventInfo = eventInfo;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserMsgItem [mchId=");
		builder.append(mchId);
		builder.append(", phone=");
		builder.append(phone);
		builder.append(", custId=");
		builder.append(custId);
		builder.append(", name=");
		builder.append(name);
		builder.append(", nickName=");
		builder.append(nickName);
		builder.append(", msgChannel=");
		builder.append(msgChannel);
		builder.append(", msgTemplate=");
		builder.append(msgTemplate);
		builder.append(", eventInfo=");
		builder.append(eventInfo);
		builder.append(", idType=");
		builder.append(idType);
		builder.append(", idNo=");
		builder.append(idNo);
		builder.append(", address=");
		builder.append(address);
		builder.append(", sex=");
		builder.append(sex);
		builder.append(", email=");
		builder.append(email);
		builder.append(", zipcode=");
		builder.append(zipcode);
		builder.append("]");
		return builder.toString();
	}

}
