package com.ink.channel.core.boofoopay.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.IdCodeGenerator;
import com.ink.channel.core.boofoopay.BooFooRequest;
import com.ink.channel.core.boofoopay.constant.BoofooConstant;
import com.ink.channel.core.boofoopay.request.BFPay2AccountReq;
import com.ink.channel.core.boofoopay.response.BFPay2AccountResp;
import com.ink.channel.core.cnst.ChannelConstants;
import com.ink.channel.core.enums.OrderStatus;
import com.ink.channel.core.enums.SystemCodeEnums;
import com.ink.channel.core.ldyspay.LdysConstant;
import com.ink.channel.core.model.in.AsilePayAccountIn;
import com.ink.channel.core.model.out.AsilePayAccountOut;
import com.ink.channel.core.service.AsilePay2AccountService;
import com.ink.channel.core.utils.Constants;


/**
 * 代收  （开通了短信验证服务就需要短信验证）
 * @author ZYC7-DZ-057
 *
 */
@Service("bFPay2AccountServiceImpl")
public class BFPay2AccountServiceImpl implements AsilePay2AccountService{
	private static YinkerLogger logger = YinkerLogger.getLogger(BFPay2AccountServiceImpl.class);
	@Autowired
    private IdCodeGenerator idCodeGenerator;
	@Override
	public AsilePayAccountOut payAccount(AsilePayAccountIn input) {
		AsilePayAccountOut out=new AsilePayAccountOut();
		try{
			logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.BFOO_PAY_ACCOUNT_CODE,"开始宝付代收方法");
			BFPay2AccountReq req=new BFPay2AccountReq();
			req.setTxn_sub_type("07");//交易子类取值:07
			String code = input.getBankShort();
	        for (int i = 0; i < Constants.BF_PAY_ACCOUNT_BANK_LIST.length; i++) {
	            if (input.getBankShort().toString().trim().contains(Constants.BF_PAY_ACCOUNT_BANK_LIST[i][1])) {
	                code = Constants.BF_PAY_ACCOUNT_BANK_LIST[i][0];
	            }
	        }
	        req.setBiz_type("0000");
			req.setPay_code(code);
			req.setAcc_no(input.getAccountNo());
			req.setId_card_type(input.getCertType());
			req.setId_card(input.getCertNo());
			req.setId_holder(input.getAccountName());
			req.setMobile(input.getPhoneNo());
			req.setTrans_id(input.getOrderNo());//商户订单号
			int asileAmount =input.getAmount().multiply(new BigDecimal(100)).intValue();
			req.setTxn_amt(asileAmount+"");//分为单位
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");//订单日期
			req.setTrade_date(sdf.format(input.getTradeDate()));
			req.setTrans_serial_no(idCodeGenerator.getId());//商户流水号
			
			BFPay2AccountResp resp=BooFooRequest.excuteRequest2Account(input.getMerchantNo(),req, BoofooConstant.PAY2ACCOUNT_URL, BFPay2AccountResp.class);
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
				String respCode = resp.getResp_code();
				if("0000".equals(respCode)){
					String amount = resp.getSucc_amt();
					double newAmount = new BigDecimal(amount).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP).doubleValue();
					out.setAmount(newAmount+"");
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
		logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.BFOO_PAY_ACCOUNT_CODE,"结束宝付代收方法");
		return null;
	}
}
