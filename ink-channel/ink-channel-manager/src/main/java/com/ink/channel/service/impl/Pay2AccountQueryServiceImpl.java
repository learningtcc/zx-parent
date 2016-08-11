package com.ink.channel.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.BeanCopyConverter;
import com.ink.base.utils.IdCodeGenerator;
import com.ink.channel.api.model.in.QueryPayAccountIn;
import com.ink.channel.api.model.out.QueryPayAccountOut;
import com.ink.channel.api.service.Pay2AccountQueryService;
import com.ink.channel.core.cnst.ChannelConstants;
import com.ink.channel.core.enums.SystemCodeEnums;
import com.ink.channel.core.model.in.AsileQueryPayAccountIn;
import com.ink.channel.core.model.out.AsileQueryPayAccountOut;
import com.ink.channel.core.service.AsilePay2AccountQueryService;
/**
 * 代收查询
 * @author ZYC7-DZ-057
 *
 */
@Service
public class Pay2AccountQueryServiceImpl implements Pay2AccountQueryService{
	
	private static YinkerLogger logger = YinkerLogger.getLogger(Pay2AccountQueryServiceImpl.class);
	
	@Autowired
    private Map<String,AsilePay2AccountQueryService> asilePay2AccountQueryContainer;
	
	@Autowired
	private IdCodeGenerator channelRelUtil;
	
	@Override
	public QueryPayAccountOut accountQuery(QueryPayAccountIn queryPay) {
		//获取渠道对应service
		logger.debug("channelId："+queryPay.getChannelId()+"QueryType："+queryPay.getQueryType());
    	String key=getKey(queryPay.getChannelId(),queryPay.getQueryType());
    	logger.debug("key:"+key);
    	AsileQueryPayAccountIn queryPay1= BeanCopyConverter.converterClass(queryPay, AsileQueryPayAccountIn.class);
    	//发送请求
    	AsilePay2AccountQueryService channelName = asilePay2AccountQueryContainer.get(key);
    	QueryPayAccountOut out=null;
    	if(channelName==null){
        	out=new QueryPayAccountOut();
        	out.setResCode(SystemCodeEnums.FAILE_CODE.getCode());
        	out.setResMsg("渠道号" +queryPay.getChannelId()+"不支持交易查询");
        	logger.error(ChannelConstants.LOGGER_MODULE_NAME, out.getResMsg());
        	return out;
        }
    	 AsileQueryPayAccountOut asilePay2AccountOut = channelName.queryPayOrder(queryPay1);
    	
         out = BeanCopyConverter.converterClass(asilePay2AccountOut,QueryPayAccountOut.class);
        return out;
	}
	/**
	 * 获取渠道对应service
	 * @param channelId
	 * @return
	 */
	public String getKey(String channelId,String queryType){
		
    	return channelRelUtil.getIpMapsConfig().get(channelId+queryType.trim());
    }

}
