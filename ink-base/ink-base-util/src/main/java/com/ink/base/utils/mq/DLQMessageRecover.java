package com.ink.base.utils.mq;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.retry.MessageRecoverer;

/**
 * 用于MQ消息处理失败时进入死信队列
 * @author zongtt
 * 2016年7月25日13:48:56
 */
public class DLQMessageRecover implements MessageRecoverer {

	@Override
	public void recover(Message message, Throwable cause) {
		throw new AmqpRejectAndDontRequeueException(cause);
	}
}
