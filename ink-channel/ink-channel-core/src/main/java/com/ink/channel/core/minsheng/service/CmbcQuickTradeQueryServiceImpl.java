package com.ink.channel.core.minsheng.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.BeanCopyConverter;
import com.ink.base.utils.IdCodeGenerator;
import com.ink.cert.api.client.DataSecurityClient;
import com.ink.cert.api.constant.ResultConstant;
import com.ink.cert.api.module.MsgOutput;
import com.ink.channel.core.cnst.ChannelConstants;
import com.ink.channel.core.minsheng.CmbcConstants;
import com.ink.channel.core.minsheng.CmbcHttpUtil;
import com.ink.channel.core.minsheng.CmbcMacPloy;
import com.ink.channel.core.minsheng.request.CmbcHeadBean;
import com.ink.channel.core.minsheng.request.CmbcQuickTradeQueryReqBean;
import com.ink.channel.core.minsheng.request.CmbcRootBean;
import com.ink.channel.core.minsheng.response.CmbcQuickTradeQueryResBean;
import com.ink.channel.core.model.in.AsileQueryPayAccountIn;
import com.ink.channel.core.model.out.AsileQueryPayAccountOut;
import com.ink.channel.core.service.AsilePay2AccountQueryService;

/**
 * 
 * @Description 民生快捷支付交易流水查询实现
 * @author xuguoqi
 * @date 2016年6月15日 下午3:23:27
 */
@Service("cmbcQuickTradeQueryServiceImpl")
public class CmbcQuickTradeQueryServiceImpl implements AsilePay2AccountQueryService {
    @Autowired
    private DataSecurityClient dataSecurityClient;
    @Autowired
    private IdCodeGenerator certificateUtil;
	private static YinkerLogger log = YinkerLogger.getLogger(CmbcQuickTradeQueryServiceImpl.class);


	/**
	 * @Description 民生快捷支付交易流水查询实现
	 * @author xuguoqi
	 * @date 2016年6月15日 下午2:27:06
	 * @param input
	 * @return  
	 */
	@Override
	public AsileQueryPayAccountOut queryPayOrder(AsileQueryPayAccountIn input) {
	    final String merchantId=input.getMerchantNo();
	       String ASILE_NO=certificateUtil.getIpMapsConfig().get(CmbcConstants.ASILE_CMBC);
        final String certCode=certificateUtil.getIpMapsConfig().get(input.getMerchantNo()+ASILE_NO+CmbcConstants.quickPayQueryCert);
        final String quickPayQueryUrl=certificateUtil.getIpMapsConfig().get(input.getMerchantNo()+ASILE_NO+CmbcConstants.quickPayUrl);
        final String merchantNo=certificateUtil.getIpMapsConfig().get(input.getMerchantNo()+ASILE_NO+CmbcConstants.merchantNoRechargeAndCash);
		long startTime = System.currentTimeMillis();
		AsileQueryPayAccountOut outPut = new AsileQueryPayAccountOut(); 
		try{
			log.info(ChannelConstants.LOGGER_MODULE_NAME, ChannelConstants.MAS_QUICK_QUERY,"进入民生快捷交易订单查询接口，订单号：" + input.getOrderNo());
			DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			Date date = new Date();
			CmbcRootBean rootBean = new CmbcRootBean();
			CmbcHeadBean headBean = new CmbcHeadBean();
			CmbcQuickTradeQueryReqBean bodyBean = new CmbcQuickTradeQueryReqBean();
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
			headBean.setTranCode("QP0006");//交易代码
			bodyBean.setMerOderNo(input.getOrderNo());//订单号
			bodyBean.setTxnType("QP0001");
			rootBean.setHead(headBean);
			rootBean.setBody(bodyBean);
	
			CmbcRootBean resBean = CmbcHttpUtil.postXml(quickPayQueryUrl, rootBean, CmbcQuickTradeQueryResBean.class, new CmbcMacPloy() {
	
				@Override
				public String getMac(String xml) {
				    String mac="";
                    MsgOutput out= dataSecurityClient.dataEncrypt(merchantId, certCode, xml);
                    if(ResultConstant.EXECUTE_SUCCESS.equals(out.getResultCode()))
                    mac=out.getMessage().toString();
                    return mac;
				}
	
			});
			CmbcQuickTradeQueryResBean payBodyBean = (CmbcQuickTradeQueryResBean)resBean.getBody();
			if("C000000000".equals(resBean.getHead().getRespCode())){
				AsileQueryPayAccountOut out = BeanCopyConverter.converterClass(resBean.getBody(), AsileQueryPayAccountOut.class);
				out.setResCode(resBean.getHead().getRespCode());
				out.setResMsg(resBean.getHead().getRespMsg());
				log.info(ChannelConstants.LOGGER_MODULE_NAME, ChannelConstants.MAS_QUICK_QUERY,"进入民生快捷交易订单查询结束,耗时:"+(System.currentTimeMillis()-startTime)+"ms，订单号：" + input.getOrderNo());
				return out;
			}
			outPut.setResCode(resBean.getHead().getRespCode());
			outPut.setResMsg(resBean.getHead().getRespMsg());
			String stat =payBodyBean.getTxnStat();
			if(StringUtils.isNotBlank(stat)){
				if(stat.equals("1")){
					outPut.setOrderStatus("00");//成功
				}else if(stat.equals("2")){
					outPut.setOrderStatus("02");//失败
				}else{
					outPut.setOrderStatus("01");//处理中
				}
			}
			log.info(resBean.toString());
			return outPut;
		}catch(Exception ex){
            log.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.MAS_QUICK_QUERY,"民生快捷支付交易订单查询异常"+ex.getMessage(),ex,null);
            outPut.setResCode("W000000001");
			outPut.setResMsg("查询失败");
        }
		log.info(ChannelConstants.LOGGER_MODULE_NAME, ChannelConstants.MAS_QUICK_QUERY,"民生快捷支付服务执行完毕，订单号：" + input.getOrderNo());
		return null;
	}

}
