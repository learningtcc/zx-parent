package com.ink.channel.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.BeanCopyConverter;
import com.ink.base.utils.IdCodeGenerator;
import com.ink.channel.api.enums.ChannelBizType;
import com.ink.channel.api.model.in.PayAccountIn;
import com.ink.channel.api.model.out.PayAccountOut;
import com.ink.channel.api.service.Pay2AccountService;
import com.ink.channel.core.cnst.ChannelConstants;
import com.ink.channel.core.enums.SystemCodeEnums;
import com.ink.channel.core.model.in.AsilePayAccountIn;
import com.ink.channel.core.service.AsilePay2AccountService;
/**
 * 代收
 * @author ZYC7-DZ-057
 *
 */
@Service
public class Pay2AccountServiceImpl implements Pay2AccountService {
	private static YinkerLogger logger = YinkerLogger.getLogger(Pay2AccountServiceImpl.class);

	@Autowired
    private Map<String,AsilePay2AccountService> pay2AccountContainer;
	
	@Autowired
	private IdCodeGenerator channelRelUtil;
	
	@Override
	public PayAccountOut pay(PayAccountIn order) {
		long startMillis = System.currentTimeMillis();
		logger.info(ChannelConstants.LOGGER_MODULE_NAME,"开始调用代收系统时间:"+startMillis);
		//获取渠道对应service
		String key=getKey(order.getChannelId());
		//转换参数
		AsilePayAccountIn asilePayAccountIn= BeanCopyConverter.converterClass(order, AsilePayAccountIn.class);
		AsilePay2AccountService channelName = pay2AccountContainer.get(key);
		PayAccountOut out=null;
		if(channelName==null){
        	out=new PayAccountOut();
        	out.setResCode(SystemCodeEnums.FAILE_CODE.getCode());
        	out.setResMsg("渠道号" +order.getChannelId()+"不支持代收");
        	logger.error(ChannelConstants.LOGGER_MODULE_NAME, out.getResMsg());
        	return out;
        }
		 out = BeanCopyConverter.converterClass(channelName.payAccount(asilePayAccountIn),PayAccountOut.class);
		 long endMillis = System.currentTimeMillis();
		 logger.info(ChannelConstants.LOGGER_MODULE_NAME,"结束调用代收系统时间:"+endMillis);
		 long diffMillis = endMillis-startMillis;
		 logger.info(ChannelConstants.LOGGER_MODULE_NAME,"调用代收时间差:"+diffMillis);
        return out;
	}
	public String getKey(String channelId){
    	return channelRelUtil.getIpMapsConfig().get((channelId+ChannelBizType.PAY.getCode()).trim());
    }
}
