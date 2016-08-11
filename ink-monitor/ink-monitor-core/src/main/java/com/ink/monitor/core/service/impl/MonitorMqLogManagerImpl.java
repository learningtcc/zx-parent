/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.monitor.core.service.impl;

import com.ink.monitor.core.dao.IMonitorMqLogDao;
import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.monitor.core.po.MonitorMqLog;
import com.ink.monitor.core.service.IMonitorMqLogManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Service("monitorMqLogManager")
@Transactional
public class MonitorMqLogManagerImpl extends BaseManager<MonitorMqLog,Long> implements IMonitorMqLogManager{

	@Autowired
	private IMonitorMqLogDao monitorMqLogDao;
	@Autowired
	private AmqpTemplate amqpTemplate;

	public EntityDao<MonitorMqLog, Long> getEntityDao() {
		return this.monitorMqLogDao;
	}
	

	public int updateNotNull(MonitorMqLog monitorMqLog){
		return monitorMqLogDao.updateNotNull(monitorMqLog);
	}

	/**
	 * 再次发送MQ
	 *
	 * @param monitorMqLog
	 */
	@Override
	public String sendMq(MonitorMqLog monitorMqLog) {

		String result = "1";

		byte[] msgBody = null;

		if (StringUtils.isNotBlank(monitorMqLog.getClassName()) &&
				(monitorMqLog.getMsgObject() != null || monitorMqLog.getMsgObject().length > 0)){//序列化发送
			msgBody = monitorMqLog.getMsgObject();
		}else if (StringUtils.isBlank(monitorMqLog.getClassName()) && StringUtils.isNotBlank(monitorMqLog.getMsgText())){//发送
			msgBody = monitorMqLog.getMsgText().getBytes();
		}else{
			return "0";
		}

		MessageProperties messageProperties = new MessageProperties();
		messageProperties.setMessageId(monitorMqLog.getMsgId());
		messageProperties.setContentType(monitorMqLog.getMsgType());
		Message message = new Message(msgBody,messageProperties);
		amqpTemplate.send(monitorMqLog.getExchange(),monitorMqLog.getRoutingKey(),message);
		return result;
	}
}
