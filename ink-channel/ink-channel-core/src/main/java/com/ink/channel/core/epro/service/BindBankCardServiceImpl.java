package com.ink.channel.core.epro.service;

import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.springframework.stereotype.Service;

import com.ink.base.log.util.YinkerLogger;
import com.ink.channel.core.cnst.ChannelConstants;
import com.ink.channel.core.epro.Configuration;
import com.ink.channel.core.epro.ConfigurationUtil;
import com.ink.channel.core.epro.EproHttpClientUtils;
import com.ink.channel.core.model.in.AsileValidCodeInput;
import com.ink.channel.core.model.out.AsileValidCodeOutput;
import com.ink.channel.core.service.AsileValidateCodeService;
/**
 * 易宝绑卡请求接口发短验
 * @author Lenovo
 *
 */
@Service("eproValidCodeService")
public class BindBankCardServiceImpl implements AsileValidateCodeService {
	private YinkerLogger logger = YinkerLogger.getLogger(BindBankCardServiceImpl.class);
	/**
	 * 绑卡接口
	 */
    @Override
    public AsileValidCodeOutput getValidateCode(AsileValidCodeInput authority) {
    	try{
	    	logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.YEEPAY_PAY_VALIDATE_CODE,"进入易宝支付绑卡请求接口");
	        Map<String, String> params = new HashMap<String, String>();
	        String identitytype= Configuration.getInstance().getValue("identitytype");
	        //证件类型固定值
	        String idCardType=Configuration.getInstance().getValue("idcardtype");
	        //ip地址
	        String userIp=Configuration.getInstance().getValue("userip");
	        params.put("identityid", authority.getIdentityid());//用户标识
	        params.put("requestid", authority.getReqId());//请求id
	        params.put("cardno", authority.getCardNo());//银行卡号
	        params.put("idcardno", authority.getIdNo());//证件号
	        params.put("username", authority.getUserName());//持卡人姓名
	        params.put("phone", authority.getPhoneNo());//银行预留手机号
	        params.put("identitytype", identitytype);//用户标识类型 0： IMEI 1： MAC 地址 2：用户 ID 3：用户 Email 4：用户手机号 5：用户身份证号6：用户纸质订单协议号
	        params.put("idcardtype", idCardType);//证件类型
	        params.put("userip", userIp);//用户ip
	        String bindBankcardURL = ConfigurationUtil.getBindBankcardURL();
	        params.put("merchantaccount", ConfigurationUtil.getMerchantAccount());//商户号
	        Map<String, String> result=null;
	        AsileValidCodeOutput out=  new AsileValidCodeOutput();
	        out.setIdentityId(authority.getIdentityid());
			out.setReqId(authority.getReqId());
			logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.YEEPAY_PAY_VALIDATE_CODE,"易宝支付绑卡请求参数："+params.toString());
			try {
				result = EproHttpClientUtils.executePostMethod(bindBankcardURL, params);
				logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.YEEPAY_PAY_VALIDATE_CODE,"易宝支付绑卡响应参数："+result.toString());
			} catch (ConnectTimeoutException e) {
				out.setResMsg(ChannelConstants.REQUEST_EXCEPTION_MSG);
	            out.setResCode(ChannelConstants.REQUEST_EXCEPTION_CODE);
	            logger.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.YEEPAY_PAY_VALIDATE_CODE,ChannelConstants.REQUEST_EXCEPTION_MSG,e,"");
	            return out;
			} catch (SocketTimeoutException e) {
				out.setResMsg(ChannelConstants.RESPONSE_EXCEPTION_MSG);
	            out.setResCode(ChannelConstants.RESPONSE_EXCEPTION_CODE);
	            logger.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.YEEPAY_PAY_VALIDATE_CODE,ChannelConstants.RESPONSE_EXCEPTION_MSG,e,"");
	            return out;
			}
	        if(result.get("error_code")==null){
	            out.setResMsg(ChannelConstants.YEEPAY_SUCCESS_MSG);
	            out.setResCode(ChannelConstants.YEEPAY_SUCCESS_CODE);
	        }else{
	            out.setResMsg(result.get("error_msg"));
	            out.setResCode(result.get("error_code"));
	        }
	        return out;
        }catch(Exception ex){
        	logger.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.YEEPAY_PAY_VALIDATE_CODE,ex.getMessage(),ex,"");
        }
    	logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.YEEPAY_PAY_VALIDATE_CODE,"结束易宝支付绑卡请求接口");
    	return null;
    }
}
