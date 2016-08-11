package com.ink.channel.core.ldyspay;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class LdysUtils {
	public static Map<String,String> getResolveReslutForSymbol(String resultStr,String symbol){
		Map<String,String> map=new HashMap<>();
		if(StringUtils.isBlank(resultStr)){
			return null;
		}
		String[] resultArrays = resultStr.split(symbol);
		for(String resultArray:resultArrays){
			String[] elementArray = resultArray.split("=");
			if(elementArray.length<=0){
				return null;
			}
			if(elementArray.length==2){
				map.put(elementArray[0],elementArray[1]);
			}else if(elementArray.length==1){
				map.put(elementArray[0],"");
			}else{
				String value="";
				for(int i=1;i<elementArray.length;i++){
					value+=elementArray[i];
				}
				map.put(elementArray[0],value);
			}
		}
		return map;
	}
	public static Map<String,String> getResolveReslutForString(String resultStr){
		return getResolveReslutForSymbol(resultStr, "\\|");
	}
	public static Map<String,String> getResolveReslutForHtml(String resultStr){
		 Document doc=Jsoup.parse(resultStr);
		 Element element = doc.select("META").first();
		 String responseResult = element.attr("CONTENT");
		return getResolveReslutForSymbol(responseResult, "&");
	}
	public static void main(String[] args) {
		/*String ss="amount=|amt_type=|mer_date=|mer_id=|mer_priv=|order_id=|pay_date=|ret_code=00200012|ret_msg=验证码已失效|settle_date=|sign_type=RSA|trade_state=|version=4.0|sign=M=Z=z=uETOqHu1e1F+nV0HxXldDotgr8b7q=ctHToMi+bToffo0RZp71KH7iIh7npVTXqJ0ujmUxPN5VKBM7F9s5xvnzneqegZAd9MKp21ZnKQPAPjqg+dgN1i+HhYau10ryo51gHqsZ21XMp8q4/iu4HZJPDQJ9jMpk0gugkPt8qLc=";
		Map<String, String> stt = LdysUtils.getResolveReslut(ss);
		System.out.println(stt);*/
		/*String sss="<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">"+
			"<html>"+
			  "<head>"+
				"<META NAME=\"MobilePayPlatform\" CONTENT=\"amount=1&amt_type=RMB&media_id=18612484909&media_type=MOBILE&mer_date=20160627&mer_id=3660&"
				+ "order_id=20160627151724dsaasd121&pay_date=20160627&pay_seq=20160627003352569030&pay_type=DEBITCARD&product_id=P15M0000&refund_amt=0&"
				+ "ret_code=0000&ret_msg=操作成功&settle_date=20160627&sign_type=RSA&trade_no=3606271517615191&trade_state=TRADE_SUCCESS&version=4.0&"
				+ "sign=dEM6lnY7XPWES5hlJs02SMJ5AlmWWRfOVhv8EndgiGWIqldOr6pNQuGLrr+1dCzqOlRAEBD0TW7vdJT8llFbEyqRHSfDjR0oxI0IjswKG6ZEB6NY6KwrDNTidg/fEATVXGmtZ21DcMfNN8AlT2b+latvL31YpZ+F8CFG1leRr4U=\">"+
			  "</head>"+
			  "<body>"+
			  "</body>"+
			"</html>";
		Object tt = LdysUtils.getResolveReslutForHtml(sss);
		System.out.println(tt);*/
	}
}
