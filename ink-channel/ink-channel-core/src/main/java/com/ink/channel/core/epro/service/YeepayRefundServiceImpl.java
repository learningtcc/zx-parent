package com.ink.channel.core.epro.service;

import java.math.BigDecimal;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.springframework.stereotype.Service;

import com.ink.base.log.util.YinkerLogger;
import com.ink.channel.core.cnst.ChannelConstants;
import com.ink.channel.core.epro.ConfigurationUtil;
import com.ink.channel.core.epro.EproHttpClientUtils;
import com.ink.channel.core.model.in.AsileRefundInput;
import com.ink.channel.core.model.out.AsileRefundOutput;
import com.ink.channel.core.service.AsileRefundService;
/**
 * 易宝支付退款服务实现
 * @author huohb
 *
 */
@Service("yeepayRefundServiceImpl")
public class YeepayRefundServiceImpl implements AsileRefundService {
	
	private YinkerLogger logger = YinkerLogger.getLogger(YeepayRefundServiceImpl.class);
	@Override
	public AsileRefundOutput refund(AsileRefundInput input) {
		try{
			logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.YEEPAY_REFUND_CODE,"开始调用易宝支付退款服务");
			Map<String,String> params = new HashMap<String,String>();
			params.put("orderid", input.getOrderNo());//订单号
	        params.put("origyborderid", input.getOrigRefNumber());//原交易流水号
	        params.put("currency", "156");//币种
	        params.put("amount", input.getAmount().multiply(new BigDecimal("100")).toString());// 易宝发送的金额的单位是分，所以需要乘以100
	        
	        String refundURL = ConfigurationUtil.getRefundURL();
	        params.put("merchantaccount", ConfigurationUtil.getMerchantAccount());
	        AsileRefundOutput out = new AsileRefundOutput();
	        Map<String, String> result;
			logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.YEEPAY_REFUND_CODE,"易宝支付退款服务请求参数："+params);
			try {
				result = EproHttpClientUtils.executePostMethod(refundURL, params);
				logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.YEEPAY_REFUND_CODE,"易宝支付退款服务响应结果："+result);

			} catch (ConnectTimeoutException e) {
				out.setResMsg(ChannelConstants.REQUEST_EXCEPTION_MSG);
	            out.setResCode(ChannelConstants.REQUEST_EXCEPTION_CODE);
	            logger.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.YEEPAY_REFUND_CODE,ChannelConstants.REQUEST_EXCEPTION_MSG,e,"");
	            return out;
			} catch (SocketTimeoutException e) {
				out.setResMsg(ChannelConstants.RESPONSE_EXCEPTION_MSG);
	            out.setResCode(ChannelConstants.RESPONSE_EXCEPTION_CODE);
	            logger.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.YEEPAY_REFUND_CODE,ChannelConstants.RESPONSE_EXCEPTION_MSG,e,"");
	            return out;
			}
	        
	        if(result.get("error_code")==null){
	            out.setResCode(ChannelConstants.YEEPAY_SUCCESS_CODE);
	            out.setResMsg(ChannelConstants.YEEPAY_SUCCESS_MSG);
	        }else{
	            out.setResMsg(result.get("error_msg"));
	            out.setResCode(result.get("error_code"));
	        }
			return out;
		}catch(Exception ex){
            logger.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.YEEPAY_REFUND_CODE,ex.getMessage(),ex,"");
		}
		logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.YEEPAY_REFUND_CODE,"结束调用易宝支付退款服务");
		return null;
	}

}
