package com.ink.channel.core.boofoopay.request;

import java.io.Serializable;

public class BFBindBankCardReq extends BaseRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -847673922138916417L;
	private String txn_sub_type;//交易子类取值:01
	private String biz_type;//接入类型
	private String trans_serial_no;//商户流水号
	private String trans_id;// 商户订单号
	private String acc_no;// 绑定卡号
	private String id_card_type;//身份证类型
	private String id_card;// 身份证号
	private String id_holder;//持卡人姓名
	private String mobile;//银行卡绑定手机号
	private String acc_pwd;//卡号密码
	private String valid_date;//卡有效期
	private String valid_no;//卡安全码
	private String pay_code;//银行编码
	private String trade_date;//订单日期 14 位定长。格式：年年年年月月日日时时分分秒秒
	private String sms_code;//验证码
	private String additional_info;//附加字段
	private String req_reserved;//请求方保留域
	public String getTxn_sub_type() {
		return txn_sub_type;
	}
	public void setTxn_sub_type(String txn_sub_type) {
		this.txn_sub_type = txn_sub_type;
	}
	public String getTrans_serial_no() {
		return trans_serial_no;
	}
	public void setTrans_serial_no(String trans_serial_no) {
		this.trans_serial_no = trans_serial_no;
	}
	public String getTrans_id() {
		return trans_id;
	}
	public void setTrans_id(String trans_id) {
		this.trans_id = trans_id;
	}
	public String getAcc_no() {
		return acc_no;
	}
	public void setAcc_no(String acc_no) {
		this.acc_no = acc_no;
	}
	public String getId_card() {
		return id_card;
	}
	public void setId_card(String id_card) {
		this.id_card = id_card;
	}
	public String getId_holder() {
		return id_holder;
	}
	public void setId_holder(String id_holder) {
		this.id_holder = id_holder;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPay_code() {
		return pay_code;
	}
	public void setPay_code(String pay_code) {
		this.pay_code = pay_code;
	}
	public String getTrade_date() {
		return trade_date;
	}
	public void setTrade_date(String trade_date) {
		this.trade_date = trade_date;
	}
	public String getSms_code() {
		return sms_code;
	}
	public void setSms_code(String sms_code) {
		this.sms_code = sms_code;
	}
	public String getBiz_type() {
		return biz_type;
	}
	public void setBiz_type(String biz_type) {
		this.biz_type = biz_type;
	}
	public String getId_card_type() {
		return id_card_type;
	}
	public void setId_card_type(String id_card_type) {
		this.id_card_type = id_card_type;
	}
	public String getAcc_pwd() {
		return acc_pwd;
	}
	public void setAcc_pwd(String acc_pwd) {
		this.acc_pwd = acc_pwd;
	}
	public String getValid_date() {
		return valid_date;
	}
	public void setValid_date(String valid_date) {
		this.valid_date = valid_date;
	}
	public String getValid_no() {
		return valid_no;
	}
	public void setValid_no(String valid_no) {
		this.valid_no = valid_no;
	}
	public String getAdditional_info() {
		return additional_info;
	}
	public void setAdditional_info(String additional_info) {
		this.additional_info = additional_info;
	}
	public String getReq_reserved() {
		return req_reserved;
	}
	public void setReq_reserved(String req_reserved) {
		this.req_reserved = req_reserved;
	}
	
}
