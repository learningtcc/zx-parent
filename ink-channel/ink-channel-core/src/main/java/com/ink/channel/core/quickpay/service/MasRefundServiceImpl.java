package com.ink.channel.core.quickpay.service;

import org.springframework.stereotype.Service;

import com.ink.base.log.util.YinkerLogger;
import com.ink.channel.core.cnst.ChannelConstants;
import com.ink.channel.core.model.in.AsileRefundInput;
import com.ink.channel.core.model.out.AsileRefundOutput;
import com.ink.channel.core.quickpay.Configuration;
import com.ink.channel.core.quickpay.QuickPayHttpUtil;
import com.ink.channel.core.quickpay.message.MasMessage;
import com.ink.channel.core.quickpay.message.MasMessageBack;
import com.ink.channel.core.quickpay.message.TxnMsgContent;
import com.ink.channel.core.service.AsileRefundService;

/**
 * 快钱退款服务
 * 
 * @author huohb
 *
 */
@Service("masRefundServiceImpl")
public class MasRefundServiceImpl implements AsileRefundService {

	private static YinkerLogger logger = YinkerLogger.getLogger(MasRefundServiceImpl.class);

	@Override
	public AsileRefundOutput refund(AsileRefundInput input) {
		try{
			logger.info(ChannelConstants.LOGGER_MODULE_NAME, ChannelConstants.QUICKM_MAS_REFUND_CODE,"进入快钱退款服务接口，订单号：" + input.getOrderNo());
			String merchantId = Configuration.getInstance().getValue("merchantId");
			String terminalId = Configuration.getInstance().getValue("terminalId");
			String url = Configuration.getInstance().getValue("refundURL");
			String amount = input.getAmount().toString();
			String entryTime = input.getEntryTime();
			String externalRefNumber = input.getOrderNo();
			String origRefNumber = input.getOrigRefNumber();
			MasMessage msg = new MasMessage();
			msg.setVersion("1.0");
			TxnMsgContent content = new TxnMsgContent();
			content.setMerchantId(merchantId);
			content.setTerminalId(terminalId);
			content.setAmount(amount);
			content.setEntryTime(entryTime);
			content.setExternalRefNumber(externalRefNumber);
			content.setOrigRefNumber(origRefNumber);
			content.setInteractiveStatus("TR1");
			content.setTxnType("RFD");
			msg.setTxnMsgContent(content);
	
			MasMessageBack msgBack = QuickPayHttpUtil.postXml(url, msg);
			AsileRefundOutput output = new AsileRefundOutput();
			if (msgBack.getTxnMsgContent() == null) {
				output.setResCode(msgBack.getErrorMsgContent().getErrorCode());
				output.setResMsg(msgBack.getErrorMsgContent().getErrorMessage());
			} else {
				output.setResCode(msgBack.getTxnMsgContent().getResponseCode());
				output.setResMsg(msgBack.getTxnMsgContent().getResponseTextMessage());
			}
			return output;
		}catch(Exception ex){
			logger.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.QUICKM_MAS_REFUND_CODE,ex.getMessage(),ex,null);
        }
		logger.info(ChannelConstants.LOGGER_MODULE_NAME, ChannelConstants.QUICKM_MAS_REFUND_CODE,"快钱退款服务执行完毕，订单号：" + input.getOrderNo());
        return null;
	}

}
