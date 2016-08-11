package com.ink.channel.core.quickpay.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ink.base.log.util.YinkerLogger;
import com.ink.channel.core.cnst.ChannelConstants;
import com.ink.channel.core.model.in.AsileQuickPayInput;
import com.ink.channel.core.model.out.AsileQuickPayOutput;
import com.ink.channel.core.quickpay.Configuration;
import com.ink.channel.core.quickpay.QuickPayHttpUtil;
import com.ink.channel.core.quickpay.message.MasMessage;
import com.ink.channel.core.quickpay.message.MasMessageBack;
import com.ink.channel.core.quickpay.message.TxnMsgContent;
import com.ink.channel.core.service.AsileQuickPayService;

/**
 * 快钱快捷支付服务实现
 * 
 * @author huohb
 *
 */
@Service("masQuickPayServiceImpl")
public class MasQuickPayServiceImpl implements AsileQuickPayService {

	private static YinkerLogger logger = YinkerLogger.getLogger(MasQuickPayServiceImpl.class);

	@Override
	public AsileQuickPayOutput pay(AsileQuickPayInput input) {
		try{
			logger.info(ChannelConstants.LOGGER_MODULE_NAME, ChannelConstants.QUICKM_QUICK_PAY_CODE,"进入快钱快捷支付接口，订单号：" + input.getOrderNo());
			String merchantId = Configuration.getInstance().getValue("merchantId");
			String terminalId = Configuration.getInstance().getValue("terminalId");
			String entryTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			String amount = input.getAmount() == null ? "" : input.getAmount().toString();
			String cardNo = input.getCardNo();
			//String storableCardNo = getStorableCardNo(cardNo);
			String token = input.getToken();
			
			MasMessage msg = new MasMessage();
			msg.setVersion("1.0");
			TxnMsgContent req = new TxnMsgContent();
			req.setInteractiveStatus("TR1");//消息状态
			req.setTxnType("PUR");//交易类型
			req.setCardNo(cardNo);//卡号
			req.setAmount(amount);//金额
			req.setMerchantId(merchantId);//商户号
			req.setTerminalId(terminalId);//终端号
			req.setCardHolderName(input.getUserName());//持卡人姓名
			req.setCardHolderId(input.getIdNo());//持卡人身份证号
			req.setIdType(input.getIdType());//证件类型
			req.setEntryTime(entryTime);//商户端交易时间
			req.setExternalRefNumber(input.getOrderNo());//外部跟踪编号
			req.setCustomerId(input.getPhoneNo());//客户号
			req.setSpFlag("QuickPay");//特殊交易类型
			//req.setStorableCardNo(storableCardNo);//缩略卡号
			req.setTr3Url("http://www.baidu.com");//回调地址
			
			Map<String, String> extMap = new HashMap<String, String>();
			extMap.put("phone", input.getPhoneNo());//填写持卡人手机号码
			extMap.put("validCode", input.getValidCode());//手机验证码
			extMap.put("savePciFlag", "1");//是否保存鉴权信息0 不保存 1 保存   默认保存
			extMap.put("token", token);//
			extMap.put("payBatch", "1");//快捷支付批次1：首次支付 2：再次支付
			req.setExtMap(extMap);//
	
			msg.setTxnMsgContent(req);
			String url = Configuration.getInstance().getValue("quickPayURL");
	
			MasMessageBack msgBack = QuickPayHttpUtil.postXml(url, msg);
			AsileQuickPayOutput outPut = new AsileQuickPayOutput();
			if (msgBack.getTxnMsgContent() == null) {
				outPut.setResCode(msgBack.getErrorMsgContent().getErrorCode());
				outPut.setResMsg(msgBack.getErrorMsgContent().getErrorMessage());
				outPut.setOrderNo(input.getOrderNo());
			} else {
				outPut.setResCode(msgBack.getTxnMsgContent().getResponseCode());
				outPut.setResMsg(msgBack.getTxnMsgContent().getResponseTextMessage());
				outPut.setOrderNo(input.getOrderNo());
				outPut.setOrgTranFlow(msgBack.getTxnMsgContent().getRefNumber());
			}
			return outPut;
		}catch(Exception ex){
			logger.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.QUICKM_QUICK_PAY_CODE,ex.getMessage(),ex,null);
        }
		logger.info(ChannelConstants.LOGGER_MODULE_NAME, ChannelConstants.QUICKM_QUICK_PAY_CODE,"快钱快捷支付接口执行完毕，订单号：" + input.getOrderNo());
        return null;
	}

	/**
	 * 获取缩略卡号
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
