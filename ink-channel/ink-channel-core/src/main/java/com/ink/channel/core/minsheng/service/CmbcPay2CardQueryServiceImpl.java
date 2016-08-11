package com.ink.channel.core.minsheng.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
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
import com.ink.channel.core.minsheng.CmbcConstants;
import com.ink.channel.core.minsheng.CmbcHttpUtil;
import com.ink.channel.core.minsheng.CmbcMacPloy;
import com.ink.channel.core.minsheng.request.CmbcHeadBean;
import com.ink.channel.core.minsheng.request.CmbcQueryPaymentReqBean;
import com.ink.channel.core.minsheng.request.CmbcRootBean;
import com.ink.channel.core.minsheng.response.CmbcQuickPaymentResBean;
import com.ink.channel.core.model.in.AsileQueryPayAccountIn;
import com.ink.channel.core.model.out.AsileQueryPayAccountOut;
import com.ink.channel.core.service.AsilePay2AccountQueryService;

/**
 * 民生代付查询服务接口实现类
 * 
 * @author huohb
 *
 */
@Service("cmbcPay2CardQueryServiceImpl")
public class CmbcPay2CardQueryServiceImpl implements AsilePay2AccountQueryService {

	private static YinkerLogger logger = YinkerLogger.getLogger(CmbcPay2CardQueryServiceImpl.class);
	@Autowired
    private IdCodeGenerator idCodeGenerator;
	@Autowired
	private IdCodeGenerator certificateUtil;
	@Autowired
	private DataSecurityClient dataSecurityClient;
	/**
	 * 代付查询
	 */
	@Override
	public AsileQueryPayAccountOut queryPayOrder(AsileQueryPayAccountIn input) {
	    final String merchantId=input.getMerchantNo();
	    String ASILE_NO=certificateUtil.getIpMapsConfig().get(CmbcConstants.ASILE_CMBC);
        final String certCode=certificateUtil.getIpMapsConfig().get(input.getMerchantNo()+ASILE_NO+CmbcConstants.paymentQueryCert);
        final String paymentQueryUrl=certificateUtil.getIpMapsConfig().get(input.getMerchantNo()+ASILE_NO+CmbcConstants.paymentQueryURL);
        final String merchantNo=certificateUtil.getIpMapsConfig().get(input.getMerchantNo()+ASILE_NO+CmbcConstants.merchantNoRechargeAndCash);
        AsileQueryPayAccountOut out = new AsileQueryPayAccountOut();
	    try{
			logger.info(ChannelConstants.LOGGER_MODULE_NAME, ChannelConstants.MAS_QUERY_PAY_CARD_CODE,"进入民生代付订单查询接口，订单号：" + input.getOrderNo());
			DateFormat df = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
			CmbcRootBean rootBean = new CmbcRootBean();
			CmbcHeadBean headBean = new CmbcHeadBean();
			CmbcQueryPaymentReqBean bodyBean = new CmbcQueryPaymentReqBean();
			headBean.setVersion(CmbcConstants.VERSION);
			headBean.setMsgtype(CmbcConstants.MSG_TYPE_PAY_ACCOUNT);
			headBean.setChannelno(CmbcConstants.CHANNEL_NO);
			headBean.setMerchantno(merchantNo);
			headBean.setTrandate(df.format(new Date()));
			headBean.setTrantime(sdf.format(new Date()));
			// headBean.setClientDate(df.format(new Date()));
			/** 交易流水号(商户号+YYYYMMDDHHMMSS+5位流水号) */
			//int randomNum = (int) ((Math.random() * 9 + 1) * 1000);
			//SimpleDateFormat sdfsdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String tranFlow = merchantNo + idCodeGenerator.getId();
			headBean.setBussflowno(tranFlow);//流水号
			headBean.setTrancode("CP0004");//
	
			bodyBean.setOrgTranFlow(input.getOrderNo());
			rootBean.setHead(headBean);
			rootBean.setBody(bodyBean);
			CmbcRootBean resBean = CmbcHttpUtil.postXml(paymentQueryUrl, rootBean, CmbcQuickPaymentResBean.class, new CmbcMacPloy() {
				@Override
				public String getMac(String xml) {
				    String mac="";
                    MsgOutput out= dataSecurityClient.dataEncrypt(merchantId, certCode, xml);
                    if(ResultConstant.EXECUTE_SUCCESS.equals(out.getResultCode()))
                    mac=out.getMessage().toString();
//                    return DigestUtils.sha256Hex(xml);
                    return mac;
				}
	
			});
			out.setResCode(resBean.getHead().getRespCode());
			out.setResMsg(resBean.getHead().getRespMsg());
	
			if ("C000000000".equals(resBean.getHead().getRespCode())) {
				CmbcQuickPaymentResBean paymentBean = (CmbcQuickPaymentResBean) resBean.getBody();
				String state = paymentBean.getTranState();
				if(StringUtils.isNotBlank(state)){
					if(state.equals("00")){
						out.setOrderStatus(OrderStatus.PROCESSES_CODE.getCode());//处理中
					}else if(state.equals("01")){
						out.setOrderStatus(OrderStatus.SUCCESS_CODE.getCode());//成功
					}else{
						out.setOrderStatus(OrderStatus.FAILE_CODE.getCode());//失败
					}
				}else{
		        	out.setOrderStatus(OrderStatus.PROCESSES_CODE.getCode());//处理中
		        }
				out.setResCode(paymentBean.getTranRespCode());
				out.setResMsg(paymentBean.getTranRespMsg());
			}else{
	        	out.setOrderStatus(OrderStatus.PROCESSES_CODE.getCode());//处理中
	        }
			return out; 
		}catch(Exception ex){
			out.setResCode(SystemCodeEnums.CMBC_FAILE_CODE.getCode());
			out.setResMsg(SystemCodeEnums.CMBC_FAILE_CODE.getMsg());
            logger.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.MAS_QUERY_PAY_CARD_CODE,ex.getMessage(),ex,null);
        }
		logger.info(ChannelConstants.LOGGER_MODULE_NAME, ChannelConstants.MAS_QUERY_PAY_CARD_CODE,"民生代付订单查询接口执行完毕，订单号：" + input.getOrderNo());
        return out;
	}

}
