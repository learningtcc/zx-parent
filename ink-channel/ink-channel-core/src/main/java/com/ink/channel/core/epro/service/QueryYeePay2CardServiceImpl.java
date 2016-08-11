package com.ink.channel.core.epro.service;

import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.ink.base.log.util.YinkerLogger;
import com.ink.channel.core.cnst.ChannelConstants;
import com.ink.channel.core.epro.ConfigurationUtil;
import com.ink.channel.core.epro.EproHttpClientUtils;
import com.ink.channel.core.model.in.AsileQueryPayAccountIn;
import com.ink.channel.core.model.out.AsileQueryPayAccountOut;
import com.ink.channel.core.service.AsilePay2AccountQueryService;
/**
 * 易宝查询提现接口
 * @author Lenovo
 *
 */
@Service("queryYeePay2CardService")
public class QueryYeePay2CardServiceImpl implements AsilePay2AccountQueryService{
	 private YinkerLogger LOGGER = YinkerLogger.getLogger(QueryYeePay2CardServiceImpl.class);
	/**
	 * 查询提现接口
	 */
	@Override
	public AsileQueryPayAccountOut queryPayOrder(AsileQueryPayAccountIn input) {
		try{
			LOGGER.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.YEEPAY_QUERY_PAY_CARD_CODE,"开始调用查询提现接口");
	        String queryWithdrawURL = ConfigurationUtil.getQueryWithdrawURL();
	
	        TreeMap<String, String> dataMap = new TreeMap<String, String>();
	        dataMap.put("requestid", input.getOrderNo());//订单号
	        dataMap.put("ybdrawflowid", "");
	
	        Map<String, String> result = new HashMap<String, String>();
	        AsileQueryPayAccountOut out=new AsileQueryPayAccountOut();
			LOGGER.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.YEEPAY_QUERY_PAY_CARD_CODE,"查询提现接口请求参数："+dataMap);
	        try {
				result=EproHttpClientUtils.executeGetMethod(queryWithdrawURL, dataMap);
				LOGGER.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.YEEPAY_QUERY_PAY_CARD_CODE,"查询提现接口响应参数："+result);
			} catch (ConnectTimeoutException e) {
				out.setResMsg(ChannelConstants.REQUEST_EXCEPTION_MSG);
	            out.setResCode(ChannelConstants.REQUEST_EXCEPTION_CODE);
	            LOGGER.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.YEEPAY_QUERY_PAY_CARD_CODE,ChannelConstants.REQUEST_EXCEPTION_MSG,e,"");
	            return out;
			} catch (SocketTimeoutException e) {
				out.setResMsg(ChannelConstants.RESPONSE_EXCEPTION_MSG);
	            out.setResCode(ChannelConstants.RESPONSE_EXCEPTION_CODE);
	            LOGGER.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.YEEPAY_QUERY_PAY_CARD_CODE,ChannelConstants.RESPONSE_EXCEPTION_MSG,e,"");
	            return out;
			}
	        LOGGER.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.YEEPAY_QUERY_PAY_CARD_CODE,"result : " + result);
	        
	        if(result.get("error_code")==null){
	        	String status = result.get("status");
	        	if(StringUtils.isNotBlank(status)){
	        		if(status.equals("DOING")){
	        			out.setOrderStatus("01");//处理中
	        		}else if(status.equals("SUCCESS")){
	        			out.setOrderStatus("00");//成功
	        		}else{
	        			out.setOrderStatus("02");//失败
	        		}
	        	}
		        out.setResCode(ChannelConstants.YEEPAY_SUCCESS_CODE);
		        out.setResMsg(ChannelConstants.YEEPAY_SUCCESS_MSG);
	        }else{
	        	 out.setResCode(result.get("error_code"));
	             out.setResMsg(result.get("error_msg"));
	        }
	        return out;
        }catch(Exception ex){
            LOGGER.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.YEEPAY_QUERY_PAY_CARD_CODE,ex.getMessage(),ex,"");
        }
		LOGGER.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.YEEPAY_QUERY_PAY_CARD_CODE,"结束调用查询提现接口");
		return null;
	}
	
}
