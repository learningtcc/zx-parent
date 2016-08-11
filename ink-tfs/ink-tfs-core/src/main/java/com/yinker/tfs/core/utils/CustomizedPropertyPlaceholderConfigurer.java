package com.yinker.tfs.core.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class CustomizedPropertyPlaceholderConfigurer extends
        PropertyPlaceholderConfigurer {

    private static Map<String, Object> ctxPropertiesMap;

    public static Object getContextProperty(final String name) {
    	synchronized (CustomizedPropertyPlaceholderConfigurer.class) {
    		if(null==ctxPropertiesMap||ctxPropertiesMap.isEmpty()){
        		return null;
        	}
		}
        return ctxPropertiesMap.get(name);
    }

    @Override
    protected void processProperties(
            final ConfigurableListableBeanFactory beanFactoryToProcess,
            final Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        ctxPropertiesMap = new HashMap<String, Object>();
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            String value = props.getProperty(keyStr);
            ctxPropertiesMap.put(keyStr, value);
        }
    }
}