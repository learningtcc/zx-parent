package com.ink.channel.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.BeanCopyConverter;
import com.ink.base.utils.IdCodeGenerator;
import com.ink.channel.api.enums.ChannelBizType;
import com.ink.channel.api.model.in.ValidCodeInput;
import com.ink.channel.api.model.out.ValidCodeOutput;
import com.ink.channel.api.service.ValidCodeService;
import com.ink.channel.core.enums.SystemCodeEnums;
import com.ink.channel.core.model.in.AsileValidCodeInput;
import com.ink.channel.core.model.out.AsileValidCodeOutput;
import com.ink.channel.core.service.AsileValidateCodeService;
/**
 * 获取短验
 * @author ZYC7-DZ-057
 *
 */
@Service
public class ValidCodeServiceImpl implements ValidCodeService {
	
	private static YinkerLogger logger = YinkerLogger.getLogger(ValidCodeServiceImpl.class);
	
    @Autowired
    private Map<String,AsileValidateCodeService> validCodeContainer;
    
    @Autowired
	private IdCodeGenerator channelRelUtil;
    
    
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
        	out.setResMsg("渠道号" +authority.getChannelId()+"不支持发送短信验证");
        	logger.info(out.getResMsg());
        	return out;
        }
        AsileValidCodeOutput asileValidCodeOut= channelName.getValidateCode(asileValidCodeInput);
         out = BeanCopyConverter.converterClass(asileValidCodeOut,ValidCodeOutput.class);
        return out;
   }
    
    private String getKeyByChannelId(String channelId){
    	return channelRelUtil.getIpMapsConfig().get((channelId+ChannelBizType.VALIDCODE.getCode()).trim());
	}

}
