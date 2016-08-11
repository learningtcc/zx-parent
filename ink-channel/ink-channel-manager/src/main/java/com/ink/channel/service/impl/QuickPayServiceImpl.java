package com.ink.channel.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.BeanCopyConverter;
import com.ink.base.utils.IdCodeGenerator;
import com.ink.channel.api.enums.ChannelBizType;
import com.ink.channel.api.model.in.QuickPayInput;
import com.ink.channel.api.model.out.QuickPayOutput;
import com.ink.channel.api.service.QuickPayService;
import com.ink.channel.core.cnst.ChannelConstants;
import com.ink.channel.core.enums.SystemCodeEnums;
import com.ink.channel.core.model.in.AsileQuickPayInput;
import com.ink.channel.core.model.out.AsileQuickPayOutput;
import com.ink.channel.core.service.AsileQuickPayService;
/**
 * 快捷支付服务实现类
 * @author huohb
 *
 */
@Service("quickPayServiceImpl")
public class QuickPayServiceImpl implements QuickPayService {

	private static YinkerLogger logger = YinkerLogger.getLogger(QuickPayServiceImpl.class);
	/*
	 * 此容器会存放所有实现AsileQuickPayService接口的服务实现类实例
	 * 当上层接口传来渠道号的时候，会根据映射规则获取对应的service实例key 从此container中获取到实例，调用此实例的快捷支付方法
	 */
	@Autowired
	private Map<String, AsileQuickPayService> quickPayContainer;
	
	@Autowired
	private IdCodeGenerator channelRelUtil;
		
	@Override
	public QuickPayOutput pay(QuickPayInput input) {
		QuickPayOutput out=null;
		// 根据参数中的渠道号获取对应的service实例key
		String key = getKeyByChannelId(input.getChannelId());
		// 获取实例
		AsileQuickPayService instance = quickPayContainer.get(key);

		if (instance == null) {
			out=new QuickPayOutput();
			out.setResCode(SystemCodeEnums.FAILE_CODE.getCode());
        	out.setResMsg("渠道号" + input.getChannelId()+"不支持快捷支付");
        	logger.error(ChannelConstants.LOGGER_MODULE_NAME, out.getResMsg());
        	return out;
		}
		// 转换输入参数
		AsileQuickPayInput asileInput = BeanCopyConverter.converterClass(input, AsileQuickPayInput.class);
		
		AsileQuickPayOutput asileOutput = instance.pay(asileInput);
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
		// 暂时先返回channelId
		return channelRelUtil.getIpMapsConfig().get((channelId+ChannelBizType.QUICK_PAY.getCode()).trim());
	}
}
