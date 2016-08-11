package com.ink.channel.core.boofoopay.service;

import org.springframework.stereotype.Service;

import com.ink.base.log.util.YinkerLogger;
import com.ink.channel.core.boofoopay.BooFooRequest;
import com.ink.channel.core.boofoopay.constant.BoofooConstant;
import com.ink.channel.core.boofoopay.request.BFPay2CardReq;
import com.ink.channel.core.boofoopay.response.BFPay2CardResp;
import com.ink.channel.core.cnst.ChannelConstants;
import com.ink.channel.core.enums.OrderStatus;
import com.ink.channel.core.enums.SystemCodeEnums;
import com.ink.channel.core.ldyspay.LdysConstant;
import com.ink.channel.core.model.in.AsilePay2CardInput;
import com.ink.channel.core.model.out.AsilePay2CardOutput;
import com.ink.channel.core.service.AsilePay2CardService;
import com.ink.channel.core.utils.Constants;

/***
 * payAuthenticationUrl 代付
 * @author ZYC7-DZ-057
 *
 */
@Service("bFPay2CardService")
public class BFPay2CardServiceImpl implements AsilePay2CardService{
	private static YinkerLogger logger = YinkerLogger.getLogger(BFPay2CardServiceImpl.class);
	AsilePay2CardOutput out=new AsilePay2CardOutput();
	@Override
	public AsilePay2CardOutput pay(AsilePay2CardInput input) {
		try{
			logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.BFOO_PAY_CARD_CODE,"开始调用宝付代付接口");
			BFPay2CardReq req = new BFPay2CardReq();
			req.setTrans_no(input.getOrderNo());  // ----订单号tranFlow
			req.setTrans_money(input.getAmount()+"");  //-----购买金额amount元为单位
			req.setTo_acc_name(input.getUserName()); // -----用户姓名realName
			req.setTo_acc_no(input.getCardNo()); // ----银行卡号cardNo   
			String bankName = null;
	        for (int i = 0; i < Constants.BF_PAY_CARD_BANK_LIST.length; i++) {
	            if(Constants.BF_PAY_CARD_BANK_LIST[i][1].equals(input.getBankShort())){
	            	bankName = Constants.BF_PAY_CARD_BANK_LIST[i][0];
	            }
	        }
			req.setTo_bank_name(bankName);  //---银行名称cardName
//			BFPay2CardResp jsonObject2=BooFooRequest.excuteRequest2Card(req, "payAuthenticationUrl",BFPay2CardResp.class);
			BFPay2CardResp jsonObject2=BooFooRequest.excuteRequest2Card(input.getMerchantNo(),req, BoofooConstant.PAYAUTHENTICATION_URL,BFPay2CardResp.class);
			if(jsonObject2==null){
				out.setResCode(SystemCodeEnums.BF_FAILE_CODE.getCode());
				out.setResMsg(SystemCodeEnums.BF_FAILE_CODE.getMsg());
				return out;
			}
			logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.BFOO_PAY_CARD_CODE,jsonObject2.getReturn_code());
			logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.BFOO_PAY_CARD_CODE,jsonObject2.getTrans_money());
			out.setResCode(jsonObject2.getReturn_code());
			out.setResMsg(jsonObject2.getReturn_msg());
			out.setOrgTranFlow(jsonObject2.getTrans_batchid());
			out.setOrderNo(input.getOrderNo());
			out.setAmount(jsonObject2.getTrans_money());//元为单位
	//		out.setReqTranFlow(jsonObject2.getTrans_no());
			String respCode = jsonObject2.getReturn_code();
			if("200".equals(respCode)){
				out.setOrderStatus(OrderStatus.SUCCESS_CODE.getCode());
			}else if("0300".equals(respCode)||"0999".equals(respCode)||"0401".equals(respCode)||"0000".equals(respCode)){
				out.setOrderStatus(OrderStatus.PROCESSES_CODE.getCode());//处理中 
			}else if(LdysConstant.decodeCode.equals(respCode)){
				out.setOrderStatus(OrderStatus.PROCESSES_CODE.getCode());//处理中 
	        }else if(ChannelConstants.RESPONSE_EXCEPTION_CODE.equals(respCode)){
	        	out.setOrderStatus(OrderStatus.PROCESSES_CODE.getCode());//处理中
	        }else{
	        	out.setOrderStatus(OrderStatus.FAILE_CODE.getCode());//失败 
	        }
			return out;
		}catch(Exception ex){
			 logger.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.BFOO_PAY_CARD_CODE,ex.getMessage(),ex,"");
			 out.setResCode(SystemCodeEnums.BF_FAILE_CODE.getCode());
	         out.setResMsg(SystemCodeEnums.BF_FAILE_CODE.getMsg());
	         out.setOrderStatus(OrderStatus.FAILE_CODE.getCode());//失败 
		}
		logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.BFOO_PAY_CARD_CODE,"结束调用宝付代付接口");
		return null;
	}
}
