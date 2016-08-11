package com.ink.channel.core.boofoopay.request;

import java.io.Serializable;

public class BFUnbindBankCardReq extends BaseRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1185893720942579708L;
	private String txn_sub_type;//交易子类02
	private String trans_serial_no;//商户流水号 
	private String bind_id;//绑定标识号
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
	public String getBind_id() {
		return bind_id;
	}
	public void setBind_id(String bind_id) {
		this.bind_id = bind_id;
	}
	public String getTrade_date() {
		return trade_date;
	}
	public void setTrade_date(String trade_date) {
		this.trade_date = trade_date;
	}
	
}
