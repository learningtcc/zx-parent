package com.ink.channel.core.boofoopay.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.ink.base.log.util.YinkerLogger;
import com.ink.channel.core.boofoopay.BooFooRequest;
import com.ink.channel.core.boofoopay.constant.BoofooConstant;
import com.ink.channel.core.boofoopay.request.QueryBFPay2CardReq;
import com.ink.channel.core.boofoopay.response.QueryBFPay2CardResq;
import com.ink.channel.core.cnst.ChannelConstants;
import com.ink.channel.core.enums.OrderStatus;
import com.ink.channel.core.enums.SystemCodeEnums;
import com.ink.channel.core.model.in.AsileQueryPayAccountIn;
import com.ink.channel.core.model.out.AsileQueryPayAccountOut;
import com.ink.channel.core.service.AsilePay2AccountQueryService;

/**
 * 代付查询queryPayAuthenticationUrl
 * @author ZYC7-DZ-057
 *
 */
@Service("queryBFPay2CardService")
public class QueryBFPay2CardServiceImpl implements AsilePay2AccountQueryService{
	private static YinkerLogger logger = YinkerLogger.getLogger(QueryBFPay2CardServiceImpl.class);
	@Override
	public AsileQueryPayAccountOut queryPayOrder(AsileQueryPayAccountIn input) {
		AsileQueryPayAccountOut out=new AsileQueryPayAccountOut();
		try{
			logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.BFOO_QUERY_PAY_CARD_CODE,"开始调用宝付查询代付接口");
			QueryBFPay2CardReq req=new QueryBFPay2CardReq();
			req.setTrans_batchid("");// ----流水号tranFlow
			req.setTrans_no(input.getOrderNo());//-----订单号
			QueryBFPay2CardResq jsonObject2=BooFooRequest.excuteRequest2Card(input.getMerchantNo(),req, BoofooConstant.QUERYPAYAUTHENTICATION_URL,QueryBFPay2CardResq.class);
			if(jsonObject2==null){
				out.setResCode(SystemCodeEnums.BF_FAILE_CODE.getCode());
				out.setResMsg(SystemCodeEnums.BF_FAILE_CODE.getMsg());
				return out;
			}
			logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.BFOO_QUERY_PAY_CARD_CODE,jsonObject2.getReturn_code());
			logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.BFOO_QUERY_PAY_CARD_CODE,jsonObject2.getTrans_money());
			
			out.setResCode(jsonObject2.getReturn_code());
			out.setResMsg(jsonObject2.getReturn_msg());
			String state = jsonObject2.getState();
			if(StringUtils.isNotBlank(state)){
				if(state.equals("0")){
					out.setOrderStatus(OrderStatus.PROCESSES_CODE.getCode());//处理中
				}else if(state.equals("1")){
					out.setOrderStatus(OrderStatus.SUCCESS_CODE.getCode());//成功
				}else{
					out.setOrderStatus(OrderStatus.FAILE_CODE.getCode());//失败
				}
			}else{
				out.setOrderStatus(OrderStatus.PROCESSES_CODE.getCode());//处理中
			}
			out.setAmount(jsonObject2.getTrans_money());
			return out;
		}catch(Exception ex){
			logger.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.BFOO_QUERY_PAY_CARD_CODE,ex.getMessage(),ex,"");
			out.setResCode(SystemCodeEnums.BF_FAILE_CODE.getCode());
	        out.setResMsg(SystemCodeEnums.BF_FAILE_CODE.getMsg());
	        out.setOrderStatus(OrderStatus.PROCESSES_CODE.getCode());//失败
		}
		logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.BFOO_QUERY_PAY_CARD_CODE,"结束调用宝付查询代付接口");
		return null;
	}
}
