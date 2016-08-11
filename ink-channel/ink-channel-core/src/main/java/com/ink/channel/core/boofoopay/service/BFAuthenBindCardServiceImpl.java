package com.ink.channel.core.boofoopay.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.IdCodeGenerator;
import com.ink.channel.core.boofoopay.BooFooRequest;
import com.ink.channel.core.boofoopay.constant.BoofooConstant;
import com.ink.channel.core.boofoopay.request.BFAuthenBindCardReq;
import com.ink.channel.core.boofoopay.response.BFAuthenBindCardResp;
import com.ink.channel.core.cnst.ChannelConstants;
import com.ink.channel.core.enums.SystemCodeEnums;
import com.ink.channel.core.model.in.AsileAuthenBindCardInput;
import com.ink.channel.core.model.out.AsileAuthenBindCardOutput;
import com.ink.channel.core.service.AsileAuthenBindCardService;
import com.ink.channel.core.utils.Constants;
/**
 * 宝付认证支付 绑卡接口
 * @author Lenovo
 *
 */
@Service("bFAuthenBindCardService")
public class BFAuthenBindCardServiceImpl implements AsileAuthenBindCardService{
	private static YinkerLogger logger = YinkerLogger.getLogger(BFAuthenBindCardServiceImpl.class);
	
	@Autowired
    private IdCodeGenerator idCodeGenerator;
	@Override
	public AsileAuthenBindCardOutput bindCard(AsileAuthenBindCardInput input) {
		AsileAuthenBindCardOutput out=new AsileAuthenBindCardOutput();
		try{
			logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.BFOO_PAY_FAS_SING_CODE,"开始调用宝付确认绑卡接口");
			BFAuthenBindCardReq bindBankCardReq=new BFAuthenBindCardReq();
			bindBankCardReq.setTxn_sub_type("01");//交易子类取值:01
			bindBankCardReq.setTrans_serial_no(idCodeGenerator.getId());//商户流水号
			bindBankCardReq.setTrans_id(input.getOrderNo());// 商户订单号
			bindBankCardReq.setId_card_type(input.getCertType());// 身份证类型
			bindBankCardReq.setAcc_no(input.getAccountNo());// 绑定卡号
			bindBankCardReq.setId_card(input.getCertNo());// 身份证号
			bindBankCardReq.setId_holder(input.getAccountName());//持卡人姓名
			bindBankCardReq.setBiz_type("0000");
			bindBankCardReq.setMobile(input.getPhoneNo());//银行卡绑定手机号
			//转换bankCode
			String bankCode = input.getBankShort();
	        for (int i = 0; i < Constants.BF_PAY_ACCOUNT_BANK_LIST.length; i++) {
	            if(Constants.BF_PAY_ACCOUNT_BANK_LIST[i][1].equals(input.getBankShort())){
	            	bankCode = Constants.BF_PAY_ACCOUNT_BANK_LIST[i][0];
	            }
	        }
			bindBankCardReq.setPay_code(bankCode);//银行编码
			//bindBankCardReq.setSms_code(authority.getValidCode());//验证码
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
			bindBankCardReq.setTrade_date(sdf.format(new Date()));
			BFAuthenBindCardResp resp=BooFooRequest.excuteRequest2Account(input.getMerchantNo(),bindBankCardReq, BoofooConstant.BINDCARD_URL, BFAuthenBindCardResp.class);
			
			if(resp==null){
				out.setResCode(SystemCodeEnums.BF_FAILE_CODE.getCode());
				out.setResMsg(SystemCodeEnums.BF_FAILE_CODE.getMsg());
				return out;
			}
			out.setResCode(resp.getResp_code());
			out.setResMsg(resp.getResp_msg());
			out.setIdentityid(resp.getBind_id());
			logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.BFOO_PAY_FAS_SING_CODE,resp.toString());
			return out;
		}catch(Exception ex){
			 logger.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.BFOO_PAY_FAS_SING_CODE,"宝付绑卡错误",ex,"");
			 out.setResCode(SystemCodeEnums.BF_FAILE_CODE.getCode());
	         out.setResMsg(SystemCodeEnums.BF_FAILE_CODE.getMsg());
		}
		logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.BFOO_PAY_FAS_SING_CODE,"结束调用宝付确认绑卡接口");
		return null;
	}
}
