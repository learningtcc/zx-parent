package com.ink.channel.core.jdPay.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ink.base.log.util.YinkerLogger;
import com.ink.channel.core.cnst.ChannelConstants;
import com.ink.channel.core.enums.SystemCodeEnums;
import com.ink.channel.core.jdPay.enums.JDConstantEnums;
import com.ink.channel.core.jdPay.util.JDHttpClientUtils;
import com.ink.channel.core.model.in.AsileQuickAgainValidCodeInput;
import com.ink.channel.core.model.out.AsileQuickAgainValidCodeOutput;
import com.ink.channel.core.service.AsileQuickAgainValidCodeService;
/**
 * 网银在线  快捷支付 再次支付发验证码
 * @author Lenovo
 *
 */
@Service("jDQuickAgainValidCodeServiceImpl")
public class JDQuickAgainValidCodeServiceImpl implements AsileQuickAgainValidCodeService{
	private static YinkerLogger logger = YinkerLogger.getLogger(JDQuickAgainValidCodeServiceImpl.class);
	private static String[][] SUPPORT_ID_TYPE = {{ "I", "01" }};
	@Override
	public AsileQuickAgainValidCodeOutput quickAgainValidCode(AsileQuickAgainValidCodeInput input) {
		logger.info(ChannelConstants.LOGGER_MODULE_NAME, ChannelConstants.JD_QUICK_AGAIN_VALID_CODE,"进入京东快捷鉴权再次支付发送短信验证码，订单号：" + input.getOrderNo());
		AsileQuickAgainValidCodeOutput out=new AsileQuickAgainValidCodeOutput();
		try {
			Map<String, Object> params=new HashMap<String, Object>();
			String code = input.getBankShort();
	        /*for (int i = 0; i < Constants.JD_QUICK_PAY_BANK_LIST.length; i++) {
	            if (input.getBankShort().toString().trim().contains(Constants.JD_QUICK_PAY_BANK_LIST[i][1])) {
	                code = Constants.JD_QUICK_PAY_BANK_LIST[i][0];
	            }
	        }*/
			params.put("card_bank", code);//持卡人支付卡号发卡行 
			params.put("card_type", JDConstantEnums.CAEDTYPE_D.getCode());//持卡人支付卡号卡类型
			params.put("card_no", input.getCardNo());//持卡人支付卡号
			params.put("card_exp", "");//持卡人信用卡有效期
			params.put("card_cvv2", "");//持卡人信用卡校验码
			params.put("card_name", input.getUserName());//持卡人姓名
			String idType="I";
			for (int i = 0; i < SUPPORT_ID_TYPE.length; i++) {
	            if (input.getIdType().trim().contains(SUPPORT_ID_TYPE[i][1])) {
	            	idType=SUPPORT_ID_TYPE[i][0];
	            }
	        }
			params.put("card_idtype",idType);//持卡人证件类型
			params.put("card_idno", input.getIdNo());//持卡人证件号
			params.put("card_phone", input.getPhoneNo());//持卡人手机号
			params.put("trade_type", JDConstantEnums.TRADE_TYPE_V.getCode());//交易类型
			params.put("trade_id",  input.getOrderNo());//交易号
			Integer asileAmount = new BigDecimal(input.getAmount()).multiply(new BigDecimal(100)).intValue();
			params.put("trade_amount", asileAmount.toString());//交易金额
			params.put("trade_currency", JDConstantEnums.CURRENCY.getCode());//交易币种
			
			logger.info(ChannelConstants.LOGGER_MODULE_NAME, ChannelConstants.JD_QUICK_AGAIN_VALID_CODE,"京东快捷支付再次支付发短验HTTP请求参数："+params.toString());
			Map<String, Object> returnMap=JDHttpClientUtils.trade(params,input.getMerchantNo());//
			logger.info(ChannelConstants.LOGGER_MODULE_NAME, ChannelConstants.JD_QUICK_AGAIN_VALID_CODE,"京东快捷支付再次支付发短验HTTP响应结果："+returnMap.toString());
			out.setResCode((String)returnMap.get("code"));
			out.setResMsg((String)returnMap.get("desc"));
			out.setOrderNo(input.getOrderNo());
			out.setOrgTranFlow(input.getOrderNo());
		} catch (Exception e) {
			out.setResCode(SystemCodeEnums.JD_FAILE_CODE.getCode());
			out.setResMsg(SystemCodeEnums.JD_FAILE_CODE.getMsg());
			logger.error(ChannelConstants.LOGGER_MODULE_NAME, ChannelConstants.JD_QUICK_AGAIN_VALID_CODE, "网银在线快捷支付再次支付发送短信验证码"+e.getMessage(), e,null);
			e.printStackTrace();
		}
		return out;
	}

}
