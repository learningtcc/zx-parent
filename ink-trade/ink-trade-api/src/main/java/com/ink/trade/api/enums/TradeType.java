package com.ink.trade.api.enums;

/**
 * 
 * @ClassName: TradeType
 * @Description: 交易类型枚举类
 * @author wanghao
 * @date 2016年4月18日 下午7:28:10
 *
 */
public enum TradeType {

	// 签约A、充值I、提现O、查询Q、退款R、通知(未定)
	SIGN("A", "签约"),  RECHARGE("I", "充值"), WITHDRAW(
			"O", "提现"), QUERY("Q", "查询"), REFUND("R", "退款"), NOTICE("N", "通知");
	private String code;
	private String name;

	private TradeType(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return this.getCode() + "-" + this.getName();
	}

	public TradeType getNextStatus() {
		return null;
	}
	public static String getNameByCode(String code){
	    String tradeName="";
	    for (TradeType status : TradeType.values()) {
	        if(status.getCode().trim().equals(code)){
	            tradeName=status.getName();
	        }
        }
	    if(tradeName.trim().equals(""))
	    {
          throw new RuntimeException("ERROR TradeCode!");
	    }
	    return tradeName;
	}

	public static void main(String[] args) {
//		for (TradeType status : TradeType.values()) {
//			System.out.println(status);
//		}
	   System.out.println(TradeType.getNameByCode("B"));
	}

}
