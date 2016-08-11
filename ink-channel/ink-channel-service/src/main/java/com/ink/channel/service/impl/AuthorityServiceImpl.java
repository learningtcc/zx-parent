package com.yinker.channel.service.impl;

import com.yinker.base.utils.BeanCopyConverter;
import com.yinker.base.utils.logUtil.YinkerLoger;
import com.yinker.channel.api.enums.ChannelBizType;
import com.yinker.channel.api.enums.ChannelConfigRel;
import com.yinker.channel.api.model.in.AuthorityInput;
import com.yinker.channel.api.model.out.AuthorityOutput;
import com.yinker.channel.api.service.AuthorityService;
import com.yinker.channel.core.enums.SystemCodeEnums;
import com.yinker.channel.core.model.in.AsileAuthorityInput;
import com.yinker.channel.core.service.AsileAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
/**
 * 鉴权
 * @author ZYC7-DZ-057
 *
 */
@Service
public class AuthorityServiceImpl implements AuthorityService {
	private static YinkerLoger logger = YinkerLoger.getLogger(AuthorityServiceImpl.class);
	
    @Autowired
    private Map<String,AsileAuthorityService> authorityContainer;
    
    
	
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
        	out.setResMsg(authority.getChannelId()+"不支持鉴权");
        	logger.info(out.getResMsg());
        	return out;
        }
        out = BeanCopyConverter.converterClass(channelName.authorize(authority1),AuthorityOutput.class);
        return out;
    }
    
    public String getKey(String channelId){
    	return ChannelConfigRel.getBeanIdByChanAndBiz(channelId, ChannelBizType.SIGN.getCode());
    }

}
