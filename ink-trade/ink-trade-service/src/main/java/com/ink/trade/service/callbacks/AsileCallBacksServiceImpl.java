package com.ink.trade.service.callbacks;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.base.log.util.YinkerLogger;
import com.ink.channel.core.epro.service.DecryptCallbackServiceImpl;
import com.ink.trade.core.cnst.TradeLogConstant;
import com.ink.trade.service.dto.PreCollectionCallBackDto;

@Service("asileCallBacksService")
public class AsileCallBacksServiceImpl {
	
	private static YinkerLogger logger = YinkerLogger.getLogger(AsileCallBacksServiceImpl.class);
	@Autowired
	private DecryptCallbackServiceImpl decryptCallbackService;
	@Autowired
	PreCollectionCallBackServiceImpl preCollectionCallBackServiceImpl;
	@Autowired
	PrepaidCallBackServiceImpl prepaidCallBackServiceImpl;
	/**
	 * 代收付回调公共接口
	 * @param orderid
	 * @param result
	 * @return
	 */
	public String payCallBack(String data,String encryptkey,String payType){
		try{
		//解析回调参数
			Map<String, String> result=decryptCallbackService.decryptCallbackData(data, encryptkey);
			if(result==null){
	            logger.info(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_COLLECT, "未正确解析回调参数");
				return "FAIL";
			}
			//获取回调参数值。
			String amount=result.get("amount");
			String status=result.get("status");
			String orderid=null;
			String yborderid=null;
			if(payType.equals("account")){//代收
				 orderid=result.get("orderid");
				 yborderid=result.get("yborderid");
				 String errorcode = result.get("errorcode");
				if(StringUtils.isNotBlank(errorcode)){
					logger.info(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_COLLECT, "回调结果错误");
					return "FAIL";
				}
				PreCollectionCallBackDto dto=new PreCollectionCallBackDto();
				dto.setOrderNo(orderid);
				dto.setTranFlowNo(yborderid);
				if("1".equals(status)){
					dto.setOrderStatus("true");
				}else{
					dto.setOrderStatus("false");
				}
				Boolean callResult=preCollectionCallBackServiceImpl.callBack(dto);
				if(callResult){
					return "SUCCESS";
				}
			}else{//代付
				 orderid= result.get("requestid");
				 yborderid= result.get("ybdrawflowid");
				 Boolean successCode=false;
				 if("SUCCESS".equals(status)){
					 successCode=true;
				 }
				 Boolean callResult=prepaidCallBackServiceImpl.callBack(orderid, successCode, yborderid);
				 if(callResult){
						return "SUCCESS";
				 }
			}
		}catch(Exception e){
			logger.error(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_COLLECT, "回调错误："+e.getMessage(),e,null);
		}
		return "FAIL";
	}
}
