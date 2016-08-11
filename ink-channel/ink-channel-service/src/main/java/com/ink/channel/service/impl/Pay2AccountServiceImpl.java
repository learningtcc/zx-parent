package com.yinker.channel.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinker.base.utils.BeanCopyConverter;
import com.yinker.base.utils.logUtil.YinkerLoger;
import com.yinker.channel.api.enums.ChannelBizType;
import com.yinker.channel.api.enums.ChannelConfigRel;
import com.yinker.channel.api.model.in.PayAccountIn;
import com.yinker.channel.api.model.out.PayAccountOut;
import com.yinker.channel.api.service.Pay2AccountService;
import com.yinker.channel.core.enums.SystemCodeEnums;
import com.yinker.channel.core.model.in.AsilePayAccountIn;
import com.yinker.channel.core.service.AsilePay2AccountService;
/**
 * 代收
 * @author ZYC7-DZ-057
 *
 */
@Service
public class Pay2AccountServiceImpl implements Pay2AccountService {
	private static YinkerLoger logger = YinkerLoger.getLogger(Pay2AccountServiceImpl.class);

	@Autowired
    private Map<String,AsilePay2AccountService> pay2AccountContainer;
	
	@Override
	public PayAccountOut pay(PayAccountIn order) {
		//获取渠道对应service
		String key=getKey(order.getChannelId());
		//转换参数
		AsilePayAccountIn asilePayAccountIn= BeanCopyConverter.converterClass(order, AsilePayAccountIn.class);
		AsilePay2AccountService channelName = pay2AccountContainer.get(key);
		PayAccountOut out=null;
		if(channelName==null){
        	out=new PayAccountOut();
        	out.setResCode(SystemCodeEnums.FAILE_CODE.getCode());
        	out.setResMsg(order.getChannelId()+"不支持代收");
        	logger.info(out.getResMsg());
        	return out;
        }
		 out = BeanCopyConverter.converterClass(channelName.payAccount(asilePayAccountIn),PayAccountOut.class);
        return out;
	}
	public String getKey(String channelId){
    	return ChannelConfigRel.getBeanIdByChanAndBiz(channelId, ChannelBizType.PAY.getCode());
    }
}
