package com.yinker.channel.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinker.base.utils.BeanCopyConverter;
import com.yinker.base.utils.logUtil.YinkerLoger;
import com.yinker.channel.api.enums.ChannelBizType;
import com.yinker.channel.api.enums.ChannelConfigRel;
import com.yinker.channel.api.model.in.QuickAgainPayInput;
import com.yinker.channel.api.model.out.QuickPayOutput;
import com.yinker.channel.api.service.QuickAgainPayService;
import com.yinker.channel.core.model.in.AsileQuickAgainPayInput;
import com.yinker.channel.core.model.out.AsileQuickPayOutput;
import com.yinker.channel.core.service.AsileQuickAgainPayService;
@Service("quickAgainPayServiceImpl")
public class QuickAgainPayServiceImpl implements QuickAgainPayService {
	
	private static YinkerLoger logger = YinkerLoger.getLogger(QuickPayServiceImpl.class);

	/*
	 * 此容器会存放所有实现AsileQuickAuthService接口的服务实现类实例
	 * 当上层接口传来渠道号的时候，会根据映射规则获取对应的service实例key 从此container中获取到实例，调用此实例的快捷支付方法
	 */
	@Autowired
	private Map<String, AsileQuickAgainPayService> quickAgainPayContainer;
	/**
	 * 快捷支付再次支付
	 */
	@Override
	public QuickPayOutput againPay(QuickAgainPayInput input) {
		String key = getKeyByChannelId(input.getChannelId());
		// 获取实例
		AsileQuickAgainPayService instance = quickAgainPayContainer.get(key);

		if (instance == null) {
			logger.error("渠道号" + input.getChannelId() + "不支持快捷支付鉴权");
			throw new RuntimeException("渠道号" + input.getChannelId() + "不支持快捷支付鉴权");
		}
		// 转换输入参数
		AsileQuickAgainPayInput asileInput = BeanCopyConverter.converterClass(input, AsileQuickAgainPayInput.class);
		AsileQuickPayOutput asileOutput = instance.againPay(asileInput);
		
		QuickPayOutput out = BeanCopyConverter.converterClass(asileOutput, QuickPayOutput.class);
        return out;
	}
	/**
	 * 根据渠道号获取对应实例key
	 * 
	 * @param channelId
	 * @return
	 */
	private String getKeyByChannelId(String channelId) {
		return ChannelConfigRel.getBeanIdByChanAndBiz(channelId, ChannelBizType.QUICK_AGAIN_PAY.getCode());
	}

}
