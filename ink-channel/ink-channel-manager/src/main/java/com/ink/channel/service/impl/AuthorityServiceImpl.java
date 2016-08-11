package com.ink.channel.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.BeanCopyConverter;
import com.ink.base.utils.IdCodeGenerator;
import com.ink.channel.api.enums.ChannelBizType;
import com.ink.channel.api.model.in.AuthorityInput;
import com.ink.channel.api.model.out.AuthorityOutput;
import com.ink.channel.api.service.AuthorityService;
import com.ink.channel.core.cnst.ChannelConstants;
import com.ink.channel.core.enums.SystemCodeEnums;
import com.ink.channel.core.model.in.AsileAuthorityInput;
import com.ink.channel.core.service.AsileAuthorityService;
/**
 * 鉴权
 * @author ZYC7-DZ-057
 *
 */
@Service
public class AuthorityServiceImpl implements AuthorityService {
	private static YinkerLogger logger = YinkerLogger.getLogger(AuthorityServiceImpl.class);
	
    @Autowired
    private Map<String,AsileAuthorityService> authorityContainer;
    
    @Autowired
	private IdCodeGenerator channelRelUtil;
    
	
    @Override
    public AuthorityOutput authorize(AuthorityInput authority) {
        //鉴权路由
        //鉴权
        //记录鉴权流水日志
        //返回鉴权结果
    	String key=getKey(authority.getChannelId());
        AsileAuthorityInput authority1= BeanCopyConverter.converterClass(authority, AsileAuthorityInput.class);
        AsileAuthorityService channelName = authorityContainer.get(key);
        AuthorityOutput out=null; 
        if(channelName==null){
        	out=new AuthorityOutput();
        	out.setResCode(SystemCodeEnums.FAILE_CODE.getCode());
        	out.setResMsg("渠道号" +authority.getChannelId()+"不支持鉴权");
        	logger.error(ChannelConstants.LOGGER_MODULE_NAME, out.getResMsg());
        	return out;
        }
        out = BeanCopyConverter.converterClass(channelName.authorize(authority1),AuthorityOutput.class);
        return out;
    }
    
    public String getKey(String channelId){
    	return channelRelUtil.getIpMapsConfig().get(channelId+ChannelBizType.SIGN.getCode().trim());
    }

}
