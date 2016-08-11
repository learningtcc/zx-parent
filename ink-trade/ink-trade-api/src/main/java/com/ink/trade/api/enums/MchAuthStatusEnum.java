/**
 * yinker.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月21日 下午5:54:17
 */
package com.ink.trade.api.enums;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月21日 下午5:54:17
 */
public enum MchAuthStatusEnum {
	
	Open(1,"开启"),
	Stop(2,"停用");
	
	private Integer code;
	
	private String value;

	private MchAuthStatusEnum(Integer code, String value) {
		this.code = code;
		this.value = value;
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
	
	public static List<Map<String,Object>> getMchAuthStatusEnum() {
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
    	for (MchAuthStatusEnum e:EnumSet.allOf(MchAuthStatusEnum.class)){
    		Map<String,Object> map=new HashMap<String,Object>() ;
    		map.put("code", e.getCode());
    		map.put("value", e.getValue());
    		list.add(map);
    	}
		return list;
	}
	
	

}
