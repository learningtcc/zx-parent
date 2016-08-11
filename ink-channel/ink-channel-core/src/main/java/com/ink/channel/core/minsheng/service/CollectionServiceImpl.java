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
import com.ink.channel.core.enums.OrderStatus;
import com.ink.channel.core.enums.SystemCodeEnums;
import com.ink.channel.core.ldyspay.LdysConstant;
import com.ink.channel.core.minsheng.CmbcConstants;
import com.ink.channel.core.minsheng.CmbcHttpUtil;
import com.ink.channel.core.minsheng.CmbcMacPloy;
import com.ink.channel.core.minsheng.request.CmbcCollectionReqBean;
import com.ink.channel.core.minsheng.request.CmbcHeadBean;
import com.ink.channel.core.minsheng.request.CmbcRootBean;
import com.ink.channel.core.minsheng.response.CmbcCollectionResBean;
import com.ink.channel.core.model.in.AsilePayAccountIn;
import com.ink.channel.core.model.out.AsilePayAccountOut;
import com.ink.channel.core.service.AsilePay2AccountService;
import com.ink.channel.core.utils.Constants;
/**
 * 
 *<pre>
 *<b>类描述:</b>单笔代收接口---代收
 *<b>作者:</b>zx
 *<b>创建日期:</b>2016年3月14日 上午10:56:42
 *</pre>
 */
@Service("collectionService")
public class CollectionServiceImpl implements AsilePay2AccountService{
    @Autowired
    private IdCodeGenerator certificateUtil;
    @Autowired
    private DataSecurityClient dataSecurityClient;
	private YinkerLogger logger = YinkerLogger.getLogger(CollectionServiceImpl.class);
	@Override
	public AsilePayAccountOut payAccount(AsilePayAccountIn asilePayAccountIn) {
	    final String merchantId = asilePayAccountIn.getMerchantNo();
	       String ASILE_NO=certificateUtil.getIpMapsConfig().get(CmbcConstants.ASILE_CMBC);
        final String certCode = certificateUtil.getIpMapsConfig().get(
                        asilePayAccountIn.getMerchantNo() + ASILE_NO + CmbcConstants.collectionCert);
        final String collectionUrl = certificateUtil.getIpMapsConfig().get(
                        asilePayAccountIn.getMerchantNo() + ASILE_NO + CmbcConstants.collectionURL);
        final String merchantNo = certificateUtil.getIpMapsConfig().get(
                        asilePayAccountIn.getMerchantNo() + ASILE_NO + CmbcConstants.merchantNoRechargeAndCash);
        AsilePayAccountOut asilePayAccountOut=new AsilePayAccountOut();
	    try{
			logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.MAS_PAY_ACCOUNT_CODE,"开始调用民生安保银代收接口");
	        CmbcRootBean rootBean = new CmbcRootBean();
	        CmbcHeadBean headBean = new CmbcHeadBean();
	        DateFormat df = new SimpleDateFormat("yyyyMMdd");
	        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
	        CmbcCollectionReqBean bodyBean = new CmbcCollectionReqBean();
	        headBean.setVersion(CmbcConstants.VERSION);//信息格式版本号
	        headBean.setMsgtype(CmbcConstants.MSG_TYPE_PAY_ACCOUNT);//报文类型
	        headBean.setChannelno(CmbcConstants.CHANNEL_NO);//渠道代号 
	        headBean.setMerchantno(merchantNo);//商户号
	        headBean.setTrandate(df.format(new Date()));//交易日期
	        headBean.setTrantime(sdf.format(new Date()));//交易时间
	//        int randomNum = (int) ((Math.random() * 9 + 1) * 1000);
	//        SimpleDateFormat sdfsdf = new SimpleDateFormat("yyyyMMddHHmmss");
	        String tranFlow =asilePayAccountIn.getOrderNo();
	        headBean.setBussflowno(tranFlow);//交易流水号
	        headBean.setTrancode("CP0001");//交易代码
	        bodyBean.setMerPlatAcctAlias("");//商户平台收款账户别名
	        bodyBean.setProtocolNo("");//协议号
	        String bankName = null;
	        for (int i = 0; i < Constants.CMBC_BANK_NAME_LIST.length; i++) {
	            if(Constants.CMBC_BANK_NAME_LIST[i][1].equals(asilePayAccountIn.getBankShort())){
	            	bankName = Constants.CMBC_BANK_NAME_LIST[i][0];
	            }
	        }
	        bodyBean.setBankName(bankName);//银行名称
	        bodyBean.setAccountNo(asilePayAccountIn.getAccountNo());//银行账号
	        bodyBean.setAccountName(asilePayAccountIn.getAccountName());//银行账户名称
	        bodyBean.setAccountType(CmbcConstants.accountType);//账户类型
	        bodyBean.setOpenProvince("");//开户行所在省
	        bodyBean.setOpenCity("");//开户行所在市
	        bodyBean.setOpenName("");//开户行名称
	        bodyBean.setTranAmt(asilePayAccountIn.getAmount().toString());//交易金额
	        bodyBean.setCurType(CmbcConstants.currencyCode);//币种
	        bodyBean.setBsnType(CmbcConstants.bsnType);//11203业务类型
	        bodyBean.setCertType(asilePayAccountIn.getCertType());//开户证件类型 
	        bodyBean.setCertNo(asilePayAccountIn.getCertNo());//证件号
	        bodyBean.setMobileNo("");//手机号码
	        bodyBean.setProdInfo("");//商品信息
	        bodyBean.setMsgExt("");//附加信息
	        rootBean.setHead(headBean);
	        rootBean.setBody(bodyBean);
	        long startMillis = System.currentTimeMillis();
			logger.info(ChannelConstants.LOGGER_MODULE_NAME, ChannelConstants.MAS_PAY_CARD_CODE,"开始调用代收CmbcHttpUtil.postXml系统时间:"+startMillis);
	        CmbcRootBean resBean = CmbcHttpUtil.postXml(collectionUrl, rootBean, CmbcCollectionResBean.class
	        		,new CmbcMacPloy() {
						
						@Override
						public String getMac(String xml) {
						    String mac="";
                            MsgOutput out= dataSecurityClient.dataEncrypt(merchantId, certCode, xml);
                            if(ResultConstant.EXECUTE_SUCCESS.equals(out.getResultCode()))
                            mac=out.getMessage().toString();
                            return mac;
						}
					});
	        
	        /*long endMillis = System.currentTimeMillis();
			logger.info(ChannelConstants.LOGGER_MODULE_NAME, ChannelConstants.MAS_PAY_CARD_CODE,"结束调用代收CmbcHttpUtil.postXml系统时间:"+endMillis);
			long diffMillis = endMillis-startMillis;
			logger.info(ChannelConstants.LOGGER_MODULE_NAME, ChannelConstants.MAS_PAY_CARD_CODE,"调用代收CmbcHttpUtil.postXml时间差:"+diffMillis);
			*/
	        asilePayAccountOut.setResCode(resBean.getHead().getRespCode());
	        asilePayAccountOut.setResMsg(resBean.getHead().getRespMsg());
	        asilePayAccountOut.setOrderNo(asilePayAccountIn.getOrderNo());
	        asilePayAccountOut.setOrgTranFlow(tranFlow);
	        if("C000000000".equals(resBean.getHead().getRespCode())){
		        CmbcCollectionResBean authBodyBean = (CmbcCollectionResBean)resBean.getBody();
	        	if("01".equals(authBodyBean.getTranState())){
	        		asilePayAccountOut.setOrderStatus(OrderStatus.SUCCESS_CODE.getCode());//成功
	        	}else if("03".equals(authBodyBean.getTranState())){
	        		asilePayAccountOut.setOrderStatus(OrderStatus.FAILE_CODE.getCode());//失败 
	        	}else{
	        		asilePayAccountOut.setOrderStatus(OrderStatus.PROCESSES_CODE.getCode());//处理中
	        	}
	        }else if(ChannelConstants.RESPONSE_EXCEPTION_CODE.equals(resBean.getHead().getRespCode())){
	        	asilePayAccountOut.setOrderStatus(OrderStatus.PROCESSES_CODE.getCode());//处理中
	        }else if(LdysConstant.decodeCode.equals(resBean.getHead().getRespCode())){
	        	asilePayAccountOut.setOrderStatus(OrderStatus.PROCESSES_CODE.getCode());//处理中
	        }else{
	        	asilePayAccountOut.setOrderStatus(OrderStatus.FAILE_CODE.getCode());//失败 
	        }
			return asilePayAccountOut;
		}catch(Exception ex){
			 	asilePayAccountOut.setResCode(SystemCodeEnums.CMBC_FAILE_CODE.getCode());
		        asilePayAccountOut.setResMsg(SystemCodeEnums.CMBC_FAILE_CODE.getMsg());
		        asilePayAccountOut.setOrderStatus(OrderStatus.PROCESSES_CODE.getCode());//失败 
		        logger.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.MAS_PAY_ACCOUNT_CODE,ex.getMessage(),ex,null);
        }
        logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.MAS_PAY_ACCOUNT_CODE,"结束调用民生安保银代收接口");
        return asilePayAccountOut;
	}
	 /*private static String[][] SUPPORT_BANK_LIST = {{ "中国民生银行", "CMBC" },
			 	{ "广东发展银行", "GDB" },{ "中国工商银行", "ICBC" },
	            { "中国农业银行", "ABC" }, { "中国银行", "BOC" },
	            { "中国建设银行", "CCB" },{ "中国邮政储蓄银行", "PSBC" },
	            { "招商银行", "CMB" },{ "中国光大银行", "CEB" },
	            { "兴业银行", "CIB" }, { "平安银行", "PAB" }};*/
    
    
}

