package com.ink.msgcenter.cache;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.ink.base.redis.client.CacheObject;
import com.ink.base.redis.client.Reader;
import com.ink.base.redis.client.Yedis;
import com.ink.msgcenter.api.constants.CacheConstant;
import com.ink.msgcenter.cache.object.MerchantCache;
import com.ink.msgcenter.core.po.MerchantChn;
import com.ink.msgcenter.core.po.MerchantInfo;
import com.ink.msgcenter.core.query.MerchantChnQuery;
import com.ink.msgcenter.core.query.MerchantInfoQuery;
import com.ink.msgcenter.core.service.IMerchantChnManager;
import com.ink.msgcenter.core.service.IMerchantInfoManager;

@Component("merchantUtil")
public class MerchantUtil {
	@Autowired
	private Yedis yedis;
	@Autowired
	private IMerchantInfoManager merchantInfoManager;
	@Autowired
	private IMerchantChnManager merchantChnManager;
	
	
	public MerchantCache getMerchantInfo(final String merctCode){
		
		CacheObject co = yedis.getObject(CacheConstant.MSG_MERCT, merctCode, MerchantCache.class, new Reader<Object>() {
			@Override
			public Object readerFromDatabase() {
				
				MerchantCache merchant = null;
				
				MerchantInfoQuery query = new MerchantInfoQuery();
				query.setStatus("0");
				query.setSn(merctCode);
				List<MerchantInfo> merchantList = merchantInfoManager.find(query);
				
				if(merchantList.size() > 0){
					MerchantInfo mi = merchantList.get(0);

					merchant = new MerchantCache();
					merchant.setMerctSn(merctCode);
					merchant.setChnType(mi.getChannelType());
					merchant.setId(mi.getId());
					
					//查询商户通道
					MerchantChnQuery chnQuery = new MerchantChnQuery();
					chnQuery.setMerctCode(merctCode);
					chnQuery.setMerctId(mi.getId());
					chnQuery.setStatus("0");
					
					List<MerchantChn> chnList = merchantChnManager.find(chnQuery);
					
					StringBuffer smsBuffer = new StringBuffer();
					StringBuffer mailBuffer = new StringBuffer();
					for (MerchantChn merchantChn : chnList) {
						if("1".equalsIgnoreCase(merchantChn.getChnType())){//短信
							smsBuffer.append(merchantChn.getChnCode()).append(",");
						}else{//邮件
							mailBuffer.append(merchantChn.getChnCode()).append(",");
						}
					}
					
					//短信通道代码
					if(smsBuffer.length() > 1){
						merchant.setSmsChnCode(smsBuffer.substring(0, smsBuffer.length()-1));
					}
					
					//邮件通道代码
					if(mailBuffer.length() > 1){
						merchant.setMailChnCode(mailBuffer.substring(0, mailBuffer.length()-1));
					}
					
					String emailNotify = mi.getEmailNotify();
					String smsNotify = mi.getSmsNotify();
					
					if(StringUtils.isNotBlank(emailNotify)){
						JSONObject jb = JSONObject.parseObject(emailNotify).getJSONObject("httpInfo");
						merchant.setEmailNotifyUrl((String)jb.get("reportUrl"));
					}
					
					if(StringUtils.isNotBlank(smsNotify)){
						JSONObject jb = JSONObject.parseObject(smsNotify).getJSONObject("httpInfo");
						merchant.setSmsReportUrl((String)jb.get("reportUrl"));
						merchant.setSmsUpUrl((String)jb.get("upUrl"));
					}
				}
				
				return merchant;
			}
		});
		
		return (MerchantCache)co.getValue();
	}
}
