package com.ink.channel.core.boofoopay.request;

import java.io.Serializable;

public class QueryBFPay2CardReq implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6353279877881433868L;
	
	private String trans_batchid;//宝付批次号
	
	private String trans_no;// 商户订单号
	
	public String getTrans_batchid() {
		return trans_batchid;
	}
	public void setTrans_batchid(String trans_batchid) {
		this.trans_batchid = trans_batchid;
	}
	public String getTrans_no() {
		return trans_no;
	}
	public void setTrans_no(String trans_no) {
		this.trans_no = trans_no;
	}
	
}
