package com.ink.channel.core.quickpay.service;

import org.springframework.stereotype.Service;

import com.ink.base.log.util.YinkerLogger;
import com.ink.channel.core.cnst.ChannelConstants;
import com.ink.channel.core.model.in.AsileQuickAuthInput;
import com.ink.channel.core.model.out.AsileQuickAuthOutput;
import com.ink.channel.core.quickpay.Configuration;
import com.ink.channel.core.quickpay.QuickPayHttpUtil;
import com.ink.channel.core.quickpay.message.GetDynNumContent;
import com.ink.channel.core.quickpay.message.MasMessage;
import com.ink.channel.core.quickpay.message.MasMessageBack;
import com.ink.channel.core.service.AsileQuickAuthService;
import com.ink.channel.core.utils.Constants;
/**
 * 快钱快捷支付鉴权服务接口实现类
 * @author huohb
 *
 */
@Service("masQuickAuthServiceImpl")
public class MasQuickAuthServiceImpl implements AsileQuickAuthService {
	
	private static YinkerLogger logger = YinkerLogger.getLogger(MasQuickAuthServiceImpl.class);
	/**
	 * 快捷支付鉴权
	 */
	@Override
	public AsileQuickAuthOutput quickAuth(AsileQuickAuthInput input) {
		try{
			logger.info(ChannelConstants.LOGGER_MODULE_NAME, ChannelConstants.QUICKM_AUTH_QUICK_PAY_CODE,"进入快钱快捷支付鉴权接口，订单号：" + input.getOrderNo());
			String merchantId = Configuration.getInstance().getValue("merchantId");
	        String bankName ="";
	        MasMessage msg = new MasMessage();
	        msg.setVersion("1.0");//版本号
	        GetDynNumContent req = new GetDynNumContent();
	        req.setMerchantId(merchantId);//商户号
	        req.setCustomerId(input.getPhoneNo());//客户号
	        req.setExternalRefNumber(input.getOrderNo());//外部跟踪编号
	        req.setCardHolderName(input.getUserName());//持卡人姓名
	        req.setIdType(input.getIdType());//证件类型
	        req.setCardHolderId(input.getIdNo());//持卡人身份证号
	        req.setPan(input.getCardNo());//卡号
	        for (int i = 0; i < Constants.MAS_QUICK_PAY_CARD_BANK_LIST.length; i++) {
	            if(Constants.MAS_QUICK_PAY_CARD_BANK_LIST[i][1].equals(input.getBankShort())){
	            	bankName = Constants.MAS_QUICK_PAY_CARD_BANK_LIST[i][0];
	            }
	        }
	        req.setBankId(bankName);//银行代码
	        req.setPhoneNO(input.getPhoneNo());//手机号码
	        req.setAmount(input.getAmount());//金额
	        
	        msg.setGetDynNumContent(req);
	        String url = Configuration.getInstance().getValue("quickAuth");
	        
	        MasMessageBack msgBack = QuickPayHttpUtil.postXml(url, msg);
	        
	        AsileQuickAuthOutput out = new AsileQuickAuthOutput();
	        if(msgBack.getGetDynNumContent() == null){
	        	out.setResCode(msgBack.getErrorMsgContent().getErrorCode());
	        	out.setResMsg(msgBack.getErrorMsgContent().getErrorMessage());
	        }else{
	        	out.setResCode(msgBack.getGetDynNumContent().getResponseCode());
	        	out.setResMsg("成功");
	        	out.setToken(msgBack.getGetDynNumContent().getToken());
	        }
			return out;
		}catch(Exception ex){
			logger.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.QUICKM_AUTH_QUICK_PAY_CODE,ex.getMessage(),ex,null);
        }
        logger.info(ChannelConstants.LOGGER_MODULE_NAME, ChannelConstants.QUICKM_AUTH_QUICK_PAY_CODE,"快钱快捷鉴权服务执行完毕，订单号：" + input.getOrderNo());
        return null;
	}

}
