package com.ink.base.log.rabbit.listener;

import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.impl.AMQConnection;
import com.rabbitmq.client.impl.FrameHandler;
import com.ink.base.log.rabbit.core.RabbitProperties;
import com.ink.base.log.util.MDCLog;
import com.ink.base.log.util.YinkerLogger;

public class YinkerSimpleMessageListeberContainer extends SimpleMessageListenerContainer {
	
	private YinkerLogger loger = YinkerLogger.getLogger(YinkerSimpleMessageListeberContainer.class);

	@Override
	protected void invokeListener(Channel channel, Message message) throws Exception {
		Map<String, Object> headers = message.getMessageProperties().getHeaders();
		
		AMQConnection connection = (AMQConnection)channel.getConnection();
		loger.info("*********"+JSON.toJSONString(connection));
		
		FrameHandler frameHandler = connection.getFrameHandler();
		headers.put(RabbitProperties.MQ_ADDRESS, frameHandler.getAddress().getHostAddress());
		headers.put(RabbitProperties.MQ_PORT, frameHandler.getPort());
		headers.put(RabbitProperties.MQ_LOCALADDRESS, frameHandler.getLocalAddress().getHostAddress());
		
		String log = (String)headers.get(RabbitProperties.MQ_LOG);
		
		String msgId = message.getMessageProperties().getMessageId();
		
		if(StringUtils.isBlank(msgId)){
			message.getMessageProperties().setMessageId(StringUtils.remove(UUID.randomUUID().toString(),"-"));
		}
		
		MDCLog.setMdcConfig(log, "rabbit");
		
		super.invokeListener(channel, message);
		
		MDCLog.clear();
	}
	
	public YinkerSimpleMessageListeberContainer() {
		super();
	}

	public YinkerSimpleMessageListeberContainer(ConnectionFactory connectionFactory) {
		super(connectionFactory);
	}
}