package com.ink.channel.core.minsheng.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.IdCodeGenerator;
import com.ink.cert.api.client.DataSecurityClient;
import com.ink.cert.api.constant.ResultConstant;
import com.ink.cert.api.module.MsgOutput;
import com.ink.channel.core.cnst.ChannelConstants;
import com.ink.channel.core.minsheng.CmbcConstants;
import com.ink.channel.core.minsheng.CmbcHttpUtil;
import com.ink.channel.core.minsheng.CmbcMacPloy;
import com.ink.channel.core.minsheng.CryptoUtils;
import com.ink.channel.core.minsheng.request.CmbcHeadBean;
import com.ink.channel.core.minsheng.request.CmbcQuickPayAuthReqBean;
import com.ink.channel.core.minsheng.request.CmbcRootBean;
import com.ink.channel.core.minsheng.response.CmbcQuickPayAuthResBean;
import com.ink.channel.core.model.in.AsileQuickAuthInput;
import com.ink.channel.core.model.out.AsileQuickAuthOutput;
import com.ink.channel.core.service.AsileQuickAuthService;
import com.ink.channel.core.utils.Constants;

/**
 * 民生快捷支付鉴权服务接口实现类
 * 
 * @author huohb
 *
 */
@Service("cmbcQuickAuthServiceImpl")
public class CmbcQuickAuthServiceImpl implements AsileQuickAuthService {
    @Autowired
    private DataSecurityClient dataSecurityClient;
    @Autowired
    private IdCodeGenerator certificateUtil;
	private static YinkerLogger logger = YinkerLogger.getLogger(CmbcQuickAuthServiceImpl.class);
	private static String[][] SUPPORT_ID_TYPE = {{ "0", "01" },
		 	{ "6", "02" },{ "2", "03" },{ "1", "06" }};
	/**
	 * 快捷支付鉴权
	 */
	@Override
	public AsileQuickAuthOutput quickAuth(AsileQuickAuthInput input) {
	    final String merchantId=input.getMerchantNo();
	       String ASILE_NO=certificateUtil.getIpMapsConfig().get(CmbcConstants.ASILE_CMBC);
        final String certCode=certificateUtil.getIpMapsConfig().get(input.getMerchantNo()+ASILE_NO+CmbcConstants.quickAuthCert);
        final String quickAuthUrl=certificateUtil.getIpMapsConfig().get(input.getMerchantNo()+ASILE_NO+CmbcConstants.quickAuthUrl);
        final String merchantNo=certificateUtil.getIpMapsConfig().get(input.getMerchantNo()+ASILE_NO+CmbcConstants.merchantNoRechargeAndCash);
        final String quickEncryptKey=certificateUtil.getIpMapsConfig().get(input.getMerchantNo()+ASILE_NO+CmbcConstants.quickEncryptKey);
		try{
			logger.info(ChannelConstants.LOGGER_MODULE_NAME, ChannelConstants.MAS_AUTH_QUICK_PAY_CODE,"进入民生快捷鉴权，订单号：" + input.getOrderNo());
			String nickName = input.getUserName();
			String certNo = input.getIdNo();
			String certType ="0";
			for (int i = 0; i < SUPPORT_ID_TYPE.length; i++) {
	            if (input.getIdType().trim().contains(SUPPORT_ID_TYPE[i][1])) {
	            	certType=SUPPORT_ID_TYPE[i][0];
	            }
	        }
			String cardNo = input.getCardNo();
			String phoneNo = input.getPhoneNo();
			String amount = input.getAmount();
			DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			Date date = new Date();
			CmbcRootBean rootBean = new CmbcRootBean();
			CmbcHeadBean headBean = new CmbcHeadBean();
			CmbcQuickPayAuthReqBean bodyBean = new CmbcQuickPayAuthReqBean();
			headBean.setVersion("01");//版本号
			headBean.setMsgType(CmbcConstants.MSG_TYPE_PAY_ACCOUNT);//报文类型  请求报文：0001 应答报文：0002
			headBean.setChanId(CmbcConstants.CHANNEL_NO);// 渠道代号    由快捷支付平台分配，民生体系外商户填99
			headBean.setMerchantNo(merchantNo);//商户号
			headBean.setClientDate(df.format(date));//商户端发送日期
			/** 交易流水号(商户号+YYYYMMDDHHMMSS+5位流水号) */
			int randomNum = (int) ((Math.random() * 9 + 1) * 1000);
			SimpleDateFormat sdfsdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String tranFlow = merchantNo + sdfsdf.format(new Date()) + randomNum + 5;
	
			headBean.setTranFlow(tranFlow);//交易流水号
			headBean.setTranCode("QP0002");//交易代码
			Long timestamp = date.getTime();
			String merOrderId =input.getOrderNo();
			String custIdMac = CryptoUtils.encryptKeyData(phoneNo, timestamp, quickEncryptKey);
			logger.info(ChannelConstants.LOGGER_MODULE_NAME, ChannelConstants.MAS_AUTH_QUICK_PAY_CODE,"custId：" + custIdMac);
			String cardIdMac = CryptoUtils.encryptKeyData(cardNo, timestamp, quickEncryptKey);
	
			bodyBean.setMerOrderId(merOrderId);//订单号
			bodyBean.setCustId(custIdMac);//客户号
			bodyBean.setCustName(nickName);//持卡人姓名(已开通快捷支付再此支付不必填)
			bodyBean.setCustIdNo(certNo);//持卡人证件号(已开通快捷支付再此支付不必填)
			bodyBean.setCustIdType(certType);//持卡人证件类型(已开通快捷支付再此支付不必填)
	
			bodyBean.setCardNo(cardIdMac);//卡号
			bodyBean.setStorableCardNo(getStorableCardNo(cardNo));//短卡号
			bodyBean.setBankNo(getBankNo(input.getBankShort()));//支付银行号
			bodyBean.setExpiredDate("");//卡有效期
			bodyBean.setCvv2("");//卡校验码
			bodyBean.setAmount(amount);//金额  以元为单位
			bodyBean.setPhoneNo(phoneNo);//手机号
			rootBean.setHead(headBean);
			rootBean.setBody(bodyBean);
	
			CmbcRootBean resBean = CmbcHttpUtil.postXml(quickAuthUrl, rootBean, CmbcQuickPayAuthResBean.class, new CmbcMacPloy() {
	
				@Override
				public String getMac(String xml) {
				    String mac="";
                    MsgOutput out= dataSecurityClient.dataEncrypt(merchantId, certCode, xml);
                    if(ResultConstant.EXECUTE_SUCCESS.equals(out.getResultCode()))
                    mac=out.getMessage().toString();
                    return mac;
				}
	
			});
			CmbcQuickPayAuthResBean authBodyBean = (CmbcQuickPayAuthResBean) resBean.getBody();
	
			AsileQuickAuthOutput out = new AsileQuickAuthOutput();
			out.setResCode(resBean.getHead().getRespCode());
			out.setResMsg(resBean.getHead().getRespMsg());
			//out.setToken(authBodyBean.getPhoneToken());
			/*try {
	            baseRedis.setCache(merOrderId+"Token", authBodyBean.getPhoneToken(), 300);
	        } catch (Exception e) {
	            e.printStackTrace();
	            logger.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.MAS_PAY_VALIDATE_CODE,"validCode put cache into Redis error,requestId "+authBodyBean.getPhoneToken(),"");
	            throw new RuntimeException("put cache from Redis error");
	        }*/
			out.setToken(authBodyBean == null ? "" : authBodyBean.getPhoneToken());
			return out;
		}catch(Exception ex){
            logger.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.MAS_AUTH_QUICK_PAY_CODE,ex.getMessage(),ex,null);
        }
		logger.info(ChannelConstants.LOGGER_MODULE_NAME, ChannelConstants.MAS_AUTH_QUICK_PAY_CODE,"民生快捷鉴权执行完毕，订单号：" + input.getOrderNo());
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

	/**
	 * 根据银行名称英文缩写获取民生对应的银行编号
	 * 
	 * @param bankEnShortName
	 * @return
	 */
	private String getBankNo(String bankEnShortName) {
		
		for (int i = 0; i < Constants.CMBC_QUICK_BANK_NAME_LIST.length; i++) {
            if (bankEnShortName.toString().trim().contains(Constants.CMBC_QUICK_BANK_NAME_LIST[i][1])) {
            	return Constants.CMBC_QUICK_BANK_NAME_LIST[i][0];
            }
        }
		return null;
	}
}