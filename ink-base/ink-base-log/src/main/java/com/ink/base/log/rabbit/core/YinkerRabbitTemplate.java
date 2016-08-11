package com.ink.base.log.rabbit.core;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelCallback;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;

import com.rabbitmq.client.Channel;
import com.ink.base.log.util.RequestLog;

public class YinkerRabbitTemplate extends RabbitTemplate {
	
	/**
	 * Convenient constructor for use with setter injection. Don't forget to set the connection factory.
	 */
	public YinkerRabbitTemplate() {
		super();
	}

	/**
	 * Create a rabbit template with default strategies and settings.
	 *
	 * @param connectionFactory the connection factory to use
	 */
	public YinkerRabbitTemplate(ConnectionFactory connectionFactory) {
		super(connectionFactory);
	}
	
	@Override
	public void send(final String exchange, final String routingKey,
			final Message message, final CorrelationData correlationData)
			throws AmqpException {
		
		execute(new ChannelCallback<Object>() {
			@Override
			public Object doInRabbit(Channel channel) throws Exception {
				RequestLog log = RequestLog.getInstance();
				message.getMessageProperties().setHeader(RabbitProperties.MQ_LOG, log.encodeBase64());
				
				String msgId = message.getMessageProperties().getMessageId();
				if(StringUtils.isBlank(msgId)){
					message.getMessageProperties().setMessageId(StringUtils.remove(UUID.randomUUID().toString(),"-"));
				}
				
				doSend(channel, exchange, routingKey, message, correlationData);
				return null;
			}
		});
	}
}