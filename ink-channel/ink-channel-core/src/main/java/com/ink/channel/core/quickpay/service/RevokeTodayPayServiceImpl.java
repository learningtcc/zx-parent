package com.ink.channel.core.quickpay.service;

import org.springframework.stereotype.Service;

import com.ink.base.log.util.YinkerLogger;
import com.ink.channel.core.cnst.ChannelConstants;
import com.ink.channel.core.model.in.AsileOrderCancelIn;
import com.ink.channel.core.model.out.AsileOrderCancelOut;
import com.ink.channel.core.quickpay.Configuration;
import com.ink.channel.core.quickpay.QuickPayHttpUtil;
import com.ink.channel.core.quickpay.message.ErrorMsgContent;
import com.ink.channel.core.quickpay.message.MasMessage;
import com.ink.channel.core.quickpay.message.MasMessageBack;
import com.ink.channel.core.quickpay.message.TxnMsgContent;
import com.ink.channel.core.service.AsileOrderCancelService;
/**
 * 快钱撤销当天订单接口
 * @author Lenovo
 *
 */
@Service("revokeTodayPayService")
public class RevokeTodayPayServiceImpl implements AsileOrderCancelService{
	private YinkerLogger logger = YinkerLogger.getLogger(RevokeTodayPayServiceImpl.class);
	  
	@Override
	public AsileOrderCancelOut orderCancel(AsileOrderCancelIn asileOrderCancelIn) {
		try{
			logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.QUICKM_MAS_REVOKE_CODE,"接入快钱取消订单接口");
		    String refNumber=asileOrderCancelIn.getOrgTranFlow();
	        String externalRefNumber=asileOrderCancelIn.getOrderid();
	        String entryTime=asileOrderCancelIn.getEntryTime();
	        MasMessage msg = new MasMessage();
	        msg.setVersion("1.0");
	        TxnMsgContent content = new TxnMsgContent();
	        content.setTxnType("VTX");
	        content.setInteractiveStatus("TR1");
	        content.setAmount(asileOrderCancelIn.getAmount());
	        content.setMerchantId(Configuration.getInstance().getValue("merchantId"));
	        content.setTerminalId(Configuration.getInstance().getValue("terminalId"));
	        content.setEntryTime(entryTime);
	        content.setExternalRefNumber(externalRefNumber);
	        content.setRefNumber(refNumber);
	        content.setOrignalTxnType("PUR");
	        msg.setTxnMsgContent(content);
	        String url=Configuration.getInstance().getValue("unDoURL");
	        MasMessageBack msgBack = QuickPayHttpUtil.postXml(url, msg);
	        AsileOrderCancelOut out=new AsileOrderCancelOut();
	        if(msgBack.getTxnMsgContent() == null){
	        	ErrorMsgContent errorMsg = msgBack.getErrorMsgContent();
	        	out.setResCode(errorMsg.getErrorCode());
	        	out.setResMsg(errorMsg.getErrorMessage());
	        }else{
	        	TxnMsgContent txnMsg = msgBack.getTxnMsgContent();
	        	out.setResCode(txnMsg.getResponseCode());
	        	out.setResMsg(txnMsg.getResponseTextMessage());
	        }
	        String resMsg = (msgBack.getTxnMsgContent() == null) ? msgBack.getErrorMsgContent().toString() : msgBack.getTxnMsgContent().toString();
	        logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.QUICKM_MAS_REVOKE_CODE,"返回信息："+resMsg);
	        return out;
		}catch(Exception ex){
			logger.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.QUICKM_MAS_REVOKE_CODE,ex.getMessage(),ex,null);
        }
        logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.QUICKM_MAS_REVOKE_CODE,"结束快钱取消订单接口");
        return null;
	}
}
