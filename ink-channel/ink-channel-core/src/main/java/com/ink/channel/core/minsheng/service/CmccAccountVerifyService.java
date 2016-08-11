package com.ink.channel.core.minsheng.service;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.zip.GZIPInputStream;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
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
import com.ink.channel.core.minsheng.request.CmbcAccountVerifyReqBean;
import com.ink.channel.core.minsheng.request.CmbcHeadBean;
import com.ink.channel.core.minsheng.request.CmbcRootBean;
import com.ink.channel.core.minsheng.response.CmbcAccountVerifyResBean;
import com.ink.channel.core.utils.ZipUtils;
import com.ink.tfs.client.TFSOperator;
/**
 * 历史明细查询 下载文件
 * @author Lenovo
 */
@Service("cmccAccountVerifyService")
public class CmccAccountVerifyService {
    private YinkerLogger logger = YinkerLogger.getLogger(CmccAccountVerifyService.class);
    @Autowired
    private TFSOperator tFSOperator;
    @Autowired
    private IdCodeGenerator idCodeGenerator;
    @Autowired
	private IdCodeGenerator certificateUtil;
    @Autowired
    private DataSecurityClient dataSecurityClient;
    //private Thread t; 
    @SuppressWarnings("static-access")
	public boolean queryHistory() {
    	boolean status=false;
    	String allMerchantno = certificateUtil.getIpMapsConfig().get(CmbcConstants.SYSTEM_ALL_MERCHANTNO);
    	if(StringUtils.isNotBlank(allMerchantno)){
    		final String[] merchantnoArray = allMerchantno.split(",");
    		if(ArrayUtils.isEmpty(merchantnoArray)){
    			return true;
    		}
    		
    		
    		
    		for(final String merchantno:merchantnoArray){
    			logger.info(ChannelConstants.LOGGER_MODULE_NAME,"进入查询历史明细线程");
    			final String asile_no=certificateUtil.getIpMapsConfig().get(CmbcConstants.ASILE_CMBC);
    			final String merchantNoMasForChannel = certificateUtil.getIpMapsConfig().get(merchantno+asile_no+CmbcConstants.merchantNoRechargeAndCash);
    			String historyQueryUrl=certificateUtil.getIpMapsConfig().get(merchantno+asile_no+CmbcConstants.historyUrl);
    			if(StringUtils.isBlank(merchantNoMasForChannel)){
    				continue;
    			}
    			GZIPInputStream inputStream=null;
    			try{
    				//初始化请求参数
    				CmbcRootBean rootBean = init(merchantNoMasForChannel);
    				//发送请求
    				CmbcRootBean resBean = CmbcHttpUtil.postXml(historyQueryUrl, rootBean,
    						CmbcAccountVerifyResBean.class, new CmbcMacPloy() {
    					@Override
    					public String getMac(String xml) {
    						String mac="";
    						String certCode=certificateUtil.getIpMapsConfig().get(merchantno+asile_no+CmbcConstants.historyCert);
    						MsgOutput out= dataSecurityClient.dataEncrypt(merchantno, certCode, xml);
    						if(ResultConstant.EXECUTE_SUCCESS.equals(out.getResultCode()))
    							mac=out.getMessage().toString();
    						return mac;
    					}
    				});
    				CmbcAccountVerifyResBean resBodyBean = (CmbcAccountVerifyResBean) resBean.getBody();
    				if ("C000000000".equals(resBean.getHead().getRespCode())) {
    					//获取数据
    					String data = resBodyBean.getData();
    					//文件名   渠道号+日期
    					String fileName=asile_no+merchantNoMasForChannel+"00"+getYesterdayString()+".txt";
    					//String fileName="1000220160603.txt";
    					String tFSRresult="";
    					if(StringUtils.isNotBlank(data)){
    						//解压并上传到TFS
    						inputStream= ZipUtils.gunzipInputStream(data);
    						// 文件名   流    模块id   渠道id   操作人
    						tFSRresult= tFSOperator.saveInputStreamToTfs(fileName, inputStream, "1001", "10002", "timer");
    					}else{
    						logger.info(ChannelConstants.LOGGER_MODULE_NAME,data+"民生对账文件为空");
    					}
    					logger.info(ChannelConstants.LOGGER_MODULE_NAME,"TFS返回结果："+tFSRresult);
    					if(StringUtils.isNotBlank(tFSRresult)){
    					}
    				}
    				logger.info(ChannelConstants.LOGGER_MODULE_NAME,"查询历史明细线程输出");
    			}catch(Exception ex){
    				logger.error(ChannelConstants.LOGGER_MODULE_NAME, "查询历史明细异常："+ex.getMessage(), ex);
    			}finally{
    				if(inputStream!=null){
    					try {
    						inputStream.close();
    					} catch (IOException e) {
    						e.printStackTrace();
    						logger.error(ChannelConstants.LOGGER_MODULE_NAME, "查询历史明细inputStream关闭异常："+e.getMessage(), e);
    					}
    				}
    			}
    			
    			//主线程休眠6分钟
    			try {
					Thread.currentThread().sleep(6*60*1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
    		}
    		
    		
//	    	t=new Thread(){
//				@Override
//				public void run() {
//					
//				}
//			};
//			t.start();
//			try {
//				t.join();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
    	}	
    	status=true;
        return status;
    }
    /**
     * 初始化数据
     * @return
     */
    private CmbcRootBean init(String merchantNo) {
        CmbcRootBean rootBean = new CmbcRootBean();
        CmbcHeadBean headBean = new CmbcHeadBean();
        headBean.setVersion("1.0.0");
        headBean.setMsgtype("0001");
        headBean.setChannelno("99");
        headBean.setMerchantno(merchantNo);
        headBean.setTrandate(new SimpleDateFormat("yyyyMMdd").format(new Date()));
        headBean.setTrantime(new SimpleDateFormat("HHmmss").format(new Date()));
        /** 交易流水号(商户号+YYYYMMDDHHMMSS+5位流水号) */
        //int randomNum = (int) ((Math.random() * 9 + 1) * 1000);
        String tranFlow = merchantNo
                        + idCodeGenerator.getId();
        headBean.setBussflowno(tranFlow);// 流水号
        headBean.setTrancode("CP0023");//
        headBean.setRespCode("");
        headBean.setRespMsg("");
        CmbcAccountVerifyReqBean bodyBean = new CmbcAccountVerifyReqBean();
        bodyBean.setEndDate(getYesterdayString());
        bodyBean.setStartDate(getYesterdayString());
//        bodyBean.setEndDate("20160603");
//        bodyBean.setStartDate("20160603");
        bodyBean.setTranCode("ALL");
        bodyBean.setTranState("99");
        rootBean.setBody(bodyBean);
        rootBean.setHead(headBean);
        return rootBean;
    }
    /**
     * 获取前一天日期
     * @return
     */
    private Date getYesterday(){
    	Calendar calendar = Calendar.getInstance(); //得到日历
    	calendar.setTime(new Date());//把当前时间赋给日历
    	calendar.add(Calendar.DAY_OF_MONTH, -1);  //设置为前一天
    	Date dBefore = calendar.getTime();   //得到前一天的时间
    	return dBefore;
    }
    /**
     * 格式化前一天日期时间
     * @return
     */
    private String getYesterdayString(){
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        String formatStr = sdf.format(getYesterday());
        return formatStr;
    }
}
