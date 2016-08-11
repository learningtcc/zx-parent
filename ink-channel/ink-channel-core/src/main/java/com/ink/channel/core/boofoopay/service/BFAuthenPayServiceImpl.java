package com.ink.channel.core.boofoopay.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.IdCodeGenerator;
import com.ink.channel.core.boofoopay.BooFooRequest;
import com.ink.channel.core.boofoopay.constant.BoofooConstant;
import com.ink.channel.core.boofoopay.request.BFAuthenPayReq;
import com.ink.channel.core.boofoopay.response.BFAuthenPayResp;
import com.ink.channel.core.cnst.ChannelConstants;
import com.ink.channel.core.enums.OrderStatus;
import com.ink.channel.core.enums.SystemCodeEnums;
import com.ink.channel.core.ldyspay.LdysConstant;
import com.ink.channel.core.model.in.AsileAuthenPayInput;
import com.ink.channel.core.model.out.AsileAuthenPayOutput;
import com.ink.channel.core.service.AsileAuthenPayService;

/**
 * 宝付认证支付 不发短验
 * @author Lenovo
 *
 */
@Service("bFAuthenPayService")
public class BFAuthenPayServiceImpl implements AsileAuthenPayService{
	private static YinkerLogger logger = YinkerLogger.getLogger(BFAuthenPayServiceImpl.class);
	@Autowired
    private IdCodeGenerator idCodeGenerator;
	@Override
	public AsileAuthenPayOutput authenPay(AsileAuthenPayInput input) {
		AsileAuthenPayOutput out=new AsileAuthenPayOutput();
		try{
			logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.BFOO_PAY_ACCOUNT_CODE,"开始宝付认证支付绑卡不发短验方法");
			BFAuthenPayReq req=new BFAuthenPayReq();
			req.setTxn_sub_type("04");//交易子类取值:04
			req.setTrans_serial_no(idCodeGenerator.getId());//商户流水号
			req.setTrans_id(input.getOrderNo());//商户流水号
			req.setBind_id(input.getIdentityid());//绑定标识号
			int asileAmount =input.getAmount().multiply(new BigDecimal(100)).intValue();
			req.setTxn_amt(asileAmount+"");//交易金额分为单位
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");//订单日期
			req.setTrade_date(sdf.format(input.getTradeDate()));
			BFAuthenPayResp resp=BooFooRequest.excuteRequest2Account(input.getMerchantNo(),req, BoofooConstant.RZPAY_URL, BFAuthenPayResp.class);
			if(resp==null){
				out.setResCode(SystemCodeEnums.BF_FAILE_CODE.getCode());
				out.setResMsg(SystemCodeEnums.BF_FAILE_CODE.getMsg());
				return out;
			}
			logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.BFOO_PAY_ACCOUNT_CODE,resp.toString());
			
			out.setResCode(resp.getResp_code());
			out.setResMsg(resp.getResp_msg());
			out.setOrderNo(input.getOrderNo());
			out.setOrgTranFlow(resp.getTrans_serial_no());
			try{
				String amount = resp.getSucc_amt();
				double newAmount = new BigDecimal(amount).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP).doubleValue();
				out.setAmount(newAmount+"");
				String respCode = resp.getResp_code();
				if("0000".equals(respCode)){
					out.setOrderStatus(OrderStatus.SUCCESS_CODE.getCode());
				}else if(LdysConstant.decodeCode.equals(respCode)){
					out.setOrderStatus(OrderStatus.PROCESSES_CODE.getCode());//处理中 
		        }else if(ChannelConstants.RESPONSE_EXCEPTION_CODE.equals(respCode)){
		        	out.setOrderStatus(OrderStatus.PROCESSES_CODE.getCode());//处理中
		        }else{
		        	out.setOrderStatus(OrderStatus.FAILE_CODE.getCode());//失败 
		        }
			}catch(Exception ex){
				out.setOrderStatus(OrderStatus.PROCESSES_CODE.getCode());//处理中
			}
			return out;
		}catch(Exception ex){
			logger.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.BFOO_PAY_ACCOUNT_CODE,ex.getMessage(),ex,"");
			 out.setResCode(SystemCodeEnums.BF_FAILE_CODE.getCode());
	         out.setResMsg(SystemCodeEnums.BF_FAILE_CODE.getMsg());
	         out.setOrderStatus(OrderStatus.FAILE_CODE.getCode());//失败 
		}
		logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.BFOO_PAY_ACCOUNT_CODE,"结束宝付认证支付绑卡不发短验方法");
		return null;
	}
}
