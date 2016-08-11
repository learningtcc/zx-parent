package com.ink.channel.core.quickpay.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ink.channel.core.model.in.AsilePayAccountIn;
import com.ink.channel.core.model.out.AsilePayAccountOut;
import com.ink.channel.core.quickpay.Configuration;
import com.ink.channel.core.quickpay.QuickPayHttpUtil;
import com.ink.channel.core.quickpay.message.MasMessage;
import com.ink.channel.core.quickpay.message.MasMessageBack;
import com.ink.channel.core.quickpay.message.TxnMsgContent;
import com.ink.channel.core.service.AsilePay2AccountService;
import com.ink.base.log.util.YinkerLogger;
import com.ink.channel.core.cnst.ChannelConstants;
/**
 * 快钱代收接口
 * @author Lenovo
 *
 */
@Service("directedPayService")
public class DirectedPayServiceImpl implements AsilePay2AccountService{
	private YinkerLogger LOGGER = YinkerLogger.getLogger(DirectedPayServiceImpl.class);
		@Override
		public AsilePayAccountOut payAccount(AsilePayAccountIn asilePayAccountIn) {
			try{
				LOGGER.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.QUICKM_PAY_ACCOUNT_CODE,"进入快钱银行卡代收接口");
		        MasMessage msg = new MasMessage();
		        msg.setVersion(Configuration.getInstance().getValue("version"));
		        TxnMsgContent req = new TxnMsgContent();
		        req.setTxnType("PUR");//交易类型
		        req.setInteractiveStatus("TR1");//消息状态
		        req.setAmount(asilePayAccountIn.getAmount().toString());//交易金额
		        req.setSpFlag("QuickPay");//
		        
		        req.setMerchantId(Configuration.getInstance().getValue("merchantId"));//商户号
		        req.setTerminalId(Configuration.getInstance().getValue("terminalId"));//终端号
		        req.setExternalRefNumber(asilePayAccountIn.getOrderNo());//外部跟踪号
		        req.setEntryTime(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		        //req.setCustomerId(asilePayAccountIn.getIdentityid());//客户号
		        req.setStorableCardNo(getStorableCardNo(asilePayAccountIn.getAccountNo()));//缩略卡号
		        Map<String,String> extMap = new HashMap<String,String>();
		        extMap.put("savePciFlag", "0");//是否保存鉴权信息  0不保存  1保存 默认为0
		        extMap.put("payBatch", "2");//快捷支付批次   1首次支付 2 再次支付
		        req.setExtMap(extMap);
		        
		        msg.setTxnMsgContent(req);
		        
		        String url=Configuration.getInstance().getValue("onePay");
		        
		        MasMessageBack msgBack = QuickPayHttpUtil.postXml(url, msg);
		        
		        AsilePayAccountOut out=new AsilePayAccountOut();
		        if(msgBack.getErrorMsgContent()!= null){
		            out.setResMsg(msgBack.getErrorMsgContent().getErrorMessage());
		            out.setResCode(msgBack.getErrorMsgContent().getErrorCode());
		            out.setOrderNo(asilePayAccountIn.getOrderNo());
		        }else{
		            out.setResMsg(msgBack.getTxnMsgContent().getResponseTextMessage());
		            out.setResCode(msgBack.getTxnMsgContent().getResponseCode());
		            out.setOrderNo(asilePayAccountIn.getOrderNo());
		            out.setOrgTranFlow(msgBack.getTxnMsgContent().getRefNumber());
		        }
		        String resMsg = (msgBack.getTxnMsgContent() == null) ? msgBack.getErrorMsgContent().toString() : msgBack.getTxnMsgContent().toString();
		        LOGGER.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.QUICKM_PAY_ACCOUNT_CODE,"返回信息"+resMsg);
				return out;
			}catch(Exception ex){
	            LOGGER.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.QUICKM_PAY_ACCOUNT_CODE,ex.getMessage(),ex,null);
	        }
	        LOGGER.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.QUICKM_PAY_ACCOUNT_CODE,"结束快钱银行卡代收接口");
	        return null;
		}
		/**
		 * 获取缩略卡号（前6后4）
		 * 
		 * @param cardNo
		 * @return
		 */
		private String getStorableCardNo(String cardNo) {
			if (cardNo == null || "".equals(cardNo.trim())) {
				return null;
			}

			return cardNo.substring(0, 6).concat(cardNo.substring(cardNo.length() - 4, cardNo.length()));
		}
}
