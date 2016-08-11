package com.ink.trade.api.enums;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * <b>类描述:</b>(支付类型枚举)
 * <b>作者:</b>zx
 * <b>创建日期:</b>2016年5月31日 下午1:49:16
 * </pre>
 */
public enum PayType {
    COLLECT("代收", "DS"),PAY("代付","DF"), QUICKPAY("快捷", "KJ"), GATEWAY("网关", "WG"),AUTHPAY("认证支付","RZ"),ALL("全部","QB");
    private String name;
    private String value;

    private PayType(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    public static List<Map<String,Object>> getPayType() {
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
    	for (PayType e:EnumSet.allOf(PayType.class)){
    		Map<String,Object> map=new HashMap<String,Object>() ;
    		map.put("name", e.getName());
    		map.put("value", e.getValue());
    		list.add(map);
    	}
		return list;
	}

}
