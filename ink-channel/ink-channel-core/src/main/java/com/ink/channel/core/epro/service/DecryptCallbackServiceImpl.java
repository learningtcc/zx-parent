package com.ink.channel.core.epro.service;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.ink.base.log.util.YinkerLogger;
import com.ink.channel.core.cnst.ChannelConstants;
import com.ink.channel.core.epro.AES;
import com.ink.channel.core.epro.ConfigurationUtil;
import com.ink.channel.core.epro.EncryUtil;
import com.ink.channel.core.epro.RSA;
/**
 * 解密支付回调参数
 * @author Lenovo
 *
 */
@Service("decryptCallbackService")
public class DecryptCallbackServiceImpl {
	private YinkerLogger LOGGER = YinkerLogger.getLogger(BindBankCardServiceImpl.class);
	/**
     * decryptCallbackData() : 解密支付回调参数data
     *
     */
    public  Map<String, String> decryptCallbackData(String data, String encryptkey) {
    	try{
	        LOGGER.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.YEEPAY_PAY_ACCOUNT_CODE,"开始调用易宝回调解密参数");
	        String merchantPrivateKey = ConfigurationUtil.getMerchantPrivateKey();
	        String yeepayPublicKey = ConfigurationUtil.getYeepayPublicKey();
	        LOGGER.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.YEEPAY_PAY_ACCOUNT_CODE,"回调接口data : " + data);
	        LOGGER.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.YEEPAY_PAY_ACCOUNT_CODE,"回调接口encryptkey : " + encryptkey);
	        Map<String, String> callbackResult = new HashMap<String, String>();
	        String customError = "";
	        try {
	            boolean signMatch = EncryUtil.checkDecryptAndSign(data, encryptkey, yeepayPublicKey, merchantPrivateKey);
	            if (!signMatch) {
	                customError = "Sign not match error";
	                callbackResult.put("customError", customError);
	                return callbackResult;
	            }
	            String yeepayAESKey = RSA.decrypt(encryptkey, merchantPrivateKey);
	            String decryptData = AES.decryptFromBase64(data, yeepayAESKey);
	            callbackResult = JSON.parseObject(decryptData, new TypeReference<TreeMap<String, String>>() {
	            });
	        } catch (Exception e) {
	        	LOGGER.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.YEEPAY_PAY_ACCOUNT_CODE,"回调接口"+e.getMessage(),e,null);
	            e.printStackTrace();
	        }
	        LOGGER.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.YEEPAY_PAY_ACCOUNT_CODE,"回调接口callbackResult : " + callbackResult);
	        return (callbackResult);
        }catch(Exception ex){
    		LOGGER.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.YEEPAY_PAY_ACCOUNT_CODE,"回调接口"+ex.getMessage(),ex,null);
        }
        LOGGER.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.YEEPAY_PAY_ACCOUNT_CODE,"结束调用易宝回调解密参数");
    	return null;
    }
}
