//package com.yinker.user.util;
//
//import java.io.IOException;
//import java.util.Map;
//import java.util.Properties;
//
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
//import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
//import org.springframework.core.env.ConfigurablePropertyResolver;
//
///**
// * protertis重写,用来实现获取属性的方法
// * @author wanghao^_^
// * @date 2016年6月7日 下午2:42:31
// *
// */
//public class CustomizedPropertyPlaceholderConfigurer extends PropertySourcesPlaceholderConfigurer {
//
//	private ConfigurablePropertyResolver propertyResolver;
//	public Object getContextProperty(final String name) {
//		return propertyResolver.getProperty(name);
//	}
//
//	@Override
//	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess,
//			ConfigurablePropertyResolver propertyResolver) throws BeansException {
//		super.processProperties(beanFactoryToProcess, propertyResolver);
//		this.propertyResolver = propertyResolver;
//	}
//
//}