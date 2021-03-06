package com.ink.base.log.rabbit.config.parser;

import java.util.List;

import org.springframework.amqp.rabbit.config.NamespaceUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.springframework.util.xml.DomUtils;
import org.w3c.dom.Element;

import com.ink.base.log.rabbit.config.YinkerRabbitNamespaceUtils;
import com.ink.base.log.rabbit.core.YinkerRabbitTemplate;

public class YinkerTemplateParser extends AbstractSingleBeanDefinitionParser {
	
	private static final String CONNECTION_FACTORY_ATTRIBUTE = "connection-factory";

	private static final String EXCHANGE_ATTRIBUTE = "exchange";

	private static final String QUEUE_ATTRIBUTE = "queue";

	private static final String ROUTING_KEY_ATTRIBUTE = "routing-key";

	private static final String REPLY_TIMEOUT_ATTRIBUTE = "reply-timeout";

	private static final String MESSAGE_CONVERTER_ATTRIBUTE = "message-converter";

	private static final String ENCODING_ATTRIBUTE = "encoding";

	private static final String CHANNEL_TRANSACTED_ATTRIBUTE = "channel-transacted";

	private static final String REPLY_QUEUE_ATTRIBUTE = "reply-queue";

	private static final String LISTENER_ELEMENT = "reply-listener";

	private static final String MANDATORY_ATTRIBUTE = "mandatory";

	private static final String IMMEDIATE_ATTRIBUTE = "immediate";

	private static final String RETURN_CALLBACK_ATTRIBUTE = "return-callback";

	private static final String CONFIRM_CALLBACK_ATTRIBUTE = "confirm-callback";

	private static final String CORRELATION_KEY = "correlation-key";

	private static final String RETRY_TEMPLATE = "retry-template";

	@Override
	protected Class<?> getBeanClass(Element element) {
		return YinkerRabbitTemplate.class;
	}

	@Override
	protected boolean shouldGenerateId() {
		return false;
	}

	@Override
	protected boolean shouldGenerateIdAsFallback() {
		return false;
	}

	@Override
	protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
		String connectionFactoryRef = element.getAttribute(CONNECTION_FACTORY_ATTRIBUTE);

		if (!StringUtils.hasText(connectionFactoryRef)) {
			parserContext.getReaderContext().error("A '" + CONNECTION_FACTORY_ATTRIBUTE + "' attribute must be set.",
					element);
		}

		if (StringUtils.hasText(connectionFactoryRef)) {
			// Use constructor with connectionFactory parameter
			builder.addConstructorArgReference(connectionFactoryRef);
		}

		NamespaceUtils.setValueIfAttributeDefined(builder, element, CHANNEL_TRANSACTED_ATTRIBUTE);
		NamespaceUtils.setValueIfAttributeDefined(builder, element, QUEUE_ATTRIBUTE);
		NamespaceUtils.setValueIfAttributeDefined(builder, element, EXCHANGE_ATTRIBUTE);
		NamespaceUtils.setValueIfAttributeDefined(builder, element, ROUTING_KEY_ATTRIBUTE);
		NamespaceUtils.setValueIfAttributeDefined(builder, element, REPLY_TIMEOUT_ATTRIBUTE);
		NamespaceUtils.setValueIfAttributeDefined(builder, element, ENCODING_ATTRIBUTE);
		NamespaceUtils.setReferenceIfAttributeDefined(builder, element, MESSAGE_CONVERTER_ATTRIBUTE);
		NamespaceUtils.setReferenceIfAttributeDefined(builder, element, REPLY_QUEUE_ATTRIBUTE);
		NamespaceUtils.setValueIfAttributeDefined(builder, element, MANDATORY_ATTRIBUTE);
		NamespaceUtils.setValueIfAttributeDefined(builder, element, IMMEDIATE_ATTRIBUTE);
		NamespaceUtils.setReferenceIfAttributeDefined(builder, element, RETURN_CALLBACK_ATTRIBUTE);
		NamespaceUtils.setReferenceIfAttributeDefined(builder, element, CONFIRM_CALLBACK_ATTRIBUTE);
		NamespaceUtils.setValueIfAttributeDefined(builder, element, CORRELATION_KEY);
		NamespaceUtils.setReferenceIfAttributeDefined(builder, element, RETRY_TEMPLATE);

		BeanDefinition replyContainer = null;
		Element childElement = null;
		List<Element> childElements = DomUtils.getChildElementsByTagName(element, LISTENER_ELEMENT);
		if (childElements.size() > 0) {
			childElement = childElements.get(0);
		}
		if (childElement != null) {
			replyContainer = parseListener(childElement, element,
					parserContext);
			if (replyContainer != null) {
				replyContainer.getPropertyValues().add("messageListener",
						new RuntimeBeanReference(element.getAttribute(ID_ATTRIBUTE)));
				String replyContainerName = element.getAttribute(ID_ATTRIBUTE) + ".replyListener";
				parserContext.getRegistry().registerBeanDefinition(replyContainerName, replyContainer);
			}
		}
		if (replyContainer == null && element.hasAttribute(REPLY_QUEUE_ATTRIBUTE)) {
			parserContext.getReaderContext().error(
					"For template '" + element.getAttribute(ID_ATTRIBUTE)
							+ "', when specifying a reply-queue, "
							+ "a <reply-listener/> element is required",
					element);
		}
		else if (replyContainer != null && !element.hasAttribute(REPLY_QUEUE_ATTRIBUTE)) {
			parserContext.getReaderContext().error(
					"For template '" + element.getAttribute(ID_ATTRIBUTE)
							+ "', a <reply-listener/> element is not allowed if no " +
							"'reply-queue' is supplied",
					element);
		}
	}

	private BeanDefinition parseListener(Element childElement, Element element,
			ParserContext parserContext) {
		BeanDefinition replyContainer = YinkerRabbitNamespaceUtils.parseContainer(childElement, parserContext);
		if (replyContainer != null) {
			replyContainer.getPropertyValues().add(
					"connectionFactory",
					new RuntimeBeanReference(element.getAttribute(CONNECTION_FACTORY_ATTRIBUTE)));
		}
		replyContainer.getPropertyValues().add("queues", new RuntimeBeanReference(element.getAttribute(REPLY_QUEUE_ATTRIBUTE)));
		return replyContainer;
	}


}