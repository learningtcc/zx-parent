package com.ink.channel.core.quickpay.batch;

import com.bill99.schema.fo.settlement.SettlementPkiApiRequest;
import com.bill99.schema.fo.settlement.SettlementPkiApiResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


/**
 * webservices 请求应答码
 * */
public class FoApiPkiWSClient {
	//private static final String URL = "http://192.168.13.203:8060/fo-batch-settlement/services";
	private static final String URL = "https://sandbox.99bill.com/fo-batch-settlement/services";
	//private static final String URL = "https://101.227.69.170:443/fo-batch-settlement/services";
	
//	public static final String URL = "http://211.148.7.244/fo-batch-settlement/services";
	private static String responseXML="";//返回的xml
	/**
	 * 用于把请求信息发送给快钱的webservices服务，同时拿到对应的应答信息
	 * */
	public static SettlementPkiApiResponse doit(SettlementPkiApiRequest request) {
		SettlementPkiApiResponse response = null;
		InputStreamReader isr=null;
		BufferedReader br=null;
		try {
			//创建URL
			URL urlString = new URL(FoApiPkiWSClient.URL);
			URLConnection urlConn = urlString.openConnection();
			urlConn.setRequestProperty("content-type","text/xml;charset=utf-8");
			urlConn.setDoOutput(true);
			urlConn.setReadTimeout(1200000);
//			urlConn.setConnectTimeout(1200000); 
			PrintWriter out = new PrintWriter(urlConn.getOutputStream());
			String postContent = StringUtils.ReqFormat(CustomerUtil.settlementPkiApiRequestToXml(request));
			if (postContent == null){
				return null;
			}
			out.print(postContent);
			out.close();
			urlConn.connect();
			
			/*获取服务器端返回信息*/
			//isr=new InputStreamReader(urlConn.getInputStream());
			isr=new InputStreamReader(urlConn.getInputStream(),"utf-8"); //解决乱码错配合61行
			//isr = new InputStreamReader((InputStream)urlConn.getContent(),"utf-8");
			StringBuffer sb=new StringBuffer();
			if(isr!=null){
				br = new BufferedReader(isr);
	            String inputLine="";
	            while ((inputLine = br.readLine())!= null){
	                sb.append(inputLine);
	            }
			}
            String sbr=new String(sb.toString().getBytes());

            //String sbr=new String(sb.toString().getBytes(),"utf-8");
			if (sbr.length() > 0) {
				System.out.println(sbr);
				responseXML=StringUtils.ResFormat(sbr);
				response=CustomerUtil.xmlToSettlementPkiApiResponse(responseXML);
			}
		} catch (MalformedURLException e) {
			System.out.println(e.toString());
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.toString());
		} catch (IOException e) {
			System.out.println(e.toString());
		}finally{
			try {
				br.close();
				isr.close();
			} catch (IOException e) {
				br=null;
				isr=null;
				e.printStackTrace();
			}
		}
		return response;
	}
	public static String getResponseXML() {
		return responseXML;
	}
	public static void setResponseXML(String responseXML) {
		FoApiPkiWSClient.responseXML = responseXML;
	}
}
