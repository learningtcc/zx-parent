package com.yinker.channel.service.impl;

import com.yinker.base.utils.BeanCopyConverter;
import com.yinker.base.utils.logUtil.YinkerLoger;
import com.yinker.channel.api.enums.ChannelBizType;
import com.yinker.channel.api.enums.ChannelConfigRel;
import com.yinker.channel.api.model.in.Pay2CardQueryInput;
import com.yinker.channel.api.model.out.Pay2CardQueryOutput;
import com.yinker.channel.api.service.Pay2CardQueryService;
import com.yinker.channel.core.cnst.ChannelConstants;
import com.yinker.channel.core.model.in.AsilePay2CardQueryInput;
import com.yinker.channel.core.model.out.AsilePay2CardQueryOutput;
import com.yinker.channel.core.service.AsilePay2CardQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 代付查询服务接口实现类
 * 
 * @author huohb
 *
 */
@Service("pay2CardQueryServiceImpl")
public class Pay2CardQueryServiceImpl implements Pay2CardQueryService {

	private static YinkerLoger logger = YinkerLoger.getLogger(Pay2CardQueryServiceImpl.class);

	/*
	 * 此容器会存放所有实现AsilePay2CardQueryService接口的服务实现类实例
	 * 当上层接口传来渠道号的时候，会根据映射规则获取对应的service实例key 从此container中获取到实例，调用此实例的快捷支付方法
	 */
	@Autowired
	private Map<String, AsilePay2CardQueryService> pay2CardQueryContainer;
	
	@Override
	public Pay2CardQueryOutput query(Pay2CardQueryInput input) {
		// 根据参数中的渠道号获取对应的service实例key
		String key = getKeyByChannelId(input.getChannelId());
		// 获取实例
		AsilePay2CardQueryService instance = pay2CardQueryContainer.get(key);

		if (instance == null) {
			logger.info(ChannelConstants.LOGGER_MODULE_NAME, ChannelConstants.LOGGER_BUSINESS_ID_PAY2CARDQUERY, "渠道号" + input.getChannelId() + "不支持代付查询", false, input.getOrderNo());
			throw new RuntimeException("渠道号" + input.getChannelId() + "不支持代付查询");
		}
		// 转换输入参数
		AsilePay2CardQueryInput asileInput = BeanCopyConverter.converterClass(input, AsilePay2CardQueryInput.class);
		AsilePay2CardQueryOutput asileOutput = instance.query(asileInput);

		Pay2CardQueryOutput out = BeanCopyConverter.converterClass(asileOutput, Pay2CardQueryOutput.class);
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
		return ChannelConfigRel.getBeanIdByChanAndBiz(channelId, ChannelBizType.WITHDRAW_QUERY.getCode());
	}

}
