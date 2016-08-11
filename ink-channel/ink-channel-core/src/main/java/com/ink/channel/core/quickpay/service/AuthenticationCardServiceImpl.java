package com.ink.channel.core.quickpay.service;

import org.springframework.stereotype.Service;

import com.ink.base.log.util.YinkerLogger;
import com.ink.channel.core.cnst.ChannelConstants;
import com.ink.channel.core.model.in.AsileAuthorityInput;
import com.ink.channel.core.model.out.AsileAuthorityOutput;
import com.ink.channel.core.quickpay.Configuration;
import com.ink.channel.core.quickpay.QuickPayHttpUtil;
import com.ink.channel.core.quickpay.message.IndAuthDynVerifyContent;
import com.ink.channel.core.quickpay.message.MasMessage;
import com.ink.channel.core.quickpay.message.MasMessageBack;
import com.ink.channel.core.service.AsileAuthorityService;
@Service("quickPayAuth")
public class AuthenticationCardServiceImpl implements AsileAuthorityService{
	private YinkerLogger LOGGER = YinkerLogger.getLogger(AuthenticationCardServiceImpl.class);
	
	/**
     * 快钱银行卡鉴权
     * 前置条件获取鉴权短信验证
     * @param xml
     * @return
     */
	
    @Override
    public AsileAuthorityOutput authorize(AsileAuthorityInput authority) {
        //String merchantId = "812310062110103";
        //String pan = "6226660205208024";
        //String phoneNO = "18211105930";
        //String externalRefNumber = "20160309114245acv433vca";
        //String customerId = "20160309114245888";
        //String token = "381158541";
        //String validCode = "450843";
    	try{
	    	LOGGER.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.QUICKM_PAY_FAS_SING_CODE,"进入快钱银行卡鉴权接口");
	        MasMessage msg = new MasMessage();
	        msg.setVersion("1.0");
	        IndAuthDynVerifyContent authBean = new IndAuthDynVerifyContent();
	        authBean.setMerchantId(Configuration.getInstance().getValue("merchantId"));//商户号
	        //authBean.setCustomerId(customerId);
	        authBean.setCustomerId(authority.getIdentityId());//客户号
	        //authBean.setExternalRefNumber(externalRefNumber);
	        authBean.setExternalRefNumber(authority.getReqId());//外部跟踪号
	        authBean.setPan(authority.getCardNo());//卡号
	        authBean.setPhoneNO(authority.getPhoneNo());//手机号
	        authBean.setValidCode(authority.getValidCode());//验证码
	        String token=authority.getToken();
	        /*try {
	            token=(String)baseRedis.getCache(authority.getReqId());//令牌
	        } catch (Exception e) {
	            e.printStackTrace();
	            LOGGER.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.QUICKM_PAY_FAS_SING_CODE,"validCode get cache from Redis error,requestId "+authority.getReqId(),"");
	            throw new RuntimeException("get cache from Redis error");
	        }*/
	        authBean.setToken(token);
	        msg.setIndAuthDynVerifyContent(authBean);
	        
	        String url = Configuration.getInstance().getValue("authURL");
	        MasMessageBack msgBack = QuickPayHttpUtil.postXml(url, msg);
	        AsileAuthorityOutput out=new AsileAuthorityOutput();
	        if(msgBack.getIndAuthDynVerifyContent() == null)
	        {
	            out.setResMsg(msgBack.getErrorMsgContent().getErrorMessage());
	            out.setResCode(msgBack.getErrorMsgContent().getErrorCode());
	        }else{
	            out.setResMsg(msgBack.getIndAuthDynVerifyContent().getResponseTextMessage());
	            out.setResCode(msgBack.getIndAuthDynVerifyContent().getResponseCode());
	            out.setIdentityId(authority.getIdentityId());
	        }
	        return out;
        }catch(Exception ex){
            LOGGER.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.QUICKM_PAY_FAS_SING_CODE,ex.getMessage(),ex,null);
        }
        LOGGER.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.QUICKM_PAY_FAS_SING_CODE,"结束快钱银行卡鉴权接口");
        return null;
    }
}
