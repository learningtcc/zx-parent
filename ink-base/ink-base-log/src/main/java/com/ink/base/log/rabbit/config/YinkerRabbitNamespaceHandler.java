package com.ink.base.log.rabbit.config;

import org.springframework.amqp.rabbit.config.RabbitNamespaceHandler;

import com.ink.base.log.rabbit.config.parser.YinkerListenerContainerParser;
import com.ink.base.log.rabbit.config.parser.YinkerTemplateParser;

public class YinkerRabbitNamespaceHandler extends RabbitNamespaceHandler {

	@Override
	public void init() {
		super.init();
		registerBeanDefinitionParser("template", new YinkerTemplateParser());
		registerBeanDefinitionParser("listener-container", new YinkerListenerContainerParser());
	}
}