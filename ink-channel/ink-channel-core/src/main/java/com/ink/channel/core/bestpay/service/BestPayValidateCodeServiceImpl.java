package com.ink.channel.core.bestpay.service;

import org.springframework.stereotype.Service;

import com.ink.base.log.util.YinkerLogger;
import com.ink.channel.core.cnst.ChannelConstants;
import com.ink.channel.core.model.in.AsileValidCodeInput;
import com.ink.channel.core.model.out.AsileValidCodeOutput;
import com.ink.channel.core.service.AsileValidateCodeService;
/**
 * 发送短验     翼支付无发送短验的接口
 * @author Lenovo
 *
 */
@Service("bestPayValidateCodeService")
public class BestPayValidateCodeServiceImpl implements AsileValidateCodeService{
	private YinkerLogger logger = YinkerLogger.getLogger(BestPayValidateCodeServiceImpl.class);
	@Override
	public AsileValidCodeOutput getValidateCode(AsileValidCodeInput asileValidCodeInput) {
		AsileValidCodeOutput out=new AsileValidCodeOutput();
		out.setResCode("000000");
		out.setResMsg("成功");
		out.setToken("");
		out.setIdentityId(asileValidCodeInput.getIdentityid());
		out.setReqId(asileValidCodeInput.getReqId());
		logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.BEST_PAY_VALIDATE_CODE,"");
		return out;
	}
	
}
