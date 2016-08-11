package com.ink.msgcenter.cache;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ink.base.redis.client.CacheObject;
import com.ink.base.redis.client.Reader;
import com.ink.base.redis.client.Yedis;
import com.ink.msgcenter.api.constants.CacheConstant;
import com.ink.msgcenter.cache.object.EmailChnCache;
import com.ink.msgcenter.cache.object.SmsChnCache;
import com.ink.msgcenter.core.po.EmailChannel;
import com.ink.msgcenter.core.po.SmsChannel;
import com.ink.msgcenter.core.query.EmailChannelQuery;
import com.ink.msgcenter.core.query.SmsChannelQuery;
import com.ink.msgcenter.core.service.IEmailChannelManager;
import com.ink.msgcenter.core.service.ISmsChannelManager;

@Component("channelUtil")
public class ChannelUtil {
	@Autowired
	private Yedis yedis;
	@Autowired
	private IEmailChannelManager emailChannelManager;
	@Autowired
	private ISmsChannelManager smsChannelManager;
	

	public EmailChnCache getEmailChannel(final String chnCode){
		
		CacheObject co = yedis.getObject(CacheConstant.MSG_CHANNEL, chnCode, EmailChnCache.class, new Reader<Object>() {
			
			@Override
			public Object readerFromDatabase() {
				EmailChnCache emailChn = null;
				
				EmailChannelQuery query = new EmailChannelQuery();
				query.setStatus("0");
				query.setChnCode(chnCode);
				List<EmailChannel> chnList = emailChannelManager.find(query);
				
				if(chnList.size() > 0){
					emailChn = new EmailChnCache();
					emailChn.setChnCode(chnCode);
					EmailChannel ec = chnList.get(0);
					emailChn.setId(ec.getId());
					emailChn.setHost(ec.getSmtpHost());
					emailChn.setMail(ec.getMailAddr());
					emailChn.setPassword(ec.getMailPwd());
					emailChn.setUserName(ec.getMailAddr());
					emailChn.setPort(Integer.valueOf(ec.getSmtpPort()));
					emailChn.setPriority(ec.getPriorityLevel());
				}
				
				return emailChn;
			}
		});
		
		return (EmailChnCache)co.getValue();
	}

	/**
	 * 获取短信通道
	 * @param chnCode
	 * @return
	 */
	public SmsChnCache getSmsChannel(final String chnCode) {
		
		CacheObject co = yedis.getObject(CacheConstant.MSG_CHANNEL, chnCode, SmsChnCache.class, new Reader<Object>() {

			@Override
			public Object readerFromDatabase() {
				SmsChnCache smsChnCache = null;

				SmsChannelQuery query = new SmsChannelQuery();
				query.setStatus("0");
				query.setChnCode(chnCode);
				List<SmsChannel> chnList = smsChannelManager.find(query);

				if(chnList.size() > 0){
					smsChnCache = new SmsChnCache();
					
					SmsChannel smsChannel = chnList.get(0);
					smsChnCache.setId(smsChannel.getId());
					smsChnCache.setName(smsChannel.getName());
					smsChnCache.setChnCode(smsChannel.getChnCode());
					smsChnCache.setChnType(smsChannel.getChnType());
					smsChnCache.setChnParam(smsChannel.getChnParam());
					smsChnCache.setPriorityLevel(smsChannel.getPriorityLevel());
				}
				
				return smsChnCache;
			}
			
		});
		
		return (SmsChnCache) co.getValue();
	}
}
