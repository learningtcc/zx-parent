package com.yinker.channel.service.impl;

import com.yinker.base.utils.BeanCopyConverter;
import com.yinker.base.utils.logUtil.YinkerLoger;
import com.yinker.channel.api.enums.ChannelBizType;
import com.yinker.channel.api.enums.ChannelConfigRel;
import com.yinker.channel.api.model.in.Pay2CardInput;
import com.yinker.channel.api.model.out.Pay2CardOutput;
import com.yinker.channel.api.service.Pay2CardService;
import com.yinker.channel.core.model.in.AsilePay2CardInput;
import com.yinker.channel.core.model.out.AsilePay2CardOutput;
import com.yinker.channel.core.service.AsilePay2CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 代付服务接口实现类
 * 
 * @author huohb
 *
 */
@Service("pay2CardServiceImpl")
public class Pay2CardServiceImpl implements Pay2CardService {
	private static YinkerLoger logger = YinkerLoger.getLogger(Pay2CardServiceImpl.class);

	/*
	 * 此容器会存放所有实现AsilePay2CardService接口的服务实现类实例
	 * 当上层接口传来渠道号的时候，会根据映射规则获取对应的service实例key 从此container中获取到实例，调用此实例的快捷支付方法
	 */
	@Autowired
	private Map<String, AsilePay2CardService> pay2CardContainer;
	/**
	 * 代付
	 */
	@Override
	public Pay2CardOutput pay(Pay2CardInput input) {
		// 根据参数中的渠道号获取对应的service实例key
		String key = getKeyByChannelId(input.getChannelId());
		// 获取实例
		AsilePay2CardService instance = pay2CardContainer.get(key);

		if (instance == null) {
			logger.error("渠道号" + input.getChannelId() + "不支持代付");
			throw new RuntimeException("渠道号" + input.getChannelId() + "不支持代付");
		}
		// 转换输入参数
		AsilePay2CardInput asileInput = BeanCopyConverter.converterClass(input, AsilePay2CardInput.class);
		AsilePay2CardOutput asileOutput = instance.pay(asileInput);
		
		Pay2CardOutput out = BeanCopyConverter.converterClass(asileOutput, Pay2CardOutput.class);
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
		return ChannelConfigRel.getBeanIdByChanAndBiz(channelId, ChannelBizType.WITHDRAW.getCode());
	}

}
