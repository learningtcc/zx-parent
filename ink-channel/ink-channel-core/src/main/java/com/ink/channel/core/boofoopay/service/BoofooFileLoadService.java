/**
 * yinker.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月5日 下午3:18:33
 */
package com.ink.channel.core.boofoopay.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipInputStream;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.IdCodeGenerator;
import com.ink.channel.core.boofoopay.fileLoad.http.HttpUtil;
import com.ink.channel.core.boofoopay.fileLoad.util.SecurityUtil;
import com.ink.channel.core.cnst.ChannelConstants;
import com.ink.tfs.client.TFSOperator;

/**
 * @Description 宝付对账文件拉取
 * @author xuguoqi
 * @date 2016年7月5日 下午3:18:33
 */
@Service("boofooFileLoadService")
public class BoofooFileLoadService {
	private YinkerLogger logger = YinkerLogger.getLogger(BoofooFileLoadService.class);
	@Autowired
	private IdCodeGenerator channelRelUtil;
	@Autowired
    private TFSOperator tFSOperator;
	@Autowired
	private IdCodeGenerator certificateUtil;
	
	private final String requestUrl = "boofoo_fileload_url";// 测试地址

	private final String channelMchIds = "boofoo_fi_channel_mchIds";//测试环境代收100000749  代付	100000178
	
	private final String fileloadClientIp="boofoo_fileload_client_ip";
	
	private final String asileNo="boofoo";
	
	/**
	 * 
	 * @Description 抓取收款对账文件
	 * @author xuguoqi
	 * @date 2016年7月5日 下午3:22:27
	 * @param channlMchId
	 *            渠道商户id
	 */
	public void fileLoadFiRequest() {
		logger.info(ChannelConstants.LOGGER_MODULE_NAME, "开始执行宝付代收对账文件下载");
		String asile_no=certificateUtil.getIpMapsConfig().get(asileNo);
		InputStream dateByte = null;
		ZipInputStream ginzip=null;
		String[] merchantnoArray = this.getMerchants();
		if (ArrayUtils.isEmpty(merchantnoArray)) {
			return;
		}
		try {
			String url = channelRelUtil.getIpMapsConfig().get(requestUrl);
			if (StringUtils.isEmpty(url)) {
				return;
			}
			for (String merchantId : merchantnoArray) {

				Map<String, String> requestParam = this.getRequestParam(merchantId, "fi");
				logger.info(ChannelConstants.LOGGER_MODULE_NAME, "HTTP请求参数："+requestParam);
				String PostString = HttpUtil.RequestForm(url.trim(), requestParam);
				logger.info(ChannelConstants.LOGGER_MODULE_NAME, "HTTP响应参数："+PostString);
				String[] splitstr = PostString.split("="); // 解板返回的文件参数
				int StrOf = PostString.indexOf("resp_code=0000");
				if (StrOf < 0) {
					return;
				}
				byte[] Restr;
				Restr = SecurityUtil.Base64Decode(splitstr[3]);
				// 进行base64解码，解密后为byte类型
				String fileName = asile_no+merchantId+"01"+getYesterdayString("yyyyMMdd") + ".txt"; // 存在本地的路径（自行设置）
				dateByte = new ByteArrayInputStream(Restr);// 把获取的zip文件的byte放入输入流
				 ginzip = new ZipInputStream(dateByte);
				ginzip.getNextEntry();
                String tFSRresult = tFSOperator.saveInputStreamToTfs(fileName, ginzip, "1001",asile_no, "timer");
                logger.info(ChannelConstants.LOGGER_MODULE_NAME, "TFS返回值："+tFSRresult);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (dateByte != null) {
				try {
					dateByte.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (ginzip != null) {
				try {
					ginzip.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		logger.info(ChannelConstants.LOGGER_MODULE_NAME, "结束执行宝付代收对账文件下载");
	}

	/**
	 * 
	 * @Description 抓取出款对账文件
	 * @author xuguoqi
	 * @date 2016年7月5日 下午4:50:25
	 * @param channlMchId
	 */
	public void fileLoadFoRequest() {
		logger.info(ChannelConstants.LOGGER_MODULE_NAME, "开始执行宝付代付对账文件下载");
		String asile_no=certificateUtil.getIpMapsConfig().get(asileNo);
		InputStream dateByte = null;
		ZipInputStream ginzip=null;
		String[] merchantnoArray = this.getMerchants();
		if (ArrayUtils.isEmpty(merchantnoArray)) {
			return;
		}
		String url = channelRelUtil.getIpMapsConfig().get(requestUrl);
		if (StringUtils.isEmpty(url)) {
			return;
		}
		try {
			for (String merchantId : merchantnoArray) {
				Map<String, String> requestParam = this.getRequestParam(merchantId, "fo");
				logger.info(ChannelConstants.LOGGER_MODULE_NAME, "HTTP请求参数："+requestParam);
				String PostString = HttpUtil.RequestForm(url.trim(), requestParam);
				logger.info(ChannelConstants.LOGGER_MODULE_NAME, "HTTP响应参数："+PostString);
				String[] splitstr = PostString.split("="); // 解板返回的文件参数
				int StrOf = PostString.indexOf("resp_code=0000");
				if (StrOf < 0) {
					return;
				}
				
				byte[] Restr;
				Restr = SecurityUtil.Base64Decode(splitstr[3]);
				// 进行base64解码，解密后为byte类型
				String fileName = asile_no+merchantId+"02"+getYesterdayString("yyyyMMdd") + ".txt"; // 存在本地的路径（自行设置）
				dateByte = new ByteArrayInputStream(Restr);// 把获取的zip文件的byte放入输入流
				ginzip = new ZipInputStream(dateByte);
				ginzip.getNextEntry();
                String tFSRresult = tFSOperator.saveInputStreamToTfs(fileName, ginzip, "1001", asile_no, "timer");
                logger.info(ChannelConstants.LOGGER_MODULE_NAME, "TFS返回结果："+tFSRresult);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (dateByte != null) {
				try {
					dateByte.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (ginzip != null) {
				try {
					ginzip.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		logger.info(ChannelConstants.LOGGER_MODULE_NAME, "结束执行宝付代付对账文件下载");
	}

	/**
	 * 
	 * @Description 获取宝付下所有商户
	 * @author xuguoqi
	 * @date 2016年7月6日 下午1:42:53
	 * @return
	 */
	private String[] getMerchants() {
		String allMerchantno = channelRelUtil.getIpMapsConfig().get(channelMchIds);
		if (StringUtils.isNotBlank(allMerchantno)) {
			String[] merchantnoArray = allMerchantno.split(",");
			return merchantnoArray;
		}
		return null;
	}

	/**
	 * 
	 * @Description 宝付请求参数封装
	 * @author xuguoqi
	 * @date 2016年7月6日 下午1:43:08
	 * @param channlMchId
	 * @param fileType
	 * @return
	 */
	private Map<String, String> getRequestParam(String channlMchId, String fileType) {

		Map<String, String> poststr = new HashMap<String, String>();

		poststr.put("version", "4.0.0.1");
		poststr.put("member_id", channlMchId);// 商户号
		poststr.put("file_type", fileType);// 收款：fi 出款：fo
		poststr.put("client_ip", channelRelUtil.getIpMapsConfig().get(fileloadClientIp));// 要与服务器IP保持一致
		poststr.put("settle_date", getYesterdayString("yyyy-MM-dd"));// 指定日期的对帐文件（除当天）
		return poststr;
	}

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
    private String getYesterdayString(String fomate){
    	SimpleDateFormat sdf=new SimpleDateFormat(fomate);
        String formatStr = sdf.format(getYesterday());
        return formatStr;
    }

}
