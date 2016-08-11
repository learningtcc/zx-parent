package com.ink.channel.core.jdPay.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.IdCodeGenerator;
import com.ink.channel.core.cnst.ChannelConstants;
import com.ink.channel.core.enums.OrderStatus;
import com.ink.channel.core.enums.SystemCodeEnums;
import com.ink.channel.core.jdPay.enums.JDConstantEnums;
import com.ink.channel.core.jdPay.util.JDConstant;
import com.ink.channel.core.jdPay.util.JDHttpClientUtils;
import com.ink.channel.core.ldyspay.LdysConstant;
import com.ink.channel.core.model.in.AsileQuickPayInput;
import com.ink.channel.core.model.out.AsileQuickPayOutput;
import com.ink.channel.core.service.AsileQuickPayService;
/**
 * 网银在线快捷支付接口
 * @author Lenovo
 *
 */
@Service("jDQuickPayServiceImpl")
public class JDQuickPayServiceImpl implements AsileQuickPayService{
	private static YinkerLogger logger = YinkerLogger.getLogger(JDQuickPayServiceImpl.class);
	private static String[][] SUPPORT_ID_TYPE = {{ "I", "01" }};
	@Autowired
	private IdCodeGenerator idCodeGenerator;
	@Autowired
	private IdCodeGenerator certificateUtil;
	@Override
	public AsileQuickPayOutput pay(AsileQuickPayInput input) {
		logger.info(ChannelConstants.LOGGER_MODULE_NAME, ChannelConstants.JD_QUICK_PAY_CODE,"进入京东快捷支付，订单号：" + input.getOrderNo());
		AsileQuickPayOutput out=new AsileQuickPayOutput();
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
			params.put("card_idtype", idType);//持卡人证件类型
			params.put("card_idno", input.getIdNo());//持卡人证件号
			params.put("card_phone", input.getPhoneNo());//持卡人手机号
			params.put("trade_type", JDConstantEnums.TRADE_TYPE_S.getCode());//交易类型
			params.put("trade_id", input.getOrderNo());//交易号
			double asileAmount =input.getAmount().multiply(new BigDecimal(100)).doubleValue();
			params.put("trade_amount",asileAmount);//交易金额
			params.put("trade_currency", JDConstantEnums.CURRENCY.getCode());//交易币种
			Date date = new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
			params.put("trade_date", sdf.format(date));//交易日期
			SimpleDateFormat sdf2=new SimpleDateFormat("HHmmss");
			params.put("trade_time", sdf2.format(date));//交易时间
			String asile_no=certificateUtil.getIpMapsConfig().get(JDConstant.Asile_NO);
			params.put("trade_notice",certificateUtil.getIpMapsConfig().get(input.getMerchantNo()+asile_no+JDConstant.callBackUrl));//回调地址
			params.put("trade_code", input.getValidCode());//交易验证码
			
			logger.info(ChannelConstants.LOGGER_MODULE_NAME, ChannelConstants.JD_QUICK_PAY_CODE,"京东快捷支付首次支付HTTP请求参数："+params.toString());
			Map<String, Object> returnMap=JDHttpClientUtils.trade(params,input.getMerchantNo());
			logger.info(ChannelConstants.LOGGER_MODULE_NAME, ChannelConstants.JD_QUICK_PAY_CODE,"京东快捷支付首次支付HTTP响应结果："+returnMap.toString());

			String exceptionCode = (String) returnMap.get("exceptionCode");
			if(LdysConstant.decodeCode.equals(exceptionCode)){
				//解密异常
				out=new AsileQuickPayOutput();
				out.setResCode(SystemCodeEnums.JD_FAILE_CODE.getCode());
				out.setResMsg("京东快捷首次支付解密验签失败");
				out.setOrderNo(input.getOrderNo());
				out.setResStatus(OrderStatus.PROCESSES_CODE.getCode());//处理中
				logger.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.JD_QUICK_PAY_CODE, "京东快捷支付首次支付解密验签失败",null);
			}else{
				out.setResCode((String)returnMap.get("code"));
				out.setResMsg((String)returnMap.get("desc"));
				out.setOrgTranFlow(input.getOrderNo());
				out.setOrderNo(input.getOrderNo());
				String resCode = (String)returnMap.get("code");
				if(SystemCodeEnums.JD_SUCCESS_CODE.getCode().equals(resCode)){
					try{
						out.setIdentityId(idCodeGenerator.getId());
						out.setAmount((String)returnMap.get("amount"));
						String stat = (String)returnMap.get("status");
						if(StringUtils.isNotBlank(stat)){
							if(stat.equals("6")){
								out.setResStatus(OrderStatus.PROCESSES_CODE.getCode());//处理中
							}else if(stat.equals("7")){
								out.setResStatus(OrderStatus.FAILE_CODE.getCode());//失败
							}else{
								out.setResStatus(OrderStatus.SUCCESS_CODE.getCode());//成功
							}
						}
					}catch(Exception ex){
						out.setResStatus(OrderStatus.PROCESSES_CODE.getCode());//处理中
						logger.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.JD_QUICK_PAY_CODE, ex.getMessage(),ex,null);
					}
				}else if("0001".equals(resCode)){
					out.setResStatus(OrderStatus.PROCESSES_CODE.getCode());//处理中
				}else if(ChannelConstants.RESPONSE_EXCEPTION_CODE.equals(resCode)){
		        	out.setResStatus(OrderStatus.PROCESSES_CODE.getCode());//处理中
		        }else{
		        	out.setResStatus(OrderStatus.FAILE_CODE.getCode());//失败 
		        }
			}
		} catch (Exception e) {
			out.setResCode(SystemCodeEnums.JD_FAILE_CODE.getCode());
			out.setResMsg(SystemCodeEnums.JD_FAILE_CODE.getMsg());
			out.setResStatus(OrderStatus.PROCESSES_CODE.getCode());//失败
			logger.error(ChannelConstants.LOGGER_MODULE_NAME, ChannelConstants.JD_QUICK_PAY_CODE, "网银在线快捷支付首次支付"+e.getMessage(), e,null);
			e.printStackTrace();
		}
		return out;
	}
}
