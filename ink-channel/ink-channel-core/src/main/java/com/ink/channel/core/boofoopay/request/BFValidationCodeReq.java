package com.ink.channel.core.boofoopay.request;

public class BFValidationCodeReq extends BaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6283054613743198001L;
	
	private String txn_sub_type;//交易子类取值05
	private String trans_serial_no;//商户流水号
	private String trans_id;//商户订单号
	private String acc_no;//绑定的卡号
	private String mobile;//银行卡绑定手机号
	private String bind_id;//绑定标识号
	private String txn_amt;//交易金额
	private String next_txn_sub_type;//下一步进行的交易子类
	private String trade_date;//订单日期
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
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getBind_id() {
		return bind_id;
	}
	public void setBind_id(String bind_id) {
		this.bind_id = bind_id;
	}
	public String getTxn_amt() {
		return txn_amt;
	}
	public void setTxn_amt(String txn_amt) {
		this.txn_amt = txn_amt;
	}
	public String getNext_txn_sub_type() {
		return next_txn_sub_type;
	}
	public void setNext_txn_sub_type(String next_txn_sub_type) {
		this.next_txn_sub_type = next_txn_sub_type;
	}
	public String getTrade_date() {
		return trade_date;
	}
	public void setTrade_date(String trade_date) {
		this.trade_date = trade_date;
	}
}
