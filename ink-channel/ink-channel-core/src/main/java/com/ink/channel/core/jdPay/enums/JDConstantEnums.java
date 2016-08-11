package com.ink.channel.core.jdPay.enums;


public enum JDConstantEnums {
	CAEDTYPE_C("C","信用卡"),CAEDTYPE_D("D","借记卡"),
	TRADE_TYPE_V("V","签约"),TRADE_TYPE_S("S","消费"),TRADE_TYPE_Q("Q","查询"),TRADE_TYPE_R("R","退款"),
	CURRENCY("CNY","人民币");
	private String code;
	private String name;
	JDConstantEnums(String code,String name){
		this.code=code;
		this.name=name;
	};
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
