package com.ink.trade.core.enums;

/**
 * 
 * @ClassName: TradeStatus
 * @Description: 交易状态枚举类
 * @author wanghao
 * @date 2016年4月18日 下午7:27:42
 *
 */
public enum TradeStatus {

	// 成功,失败
	FAIL("0", "失败"), SUCCESS("1", "成功"), PROCESSING("2", "处理中");

	private String value;
	private String name;

	private TradeStatus(String value, String name) {
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

	public TradeStatus getNextStatus() {
		return null;
	}

	public static void main(String[] args) {
		for (TradeStatus status : TradeStatus.values()) {
			System.out.println(status);
		}
	}
}
