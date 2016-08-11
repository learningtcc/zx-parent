package com.ink.user.api.enums;

public enum AccSacTypeEnum {

	CURRENT("0001", "活期账户"), REGULAR("0002", "定期账户"), BALANCE("0003", "余额账户"), EXPERIENCEGOLD(
			"0004", "体验金账户"), RED("0005", "红包账户"), ACOUNTFEE("0006", "客户手续费账户");

	private String value;
	private String name;

	private AccSacTypeEnum(String value, String name) {
		this.value = value;
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return this.getValue() + "-" + this.getName();
	}

	public AccSacTypeEnum getNextStatus() {
		return null;
	}

	public static void main(String[] args) {
		for (AccSacTypeEnum status : AccSacTypeEnum.values()) {
			System.out.println(status);
		}
	}
}
