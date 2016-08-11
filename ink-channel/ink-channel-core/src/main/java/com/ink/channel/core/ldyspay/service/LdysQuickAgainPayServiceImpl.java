package com.ink.channel.core.ldyspay.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.IdCodeGenerator;
import com.ink.channel.core.cnst.ChannelConstants;
import com.ink.channel.core.enums.OrderStatus;
import com.ink.channel.core.enums.SystemCodeEnums;
import com.ink.channel.core.ldyspay.LdysConstant;
import com.ink.channel.core.ldyspay.LdysMessEncryptUtils;
import com.ink.channel.core.model.in.AsileQuickAgainPayInput;
import com.ink.channel.core.model.out.AsileQuickPayOutput;
import com.ink.channel.core.service.AsileQuickAgainPayService;
@Service("ldysQuickAgainPayService")
public class LdysQuickAgainPayServiceImpl implements AsileQuickAgainPayService{
	private static YinkerLogger logger = YinkerLogger.getLogger(LdysQuickAgainPayServiceImpl.class);
	private static String[][] SUPPORT_ID_TYPE = {{ "IDENTITY_CARD", "01" }};
	
	@Autowired
	private IdCodeGenerator idCodeGenerator;
	
	@Autowired
	private IdCodeGenerator certificateUtil;
	
	@Override
	public AsileQuickPayOutput againPay(AsileQuickAgainPayInput input) {
		logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.LDYS_QUICK_AGAIN_PAY_CODE, "开始调用联动优势再次支付接口");
		AsileQuickPayOutput out=null;
		String  merchantNo=input.getMerchantNo();
		try {
			String asileNo = certificateUtil.getIpMapsConfig().get(LdysConstant.CHANNELID);//渠道号
			String mer_id = certificateUtil.getIpMapsConfig().get(merchantNo+asileNo+LdysConstant.MER_ID);//商户号
			Map<String,Object> map = new HashMap<>();  
			map.put("service","pay_confirm");//接口名称
			map.put("charset",LdysConstant.CHARSET);//参数字符编码集
			map.put("mer_id",mer_id);//商户编号
			map.put("sign_type",LdysConstant.SING_TYPE);//签名方式暂只支持RSA必须大写
			map.put("res_format",LdysConstant.RES_FORMAT); // 响应数据格式
			map.put("version",LdysConstant.VERSION);// 版本号
			map.put("trade_no",input.getOrgTranFlow());// U付交易号
			map.put("pay_category",LdysConstant.PAY_CATEGORY);// 支付类别 02 借记卡
			map.put("verify_code",input.getValidCode());//验证码
			map.put("media_id",input.getPhoneNo());//  媒介值  用户在银行预留手机号码
			map.put("media_type",LdysConstant.MEDIA_TYPE);//媒介类型
			map.put("card_id",input.getCardNo());//  卡号
			String idType="IDENTITY_CARD";
			for (int i = 0; i < SUPPORT_ID_TYPE.length; i++) {
	            if (input.getIdType().trim().contains(SUPPORT_ID_TYPE[i][1])) {
	            	idType=SUPPORT_ID_TYPE[i][0];
	            }
	        }
			map.put("identity_type",idType);//  证件类型   IDENTITY_CARD（身份证）
			map.put("identity_code",input.getIdNo());// 证件号
			map.put("card_holder",input.getUserName());//  持卡人姓名
			map.put("user_ip","");// 用户IP地址
			logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.LDYS_QUICK_AGAIN_PAY_CODE, "联动优势http请求参数:"+map.toString());
			Map<String, String> returnMap = LdysMessEncryptUtils.messEncrypt(map, input.getMerchantNo());
			logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.LDYS_QUICK_AGAIN_PAY_CODE, "联动优势http响应结果:"+returnMap.toString());
			String exceptionCode = returnMap.get("exceptionCode");
			if(LdysConstant.decodeCode.equals(exceptionCode)){
				//解密异常
				out=new AsileQuickPayOutput();
				out.setResCode(SystemCodeEnums.LDYS_SUCCESS_CODE.getCode());
				out.setResMsg("联动优势再次支付解密验签失败");
				out.setOrderNo(input.getOrderNo());
				out.setResStatus(OrderStatus.PROCESSES_CODE.getCode());//处理中
				logger.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.LDYS_QUICK_AGAIN_PAY_CODE, "联动优势再次支付解密验签失败",null);
			}else{
				out=new AsileQuickPayOutput();
				out.setResCode(returnMap.get("ret_code"));
				out.setResMsg(returnMap.get("ret_msg"));
				out.setOrderNo(input.getOrderNo());
				if("0000".equals(returnMap.get("ret_code"))){
					try{
						String amount = returnMap.get("amount");
						double newAmount = new BigDecimal(amount).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP).doubleValue();  
						out.setAmount(newAmount+"");
						out.setIdentityId(idCodeGenerator.getId());
						out.setOrgTranFlow(returnMap.get("order_id"));
						String status = returnMap.get("trade_state");
						if("TRADE_SUCCESS".equals(status)){
							out.setResStatus(OrderStatus.SUCCESS_CODE.getCode());//成功
						}else if("WAIT_BUYER_PAY".equals(status)){
							out.setResStatus(OrderStatus.PROCESSES_CODE.getCode());//处理中
						}else{
							out.setResStatus(OrderStatus.FAILE_CODE.getCode());//失败
						}
					}catch(Exception e){
						out.setResStatus(OrderStatus.PROCESSES_CODE.getCode());//处理中
					}
				}else if(ChannelConstants.RESPONSE_EXCEPTION_CODE.equals(returnMap.get("ret_code"))){
					out.setResStatus(OrderStatus.PROCESSES_CODE.getCode());//处理中
				}else{
					out.setResStatus(OrderStatus.FAILE_CODE.getCode());//失败
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			out=new AsileQuickPayOutput();
			out.setResCode(SystemCodeEnums.LDYS_FAILE_CODE.getCode());
			out.setResMsg(SystemCodeEnums.LDYS_FAILE_CODE.getMsg());
			out.setOrderNo(input.getOrderNo());
			out.setResStatus(OrderStatus.PROCESSES_CODE.getCode());//处理中
			logger.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.LDYS_QUICK_AGAIN_PAY_CODE, e.getMessage(),e,null);
		}
		logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.LDYS_QUICK_AGAIN_PAY_CODE, "结束调用联动优势再次支付接口");
		return out;
	}

}
