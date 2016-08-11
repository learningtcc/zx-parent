package com.ink.channel.core.boofoopay.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.IdCodeGenerator;
import com.ink.channel.core.boofoopay.BooFooRequest;
import com.ink.channel.core.boofoopay.constant.BoofooConstant;
import com.ink.channel.core.boofoopay.request.BFBindBankCardReq;
import com.ink.channel.core.boofoopay.response.BFBindBankCardResp;
import com.ink.channel.core.cnst.ChannelConstants;
import com.ink.channel.core.model.in.AsileAuthorityInput;
import com.ink.channel.core.model.out.AsileAuthorityOutput;
import com.ink.channel.core.service.AsileAuthorityService;
import com.ink.channel.core.utils.Constants;
/**
 * 宝付绑卡接口
 * @author Lenovo
 *
 */
@Service("bFBindBankCardService")
public class BFBindBankCardServiceImpl implements AsileAuthorityService{
	private static YinkerLogger logger = YinkerLogger.getLogger(BFBindBankCardServiceImpl.class);
	
	@Autowired
    private IdCodeGenerator idCodeGenerator;
	
	@Override
	public AsileAuthorityOutput authorize(AsileAuthorityInput authority) {
		try{
			logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.BFOO_PAY_FAS_SING_CODE,"开始调用宝付确认绑卡接口");
			BFBindBankCardReq bindBankCardReq=new BFBindBankCardReq();
			bindBankCardReq.setTxn_sub_type("01");//交易子类取值:01
			bindBankCardReq.setTrans_serial_no(idCodeGenerator.getId());//商户流水号
			bindBankCardReq.setTrans_id(authority.getReqId());// 商户订单号
			bindBankCardReq.setId_card_type(authority.getIdType());// 身份证类型
			bindBankCardReq.setAcc_no(authority.getCardNo());// 绑定卡号
			bindBankCardReq.setId_card(authority.getIdNo());// 身份证号
			bindBankCardReq.setId_holder(authority.getUserName());//持卡人姓名
			bindBankCardReq.setBiz_type("0000");
			bindBankCardReq.setMobile(authority.getPhoneNo());//银行卡绑定手机号
			//转换bankCode
			String bankCode = authority.getBankShort();;
	        for (int i = 0; i < Constants.BF_PAY_ACCOUNT_BANK_LIST.length; i++) {
	            if(Constants.BF_PAY_ACCOUNT_BANK_LIST[i][1].equals(authority.getBankShort())){
	            	bankCode = Constants.BF_PAY_ACCOUNT_BANK_LIST[i][0];
	            }
	        }
			bindBankCardReq.setPay_code(bankCode);//银行编码
			//bindBankCardReq.setSms_code(authority.getValidCode());//验证码
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
			bindBankCardReq.setTrade_date(sdf.format(new Date()));
			BFBindBankCardResp resp=BooFooRequest.excuteRequest2Account(authority.getMerchantNo(),bindBankCardReq, BoofooConstant.BINDCARD_URL, BFBindBankCardResp.class);
			AsileAuthorityOutput out=new AsileAuthorityOutput();
			if(resp==null){
				return null;
			}
			out.setResCode(resp.getResp_code());
			out.setResMsg(resp.getResp_msg());
			out.setIdentityId(resp.getBind_id());
			logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.BFOO_PAY_FAS_SING_CODE,resp.toString());
			return out;
		}catch(Exception ex){
			logger.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.BFOO_PAY_FAS_SING_CODE,"宝付绑卡错误",ex,"");
		}
		logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.BFOO_PAY_FAS_SING_CODE,"结束调用宝付确认绑卡接口");
		return null;
	}
}
