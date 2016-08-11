package com.ink.user.api.enums;

import java.util.HashMap;
import java.util.Map;

public enum DepositEnum {

	
	APT("APT0000001", "支付交易平台"), CMBC("CMBC000001", "民生托管平台");
	
	private String value;
	private String name;
	
	
	private DepositEnum(String value, String name) {
		this.value = value;
		this.name = name;
	}
	
	
	public static String getNameByCode(String value) {
		DepositEnum status = codeMap.get(value);
		if (status != null) {
			return status.getName(); 
		}
		return "未知状态";
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static DepositEnum toDelFlagEnum(String value) {
		return codeMap.get(value);
	}
	
	private static Map<String, DepositEnum> codeMap;
	private static Map<String, DepositEnum> nameMap;
	static {
		codeMap = new HashMap<String, DepositEnum>();
		nameMap = new HashMap<String, DepositEnum>();
		for (DepositEnum status : DepositEnum.values()) {
			codeMap.put(status.getValue(), status);
			nameMap.put(status.getName(), status);
		}
	}

	public String toString() {
		return this.getValue() + "-" + this.getName();
	}

	public static void main(String[] args) {
		for (DepositEnum status : DepositEnum.values()) {
			System.out.println(status);
		}
	}
}
