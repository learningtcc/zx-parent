package com.ink.trade.api.enums;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @ClassName: OrderStatus
 * @Description: 订单状态枚举类
 * @author wanghao
 * @date 2016年4月18日 下午7:27:55
 *
 */
public enum OrderStatus {
	// 0 创建订单1待支付2处理中3 成功4 失败5.已退款
	CREATEORDER(0, "创建订单"), PENDING(1, "待支付"), PROCESSING(2, "处理中"), SUCCESS(3,
			"成功"), FAIL(4, "失败"), TOBEREFUNDED(5, "待退款"), REFUNDING(6, "退款中"), REFUND_SUCCESS(
			7, "退款成功"), REFUND_FAIL(8, "退款失败");

	private Integer value;
	private String name;

	private OrderStatus(int value, String name) {
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

	public OrderStatus getNextStatus() {
		return null;
	}
	 public static List<Map<String,Object>> getStatus() {
			List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
	    	for (OrderStatus e:EnumSet.allOf(OrderStatus.class)){
	    		Map<String,Object> map=new HashMap<String,Object>() ;
	    		map.put("name", e.getName());
	    		map.put("value", e.getValue());
	    		list.add(map);
	    	}
			return list;
		}
	public static void main(String[] args) {
		for (OrderStatus status : OrderStatus.values()) {
			System.out.println(status);
		}
	}
}
