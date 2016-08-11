package com.ink.channel.core.jdPay.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.IdCodeGenerator;
import com.ink.channel.core.cnst.ChannelConstants;
import com.ink.channel.core.enums.SystemCodeEnums;
import com.ink.channel.core.jdPay.util.JDConstant;
import com.ink.channel.core.jdPay.util.JDVerifyInfoUtils;
import com.ink.channel.core.ldyspay.LdysConstant;
import com.ink.channel.core.model.in.AsileAuthorityInput;
import com.ink.channel.core.model.out.AsileAuthorityOutput;
import com.ink.channel.core.service.AsileAuthorityService;
/**
 * 京东四要素验证 确认接口
 * @author Lenovo
 *
 */
@Service("jDVerifyInfoAffirmService")
public class JDVerifyInfoAffirmServiceImpl implements AsileAuthorityService{
	
	private static YinkerLogger logger = YinkerLogger.getLogger(JDVerifyInfoAffirmServiceImpl.class);
	
	@Autowired
	private IdCodeGenerator certificateUtil;
	
	@Override
	public AsileAuthorityOutput authorize(AsileAuthorityInput input) {
		AsileAuthorityOutput out=null;
		try {
			String merchantNo=input.getMerchantNo();
			String asileNo=certificateUtil.getIpMapsConfig().get(JDConstant.Asile_NO);//渠道号
			//String merchantCode = certificateUtil.getIpMapsConfig().get(JDConstant.merchantNo);//商户号"300290"22907856
			String affirmUrl = certificateUtil.getIpMapsConfig().get(merchantNo+asileNo+JDConstant.VERIFYINFO_AFFIRM_URL);;
			JSONObject json = new JSONObject();
			json.put("token", input.getToken());//
			json.put("realName", input.getUserName());//姓名
			json.put("idNumber", input.getIdNo());//身份证件号
			json.put("cardPan", input.getCardNo());//银行卡号
			json.put("cardType", "D");//借贷标示信用卡“C”,借记卡“D”
			json.put("bankCode", input.getBankShort());//发卡行
			json.put("mobile", input.getPhoneNo());//银行预留手机号
			json.put("verfyCode", input.getValidCode());//验证码
			logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.JD_QUICK_AGAIN_PAY_CODE, "京东四要素确认验证http请求参数:"+json.toString());
			Map<String, String> resultMap = JDVerifyInfoUtils.doPost(affirmUrl,json.toJSONString(),merchantNo);
			logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.JD_QUICK_AGAIN_PAY_CODE, "京东四要素确认验证http请求参数:"+resultMap.toString());
			String exceptionCode = resultMap.get("exceptionCode");
			if(LdysConstant.decodeCode.equals(exceptionCode)){
				out=new AsileAuthorityOutput();
				out.setResCode(SystemCodeEnums.JD_FAILE_CODE.getCode());
				out.setResMsg("京东四要素验证解密验签失败");
				logger.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.JD_QUICK_AGAIN_PAY_CODE, "京东四要素验证解密验签失败",null);
			}else{
				out=new AsileAuthorityOutput();
				if("000".equals(resultMap.get("resultCode"))){
					out.setResCode(SystemCodeEnums.JD_SUCCESS_CODE.getCode());
				}else{
					out.setResCode(resultMap.get("resultCode"));
				}
				out.setResMsg(resultMap.get("resultInfo"));
				out.setIdentityId(resultMap.get("userCode"));
			}
		} catch (Exception e) {
			out=new AsileAuthorityOutput();
			out.setResCode(SystemCodeEnums.JD_FAILE_CODE.getCode());
			out.setResMsg(SystemCodeEnums.JD_FAILE_CODE.getMsg());
			logger.error(ChannelConstants.LOGGER_MODULE_NAME,"渠道异常",e);
		}
		return out;
	}
	
}
