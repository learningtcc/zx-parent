package com.ink.trade.api.enums;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 签约订单状态枚举
 *<pre>
 *<b>类描述:</b>()
 *<b>作者:</b>zx
 *<b>创建日期:</b>2016年4月22日 下午2:33:26
 *</pre>
 */
public enum AuthOrderStatus {
   /** 0 失败 1 成功 2 预签约 3 处理中*/
    SUCCESS(1,"成功"),FAIL(0,"失败"),PRECONTRACT(2,"预签约"), PROCESSING(3,"处理中");
    private int value;
    private String name;
    private AuthOrderStatus(int value,String name){
        this.value=value;
        this.name=name;
    }
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public static List<Map<String,Object>> getAuthOrderEnum() {
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
    	for (AuthOrderStatus e:EnumSet.allOf(AuthOrderStatus.class)){
    		Map<String,Object> map=new HashMap<String,Object>() ;
    		map.put("value", e.getValue());
    		map.put("name", e.getName());
    		list.add(map);
    	}
		return list;
	}
    
}
