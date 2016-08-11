package com.ink.channel.core.boofoopay.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.IdCodeGenerator;
import com.ink.channel.core.boofoopay.BooFooRequest;
import com.ink.channel.core.boofoopay.constant.BoofooConstant;
import com.ink.channel.core.boofoopay.request.QueryBFPay2AccountReq;
import com.ink.channel.core.boofoopay.response.QueryBFPay2AccountResp;
import com.ink.channel.core.cnst.ChannelConstants;
import com.ink.channel.core.enums.OrderStatus;
import com.ink.channel.core.enums.SystemCodeEnums;
import com.ink.channel.core.model.in.AsileQueryPayAccountIn;
import com.ink.channel.core.model.out.AsileQueryPayAccountOut;
import com.ink.channel.core.service.AsilePay2AccountQueryService;
/**
 * 宝付认证支付查询
 * @author Lenovo
 *
 */
@Service("queryBFAuthenPayService")
public class QueryBFAuthenPayServiceImpl implements AsilePay2AccountQueryService{
	private static YinkerLogger logger = YinkerLogger.getLogger(QueryBFAuthenPayServiceImpl.class);
	@Autowired
    private IdCodeGenerator idCodeGenerator;
	@Override
	public AsileQueryPayAccountOut queryPayOrder(AsileQueryPayAccountIn queryIn) {
		AsileQueryPayAccountOut out=new AsileQueryPayAccountOut();
		try{
			logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.BFOO_QUERY_PAY_ACCOUNT_CODE,"开始调用宝付认证支付查询接口");
			QueryBFPay2AccountReq req=new QueryBFPay2AccountReq();
			req.setTxn_sub_type("06");//交易子类取值:06
			req.setTrans_serial_no(idCodeGenerator.getId());//商户流水号
			req.setOrig_trans_id(queryIn.getOrderNo());// 原始商户订单号
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
			req.setTrade_date(sdf.format(new Date()));//订单日期
			QueryBFPay2AccountResp resp=BooFooRequest.excuteRequest2Account(queryIn.getMerchantNo(),req, BoofooConstant.QUERYRZPAY_URL, QueryBFPay2AccountResp.class);
			if(resp==null){
				out.setResCode(SystemCodeEnums.BF_FAILE_CODE.getCode());
				out.setResMsg(SystemCodeEnums.BF_FAILE_CODE.getMsg());
				return out;
			}
			logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.BFOO_QUERY_PAY_ACCOUNT_CODE,resp.toString());
			out.setResCode(resp.getResp_code());
			out.setResMsg(resp.getResp_msg());
			out.setOrderNo(resp.getTrans_serial_no());
			out.setOrgTranFlow(resp.getTrans_no());
			out.setAmount(resp.getSucc_amt());
			out.setTradeDate(resp.getTrade_date());
			if("0000".equals(resp.getResp_code())){
				if(StringUtils.isNoneBlank(resp.getSucc_amt())){
					out.setOrderStatus(OrderStatus.SUCCESS_CODE.getCode());//成功
				}else{
					out.setOrderStatus(OrderStatus.FAILE_CODE.getCode());//失败
				}
			}else{
				out.setOrderStatus(OrderStatus.PROCESSES_CODE.getCode());//处理中
			}
		}catch(Exception ex){
			logger.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.BFOO_QUERY_PAY_ACCOUNT_CODE,ex.getMessage(),ex,"");
			out.setResCode(SystemCodeEnums.BF_FAILE_CODE.getCode());
	        out.setResMsg(SystemCodeEnums.BF_FAILE_CODE.getMsg());
	        out.setOrderStatus(OrderStatus.PROCESSES_CODE.getCode());//失败
		}
		logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.BFOO_QUERY_PAY_ACCOUNT_CODE,"结束调用宝付认证支付查询接口");
		return out;
	}

}
