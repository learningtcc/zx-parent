package com.yinker.channel.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinker.base.utils.BeanCopyConverter;
import com.yinker.base.utils.logUtil.YinkerLoger;
import com.yinker.channel.api.enums.ChannelBizType;
import com.yinker.channel.api.enums.ChannelConfigRel;
import com.yinker.channel.api.model.in.QueryPayAccountIn;
import com.yinker.channel.api.model.out.QueryPayAccountOut;
import com.yinker.channel.api.service.Pay2AccountQueryService;
import com.yinker.channel.core.enums.SystemCodeEnums;
import com.yinker.channel.core.model.in.AsileQueryPayAccountIn;
import com.yinker.channel.core.model.out.AsileQueryPayAccountOut;
import com.yinker.channel.core.service.AsilePay2AccountQueryService;
/**
 * 代收查询
 * @author ZYC7-DZ-057
 *
 */
@Service
public class Pay2AccountQueryServiceImpl implements Pay2AccountQueryService{
	
	private static YinkerLoger logger = YinkerLoger.getLogger(Pay2AccountQueryServiceImpl.class);
	
	@Autowired
    private Map<String,AsilePay2AccountQueryService> asilePay2AccountQueryContainer;
	
	@Override
	public QueryPayAccountOut accountQuery(QueryPayAccountIn queryPay) {
		//获取渠道对应service
    	String key=getKey(queryPay.getChannelId());
    	AsileQueryPayAccountIn queryPay1= BeanCopyConverter.converterClass(queryPay, AsileQueryPayAccountIn.class);
    	//发送请求
    	AsilePay2AccountQueryService channelName = asilePay2AccountQueryContainer.get(key);
    	QueryPayAccountOut out=null;
    	if(channelName==null){
        	out=new QueryPayAccountOut();
        	out.setResCode(SystemCodeEnums.FAILE_CODE.getCode());
        	out.setResMsg(queryPay.getChannelId()+"不支持代收查询");
        	logger.info(out.getResMsg());
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
	public String getKey(String channelId){
    	return ChannelConfigRel.getBeanIdByChanAndBiz(channelId, ChannelBizType.PAY_QUERY.getCode());
    }

}
