package com.ink.channel.core.epro.service;

import java.math.BigDecimal;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.springframework.stereotype.Service;

import com.ink.base.log.util.YinkerLogger;
import com.ink.channel.core.cnst.ChannelConstants;
import com.ink.channel.core.epro.Configuration;
import com.ink.channel.core.epro.ConfigurationUtil;
import com.ink.channel.core.epro.EproHttpClientUtils;
import com.ink.channel.core.model.in.AsilePay2CardInput;
import com.ink.channel.core.model.out.AsilePay2CardOutput;
import com.ink.channel.core.service.AsilePay2CardService;

/**
 * 易宝代付服务实现
 * @author huohb
 */
@Service("yeepayPay2CardServiceImpl")
public class YeepayPay2CardServiceImpl implements AsilePay2CardService {

	private static YinkerLogger LOGGER = YinkerLogger.getLogger(YeepayPay2CardServiceImpl.class);

	@Override
	public AsilePay2CardOutput pay(AsilePay2CardInput input) {
		try{
			LOGGER.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.YEEPAY_PAY_CARD_CODE,"开始调用易宝代付请求接口");
			Map<String, String> result = new HashMap<String, String>();
	
			String withdrawURL = ConfigurationUtil.getWithdrawURL();
	
			String requestid = ConfigurationUtil.formatString(input.getOrderNo());//商 户 请 求号
			String identityid = ConfigurationUtil.formatString(input.getIdentityId());//用户标识
			String card_top = ConfigurationUtil.formatString(input.getCardNo().substring(0, 6));//卡号前 6 位
			String card_last = ConfigurationUtil.formatString(input.getCardNo().substring(input.getCardNo().length() - 4));//卡号后 4 位
			// 提现类型： NATRALDAY_NORMAL( 自 然 日t+1);NATRALDAY_URGENT(自然日t+0)
			String drawtype = ConfigurationUtil.formatString("NATRALDAY_URGENT");//提现类型
			String userip = Configuration.getInstance().getValue("userip");//用 户 请 求ip
			
			int amount = input.getAmount().multiply(new BigDecimal(100)).intValue();
			int identitytype =Integer.parseInt(Configuration.getInstance().getValue("identitytype"));// 用户标识类型 0： IMEI 1： MAC 地址 2：用户 ID 3：用户 Email
									// 4：用户手机号 5：用户身份证号 6：用户纸质订单协议号
			int currency = 156;// 人民币
	
			TreeMap<String, String> dataMap = new TreeMap<String, String>();
			 dataMap.put("merchantaccount", ConfigurationUtil.getMerchantAccount());
			dataMap.put("requestid", requestid);
			dataMap.put("identityid", identityid);
			dataMap.put("identitytype", identitytype+"");
			dataMap.put("card_top", card_top);
			dataMap.put("card_last", card_last);
			dataMap.put("amount", amount+"");
			dataMap.put("currency", currency+"");
			dataMap.put("drawtype", drawtype);
			dataMap.put("callbackurl", Configuration.getInstance().getValue("cardCallbackUrl"));
			dataMap.put("imei", "");
			dataMap.put("userip", userip);
			dataMap.put("ua", "");
			AsilePay2CardOutput out = new AsilePay2CardOutput();
			LOGGER.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.YEEPAY_PAY_CARD_CODE,"易宝代付请求参数："+dataMap);
			try {
				result= EproHttpClientUtils.executePostMethod(withdrawURL, dataMap);
				LOGGER.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.YEEPAY_PAY_CARD_CODE,"易宝代付响应结果："+result);
			} catch (ConnectTimeoutException e) {
				out.setResMsg(ChannelConstants.REQUEST_EXCEPTION_MSG);
	            out.setResCode(ChannelConstants.REQUEST_EXCEPTION_CODE);
	            LOGGER.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.YEEPAY_PAY_CARD_CODE,ChannelConstants.REQUEST_EXCEPTION_MSG,e,"");
	            return out;
			} catch (SocketTimeoutException e) {
				out.setResMsg(ChannelConstants.RESPONSE_EXCEPTION_MSG);
	            out.setResCode(ChannelConstants.RESPONSE_EXCEPTION_CODE);
	            LOGGER.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.YEEPAY_PAY_CARD_CODE,ChannelConstants.RESPONSE_EXCEPTION_MSG,e,"");
	            return out;
			}
			if(result.containsKey("error_code")){
				out.setResCode(result.get("error_code"));
				out.setResMsg(result.get("error_msg"));
			}else{
				out.setResCode(ChannelConstants.YEEPAY_SUCCESS_CODE);
				out.setResMsg(ChannelConstants.YEEPAY_SUCCESS_MSG);
				out.setOrgTranFlow(result.get("ybdrawflowid"));
				out.setOrderNo(input.getOrderNo());
				BigDecimal bigAmout = new BigDecimal(result.get("amount"));
				BigDecimal amountMin = bigAmout.divide(new BigDecimal(100),2, BigDecimal.ROUND_HALF_EVEN);
				out.setAmount(amountMin.toString());
			}
			return out;
		}catch(Exception ex){
            LOGGER.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.YEEPAY_PAY_CARD_CODE,ex.getMessage(),ex,"");
		}
		LOGGER.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.YEEPAY_PAY_CARD_CODE,"开始调用易宝代付请求接口");
		return null;
	}

}
