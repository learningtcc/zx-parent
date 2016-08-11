package com.yinker.channel.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinker.base.utils.BeanCopyConverter;
import com.yinker.base.utils.logUtil.YinkerLoger;
import com.yinker.channel.api.enums.ChannelBizType;
import com.yinker.channel.api.enums.ChannelConfigRel;
import com.yinker.channel.api.model.in.QuickPayInput;
import com.yinker.channel.api.model.out.QuickPayOutput;
import com.yinker.channel.api.service.QuickPayService;
import com.yinker.channel.core.model.in.AsileQuickPayInput;
import com.yinker.channel.core.model.out.AsileQuickPayOutput;
import com.yinker.channel.core.service.AsileQuickPayService;
/**
 * 快捷支付服务实现类
 * @author huohb
 *
 */
@Service("quickPayServiceImpl")
public class QuickPayServiceImpl implements QuickPayService {

	private static YinkerLoger logger = YinkerLoger.getLogger(QuickPayServiceImpl.class);

	/*
	 * 此容器会存放所有实现AsileQuickPayService接口的服务实现类实例
	 * 当上层接口传来渠道号的时候，会根据映射规则获取对应的service实例key 从此container中获取到实例，调用此实例的快捷支付方法
	 */
	@Autowired
	private Map<String, AsileQuickPayService> quickPayContainer;
		
	@Override
	public QuickPayOutput pay(QuickPayInput input) {
		// 根据参数中的渠道号获取对应的service实例key
		String key = getKeyByChannelId(input.getChannelId());
		// 获取实例
		AsileQuickPayService instance = quickPayContainer.get(key);

		if (instance == null) {
			logger.error("渠道号" + input.getChannelId() + "不支持快捷支付");
			throw new RuntimeException("渠道号" + input.getChannelId() + "不支持快捷支付");
		}
		// 转换输入参数
		AsileQuickPayInput asileInput = BeanCopyConverter.converterClass(input, AsileQuickPayInput.class);
		
		AsileQuickPayOutput asileOutput = instance.pay(asileInput);
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
		// 暂时先返回channelId
		return ChannelConfigRel.getBeanIdByChanAndBiz(channelId, ChannelBizType.QUICK_PAY.getCode());
	}
}
