package com.ink.trade.core.enums;

/**
 * 
 * @ClassName: PayStatus
 * @Description: 支付状态枚举类
 * @author wanghao
 * @date 2016年4月19日 下午3:38:29
 *
 */
public enum PayStatus {
	// 0 失败1 成功2 处理中 3未知错误

	FAIL(0, "失败"), SUCCESS(1, "成功"), PROCESSING(2, "处理中"), ERROR(3, "未知错误");

	private Integer value;
	private String name;

	private PayStatus(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return this.getValue() + "-" + this.getName();
	}

	public PayStatus getNextStatus() {
		return null;
	}

	public static void main(String[] args) {
		for (PayStatus status : PayStatus.values()) {
			System.out.println(status);
		}
	}
}
