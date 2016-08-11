package com.ink.channel.core.boofoopay.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.IdCodeGenerator;
import com.ink.channel.core.boofoopay.request.BFUnbindBankCardReq;
import com.ink.channel.core.cnst.ChannelConstants;

/**
 * 取消绑定接口
 * @author Lenovo
 *
 */
public class BFUnbindBankCardServiceImpl {
	private static YinkerLogger logger = YinkerLogger.getLogger(BFPay2CardServiceImpl.class);
	@Autowired
    private IdCodeGenerator idCodeGenerator;
	public void unbindBank(){
		logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.BFOO_UNBIND_CARD_CODE,"开始调用宝付解除绑定银行卡接口");
		try{
			BFUnbindBankCardReq req=new BFUnbindBankCardReq();
			req.setTxn_sub_type("02");//交易子类02
			req.setTrans_serial_no(idCodeGenerator.getId());//商户流水号
			req.setBind_id("201603261412121000009649074");//绑定标识号
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
			req.setTrade_date(sdf.format(new Date()));//订单日期
//			BFUnbindBankCardResp resp=BooFooRequest.excuteRequest2Account(req, "unbindCardUrl", BFUnbindBankCardResp.class,"DS");
//			logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.BFOO_UNBIND_CARD_CODE,resp.toString());
		}catch(Exception ex){
			logger.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.BFOO_UNBIND_CARD_CODE,ex.getMessage(),ex,"");
		}
		logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.BFOO_UNBIND_CARD_CODE,"结束调用宝付解除绑定银行卡接口");
	}
}
