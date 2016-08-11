/**
 * ink.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月27日 下午5:29:51
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
 * @date 2016年7月27日 下午5:29:51
 */
public enum AuthEnum {

	Binding(0, "有效"), UnBinding(1, "解绑");

	private int code;
	private String value;

	private AuthEnum(int code, String value) {
		this.code = code;
		this.value = value;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public static List<Map<String, Object>> getAuthEnum() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (AuthEnum e : EnumSet.allOf(AuthEnum.class)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", e.getCode());
			map.put("value", e.getValue());
			list.add(map);
		}
		return list;
	}

}
