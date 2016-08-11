package com.ink.msgcenter.external.sms.zw.job;

import com.ink.base.log.job.JobLoggerable;
import com.ink.base.utils.context.SpringApplicationContext;
import com.ink.msgcenter.core.po.SmsChannel;
import com.ink.msgcenter.core.service.ISmsChannelManager;
import com.ink.msgcenter.external.sms.zw.job.service.SmsUpLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("smsUplink")
public class SmsUplinkJob  extends JobLoggerable {

	@Autowired
	private ISmsChannelManager smsChannelManager;

	/**
	 * 执行任务业务逻辑信息
	 */
	@Override
	protected void executeService() {
		SmsChannel record = new SmsChannel();
		record.setStatus("0");
		List<SmsChannel> smsChannels = smsChannelManager.findSmsChannels(record);
		for (SmsChannel smsChannel : smsChannels) {
			try{
				SmsUpLinkService smsUpLinkService = (SmsUpLinkService) SpringApplicationContext.getBean("smsUpLinklService" + smsChannel.getChnType());
				if (smsUpLinkService != null){
					smsUpLinkService.operateUpLink(smsChannel);
				}
			}catch (Exception e){
				e.printStackTrace();
			}

		}
	}



}
