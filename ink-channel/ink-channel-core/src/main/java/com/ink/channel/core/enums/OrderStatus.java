package com.ink.channel.core.enums;

public enum OrderStatus {
	
	SUCCESS_CODE("00","成功"),FAILE_CODE("02","失败"),PROCESSES_CODE("01","处理中");
	
	private String code;
	private String msg;
	
	private OrderStatus(String code,String msg){
		this.code=code;
		this.msg=msg;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
