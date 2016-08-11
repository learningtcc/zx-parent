package com.ink.channel.core.boofoopay.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.IdCodeGenerator;
import com.ink.channel.core.boofoopay.BooFooRequest;
import com.ink.channel.core.boofoopay.constant.BoofooConstant;
import com.ink.channel.core.boofoopay.request.BFValidationCodeReq;
import com.ink.channel.core.boofoopay.response.BFValidationCodeResp;
import com.ink.channel.core.cnst.ChannelConstants;
import com.ink.channel.core.model.in.AsileValidCodeInput;
import com.ink.channel.core.model.out.AsileValidCodeOutput;
import com.ink.channel.core.service.AsileValidateCodeService;

/**
 * 发送短验（绑卡、交易）
 * @author ZYC7-DZ-057
 *
 */
@Service("bFValidationCodeService")
public class BFValidationCodeServiceImpl implements AsileValidateCodeService{
	private static YinkerLogger logger = YinkerLogger.getLogger(BFValidationCodeServiceImpl.class);
	@Autowired
    private IdCodeGenerator idCodeGenerator;
	@Override
	public AsileValidCodeOutput getValidateCode(AsileValidCodeInput asileValidCodeInput) {
		try{
			logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.BFOO_PAY_VALIDATE_CODE,"开始调用宝付绑卡发短验接口");
			BFValidationCodeReq req=new BFValidationCodeReq();
			req.setTxn_sub_type("05");//交易子类型
			req.setTrans_serial_no(idCodeGenerator.getId());//订单号
			req.setTrans_id(asileValidCodeInput.getReqId());//流水号
			req.setAcc_no(asileValidCodeInput.getCardNo());//卡号
			req.setMobile(asileValidCodeInput.getPhoneNo());
			req.setNext_txn_sub_type("01");//下一步交易子类型
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
			req.setTrade_date(sdf.format(new Date()));
			BFValidationCodeResp resp=BooFooRequest.excuteRequest2Account(asileValidCodeInput.getMerchantNo(),req, BoofooConstant.VALIDATION_CODE, BFValidationCodeResp.class);
			AsileValidCodeOutput out=new AsileValidCodeOutput();
			if(resp==null){
				return null;
			}
			out.setResCode(resp.getResp_code());
			out.setResMsg(resp.getResp_msg());
			out.setIdentityId(asileValidCodeInput.getIdentityid());
			out.setReqId(asileValidCodeInput.getReqId());
			logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.BFOO_PAY_VALIDATE_CODE,resp.toString());
			return out;
		}catch(Exception ex){
			logger.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.BFOO_PAY_VALIDATE_CODE,ex.getMessage(),ex,"");
		}
		logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.BFOO_PAY_VALIDATE_CODE,"结束调用宝付绑卡发短验接口");
		return null;
	}
}
