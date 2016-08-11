package com.ink.channel.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.BeanCopyConverter;
import com.ink.base.utils.IdCodeGenerator;
import com.ink.channel.api.enums.ChannelBizType;
import com.ink.channel.api.model.in.Pay2CardInput;
import com.ink.channel.api.model.out.Pay2CardOutput;
import com.ink.channel.api.service.Pay2CardService;
import com.ink.channel.core.cnst.ChannelConstants;
import com.ink.channel.core.enums.SystemCodeEnums;
import com.ink.channel.core.model.in.AsilePay2CardInput;
import com.ink.channel.core.model.out.AsilePay2CardOutput;
import com.ink.channel.core.service.AsilePay2CardService;

/**
 * 代付服务接口实现类
 * 
 * @author huohb
 *
 */
@Service("pay2CardServiceImpl")
public class Pay2CardServiceImpl implements Pay2CardService {
	private static YinkerLogger logger = YinkerLogger.getLogger(Pay2CardServiceImpl.class);

	/*
	 * 此容器会存放所有实现AsilePay2CardService接口的服务实现类实例
	 * 当上层接口传来渠道号的时候，会根据映射规则获取对应的service实例key 从此container中获取到实例，调用此实例的快捷支付方法
	 */
	@Autowired
	private Map<String, AsilePay2CardService> pay2CardContainer;
	
	@Autowired
	private IdCodeGenerator channelRelUtil;
	/**
	 * 代付
	 */
	@Override
	public Pay2CardOutput pay(Pay2CardInput input) {
		long startMillis = System.currentTimeMillis();
		Pay2CardOutput out=null;
		logger.info(ChannelConstants.LOGGER_MODULE_NAME,"开始调用代付系统时间"+startMillis);
		// 根据参数中的渠道号获取对应的service实例key
		String key = getKeyByChannelId(input.getChannelId());
		// 获取实例
		AsilePay2CardService instance = pay2CardContainer.get(key);

		if (instance == null) {
			out=new Pay2CardOutput();
			out.setResCode(SystemCodeEnums.FAILE_CODE.getCode());
        	out.setResMsg("渠道号" +input.getChannelId()+"不支持代付");
        	logger.error(ChannelConstants.LOGGER_MODULE_NAME, out.getResMsg());
        	return out;
		}
		// 转换输入参数
		AsilePay2CardInput asileInput = BeanCopyConverter.converterClass(input, AsilePay2CardInput.class);
		AsilePay2CardOutput asileOutput = instance.pay(asileInput);
		
		 out = BeanCopyConverter.converterClass(asileOutput, Pay2CardOutput.class);
		 long endMillis = System.currentTimeMillis();
		 logger.info(ChannelConstants.LOGGER_MODULE_NAME,"结束调用代付系统时间:"+endMillis);
		 long diffMillis = endMillis-startMillis;
		 logger.info(ChannelConstants.LOGGER_MODULE_NAME,"调用代付时间差:"+diffMillis);
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
		return channelRelUtil.getIpMapsConfig().get((channelId+ChannelBizType.WITHDRAW.getCode()).trim());
	}

}
