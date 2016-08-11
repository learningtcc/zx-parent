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
import com.ink.channel.core.minsheng.request.CmbcQuickPayReqBean;
import com.ink.channel.core.minsheng.request.CmbcRootBean;
import com.ink.channel.core.minsheng.response.CmbcQuickPayResBean;
import com.ink.channel.core.model.in.AsileQuickPayInput;
import com.ink.channel.core.model.out.AsileQuickPayOutput;
import com.ink.channel.core.service.AsileQuickPayService;
import com.ink.channel.core.utils.Constants;

/**
 * 民生快捷支付服务实现
 * 
 * @author huohb
 *
 */
@Service("cmbcQuickPayServiceImpl")
public class CmbcQuickPayServiceImpl implements AsileQuickPayService {
    @Autowired
    private DataSecurityClient dataSecurityClient;
    @Autowired
    private IdCodeGenerator certificateUtil;
	private static YinkerLogger logger = YinkerLogger.getLogger(CmbcQuickPayServiceImpl.class);
	private static String[][] SUPPORT_ID_TYPE = {{ "0", "01" },
		 	{ "6", "02" },{ "2", "03" },{ "1", "06" }};
	public AsileQuickPayOutput pay(AsileQuickPayInput input) {
	    final String merchantId=input.getMerchantNo();
	       String ASILE_NO=certificateUtil.getIpMapsConfig().get(CmbcConstants.ASILE_CMBC);
        final String certCode=certificateUtil.getIpMapsConfig().get(input.getMerchantNo()+ASILE_NO+CmbcConstants.quickPayCert);
        final String quickPayUrl=certificateUtil.getIpMapsConfig().get(input.getMerchantNo()+ASILE_NO+CmbcConstants.quickPayUrl);
        final String merchantNo=certificateUtil.getIpMapsConfig().get(input.getMerchantNo()+ASILE_NO+CmbcConstants.merchantNoRechargeAndCash);
        final String quickEncryptKey=certificateUtil.getIpMapsConfig().get(input.getMerchantNo()+ASILE_NO+CmbcConstants.quickEncryptKey);
        final String quickCallBack=certificateUtil.getIpMapsConfig().get(input.getMerchantNo()+ASILE_NO+CmbcConstants.quickCallBack);
	    try{
			logger.info(ChannelConstants.LOGGER_MODULE_NAME, ChannelConstants.MAS_QUICK_PAY_CODE,"进入民生快捷支付服务，订单号：" + input.getOrderNo());
			DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			Date date = input.getClientDate();
			CmbcRootBean rootBean = new CmbcRootBean();
			CmbcHeadBean headBean = new CmbcHeadBean();
			CmbcQuickPayReqBean bodyBean = new CmbcQuickPayReqBean();
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
			headBean.setTranCode("QP0001");//交易代码
	
			String phoneNo = input.getPhoneNo();
			String cardNo = input.getCardNo();
			Long timestamp = date.getTime();
			String custIdMac = CryptoUtils.encryptKeyData(phoneNo, timestamp, quickEncryptKey);
			logger.info(ChannelConstants.LOGGER_MODULE_NAME, ChannelConstants.MAS_QUICK_PAY_CODE,"custId：" + custIdMac);
			String cardIdMac = CryptoUtils.encryptKeyData(cardNo, timestamp, quickEncryptKey);
	
			bodyBean.setMerOrderId(input.getOrderNo());//订单号
			bodyBean.setSubject("");//商品种类
			
			String code = "";
	        for (int i = 0; i < Constants.CMBC_QUICK_BANK_NAME_LIST.length; i++) {
	            if (input.getBankShort().toString().trim().contains(Constants.CMBC_QUICK_BANK_NAME_LIST[i][1])) {
	                code = Constants.CMBC_QUICK_BANK_NAME_LIST[i][0];
	            }
	        }
			bodyBean.setBankNo(code);//支付银行号
			bodyBean.setCardNo(cardIdMac);//卡号
			bodyBean.setExpiredDate("");//卡有效期
			bodyBean.setCvv2("");//卡校验码
			bodyBean.setAmount(input.getAmount() == null ? "" : input.getAmount().toString());//金额
			bodyBean.setCustName(input.getUserName());//持卡人姓名（已开通快捷支付再此支付不必填）
			bodyBean.setCustIdNo(input.getIdNo());//持卡人证件号（已开通快捷支付再此支付不必填）
			String certType ="0";
			for (int i = 0; i < SUPPORT_ID_TYPE.length; i++) {
	            if (input.getIdType().trim().contains(SUPPORT_ID_TYPE[i][1])) {
	            	certType=SUPPORT_ID_TYPE[i][0];
	            }
	        }
			bodyBean.setCustIdType(certType);//持卡人证件类型（已开通快捷支付再此支付不必填）
			bodyBean.setSaveCustFlag("1");//是否保存客户信息0-不保存（默认）1-保存（建议）
			bodyBean.setPhoneNo(phoneNo);//手机号
			bodyBean.setPhoneVerCode(input.getValidCode());//手机验证码
			String token=input.getToken();
	       /* try {
	            token=(String)baseRedis.getCache(input.getOrderNo()+"Token");
	        } catch (Exception e) {
	            e.printStackTrace();
	            logger.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.MAS_PAY_FAS_SING_CODE,"validCode get cache from Redis error,requestId "+input.getOrderNo(),"");
	            throw new RuntimeException("get cache from Redis error");
	        }*/
	        bodyBean.setCustId(custIdMac);//客户号
			bodyBean.setPhoneToken(token);//手机校验码令牌
			bodyBean.setStorableCardNo(getStorableCardNo(cardNo));//短卡号
			bodyBean.setBackUrl(quickCallBack);//回调地址
			bodyBean.setMsgExt("快捷支付验证");//附加信息
	
			rootBean.setHead(headBean);
			rootBean.setBody(bodyBean);
	
			CmbcRootBean resBean = CmbcHttpUtil.postXml(quickPayUrl, rootBean, CmbcQuickPayResBean.class, new CmbcMacPloy() {
	
				@Override
				public String getMac(String xml) {
				    String mac="";
                    MsgOutput out= dataSecurityClient.dataEncrypt(merchantId, certCode, xml);
                    if(ResultConstant.EXECUTE_SUCCESS.equals(out.getResultCode()))
                    mac=out.getMessage().toString();
                    return mac;
				}
	
			});
			AsileQuickPayOutput outPut = new AsileQuickPayOutput();
			CmbcQuickPayResBean authBodyBean = (CmbcQuickPayResBean)resBean.getBody();
			outPut.setResCode(resBean.getHead().getRespCode());
			outPut.setResMsg(resBean.getHead().getRespMsg());
			outPut.setOrgTranFlow(authBodyBean.getRefNo());
			outPut.setOrderNo(input.getOrderNo());
			return outPut;
		}catch(Exception ex){
            logger.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.MAS_QUICK_PAY_CODE,ex.getMessage(),ex,null);
        }
		logger.info(ChannelConstants.LOGGER_MODULE_NAME, ChannelConstants.MAS_QUICK_PAY_CODE,"民生快捷支付服务执行完毕，订单号：" + input.getOrderNo());
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
