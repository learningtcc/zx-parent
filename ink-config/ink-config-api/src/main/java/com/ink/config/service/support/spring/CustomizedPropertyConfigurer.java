package com.ink.config.service.support.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.ConfigurablePropertyResolver;

/**
 * 配置中心配置文件工具类
 * haoyunfeng 2016.6.15
 */
public class CustomizedPropertyConfigurer extends
        PropertySourcesPlaceholderConfigurer {

    private ConfigurablePropertyResolver propertyResolver;

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess,
                                     final ConfigurablePropertyResolver propertyResolver) throws BeansException{
        super.processProperties(beanFactoryToProcess,propertyResolver);
        setPropertyResolver(propertyResolver);
    }

    private ConfigurablePropertyResolver getPropertyResolver() {
        return propertyResolver;
    }

    private void setPropertyResolver(ConfigurablePropertyResolver propertyResolver) {
        this.propertyResolver = propertyResolver;
    }

    /**
     * 根据属性id取属性值
     * @param key
     * @return
     */
    public  String getProperty(String key){
        return getPropertyResolver().getProperty(key);
    }
}