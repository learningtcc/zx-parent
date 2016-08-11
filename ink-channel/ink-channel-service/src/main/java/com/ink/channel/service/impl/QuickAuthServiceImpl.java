package com.yinker.channel.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinker.base.utils.BeanCopyConverter;
import com.yinker.base.utils.logUtil.YinkerLoger;
import com.yinker.channel.api.enums.ChannelBizType;
import com.yinker.channel.api.enums.ChannelConfigRel;
import com.yinker.channel.api.model.in.QuickAuthInput;
import com.yinker.channel.api.model.out.QuickAuthOutput;
import com.yinker.channel.api.service.QuickAuthService;
import com.yinker.channel.core.model.in.AsileQuickAuthInput;
import com.yinker.channel.core.model.out.AsileQuickAuthOutput;
import com.yinker.channel.core.service.AsileQuickAuthService;

/**
 * 快捷支付鉴权服务接口实现类
 * 
 * @author huohb
 *
 */
@Service("quickAuthServiceImpl")
public class QuickAuthServiceImpl implements QuickAuthService {

	private static YinkerLoger logger = YinkerLoger.getLogger(QuickPayServiceImpl.class);

	/*
	 * 此容器会存放所有实现AsileQuickAuthService接口的服务实现类实例
	 * 当上层接口传来渠道号的时候，会根据映射规则获取对应的service实例key 从此container中获取到实例，调用此实例的快捷支付方法
	 */
	@Autowired
	private Map<String, AsileQuickAuthService> quickAuthContainer;

	@Override
	public QuickAuthOutput quickAuth(QuickAuthInput input) {
		// 根据参数中的渠道号获取对应的service实例key
		String key = getKeyByChannelId(input.getChannelId());
		// 获取实例
		AsileQuickAuthService instance = quickAuthContainer.get(key);

		if (instance == null) {
			logger.error("渠道号" + input.getChannelId() + "不支持快捷支付鉴权");
			throw new RuntimeException("渠道号" + input.getChannelId() + "不支持快捷支付鉴权");
		}
		// 转换输入参数
		AsileQuickAuthInput asileInput = BeanCopyConverter.converterClass(input, AsileQuickAuthInput.class);
		AsileQuickAuthOutput asileOutput = instance.quickAuth(asileInput);
		
		// 将渠道返回的token放到Redis
		/*boolean isSuccess = false;
		try {
			isSuccess = redisClient.setCache(input.getOrderNo(), asileOutput.getToken(), 300);
		} catch (Exception e) {
			logger.error("put quickAuth token into Redis error,orderNo " + input.getOrderNo());
			throw new RuntimeException("put cache into Redis error");
		}
		
		if(!isSuccess){
			logger.error("put token into Redis failed,orderNo " + input.getOrderNo());
		}*/
		QuickAuthOutput out = BeanCopyConverter.converterClass(asileOutput, QuickAuthOutput.class);
        return out;
	}

	/**
	 * 根据渠道号获取对应实例key
	 * 
	 * @param channelId
	 * @return
	 */
	private String getKeyByChannelId(String channelId) {
		return ChannelConfigRel.getBeanIdByChanAndBiz(channelId, ChannelBizType.QUICK_AUTH.getCode());
	}

}
