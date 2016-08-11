package com.ink.channel.core.boofoopay.response;

import java.io.Serializable;

public class RootCollection implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8892128862762711447L;
	private String resp_code;//应答码
	private String resp_msg;//应答信息
	private String member_id;//商户号
	private String terminal_id;//终端号
	private String data_type;//加密数据类型
	private String txn_type;//交易类型
	private String txn_sub_type;//交易子类
	private String biz_type;//接入类型
	public String getResp_code() {
		return resp_code;
	}
	public void setResp_code(String resp_code) {
		this.resp_code = resp_code;
	}
	public String getResp_msg() {
		return resp_msg;
	}
	public void setResp_msg(String resp_msg) {
		this.resp_msg = resp_msg;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getTerminal_id() {
		return terminal_id;
	}
	public void setTerminal_id(String terminal_id) {
		this.terminal_id = terminal_id;
	}
	public String getData_type() {
		return data_type;
	}
	public void setData_type(String data_type) {
		this.data_type = data_type;
	}
	public String getTxn_type() {
		return txn_type;
	}
	public void setTxn_type(String txn_type) {
		this.txn_type = txn_type;
	}
	public String getTxn_sub_type() {
		return txn_sub_type;
	}
	public void setTxn_sub_type(String txn_sub_type) {
		this.txn_sub_type = txn_sub_type;
	}
	public String getBiz_type() {
		return biz_type;
	}
	public void setBiz_type(String biz_type) {
		this.biz_type = biz_type;
	}
	
}
