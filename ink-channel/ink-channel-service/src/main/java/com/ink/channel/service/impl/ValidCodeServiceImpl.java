package com.yinker.channel.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinker.base.utils.BeanCopyConverter;
import com.yinker.base.utils.logUtil.YinkerLoger;
import com.yinker.channel.api.enums.ChannelBizType;
import com.yinker.channel.api.enums.ChannelConfigRel;
import com.yinker.channel.api.model.in.ValidCodeInput;
import com.yinker.channel.api.model.out.ValidCodeOutput;
import com.yinker.channel.api.service.ValidCodeService;
import com.yinker.channel.core.enums.SystemCodeEnums;
import com.yinker.channel.core.model.in.AsileValidCodeInput;
import com.yinker.channel.core.model.out.AsileValidCodeOutput;
import com.yinker.channel.core.service.AsileValidateCodeService;
/**
 * 获取短验
 * @author ZYC7-DZ-057
 *
 */
@Service
public class ValidCodeServiceImpl implements ValidCodeService {
	
	private static YinkerLoger logger = YinkerLoger.getLogger(ValidCodeServiceImpl.class);
	
    @Autowired
    private Map<String,AsileValidateCodeService> validCodeContainer;
    
    @Override
    public ValidCodeOutput getValidCode(ValidCodeInput authority) {
    	//获取渠道对应service
    	String key=getKeyByChannelId(authority.getChannelId());
    	//转换参数
        AsileValidCodeInput asileValidCodeInput=  BeanCopyConverter.converterClass(authority, AsileValidCodeInput.class);
        //发送请求
        AsileValidateCodeService channelName = validCodeContainer.get(key);
        ValidCodeOutput out=null;
        if(channelName==null){
        	out=new ValidCodeOutput();
        	out.setResCode(SystemCodeEnums.FAILE_CODE.getCode());
        	out.setResMsg(authority.getChannelId()+"不支持发送短信验证");
        	logger.info(out.getResMsg());
        	return out;
        }
        AsileValidCodeOutput asileValidCodeOut= channelName.getValidateCode(asileValidCodeInput);
         out = BeanCopyConverter.converterClass(asileValidCodeOut,ValidCodeOutput.class);
        return out;
   }
    
    private String getKeyByChannelId(String channelId){
    	return ChannelConfigRel.getBeanIdByChanAndBiz(channelId, ChannelBizType.VALIDCODE.getCode());
	}

}
