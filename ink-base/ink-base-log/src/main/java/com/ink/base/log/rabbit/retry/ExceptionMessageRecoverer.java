package com.ink.base.log.rabbit.retry;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.retry.MessageRecoverer;
import org.springframework.amqp.utils.SerializationUtils;

import com.alibaba.fastjson.JSON;
import com.ink.base.log.rabbit.core.MessageLog;
import com.ink.base.log.rabbit.core.RabbitProperties;
import com.ink.base.log.util.LogConst;
import com.ink.config.service.support.spring.CustomizedPropertyConfigurer;

import ch.qos.logback.classic.LoggerContext;

public class ExceptionMessageRecoverer implements MessageRecoverer {
	
	private AmqpTemplate amqpTemplate;
	
	private String exchange = "monitor.mq.exchange";
	
	private String routingKey = "monitor.mq.exception.key";
	
	private CustomizedPropertyConfigurer customizedPropertyConfigurer;
	
	private LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
	/**
	 * 异常日志报警
	 */
	private boolean alarm = true;

	@Override
	public void recover(Message message, Throwable cause) {
		if(alarm){
			if(amqpTemplate == null){
				throw new RuntimeException("amqpTemplate is null");
			}else{
				MessageLog log = new MessageLog();
				MessageProperties mp = message.getMessageProperties();
				Map<String, Object> headers = mp.getHeaders();
				
				log.setExchange(mp.getReceivedExchange());
				log.setLocalAddress((String)headers.get(RabbitProperties.MQ_LOCALADDRESS));
				log.setRabbitAddress((String)headers.get(RabbitProperties.MQ_ADDRESS));
				log.setRabbitPort((Integer)headers.get(RabbitProperties.MQ_PORT));
				String msgException = cause.getMessage();
				if(StringUtils.isNoneBlank(msgException)){
					log.setMsgException(getExceptionMessage(cause));
				}
				
				log.setMsgId(mp.getMessageId());
				
				convertBodyToLog(message, log);
				
				log.setRequestId(MDC.get(LogConst.REQ_ID));
				log.setRoutingKey(mp.getReceivedRoutingKey());
				log.setSysCode(customizedPropertyConfigurer.getProperty("logback.mongo.source"));
				log.setAppName(loggerContext.getName());
				
				amqpTemplate.convertAndSend(exchange, routingKey, log);
			}
		}
	}
	
	private void convertBodyToLog(Message message, MessageLog log) {
		if (message.getBody() == null) {
			return;
		}
		
		try {
			
			MessageProperties messageProperties = message.getMessageProperties();
			
			String contentType = (messageProperties != null) ? messageProperties.getContentType() : null;
			
			log.setMsgType(contentType);
			
			if (MessageProperties.CONTENT_TYPE_SERIALIZED_OBJECT.equals(contentType)) {
				Object obj = SerializationUtils.deserialize(message.getBody());
				log.setMsgText(JSON.toJSONString(SerializationUtils.deserialize(message.getBody())));
				log.setMsgObject(message.getBody());
				log.setClassName(obj.getClass().getName());
			}else if (MessageProperties.CONTENT_TYPE_TEXT_PLAIN.equals(contentType)
					|| MessageProperties.CONTENT_TYPE_JSON.equals(contentType)
					|| MessageProperties.CONTENT_TYPE_JSON_ALT.equals(contentType)
					|| MessageProperties.CONTENT_TYPE_XML.equals(contentType)) {
				log.setClassName("java.lang.String");
				log.setMsgText(new String(message.getBody(), messageProperties.getContentEncoding()));
			}
		}
		catch (Exception e) {
			// ignore
		}
	}
	
	private String getExceptionMessage(Throwable t) {
        StringBuffer remark=new StringBuffer("异常类型:"+t.getClass().getName()+";异常信息:"+t.getMessage()+"  ");
        StackTraceElement[] trace=t.getStackTrace();
        for (int i=0; i < trace.length; i++){
            remark.append("\r\n"+trace[i]+";");
        }

        return remark.toString();
    }
	
	public void setCustomizedPropertyConfigurer(CustomizedPropertyConfigurer customizedPropertyConfigurer) {
		this.customizedPropertyConfigurer = customizedPropertyConfigurer;
	}

	public void setAlarm(boolean alarm) {
		this.alarm = alarm;
	}

	public void setAmqpTemplate(AmqpTemplate amqpTemplate) {
		this.amqpTemplate = amqpTemplate;
	}
}
