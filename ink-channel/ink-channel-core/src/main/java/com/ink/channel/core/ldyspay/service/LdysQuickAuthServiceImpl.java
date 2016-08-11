package com.ink.channel.core.ldyspay.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.IdCodeGenerator;
import com.ink.channel.core.cnst.ChannelConstants;
import com.ink.channel.core.enums.SystemCodeEnums;
import com.ink.channel.core.ldyspay.LdysConstant;
import com.ink.channel.core.ldyspay.LdysMessEncryptUtils;
import com.ink.channel.core.model.in.AsileQuickAuthInput;
import com.ink.channel.core.model.out.AsileQuickAuthOutput;
import com.ink.channel.core.service.AsileQuickAuthService;
/**
 * 联动优势快捷支付首次发动短验接口
 * @author Lenovo
 *
 */
@Service("ldysQuickAuthService")
public class LdysQuickAuthServiceImpl  implements AsileQuickAuthService{
	
	private static YinkerLogger logger = YinkerLogger.getLogger(LdysQuickAuthServiceImpl.class);
	private static String[][] SUPPORT_ID_TYPE = {{ "IDENTITY_CARD", "01" }};
	
	@Autowired
	private IdCodeGenerator certificateUtil;
	
	@Override
	public AsileQuickAuthOutput quickAuth(AsileQuickAuthInput input) {
		logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.LDYS_QUICK_AUTH_PAY_CODE, "开始调用联动优势快捷支付首次发动短验接口");
		AsileQuickAuthOutput out=null;
		String  merchantNo=input.getMerchantNo();
		try {
			String asileNo = certificateUtil.getIpMapsConfig().get(LdysConstant.CHANNELID);//渠道号
			String mer_id = certificateUtil.getIpMapsConfig().get(merchantNo+asileNo+LdysConstant.MER_ID);//商户号
			Map<String,Object> map = new HashMap<>();  
			map.put("service","pay_req");//接口名称
			map.put("charset",LdysConstant.CHARSET); //参数字符编码集
			map.put("mer_id",mer_id);//商户编号
			map.put("sign_type",LdysConstant.SING_TYPE);//签名方式
			
			map.put("notify_url",certificateUtil.getIpMapsConfig().get(asileNo+LdysConstant.NOTIFY_URL));//服务器异步通知页面路径
			map.put("res_format",LdysConstant.RES_FORMAT);  //响应数据格式
			map.put("version",LdysConstant.VERSION);//版本号
			map.put("push_type","3"); //推送类型
			map.put("sms_type","0");//验证码类型
			map.put("goods_id","");// 商品号
			map.put("goods_inf","");//商品描述信息
			map.put("media_id",input.getPhoneNo()); //媒介标识手机号
			map.put("media_type",LdysConstant.MEDIA_TYPE);//媒介类型  默认MOBILE
			map.put("order_id",input.getOrderNo());// 商户唯一订单号
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
			map.put("mer_date",sdf.format(new Date())); //商户订单日期
			int asileAmount =new BigDecimal(input.getAmount()).multiply(new BigDecimal(100)).intValue();
			map.put("amount",asileAmount+"");//付款金额
			map.put("amt_type",LdysConstant.AMT_TYPE);//付款币种
			map.put("pay_type",LdysConstant.PAY_TYPE);//默认支付方式
			map.put("mer_priv","");//商户私有域
			map.put("user_ip","");//用户IP地址
			map.put("expand","");//业务扩展信息
			map.put("expire_time",LdysConstant.EXPRIE_TIME);//订单过期时长
			map.put("card_id",input.getCardNo());//卡号
			map.put("card_holder",input.getUserName());//持卡人姓名
			String idType="IDENTITY_CARD";
			for (int i = 0; i < SUPPORT_ID_TYPE.length; i++) {
	            if (input.getIdType().trim().contains(SUPPORT_ID_TYPE[i][1])) {
	            	idType=SUPPORT_ID_TYPE[i][0];
	            }
	        }
			map.put("identity_type",idType); // 证件类型
			map.put("identity_code",input.getIdNo());//证件号
			map.put("risk_expand","");//风控扩展信息
			logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.LDYS_QUICK_AUTH_PAY_CODE, "http请求参数:"+map.toString());
			Map<String, String> returnMap = LdysMessEncryptUtils.messEncrypt(map, input.getMerchantNo());
			logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.LDYS_QUICK_AUTH_PAY_CODE, "http响应结果:"+returnMap.toString());
			String exceptionCode = returnMap.get("exceptionCode");
			if(LdysConstant.decodeCode.equals(exceptionCode)){
				//解密异常
				out=new AsileQuickAuthOutput();
				out.setResCode(SystemCodeEnums.LDYS_FAILE_CODE.getCode());
				out.setResMsg("联动优势快捷首次支付发短验解密验签失败");
				out.setOrderNo(input.getOrderNo());
				logger.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.LDYS_QUICK_AUTH_PAY_CODE, "联动优势快捷首次支付发短验解密验签失败",null);
			}else{
				//无异常
				out=new AsileQuickAuthOutput();
				out.setResCode(returnMap.get("ret_code"));
				out.setResMsg(returnMap.get("ret_msg"));
				out.setOrderNo(input.getOrderNo());
				if("0000".equals(returnMap.get("ret_code"))){
					out.setToken(returnMap.get("token"));
					out.setOrgTranFlow(returnMap.get("trade_no"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			out=new AsileQuickAuthOutput();
			out.setResCode(SystemCodeEnums.LDYS_FAILE_CODE.getCode());
			out.setResMsg(SystemCodeEnums.LDYS_FAILE_CODE.getMsg());
			logger.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.LDYS_QUICK_AUTH_PAY_CODE, e.getMessage(),e,null);
		} 
		logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.LDYS_QUICK_AUTH_PAY_CODE, "结束调用联动优势快捷支付首次发动短验接口");
		return out;
	}

}
