package com.ink.channel.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.BeanCopyConverter;
import com.ink.base.utils.IdCodeGenerator;
import com.ink.channel.api.enums.ChannelBizType;
import com.ink.channel.api.model.in.AuthenBindCardInput;
import com.ink.channel.api.model.out.AuthenBindCardOutput;
import com.ink.channel.api.service.AuthenBindCardService;
import com.ink.channel.core.cnst.ChannelConstants;
import com.ink.channel.core.enums.SystemCodeEnums;
import com.ink.channel.core.model.in.AsileAuthenBindCardInput;
import com.ink.channel.core.service.AsileAuthenBindCardService;
@Service("authenBindCardService")
public class AuthenBindCardServiceImpl implements AuthenBindCardService{
	
	private static YinkerLogger logger = YinkerLogger.getLogger(AuthenBindCardServiceImpl.class);
	@Autowired
	private Map<String,AsileAuthenBindCardService> asileAuthenBindCardContainer;
	
	@Autowired
	private IdCodeGenerator channelRelUtil;
	
	@Override
	public AuthenBindCardOutput bindCard(AuthenBindCardInput input) {
		String key=getKey(input.getChannelId());
		AsileAuthenBindCardInput authority1= BeanCopyConverter.converterClass(input, AsileAuthenBindCardInput.class);
    	AsileAuthenBindCardService channelName = asileAuthenBindCardContainer.get(key);
    	AuthenBindCardOutput out=null; 
        if(channelName==null){
        	out=new AuthenBindCardOutput();
        	out.setResCode(SystemCodeEnums.FAILE_CODE.getCode());
        	out.setResMsg("渠道号" +input.getChannelId()+"不支持认证支付绑卡");
        	logger.error(ChannelConstants.LOGGER_MODULE_NAME, out.getResMsg());
        	return out;
        }
        out = BeanCopyConverter.converterClass(channelName.bindCard(authority1),AuthenBindCardOutput.class);
        return out;
    }
    
    public String getKey(String channelId){
    	String beanId = channelRelUtil.getIpMapsConfig().get(channelId+ChannelBizType.AUTHEN_NO_VALID_CODE_BIND_CARD.getCode().trim());
    	return beanId;
    	//return ChannelConfigRel.getBeanIdByChanAndBiz(channelId, ChannelBizType.AUTHEN_NO_VALID_CODE_BIND_CARD.getCode());
    }

}
