package com.ink.channel.core.ldyspay.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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
import com.ink.channel.core.model.in.AsileQueryPayAccountIn;
import com.ink.channel.core.model.out.AsileQueryPayAccountOut;
import com.ink.channel.core.service.AsilePay2AccountQueryService;
/**
 * 联动优势 查询接口
 * @author Lenovo
 *
 */
@Service("ldysQuickQueryPayService")
public class LdysQuickQueryPayServiceImpl implements AsilePay2AccountQueryService{
	private static YinkerLogger logger = YinkerLogger.getLogger(LdysQuickQueryPayServiceImpl.class);
	@Autowired
	private IdCodeGenerator certificateUtil;
	@Override
	public AsileQueryPayAccountOut queryPayOrder(AsileQueryPayAccountIn input) {
		logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.LDYS_QUICK_QUERY,"开始调用联动优势查询接口");
		AsileQueryPayAccountOut out=null;
		String  merchantNo=input.getMerchantNo();
		try {
			String asileNo = certificateUtil.getIpMapsConfig().get(LdysConstant.CHANNELID);//渠道号
			String mer_id = certificateUtil.getIpMapsConfig().get(merchantNo+asileNo+LdysConstant.MER_ID);//商户号
			Map<String,Object> msgMap = new HashMap<>();  
			msgMap.put("service","mer_order_info_query");
			msgMap.put("sign_type",LdysConstant.SING_TYPE)  ;//签名方式
			msgMap.put("charset",LdysConstant.CHARSET)  ;//编码
			msgMap.put("res_format",LdysConstant.RES_FORMAT)  ;//响应类型
			msgMap.put("mer_id",mer_id)  ;//商户号
			msgMap.put("version",LdysConstant.VERSION)  ;//版本号
			msgMap.put("order_id",input.getOrderNo());//原订单号
			SimpleDateFormat sfd=new SimpleDateFormat("yyyyMMdd");
			msgMap.put("mer_date",sfd.format(input.getTradeDate())) ;//原订单时间
			//map.put("mer_date","20160229") ;//原订单时间
			Map<String, String> returnMap = LdysMessEncryptUtils.messEncrypt(msgMap, input.getMerchantNo());
			String exceptionCode = returnMap.get("exceptionCode");
			if (LdysConstant.decodeCode.equals(exceptionCode)){
				//解密异常
				out=new AsileQueryPayAccountOut();
				out.setResCode(SystemCodeEnums.LDYS_FAILE_CODE.getCode());
				out.setResMsg("联动优势查询接口解密失败");
				out.setOrderNo(input.getOrderNo());
				out.setOrderStatus(OrderStatus.PROCESSES_CODE.getCode());//处理中
				logger.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.LDYS_QUICK_QUERY, "联动优势查询接口解密失败",null);
			}else{
				out=new AsileQueryPayAccountOut();
				out.setResCode(returnMap.get("ret_code"));
				out.setResMsg(returnMap.get("ret_msg"));
				out.setOrderNo(input.getOrderNo());
				out.setOrgTranFlow(input.getOrderNo());
				if("0000".equals(returnMap.get("ret_code"))){
					try{
						String amount = returnMap.get("amount");
						double newAmount = new BigDecimal(amount).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP).doubleValue();  
						out.setAmount(newAmount+"");
						String status = returnMap.get("trade_state");
						logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.LDYS_QUICK_QUERY,"联动优势订单："+input.getOrderNo()+"返回状态："+status);
						if("TRADE_SUCCESS".equals(status)){
							out.setOrderStatus(OrderStatus.SUCCESS_CODE.getCode());//成功
						}else if("WAIT_BUYER_PAY".equals(status)){
							out.setOrderStatus(OrderStatus.PROCESSES_CODE.getCode());//处理中
						}else{
							out.setOrderStatus(OrderStatus.FAILE_CODE.getCode());//失败
						}
					}catch(Exception e){
						out.setOrderStatus(OrderStatus.PROCESSES_CODE.getCode());//处理中
					}
				}else{
					out.setOrderStatus(OrderStatus.PROCESSES_CODE.getCode());//处理中
				}
			}
		}catch(Exception e){
        	out=new AsileQueryPayAccountOut();
			out.setResCode(SystemCodeEnums.LDYS_FAILE_CODE.getCode());
			out.setResMsg(SystemCodeEnums.LDYS_FAILE_CODE.getMsg());
			out.setOrderNo(input.getOrderNo());
			logger.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.LDYS_QUICK_QUERY, e.getMessage(),e,null);
		}
		logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.LDYS_QUICK_QUERY,"结束调用联动优势查询接口");
		return out;
	}

}
