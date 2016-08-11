package com.ink.channel.core.ldyspay.service;

import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.ConnectTimeoutException;

import com.ink.base.log.util.YinkerLogger;
import com.ink.channel.core.cnst.ChannelConstants;
import com.ink.channel.core.ldyspay.LdysConstant;
import com.ink.channel.core.ldyspay.LdysUtils;
import com.ink.channel.core.utils.HttpClientUtils;
import com.umpay.api.common.ReqData;
import com.umpay.api.paygate.v40.Mer2Plat_v40;

public class LdysVerifyUserInfoServiceImpl {
	private static YinkerLogger logger = YinkerLogger.getLogger(LdysVerifyUserInfoServiceImpl.class);
	
	@SuppressWarnings("unchecked")
	public void verifyUserInfo(){
		try {
			Map<String,Object> map = new HashMap<>();  
			map.put("service","comm_auth");//接口名称
			map.put("charset",LdysConstant.CHARSET);//参数字符编码集
			map.put("mer_id","3660");  //商户编号
			map.put("sign_type",LdysConstant.SING_TYPE) ; //签名方式
			map.put("version","1.0") ; //版本号
			map.put("auth_type","1");  //验证类型
			map.put("auth_mode","0")  ;//验证模式
			map.put("order_id","20160628220985327") ; //商户订单号
			map.put("bank_account","6230580000021112391"); // 银行卡号
			map.put("account_name","刘欢") ; //账户姓名
			map.put("identity_type","1")  ;//证件类型
			map.put("identity_code","371581199105253567") ; //证件号码
			map.put("mobile_id","15101663327") ; //手机号
			map.put("cvv2","")  ;//
			map.put("endDate","")  ;//
			ReqData reqDataPost = Mer2Plat_v40.makeReqDataByPost(map);  
			      
			String post_url = reqDataPost.getUrl();   //post请求的地址  
			Map<String,Object> fieldmap = reqDataPost.getField();    //post请求的表单数据
			String resultStr = (String)HttpClientUtils.execute(post_url,fieldmap);
			Map<String, String> returnMap = LdysUtils.getResolveReslutForHtml(resultStr);
			logger.info(ChannelConstants.LOGGER_MODULE_NAME,"四要素验证结果"+returnMap);
		}catch(ConnectTimeoutException  connectTimeoutException){
			logger.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.LDYS_QUICK_AGAIN_PAY_CODE, "联动优势再次支付接口"+ChannelConstants.REQUEST_EXCEPTION_MSG,connectTimeoutException,null);
	    }catch(SocketTimeoutException socketTimeoutException){
			logger.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.LDYS_QUICK_AGAIN_PAY_CODE, "联动优势再次支付接口"+ChannelConstants.REQUEST_EXCEPTION_MSG,socketTimeoutException,null);
	    } catch (Exception e) {
			e.printStackTrace();
			logger.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.LDYS_QUICK_AGAIN_PAY_CODE, e.getMessage(),e,null);
		} 
	}
}
