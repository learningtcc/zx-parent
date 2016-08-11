package com.ink.channel.core.jdPay.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.ink.base.log.util.YinkerLogger;
import com.ink.channel.core.cnst.ChannelConstants;
import com.ink.channel.core.enums.OrderStatus;
import com.ink.channel.core.enums.SystemCodeEnums;
import com.ink.channel.core.jdPay.enums.JDConstantEnums;
import com.ink.channel.core.jdPay.util.JDHttpClientUtils;
import com.ink.channel.core.ldyspay.LdysConstant;
import com.ink.channel.core.model.in.AsileQueryPayAccountIn;
import com.ink.channel.core.model.out.AsileQueryPayAccountOut;
import com.ink.channel.core.service.AsilePay2AccountQueryService;
/**
 * 网银在线快捷支付查询接口
 * @author Lenovo
 *
 */
@Service("jDQuickQueryServiceImpl")
public class JDQuickQueryServiceImpl implements AsilePay2AccountQueryService{
	 private static YinkerLogger logger = YinkerLogger.getLogger(JDQuickQueryServiceImpl.class);
	@Override
	public AsileQueryPayAccountOut queryPayOrder(AsileQueryPayAccountIn input) {
		logger.info(ChannelConstants.LOGGER_MODULE_NAME, ChannelConstants.JD_QUICK_QUERY,"进入京东快捷支付查询接口");
		AsileQueryPayAccountOut out=new AsileQueryPayAccountOut();
		try {
			Map<String, Object> params=new HashMap<String, Object>();
			params.put("trade_type", JDConstantEnums.TRADE_TYPE_Q.getCode());
			params.put("trade_id", input.getOrderNo());
			
			logger.info(ChannelConstants.LOGGER_MODULE_NAME, ChannelConstants.JD_AUTH_QUICK_PAY_CODE,"京东快捷支付查询接口HTTP请求参数："+params.toString());
			Map<String, Object> map = JDHttpClientUtils.trade(params,input.getMerchantNo());
			logger.info(ChannelConstants.LOGGER_MODULE_NAME, ChannelConstants.JD_AUTH_QUICK_PAY_CODE,"京东快捷支付查询接口HTTP响应结果："+map.toString());
			
			String exceptionCode = (String) map.get("exceptionCode");
			if(LdysConstant.decodeCode.equals(exceptionCode)){
				//解密异常
				out.setResCode(SystemCodeEnums.JD_FAILE_CODE.getCode());
				out.setResMsg("京东快捷首次支付解密验签失败");
				out.setOrderNo(input.getOrderNo());
				out.setOrderStatus(OrderStatus.PROCESSES_CODE.getCode());//处理中
				logger.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.JD_QUICK_PAY_CODE, "京东快捷支付首次支付解密验签失败",null);
			}else{
				out.setResCode((String)map.get("code"));
				out.setResMsg((String)map.get("desc"));
				out.setOrderNo(input.getOrderNo());
				if("0000".equals((String)map.get("code"))){
					try{
						String amount = (String)map.get("amount");
						double newAmount = new BigDecimal(amount).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP).doubleValue();  
						out.setAmount(newAmount+"");
						out.setOrgTranFlow((String)map.get("id"));
						out.setTradeDate((String)map.get("date")+(String)map.get("time"));
						String stat = (String)map.get("status");
						if(StringUtils.isNotBlank(stat)){
							if(stat.equals("6")){
								out.setOrderStatus(OrderStatus.PROCESSES_CODE.getCode());//处理中
							}else if(stat.equals("7")){
								out.setOrderStatus(OrderStatus.FAILE_CODE.getCode());//失败
							}else{
								out.setOrderStatus(OrderStatus.SUCCESS_CODE.getCode());//成功
							}
						}
					}catch(Exception e){
						out.setOrderStatus(OrderStatus.PROCESSES_CODE.getCode());//处理中
					}
				}else{
					out.setOrderStatus(OrderStatus.PROCESSES_CODE.getCode());//处理中
				}
			}
		} catch (Exception e) {
			logger.error(ChannelConstants.LOGGER_MODULE_NAME, ChannelConstants.JD_QUICK_QUERY,"京东快捷支付查询接口："+e.getMessage(),e,"");
			e.printStackTrace();
			out.setResCode(SystemCodeEnums.JD_FAILE_CODE.getCode());
			out.setResMsg(SystemCodeEnums.JD_FAILE_CODE.getMsg());
			out.setOrderStatus(OrderStatus.PROCESSES_CODE.getCode());//处理中
		}
		logger.info(ChannelConstants.LOGGER_MODULE_NAME, ChannelConstants.JD_QUICK_QUERY,"结束京东快捷支付查询接口："+out.toString());
		return out;
	}
	
}
