package com.ink.channel.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.BeanCopyConverter;
import com.ink.base.utils.IdCodeGenerator;
import com.ink.channel.api.enums.ChannelBizType;
import com.ink.channel.api.model.in.RefundInput;
import com.ink.channel.api.model.out.RefundOutput;
import com.ink.channel.api.service.RefundService;
import com.ink.channel.core.model.in.AsileRefundInput;
import com.ink.channel.core.model.out.AsileRefundOutput;
import com.ink.channel.core.service.AsileRefundService;

/**
 * 退款服务接口实现类
 * 
 * @author huohb
 *
 */
@Service("refundServiceImpl")
public class RefundServiceImpl implements RefundService {

	private static YinkerLogger logger = YinkerLogger.getLogger(RefundServiceImpl.class);

	/*
	 * 此容器会存放所有实现AsileRefundService接口的服务实现类实例
	 * 当上层接口传来渠道号的时候，会根据映射规则获取对应的service实例key 从此container中获取到实例，调用此实例的快捷支付方法
	 */
	@Autowired
	private Map<String, AsileRefundService> refundContainer;
	
	@Autowired
	private IdCodeGenerator channelRelUtil;
	
	@Override
	public RefundOutput refund(RefundInput input) {
		// 根据参数中的渠道号获取对应的service实例key
		String key = getKeyByChannelId(input.getChannelId());
		// 获取实例
		AsileRefundService instance = refundContainer.get(key);

		if (instance == null) {
			logger.error("渠道号" + input.getChannelId() + "不支持退款服务");
			throw new RuntimeException("渠道号" + input.getChannelId() + "不支持退款服务");
		}
		// 转换输入参数
		AsileRefundInput asileInput = BeanCopyConverter.converterClass(input, AsileRefundInput.class);
		AsileRefundOutput asileOutput = instance.refund(asileInput);

		RefundOutput out = BeanCopyConverter.converterClass(asileOutput, RefundOutput.class);
        return out;
	}
	
	private String getKeyByChannelId(String channelId){
		// 暂时先返回channelId，以后会返回相应的service名称
		return channelRelUtil.getIpMapsConfig().get((channelId+ChannelBizType.REFUND.getCode()).trim());
	}

}
