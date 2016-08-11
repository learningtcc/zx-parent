package com.ink.channel.core.bestpay.request;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.ink.channel.core.utils.MyJaxbDateAdapter;

/**
 * 翼支付签约请求消息类
 * @author huohb
 *
 */
@XmlRootElement(name = "data")
public class EPaySignRequestBean extends EPayBaseRequestBean {

	private static final long serialVersionUID = 1L;
	private String extUserId;// 外部用户标识
	
	private Date reqTime = new Date();// 请求时间yyyyMMddHHmmss
	
	private String extOrderSeq;// 外部系统订单号
	
	private String currencyCode = "RMB";// 币种（可选RMB,USD,HKD）
	
	private String trsSummary;// 交易摘要
	
	private String trsMemo;// 交易备注
	
	private String validateType;// 验证类型（01：无扣费验证，02：扣一分钱验证，03：无验证）
	
	private FasBankAccount bankAccount;// 银行账户信息
	
	private String userId;// 外部用户标识
	
	private String customerTransCode;// 外部业务编码
	
	private String address;// 联系地址
	
	private String netWorkNature;// 网点性质
	
	private String userFullName;// 用户全称
	
	private String ebkType;// 企业类型
	
	private String payeeName;// 收款单位名称
	
	private String netWorkAreaCode;// 网点区域编码
	
	private String arpType;// 代收付类型（01：代收，02：代付）
	
	private String operator;// 操作者
	
	private String agentCode;// 代理商编号
	
	private String agentName;// 代理商名称

	public String getExtUserId() {
		return extUserId;
	}

	public void setExtUserId(String extUserId) {
		this.extUserId = extUserId;
	}
	
	@XmlJavaTypeAdapter(value = MyJaxbDateAdapter.class)
	public Date getReqTime() {
		return reqTime;
	}

	public void setReqTime(Date reqTime) {
		this.reqTime = reqTime;
	}

	public String getExtOrderSeq() {
		return extOrderSeq;
	}

	public void setExtOrderSeq(String extOrderSeq) {
		this.extOrderSeq = extOrderSeq;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getTrsSummary() {
		return trsSummary;
	}

	public void setTrsSummary(String trsSummary) {
		this.trsSummary = trsSummary;
	}

	public String getTrsMemo() {
		return trsMemo;
	}

	public void setTrsMemo(String trsMemo) {
		this.trsMemo = trsMemo;
	}

	public String getValidateType() {
		return validateType;
	}

	public void setValidateType(String validateType) {
		this.validateType = validateType;
	}

	public FasBankAccount getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(FasBankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCustomerTransCode() {
		return customerTransCode;
	}

	public void setCustomerTransCode(String customerTransCode) {
		this.customerTransCode = customerTransCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNetWorkNature() {
		return netWorkNature;
	}

	public void setNetWorkNature(String netWorkNature) {
		this.netWorkNature = netWorkNature;
	}

	public String getUserFullName() {
		return userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}

	public String getEbkType() {
		return ebkType;
	}

	public void setEbkType(String ebkType) {
		this.ebkType = ebkType;
	}

	public String getPayeeName() {
		return payeeName;
	}

	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}

	public String getNetWorkAreaCode() {
		return netWorkAreaCode;
	}

	public void setNetWorkAreaCode(String netWorkAreaCode) {
		this.netWorkAreaCode = netWorkAreaCode;
	}

	public String getArpType() {
		return arpType;
	}

	public void setArpType(String arpType) {
		this.arpType = arpType;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getAgentCode() {
		return agentCode;
	}

	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
}
