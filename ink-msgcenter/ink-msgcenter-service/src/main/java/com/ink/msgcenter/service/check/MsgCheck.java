package com.ink.msgcenter.service.check;

import org.apache.commons.lang.StringUtils;

import com.ink.base.utils.context.SpringApplicationContext;
import com.ink.msgcenter.api.model.input.MsgInput;
import com.ink.msgcenter.api.model.output.MsgOutput;
import com.ink.msgcenter.api.util.MsgCode;
import com.ink.msgcenter.cache.MerchantUtil;
import com.ink.msgcenter.cache.object.MerchantCache;

public class MsgCheck {
	
	private static MerchantUtil merchantUtil = (MerchantUtil)SpringApplicationContext.getBean("merchantUtil");
	
	
	/**
	 * 检查输入消息
	 * @param msgInput
	 * @return
	 */
	public  static MsgOutput checkMsgInput(MsgInput msgInput, String msgType){
		
		MsgOutput out = new MsgOutput(MsgCode.SUCCESS);
		
		if(StringUtils.isBlank(msgInput.getMerctCode())){
			out.setRetCode(MsgCode.MERCHANT_NOT_FOUND);
			out.setRetMsg("商户代码为空");
			
			return out;
		}
		
		MerchantCache mc = merchantUtil.getMerchantInfo(msgInput.getMerctCode());
		if(mc == null){
			out.setRetCode(MsgCode.MERCHANT_NOT_FOUND);
			out.setRetMsg("商户不存在");
		}else if(!mc.getChnType().contains(msgType) || ("1".equalsIgnoreCase(msgType) && StringUtils.isBlank(mc.getSmsChnCode())) || ("2".equalsIgnoreCase(msgType) && StringUtils.isBlank(mc.getMailChnCode()))){
			out.setRetCode(MsgCode.MERCHANT_CHN_NOT_EXISTS);
			out.setRetMsg("该商户通道未配置");
		}
		
		return out;
	}

}
