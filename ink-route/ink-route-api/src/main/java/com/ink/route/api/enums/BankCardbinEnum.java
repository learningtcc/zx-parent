/**
 * ink.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月21日 上午10:58:23
 */
package com.ink.route.api.enums;

import java.util.*;

/**
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月21日 上午10:58:23
 */
public enum BankCardbinEnum {

	DebitCard(1, "借记卡"),
	/** 借记卡 **/
	CreditCard(2, "贷记卡"),
	/** 贷记卡 **/
	PrepaidCard(3, "预付费卡"),
	/** 贷记卡 **/
	QuasiCreditCard(4, "准贷记卡");
	/** 贷记卡 **/
	private Integer code;
	private String value;

	private BankCardbinEnum(Integer code, String value) {
		this.value = value;
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public static List<Map<String,Object>> getBankCardbinEnum() {
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
    	for (BankCardbinEnum e:EnumSet.allOf(BankCardbinEnum.class)){
    		Map<String,Object> map=new HashMap<String,Object>() ;
    		map.put("code", e.getCode());
    		map.put("value", e.getValue());
    		list.add(map);
    	}
		return list;
	}

}
