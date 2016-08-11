/**
 * yinker.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月22日 上午10:36:56
 */
package com.ink.route.api.enums;

import java.util.*;

/**
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月22日 上午10:36:56
 */
public enum AsileBussinessStatusEnum {
	
	Sync(1,"同步"),Async(0,"异步");
	
	private Integer code;
	
	private String value;

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

	private AsileBussinessStatusEnum(Integer code, String value) {
		this.code = code;
		this.value = value;
	}
	
	public static List<Map<String,Object>> getAsileBussinessStatusEnum() {
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
    	for (AsileBussinessStatusEnum e:EnumSet.allOf(AsileBussinessStatusEnum.class)){
    		Map<String,Object> map=new HashMap<String,Object>() ;
    		map.put("code", e.getCode());
    		map.put("value", e.getValue());
    		list.add(map);
    	}
		return list;
	}
	
	

}
