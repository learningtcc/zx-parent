package com.ink.trade.util;

import java.util.Map;

public class CustomizedPropertyPlaceholderConfigurer {

    private static Map<String, Object> ctxPropertiesMap;

    public static Object getContextProperty(final String name) {
    	synchronized (CustomizedPropertyPlaceholderConfigurer.class) {
    		if(null==ctxPropertiesMap||ctxPropertiesMap.isEmpty()){
        		return null;
        	}
		}
        return ctxPropertiesMap.get(name);
    }

    public static Map<String, Object> getCtxPropertiesMap() {
        return ctxPropertiesMap;
    }

    public static void setCtxPropertiesMap(Map<String, Object> ctxPropertiesMap) {
        CustomizedPropertyPlaceholderConfigurer.ctxPropertiesMap = ctxPropertiesMap;
    }

   
}