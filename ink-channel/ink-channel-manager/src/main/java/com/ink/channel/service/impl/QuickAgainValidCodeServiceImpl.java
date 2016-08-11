package com.ink.channel.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.BeanCopyConverter;
import com.ink.base.utils.IdCodeGenerator;
import com.ink.channel.api.enums.ChannelBizType;
import com.ink.channel.api.model.in.QuickAgainValidCodeInput;
import com.ink.channel.api.model.out.QuickAgainValidCodeOutput;
import com.ink.channel.api.service.QuickAgainValidCodeService;
import com.ink.channel.core.cnst.ChannelConstants;
import com.ink.channel.core.enums.SystemCodeEnums;
import com.ink.channel.core.model.in.AsileQuickAgainValidCodeInput;
import com.ink.channel.core.model.out.AsileQuickAgainValidCodeOutput;
import com.ink.channel.core.service.AsileQuickAgainValidCodeService;
@Service("quickAgainValidCodeService")
public class QuickAgainValidCodeServiceImpl implements QuickAgainValidCodeService{
	/*
	 * 当上层接口传来渠道号的时候，会根据映射规则获取对应的service实例key 从此container中获取到实例，调用此实例的快捷支付方法
	 */
	@Autowired
	private Map<String, AsileQuickAgainValidCodeService> quickAuthContainer;
	
	@Autowired
	private IdCodeGenerator channelRelUtil;
	
	private static YinkerLogger logger = YinkerLogger.getLogger(QuickPayServiceImpl.class);
	
	@Override
	public QuickAgainValidCodeOutput againValidCode(QuickAgainValidCodeInput input) {
		    QuickAgainValidCodeOutput out=null;
			// 根据参数中的渠道号获取对应的service实例key
			String key = getKeyByChannelId(input.getChannelId());
			// 获取实例
			AsileQuickAgainValidCodeService instance = quickAuthContainer.get(key);

			if (instance == null) {
				out=new QuickAgainValidCodeOutput();
				out.setResCode(SystemCodeEnums.FAILE_CODE.getCode());
	        	out.setResMsg("渠道号" + input.getChannelId()+"不支持快捷支付再次支付发送短验");
	        	logger.error(ChannelConstants.LOGGER_MODULE_NAME, out.getResMsg());
	        	return out;
			}
			// 转换输入参数
			AsileQuickAgainValidCodeInput asileInput = BeanCopyConverter.converterClass(input, AsileQuickAgainValidCodeInput.class);
			AsileQuickAgainValidCodeOutput asileOutput = instance.quickAgainValidCode(asileInput);
			 out = BeanCopyConverter.converterClass(asileOutput, QuickAgainValidCodeOutput.class);
	        return out;
		}

		/**
		 * 根据渠道号获取对应实例key
		 * 
		 * @param channelId
		 * @return
		 */
		private String getKeyByChannelId(String channelId) {
			
			return channelRelUtil.getIpMapsConfig().get((channelId+ChannelBizType.QUICK_AGAIN_VALID_CODE.getCode()).trim());
		}
}
