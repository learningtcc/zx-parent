package com.ink.channel.core.quickpay.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.ink.base.log.util.YinkerLogger;
import com.ink.channel.core.cnst.ChannelConstants;
import com.ink.channel.core.model.in.AsileQueryPayAccountIn;
import com.ink.channel.core.model.out.AsileQueryPayAccountOut;
import com.ink.channel.core.quickpay.Configuration;
import com.ink.channel.core.quickpay.QuickPayHttpUtil;
import com.ink.channel.core.quickpay.message.ErrorMsgContent;
import com.ink.channel.core.quickpay.message.MasMessage;
import com.ink.channel.core.quickpay.message.MasMessageBack;
import com.ink.channel.core.quickpay.message.QryTxnMsgContent;
import com.ink.channel.core.quickpay.message.TxnMsgContent;
import com.ink.channel.core.service.AsilePay2AccountQueryService;
@Service("queryTradeOrderService")
public class QueryTradeOrderServiceImpl implements AsilePay2AccountQueryService{
	private YinkerLogger logger = YinkerLogger.getLogger(QueryTradeOrderServiceImpl.class);
	  /**
	    * 交易记录查询
	    * @param xml
	    * @return
	    */
	@Override
	public AsileQueryPayAccountOut queryPayOrder(AsileQueryPayAccountIn queryIn) {
		try{
			logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.QUICKM_QUERY_PAY_ACCOUNT_CODE,"进入快钱交易记录接口");
	        String txnType="PUR";
	        String externalRefNumber=queryIn.getOrderNo();
	        String url=Configuration.getInstance().getValue("txnQueryURL");
	        
	        MasMessage msg = new MasMessage();
	        msg.setVersion("1.0");
	        QryTxnMsgContent content = new QryTxnMsgContent();
	        content.setMerchantId(Configuration.getInstance().getValue("merchantId"));
	        content.setTxnType(txnType);
	        content.setExternalRefNumber(externalRefNumber);
	        content.setRefNumber("");
	        msg.setQryTxnMsgContent(content);
	        AsileQueryPayAccountOut out=new AsileQueryPayAccountOut();
	        MasMessageBack msgBack = QuickPayHttpUtil.postXml(url, msg);
	        if(msgBack.getTxnMsgContent() == null){
	        	ErrorMsgContent errorMsg = msgBack.getErrorMsgContent();
	        	out.setResCode(errorMsg.getErrorCode());
	        	out.setResMsg(errorMsg.getErrorMessage());
	        }else{
	        	TxnMsgContent txnMsg = msgBack.getTxnMsgContent();
	        	//out.setActualAmount(txnMsg.getAmount());
				//out.setChannelCode(txnMsg.get);
				//out.setPlatCode(txnMsg.get);
				//out.setCurrencyCode(txnMsg.get);
				out.setOrderNo(txnMsg.getExternalRefNumber());
				out.setOrgTranFlow(txnMsg.getRefNumber());
				//out.setFee(txnMsg.get);
				//out.setPayType(txnMsg.getTxnType());
				//out.setReqTime(txnMsg.getTransTime());
				String stat = txnMsg.getTxnStatus();
				if(StringUtils.isNotBlank(stat)){
					if(stat.equals("S")){
						out.setOrderStatus("00");//成功
					}else if(stat.equals("F")){
						out.setOrderStatus("02");//失败
					}else{
						out.setOrderStatus("01");//处理中
					}
				}
				//out.setTradeTime(txnMsg.getEntryTime());
				out.setResCode(txnMsg.getResponseCode());
				out.setResMsg(txnMsg.getResponseTextMessage());
	        }
	        String resMsg = (msgBack.getQryTxnMsgContent() == null) ? msgBack.getErrorMsgContent().toString() : msgBack.getQryTxnMsgContent().toString();
	        logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.QUICKM_QUERY_PAY_ACCOUNT_CODE,"返回信息："+resMsg);
	        return out;
		}catch(Exception ex){
			logger.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.QUICKM_QUERY_PAY_ACCOUNT_CODE,ex.getMessage(),ex,null);
        }
        logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.QUICKM_QUERY_PAY_ACCOUNT_CODE,"结束快钱交易记录接口");
        return null;
	}
}
