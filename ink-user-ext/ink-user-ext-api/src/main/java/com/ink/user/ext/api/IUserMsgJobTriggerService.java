package com.ink.user.ext.api;

import java.util.Date;

/**
 * 批量发送影响短信任务触发器
 * @author yangchen
 * @date 2016年6月22日 上午11:21:53
 */
public interface IUserMsgJobTriggerService 
{
	public void sendMessage(String filePath, Long fileId, Date sendTime, Integer sendType, String mchId);
}
