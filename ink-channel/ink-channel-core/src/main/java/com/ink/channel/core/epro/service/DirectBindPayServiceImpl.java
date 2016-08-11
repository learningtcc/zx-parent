package com.ink.channel.core.epro.service;

import java.math.BigDecimal;
import java.net.SocketTimeoutException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.ink.base.log.util.YinkerLogger;
import com.ink.channel.core.cnst.ChannelConstants;
import com.ink.channel.core.epro.Configuration;
import com.ink.channel.core.epro.ConfigurationUtil;
import com.ink.channel.core.epro.EproHttpClientUtils;
import com.ink.channel.core.model.in.AsilePayAccountIn;
import com.ink.channel.core.model.out.AsilePayAccountOut;
import com.ink.channel.core.service.AsilePay2AccountService;
@Service("directBindPayService")
public class DirectBindPayServiceImpl implements AsilePay2AccountService{
	private YinkerLogger LOGGER = YinkerLogger.getLogger(DirectBindPayServiceImpl.class);
	/**
     * directBindPay() : 易宝支付
     *                   支付接口--不发送短验
     * @param 必填参数
     *            ：
     * @param orderid
     *            商户订单号
     * @param transtime
     *            交易时间
     * @param amount
     *            交易金额
     * @param productname
     *            商品名称
     * @param identityid
     *            用户标识
     * @param identitytype
     *            用户标识类型
     * @param card_top
     *            卡号前 6 位
     * @param card_last
     *            卡号后 4 位
     * @param callbackurl
     *            回调地址
     * @param userip
     *            用户请求ip
     * @return
	 * @throws SocketTimeoutException 
	 * @throws ConnectTimeoutException 
     */
	@Override
	public AsilePayAccountOut payAccount(AsilePayAccountIn asilePayAccountIn) {
		try{
			LOGGER.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.YEEPAY_PAY_ACCOUNT_CODE,"进入易宝支付代收接口");
			Map<String, String> params = new HashMap<String, String>();
	        params.put("orderid", asilePayAccountIn.getOrderNo());//商户订单号
	        long timeM=new Date().getTime()/1000;
	        params.put("transtime",timeM+"");//交易时间
	        LOGGER.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.YEEPAY_PAY_ACCOUNT_CODE,"时间"+timeM);
	        int asileAmount = asilePayAccountIn.getAmount().multiply(new BigDecimal(100)).intValue();
	        params.put("amount", String.valueOf(asileAmount));//交易金额 
	        Random random=new Random();
	        params.put("productname", ConfigurationUtil.getMerchantAccount()+timeM+random.nextInt(100));//商品名称
	        //params.put("identityid", asilePayAccountIn.getIdentityid());//用户标识
	        params.put("identitytype",Configuration.getInstance().getValue("identitytype"));//用户标识类型
	        params.put("card_top", getCardTopNo(asilePayAccountIn.getAccountNo()));//卡号前 6 位
	        params.put("card_last", getCardLastNo(asilePayAccountIn.getAccountNo()));//卡号后 4 位
	        params.put("callbackurl", Configuration.getInstance().getValue("accountCallbackUrl"));//回调地址
	        params.put("userip", Configuration.getInstance().getValue("userip"));//用户支付时使用的网络终端 IP
	        Map<String, String> resultMap = null;
	        AsilePayAccountOut out=new AsilePayAccountOut();
	        LOGGER.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.YEEPAY_PAY_ACCOUNT_CODE,"易宝支付代收请求参数："+params);
			try {
				resultMap = this.directBindPay(params);
		        LOGGER.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.YEEPAY_PAY_ACCOUNT_CODE,"易宝支付代收响应参数："+resultMap);
			} catch (ConnectTimeoutException e) {
				//异常处理
				out.setResMsg(ChannelConstants.REQUEST_EXCEPTION_MSG);
	            out.setResCode(ChannelConstants.REQUEST_EXCEPTION_CODE);
	            LOGGER.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.YEEPAY_PAY_ACCOUNT_CODE,ChannelConstants.REQUEST_EXCEPTION_MSG,e,"");
	            return out;
			} catch (SocketTimeoutException e) {
				out.setResMsg(ChannelConstants.RESPONSE_EXCEPTION_MSG);
	            out.setResCode(ChannelConstants.RESPONSE_EXCEPTION_CODE);
	            LOGGER.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.YEEPAY_PAY_ACCOUNT_CODE,ChannelConstants.RESPONSE_EXCEPTION_MSG,e,"");
	            return out;
			}
	    	if(StringUtils.isBlank(resultMap.get("error_code"))){
	            out.setResMsg(ChannelConstants.YEEPAY_SUCCESS_MSG);
	            out.setResCode(ChannelConstants.YEEPAY_SUCCESS_CODE);
	            out.setOrderNo(asilePayAccountIn.getOrderNo());
	            out.setOrgTranFlow(resultMap.get("yborderid"));
	        }else{
	            out.setResMsg(resultMap.get("error_msg"));
	            out.setResCode(resultMap.get("error_code"));
	            out.setOrderNo(asilePayAccountIn.getOrderNo());
	        }
			return out;
		}catch(Exception ex){
			LOGGER.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.YEEPAY_PAY_ACCOUNT_CODE,ex.getMessage(),ex,null);
		}
		LOGGER.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.YEEPAY_PAY_ACCOUNT_CODE,"结束易宝支付代收接口");
		return null;
	}
	
	 public Map<String, String> directBindPay(Map<String, String> params) throws ConnectTimeoutException, SocketTimeoutException {
	        LOGGER.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.YEEPAY_PAY_ACCOUNT_CODE,"##### directBindPay() #####");
	        String directBindPayURL = ConfigurationUtil.getDirectBindPayURL();
	        params.put("merchantaccount", ConfigurationUtil.getMerchantAccount());
	        return EproHttpClientUtils.executePostMethod(directBindPayURL, params);
	}
	/**
	 * 获取卡号
	 * 
	 * @param cardNo
	 * @return
	 */
	private String getCardTopNo(String cardNo) {
		if (cardNo == null || "".equals(cardNo.trim())) {
			return null;
		}

		return cardNo.substring(0, 6);
	}
	private String getCardLastNo(String cardNo) {
		if (cardNo == null || "".equals(cardNo.trim())) {
			return null;
		}

		return cardNo.substring(cardNo.length() - 4, cardNo.length());
	}
	
}
