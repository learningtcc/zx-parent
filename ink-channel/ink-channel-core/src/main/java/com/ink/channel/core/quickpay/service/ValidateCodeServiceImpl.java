package com.ink.channel.core.quickpay.service;


import org.springframework.stereotype.Service;

import com.ink.base.log.util.YinkerLogger;
import com.ink.channel.core.cnst.ChannelConstants;
import com.ink.channel.core.model.in.AsileValidCodeInput;
import com.ink.channel.core.model.out.AsileValidCodeOutput;
import com.ink.channel.core.quickpay.Configuration;
import com.ink.channel.core.quickpay.QuickPayHttpUtil;
import com.ink.channel.core.quickpay.message.IndAuthContent;
import com.ink.channel.core.quickpay.message.MasMessage;
import com.ink.channel.core.quickpay.message.MasMessageBack;
import com.ink.channel.core.service.AsileValidateCodeService;
/**
 * 快钱鉴权发送短验
 * @author Lenovo
 *
 */
@Service("quickPayValidCodeService")
public class ValidateCodeServiceImpl implements AsileValidateCodeService {
	private YinkerLogger logger = YinkerLogger.getLogger(ValidateCodeServiceImpl.class);
    @Override
    public AsileValidCodeOutput getValidateCode(AsileValidCodeInput authority) {
    	try{
            logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.QUICKM_PAY_VALIDATE_CODE,"开始调用快钱鉴权发短验接口");
	        String terminalId = Configuration.getInstance().getValue("terminalId");
	        MasMessage message = new MasMessage();
	        message.setVersion("1.0");
	        
	        IndAuthContent content = new IndAuthContent();
	        content.setMerchantId(Configuration.getInstance().getValue("merchantId"));
	        content.setTerminalId(terminalId);
	        content.setCustomerId(authority.getIdentityid());
	        content.setExternalRefNumber(authority.getReqId());
	        content.setPan(authority.getCardNo());
	        content.setCardHolderName(authority.getUserName());
	        
	        content.setIdType(getIdType(authority.getIdType().toString().trim()));
	        content.setCardHolderId(authority.getIdNo());
	        content.setPhoneNO(authority.getPhoneNo());
	        message.setIndAuthContent(content);
	        
	        String url = Configuration.getInstance().getValue("validateCode");
	        
	        MasMessageBack msgBack = QuickPayHttpUtil.postXml(url, message);
	        AsileValidCodeOutput out=new AsileValidCodeOutput();
	         out.setIdentityId(authority.getIdentityid());
			 out.setReqId(authority.getReqId());
	        if(msgBack.getErrorMsgContent()!=null){
	        	out.setResCode(msgBack.getErrorMsgContent().getErrorCode());
	            out.setResMsg(msgBack.getErrorMsgContent().getErrorMessage());
	        }else{
		    	 out.setResCode(msgBack.getIndAuthContent().getResponseCode());
		         out.setResMsg(msgBack.getIndAuthContent().getResponseTextMessage());
		        /*try {
	                baseRedis.setCache(authority.getReqId(), msgBack.getIndAuthContent().getToken(), 300);
	            } catch (Exception e) {
	                e.printStackTrace();
	                LOGGER.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.QUICKM_PAY_VALIDATE_CODE,"validCode put cache into Redis error,requestId "+authority.getReqId(),"");
	                throw new RuntimeException("put cache from Redis error");
	            }*/
		         out.setToken(msgBack.getIndAuthContent().getToken());
	        }
	        // msgBack.getIndAuthContent().getExternalRefNumber()
	        logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.QUICKM_PAY_VALIDATE_CODE,"返回信息："+msgBack.getIndAuthContent().toString());
	        return out;
    	}catch(Exception ex){
			logger.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.QUICKM_PAY_VALIDATE_CODE,ex.getMessage(),ex,null);
        }
        logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.QUICKM_PAY_VALIDATE_CODE,"结束快钱鉴权发短验接口");
        return null;
    }
    public String getIdType(String idType){
        String returnIdType="0";
        if(idType.trim().equals("01")){
            returnIdType="0";
        }else
        if(idType.trim().equals("03")){
            returnIdType="2";
        }else if(idType.trim().equals("06")){
            returnIdType="1";
        }else{
            returnIdType="3";
        }
        return returnIdType;
    }
}
