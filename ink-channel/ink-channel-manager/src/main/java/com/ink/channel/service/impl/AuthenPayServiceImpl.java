package com.ink.channel.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.BeanCopyConverter;
import com.ink.base.utils.IdCodeGenerator;
import com.ink.channel.api.enums.ChannelBizType;
import com.ink.channel.api.model.in.AuthenPayInput;
import com.ink.channel.api.model.out.AuthenPayOutput;
import com.ink.channel.api.service.AuthenPayService;
import com.ink.channel.core.cnst.ChannelConstants;
import com.ink.channel.core.enums.SystemCodeEnums;
import com.ink.channel.core.model.in.AsileAuthenPayInput;
import com.ink.channel.core.service.AsileAuthenPayService;
@Service("authenPayService")
public class AuthenPayServiceImpl implements AuthenPayService{
	private static YinkerLogger logger = YinkerLogger.getLogger(AuthenPayServiceImpl.class);
	
	@Autowired
	private Map<String,AsileAuthenPayService> authenPayContainer;
	
	@Autowired
	private IdCodeGenerator channelRelUtil;
	
	@Override
	public AuthenPayOutput authenPay(AuthenPayInput input) {
    	String key=getKey(input.getChannelId());
    	AsileAuthenPayInput authority1= BeanCopyConverter.converterClass(input, AsileAuthenPayInput.class);
        AsileAuthenPayService channelName = authenPayContainer.get(key);
        AuthenPayOutput out=null; 
        if(channelName==null){
        	out=new AuthenPayOutput();
        	out.setResCode(SystemCodeEnums.FAILE_CODE.getCode());
        	out.setResMsg("渠道号" +input.getChannelId()+"不支持认证支付充值");
        	logger.error(ChannelConstants.LOGGER_MODULE_NAME, out.getResMsg());
        	return out;
        }
        out = BeanCopyConverter.converterClass(channelName.authenPay(authority1),AuthenPayOutput.class);
        return out;
    }
    
    public String getKey(String channelId){
    	return channelRelUtil.getIpMapsConfig().get(channelId+ChannelBizType.AUTHEN_NO_VALID_CODE_PAY.getCode().trim());
    }

}
