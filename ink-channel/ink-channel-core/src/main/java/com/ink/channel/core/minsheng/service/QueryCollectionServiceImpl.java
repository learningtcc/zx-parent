package com.ink.channel.core.minsheng.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
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
import com.ink.channel.core.minsheng.request.CmbcQueryCollectionReqBean;
import com.ink.channel.core.minsheng.request.CmbcRootBean;
import com.ink.channel.core.minsheng.response.CmbcQuickCollectionResBean;
import com.ink.channel.core.model.in.AsileQueryPayAccountIn;
import com.ink.channel.core.model.out.AsileQueryPayAccountOut;
import com.ink.channel.core.service.AsilePay2AccountQueryService;
/**
 * 查询代付接口
 * @author Lenovo
 *
 */
@Service("queryCollectionService")
public class QueryCollectionServiceImpl implements AsilePay2AccountQueryService{
	private YinkerLogger logger = YinkerLogger.getLogger(QueryCollectionServiceImpl.class);
	@Autowired
    private IdCodeGenerator idCodeGenerator;
	@Autowired
	private IdCodeGenerator certificateUtil;
	@Autowired
	private DataSecurityClient dataSecurityClient;
	@Override
	public AsileQueryPayAccountOut queryPayOrder(AsileQueryPayAccountIn queryIn) {
	    final String merchantId = queryIn.getMerchantNo();
	       String ASILE_NO=certificateUtil.getIpMapsConfig().get(CmbcConstants.ASILE_CMBC);
        final String certCode = certificateUtil.getIpMapsConfig().get(
                        queryIn.getMerchantNo() + ASILE_NO + CmbcConstants.collectionQueryCert);
        final String collectionQueryUrl = certificateUtil.getIpMapsConfig().get(
                        queryIn.getMerchantNo() + ASILE_NO + CmbcConstants.collectionQueryURL);
        final String merchantNo = certificateUtil.getIpMapsConfig().get(
                        queryIn.getMerchantNo() + ASILE_NO + CmbcConstants.merchantNoRechargeAndCash);
        AsileQueryPayAccountOut asilePayAccountOut=new AsileQueryPayAccountOut();
	    try{
			logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.MAS_QUERY_PAY_ACCOUNT_CODE,"结束调用民生安保银代收接口");
	        CmbcRootBean rootBean = new CmbcRootBean();
	        CmbcHeadBean headBean = new CmbcHeadBean();
	        DateFormat df = new SimpleDateFormat("yyyyMMdd");
	        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
	        CmbcQueryCollectionReqBean bodyBean = new CmbcQueryCollectionReqBean();
	        headBean.setVersion(CmbcConstants.VERSION);
	        headBean.setMsgtype(CmbcConstants.MSG_TYPE_PAY_ACCOUNT);
	        headBean.setChannelno(CmbcConstants.CHANNEL_NO);
	        headBean.setMerchantno(merchantNo);
	        headBean.setTrandate(df.format(new Date()));
	        headBean.setTrantime(sdf.format(new Date()));
	//        headBean.setClientDate(df.format(new Date()));
	        /** 交易流水号(商户号+YYYYMMDDHHMMSS+5位流水号) */
	       // int randomNum = (int) ((Math.random() * 9 + 1) * 1000);
	        //SimpleDateFormat sdfsdf = new SimpleDateFormat("yyyyMMddHHmmss");
	        String tranFlow =merchantNo+idCodeGenerator.getId();
	        headBean.setBussflowno(tranFlow);
	        headBean.setTrancode("CP0002");
	        bodyBean.setOrgTranFlow(queryIn.getOrderNo());
	        rootBean.setHead(headBean);
	        rootBean.setBody(bodyBean);
	        CmbcRootBean resBean = CmbcHttpUtil.postXml(collectionQueryUrl, rootBean, CmbcQuickCollectionResBean.class
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
	        if ("C000000000".equals(resBean.getHead().getRespCode())) {
		        CmbcQuickCollectionResBean authBodyBean = (CmbcQuickCollectionResBean)resBean.getBody();
		        asilePayAccountOut.setResCode(resBean.getHead().getRespCode());
		        asilePayAccountOut.setResMsg(resBean.getHead().getRespMsg());
		        
		        String stat =authBodyBean.getTranState();
				if(StringUtils.isNotBlank(stat)){
					if(stat.equals("01")){
						asilePayAccountOut.setOrderStatus(OrderStatus.SUCCESS_CODE.getCode());//成功
					}else if(stat.equals("03")){
						asilePayAccountOut.setOrderStatus(OrderStatus.FAILE_CODE.getCode());//失败
					}else{
						asilePayAccountOut.setOrderStatus(OrderStatus.PROCESSES_CODE.getCode());//处理中
					}
				}else{
					asilePayAccountOut.setOrderStatus(OrderStatus.PROCESSES_CODE.getCode());//处理中
		        }
			}else{
				asilePayAccountOut.setOrderStatus(OrderStatus.PROCESSES_CODE.getCode());//处理中
	        }
			return asilePayAccountOut;
		}catch(Exception ex){
			asilePayAccountOut.setResCode(SystemCodeEnums.CMBC_FAILE_CODE.getCode());
			asilePayAccountOut.setResMsg(SystemCodeEnums.CMBC_FAILE_CODE.getMsg());
            logger.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.MAS_QUERY_PAY_ACCOUNT_CODE,ex.getMessage(),ex,null);
        }
		logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.MAS_QUERY_PAY_ACCOUNT_CODE,"结束调用民生安保银代收接口");
        return asilePayAccountOut;
	}
}
