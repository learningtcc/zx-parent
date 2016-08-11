package com.ink.channel.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.BeanCopyConverter;
import com.ink.base.utils.IdCodeGenerator;
import com.ink.channel.api.enums.ChannelBizType;
import com.ink.channel.api.model.in.QuickAgainPayInput;
import com.ink.channel.api.model.out.QuickPayOutput;
import com.ink.channel.api.service.QuickAgainPayService;
import com.ink.channel.core.cnst.ChannelConstants;
import com.ink.channel.core.enums.SystemCodeEnums;
import com.ink.channel.core.model.in.AsileQuickAgainPayInput;
import com.ink.channel.core.model.out.AsileQuickPayOutput;
import com.ink.channel.core.service.AsileQuickAgainPayService;
@Service("quickAgainPayServiceImpl")
public class QuickAgainPayServiceImpl implements QuickAgainPayService {
	
	private static YinkerLogger logger = YinkerLogger.getLogger(QuickPayServiceImpl.class);

	/*
	 * 当上层接口传来渠道号的时候，会根据映射规则获取对应的service实例key 从此container中获取到实例，调用此实例的快捷支付方法
	 */
	@Autowired
	private Map<String, AsileQuickAgainPayService> quickAgainPayContainer;
	
	@Autowired
	private IdCodeGenerator channelRelUtil;
	/**
	 * 快捷支付再次支付
	 */
	@Override
	public QuickPayOutput againPay(QuickAgainPayInput input) {
		QuickPayOutput out=null;
		String key = getKeyByChannelId(input.getChannelId());
		// 获取实例
		AsileQuickAgainPayService instance = quickAgainPayContainer.get(key);
		if (instance == null) {
			out=new QuickPayOutput();
			out.setResCode(SystemCodeEnums.FAILE_CODE.getCode());
        	out.setResMsg("渠道号" + input.getChannelId()+"不支持快捷支付再次支付");
        	logger.error(ChannelConstants.LOGGER_MODULE_NAME, out.getResMsg());
        	return out;
		}
		// 转换输入参数
		AsileQuickAgainPayInput asileInput = BeanCopyConverter.converterClass(input, AsileQuickAgainPayInput.class);
		AsileQuickPayOutput asileOutput = instance.againPay(asileInput);
		
		out = BeanCopyConverter.converterClass(asileOutput, QuickPayOutput.class);
        return out;
	}
	/**
	 * 根据渠道号获取对应实例key
	 * 
	 * @param channelId
	 * @return
	 */
	private String getKeyByChannelId(String channelId) {
		return channelRelUtil.getIpMapsConfig().get((channelId+ChannelBizType.QUICK_AGAIN_PAY.getCode()).trim());
	}

}
