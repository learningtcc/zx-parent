package com.ink.channel.core.epro.service;

import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.springframework.stereotype.Service;

import com.ink.base.log.util.YinkerLogger;
import com.ink.channel.core.cnst.ChannelConstants;
import com.ink.channel.core.epro.ConfigurationUtil;
import com.ink.channel.core.epro.EproHttpClientUtils;
import com.ink.channel.core.model.in.AsileAuthorityInput;
import com.ink.channel.core.model.out.AsileAuthorityOutput;
import com.ink.channel.core.service.AsileAuthorityService;
/**
 * 易宝确定绑卡接口
 * @author Lenovo
 *
 */
@Service("eproAuthService")
public class ConfirmBindBankcardServiceImpl implements AsileAuthorityService {
	private YinkerLogger LOGGER = YinkerLogger.getLogger(ConfirmBindBankcardServiceImpl.class);

	/**
	 * 确定绑卡接口
	 */
    @Override
    public AsileAuthorityOutput authorize(AsileAuthorityInput authority) {
    	try {
	    	LOGGER.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.YEEPAY_PAY_FAS_SING_CODE,"进入易宝支付确定绑卡请求接口");
	        Map<String, String> params = new HashMap<String, String>();
	        params.put("requestid", authority.getReqId());//请求号
	        params.put("validatecode", authority.getValidCode());//短信验证码
	        String confirmBindBankcardURL = ConfigurationUtil.getConfirmBindBankcardURL();
	        LOGGER.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.YEEPAY_PAY_FAS_SING_CODE,"易宝确定绑卡confirmBindBankcardURL : " + confirmBindBankcardURL);
	        params.put("merchantaccount", ConfigurationUtil.getMerchantAccount());
	        Map<String, String> result = null;
	        AsileAuthorityOutput out=new AsileAuthorityOutput();
	        LOGGER.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.YEEPAY_PAY_FAS_SING_CODE,"易宝确定绑卡请求参数 : " + params.toString());
			try {
				result = EproHttpClientUtils.executePostMethod(confirmBindBankcardURL, params);
				 LOGGER.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.YEEPAY_PAY_FAS_SING_CODE,"易宝确定绑卡响应参数 : " + result.toString());
			//处理异常
			} catch (ConnectTimeoutException e) {
				out.setResMsg(ChannelConstants.REQUEST_EXCEPTION_MSG);
	            out.setResCode(ChannelConstants.REQUEST_EXCEPTION_CODE);
	            LOGGER.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.YEEPAY_PAY_FAS_SING_CODE,ChannelConstants.REQUEST_EXCEPTION_MSG,e,"");
	            return out;
			} catch (SocketTimeoutException e) {
				out.setResMsg(ChannelConstants.RESPONSE_EXCEPTION_MSG);
	            out.setResCode(ChannelConstants.RESPONSE_EXCEPTION_CODE);
	            LOGGER.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.YEEPAY_PAY_FAS_SING_CODE,ChannelConstants.RESPONSE_EXCEPTION_MSG,e,"");
	            return out;
			}
			//解析返回码
	        if(result.get("error_code")==null){
	            out.setResMsg(ChannelConstants.YEEPAY_SUCCESS_MSG);
	            out.setResCode(ChannelConstants.YEEPAY_SUCCESS_CODE);
	            out.setIdentityId(authority.getIdentityId());
	        }else{
	            out.setResMsg(result.get("error_msg"));
	            out.setResCode(result.get("error_code"));
	            out.setIdentityId(authority.getIdentityId());
	        }
	        return out;
    	}catch(Exception ex){
    		LOGGER.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.YEEPAY_PAY_FAS_SING_CODE,ex.getMessage(),ex,null);
    	}
    	LOGGER.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.YEEPAY_PAY_FAS_SING_CODE,"结束易宝支付确定绑卡请求接口");
    	return null;
    }
}
