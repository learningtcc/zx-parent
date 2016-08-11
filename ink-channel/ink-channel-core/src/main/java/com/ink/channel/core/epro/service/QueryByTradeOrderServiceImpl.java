package com.ink.channel.core.epro.service;

import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;

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
@Service("queryByTradeOrderService")
public class QueryByTradeOrderServiceImpl implements AsilePay2AccountQueryService{
	private YinkerLogger LOGGER = YinkerLogger.getLogger(QueryByTradeOrderServiceImpl.class);
	/**
     * queryByOrder() :易宝交易记录查询接口
     * 
     * @param orderid
     *            客户订单号
     * @param yborderid
     *            易宝交易流水号
     * @return
	 * @throws SocketTimeoutException 
	 * @throws ConnectTimeoutException 
     */
	@Override
	public AsileQueryPayAccountOut queryPayOrder(AsileQueryPayAccountIn queryIn) {
		try{
			LOGGER.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.YEEPAY_QUERY_PAY_ACCOUNT_CODE,"进入易宝支付交易查询接口");
			String orderid = queryIn.getOrderNo();//订单号
	        Map<String, String> map = null;
	        AsileQueryPayAccountOut out=new AsileQueryPayAccountOut();
			try {
				map = queryByOrder(orderid, "");
				LOGGER.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.YEEPAY_QUERY_PAY_ACCOUNT_CODE,"易宝支付交易查询接口响应参数："+map);
			}  catch (ConnectTimeoutException e) {
				//异常处理
				out.setResMsg(ChannelConstants.REQUEST_EXCEPTION_MSG);
	            out.setResCode(ChannelConstants.REQUEST_EXCEPTION_CODE);
	            LOGGER.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.YEEPAY_QUERY_PAY_ACCOUNT_CODE,ChannelConstants.REQUEST_EXCEPTION_MSG,e,"");
			} catch (SocketTimeoutException e) {
				out.setResMsg(ChannelConstants.RESPONSE_EXCEPTION_MSG);
	            out.setResCode(ChannelConstants.RESPONSE_EXCEPTION_CODE);
	            LOGGER.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.YEEPAY_QUERY_PAY_ACCOUNT_CODE,ChannelConstants.RESPONSE_EXCEPTION_MSG,e,"");
			}
	        if(StringUtils.isBlank(map.get("error_code"))){
	        	//解析返回值
				//out.setActualAmount(map.get("amount"));
				//out.setChannelCode(map.get(""));
				//out.setPlatCode(map.get(""));
				//out.setCurrencyCode(map.get("currency"));
				out.setOrderNo(map.get("orderid"));
				out.setOrgTranFlow(map.get("yborderid"));
				//out.setFee(map.get("targetfee"));
				//out.setPayType(map.get("type"));
				//out.setReqTime(map.get("ordertime"));
				
				String stat = map.get("status");
				if(StringUtils.isNotBlank(stat)){
					if(stat.equals("1")||stat.equals("4")){
						out.setOrderStatus("00");//成功
					}else if(stat.equals("0")){
						out.setOrderStatus("02");//失败
					}else{
						out.setOrderStatus("01");//处理中
					}
				}
				//out.setTradeTime(map.get("ordertime"));
				out.setResCode(ChannelConstants.YEEPAY_SUCCESS_CODE);
				out.setResMsg(ChannelConstants.YEEPAY_SUCCESS_MSG);
			}
			else{
	            out.setResCode(map.get("error_code"));
	            out.setResMsg(map.get("error_msg"));
	        }
			return out;
		}catch(Exception ex){
            LOGGER.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.YEEPAY_QUERY_PAY_ACCOUNT_CODE,ex.getMessage(),ex,"");
		}
        LOGGER.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.YEEPAY_QUERY_PAY_ACCOUNT_CODE,"结束易宝支付交易查询接口");
        return null;
	}
	public Map<String, String> queryByOrder(String orderid, String yborderid) throws ConnectTimeoutException, SocketTimeoutException {
        LOGGER.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.YEEPAY_QUERY_PAY_ACCOUNT_CODE,"##### queryByOrder() #####");
        String merchantaccount = ConfigurationUtil.getMerchantAccount();
        String paymentQueryURL = ConfigurationUtil.getPaymentQueryURL();
        Map<String, String> params = new HashMap<String, String>();
        params.put("merchantaccount", merchantaccount);
        params.put("orderid", orderid);
		LOGGER.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.YEEPAY_QUERY_PAY_ACCOUNT_CODE,"易宝支付交易查询接口请求参数："+params);
        /*params.put("yborderid", yborderid);*/
        return EproHttpClientUtils.executeGetMethod(paymentQueryURL, params);
    }
}
