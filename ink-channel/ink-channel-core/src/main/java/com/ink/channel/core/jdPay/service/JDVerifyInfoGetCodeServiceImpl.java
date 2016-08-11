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
import com.ink.channel.core.model.in.AsileValidCodeInput;
import com.ink.channel.core.model.out.AsileValidCodeOutput;
import com.ink.channel.core.service.AsileValidateCodeService;
/**
 * 京东四要素验证 下单和发送短验接口
 * @author Lenovo
 *
 */
@Service("jDVerifyInfoGetTokenService")
public class JDVerifyInfoGetCodeServiceImpl implements AsileValidateCodeService{
	private static YinkerLogger logger = YinkerLogger.getLogger(JDVerifyInfoGetCodeServiceImpl.class);
	
	@Autowired
	private IdCodeGenerator certificateUtil;
	
	@Override
	public AsileValidCodeOutput getValidateCode(AsileValidCodeInput input) {
		AsileValidCodeOutput out=new AsileValidCodeOutput();;
		try {
			// 1.基础请求数据
			String merchantNo=input.getMerchantNo();
			String asileNo=certificateUtil.getIpMapsConfig().get(JDConstant.Asile_NO);//渠道号
			String merchantCode = certificateUtil.getIpMapsConfig().get(merchantNo+asileNo+JDConstant.VERIFYINFO_MERCHANTNO);//商户号"300290"
			String accountId = certificateUtil.getIpMapsConfig().get(merchantNo+asileNo+JDConstant.VERIFYINFO_ACCOUNT_ID);//商户id
			String accountType = certificateUtil.getIpMapsConfig().get(merchantNo+asileNo+JDConstant.VERIFYINFO_ACCOUNT_TYPE);//商户类型
			String tokenUrl = certificateUtil.getIpMapsConfig().get(merchantNo+asileNo+JDConstant.VERIFYINFO_GETTOKEN_URL);//获取tokenUrl
			String codeUrl = certificateUtil.getIpMapsConfig().get(merchantNo+asileNo+JDConstant.VERIFYINFO_GETCODE_URL);;//获取验证码URL
			String version=certificateUtil.getIpMapsConfig().get(merchantNo+asileNo+JDConstant.VERIFYINFO_VERSION);//版本号
			//String charset = certificateUtil.getIpMapsConfig().get(merchantNo+asileNo+JDConstant.charset);
			JSONObject json = new JSONObject();
	    	json.put("merchantCode", merchantCode);
	    	json.put("version", version);
	    	json.put("accountId", accountId);
	    	json.put("accountType", accountType);
	        json.put("notifyURL", "");//可为空
	        json.put("extParam", "{}");//可为空
			logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.JD_QUICK_AGAIN_PAY_CODE, "京东四要素验证下单http请求参数:"+json.toString());
			Map<String, String> jdStr = JDVerifyInfoUtils.doPost(tokenUrl,json.toJSONString(),merchantNo);
			logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.JD_QUICK_AGAIN_PAY_CODE, "京东四要素验证下单http请求参数:"+json.toString());
			String exceptionCodeStr = jdStr.get("exceptionCode");
			if(LdysConstant.decodeCode.equals(exceptionCodeStr)){
				out.setResCode(SystemCodeEnums.JD_FAILE_CODE.getCode());
				out.setResMsg("京东四要素验证下单解密验签失败");
				logger.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.JD_QUICK_AGAIN_PAY_CODE, "京东四要素验证下单解密验签失败",null);
			}else{
				if("000".equals(jdStr.get("resultCode"))){
			        //String urlToken=URLEncoder.encode(jdStr.get("token"),charset);
			        String urlToken=jdStr.get("token");
			        logger.info(ChannelConstants.LOGGER_MODULE_NAME, "获取token=："+jdStr.toString());
			        JSONObject jsonCode = new JSONObject();
			        jsonCode.put("token", urlToken);//
			        jsonCode.put("realName", input.getUserName());//姓名
			        jsonCode.put("idNumber", input.getIdNo());//身份证件号
			        jsonCode.put("cardPan", input.getCardNo());//银行卡号
			        jsonCode.put("cardType", "D");//借贷标示信用卡“C”,借记卡“D”
			        jsonCode.put("bankCode", input.getBankShort());//发卡行
			        jsonCode.put("mobile", input.getPhoneNo());//银行预留手机号
			        jsonCode.put("cVV2", "");//
					//json.put("certType", "1");//
					//json.put("dsType", "1");//
					logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.JD_QUICK_AGAIN_PAY_CODE, "京东四要素验证发送短验http请求参数:"+json.toString());
					Map<String, String> resultCode = JDVerifyInfoUtils.doPost(codeUrl,jsonCode.toJSONString(),merchantNo);
					logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.JD_QUICK_AGAIN_PAY_CODE, "京东四要素验证发送短验http请求参数:"+resultCode.toString());
					String exceptionCode = resultCode.get("exceptionCode");
					if(LdysConstant.decodeCode.equals(exceptionCode)){
						out.setResCode(SystemCodeEnums.JD_FAILE_CODE.getCode());
						out.setResMsg("京东四要素验证发送短验解密验签失败");
						logger.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.JD_QUICK_AGAIN_PAY_CODE, "京东四要素验证发送短验解密验签失败",null);
						return out;
					}else{
						if("000".equals(resultCode.get("resultCode"))){
							out.setResCode(SystemCodeEnums.JD_SUCCESS_CODE.getCode());
						}else{
							out.setResCode(resultCode.get("resultCode"));
						}
						out.setIdentityId(resultCode.get("userCode"));
						out.setReqId(input.getReqId());
						out.setResCode(resultCode.get("resultCode"));
						out.setResMsg(resultCode.get("resultInfo"));
						out.setToken(urlToken);
					}
				}else{
					out.setResCode(SystemCodeEnums.JD_FAILE_CODE.getCode());
					out.setResMsg(SystemCodeEnums.JD_FAILE_CODE.getMsg());
				}
			}
		} catch (Exception e) {
			out.setResCode(SystemCodeEnums.JD_FAILE_CODE.getCode());
			out.setResMsg(SystemCodeEnums.JD_FAILE_CODE.getMsg());
			logger.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.JD_QUICK_AGAIN_PAY_CODE, "京东四要素验证异常",e,null);
		}
		return out;
	}
}
