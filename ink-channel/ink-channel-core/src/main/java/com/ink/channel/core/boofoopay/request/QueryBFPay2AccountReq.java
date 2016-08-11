package com.ink.channel.core.boofoopay.request;

import java.io.Serializable;

public class QueryBFPay2AccountReq extends BaseRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1667929301055933320L;
	private String txn_sub_type;//交易子类取值:06
	private String trans_serial_no;//商户流水号
	private String orig_trans_id;// 原始商户订单号
	private String  trade_date;//订单日期
	
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
	public String getOrig_trans_id() {
		return orig_trans_id;
	}
	public void setOrig_trans_id(String orig_trans_id) {
		this.orig_trans_id = orig_trans_id;
	}
	public String getTrade_date() {
		return trade_date;
	}
	public void setTrade_date(String trade_date) {
		this.trade_date = trade_date;
	}
	
}
