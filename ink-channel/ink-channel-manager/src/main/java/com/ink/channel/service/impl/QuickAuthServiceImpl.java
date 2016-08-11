package com.ink.channel.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.BeanCopyConverter;
import com.ink.base.utils.IdCodeGenerator;
import com.ink.channel.api.enums.ChannelBizType;
import com.ink.channel.api.model.in.QuickAuthInput;
import com.ink.channel.api.model.out.QuickAuthOutput;
import com.ink.channel.api.service.QuickAuthService;
import com.ink.channel.core.cnst.ChannelConstants;
import com.ink.channel.core.enums.SystemCodeEnums;
import com.ink.channel.core.model.in.AsileQuickAuthInput;
import com.ink.channel.core.model.out.AsileQuickAuthOutput;
import com.ink.channel.core.service.AsileQuickAuthService;

/**
 * 快捷支付鉴权服务接口实现类
 * 
 * @author huohb
 *
 */
@Service("quickAuthServiceImpl")
public class QuickAuthServiceImpl implements QuickAuthService {

	private static YinkerLogger logger = YinkerLogger.getLogger(QuickPayServiceImpl.class);

	/*
	 * 当上层接口传来渠道号的时候，会根据映射规则获取对应的service实例key 从此container中获取到实例，调用此实例的快捷支付方法
	 */
	@Autowired
	private Map<String, AsileQuickAuthService> quickAuthContainer;
	
	@Autowired
	private IdCodeGenerator channelRelUtil;

	@Override
	public QuickAuthOutput quickAuth(QuickAuthInput input) {
		QuickAuthOutput out=null;
		// 根据参数中的渠道号获取对应的service实例key
		String key = getKeyByChannelId(input.getChannelId());
		// 获取实例
		AsileQuickAuthService instance = quickAuthContainer.get(key);

		if (instance == null) {
			out=new QuickAuthOutput();
			out.setResCode(SystemCodeEnums.FAILE_CODE.getCode());
        	out.setResMsg("渠道号" + input.getChannelId()+"不支持快捷支付鉴权");
        	logger.error(ChannelConstants.LOGGER_MODULE_NAME, out.getResMsg());
        	return out;
		}
		// 转换输入参数
		AsileQuickAuthInput asileInput = BeanCopyConverter.converterClass(input, AsileQuickAuthInput.class);
		AsileQuickAuthOutput asileOutput = instance.quickAuth(asileInput);
		
		out = BeanCopyConverter.converterClass(asileOutput, QuickAuthOutput.class);
        return out;
	}

	/**
	 * 根据渠道号获取对应实例key
	 * 
	 * @param channelId
	 * @return
	 */
	private String getKeyByChannelId(String channelId) {
		return channelRelUtil.getIpMapsConfig().get((channelId+ChannelBizType.QUICK_AUTH.getCode()).trim());
	}

}
