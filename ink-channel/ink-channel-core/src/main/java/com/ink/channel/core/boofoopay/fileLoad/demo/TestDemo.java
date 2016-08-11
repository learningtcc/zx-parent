/**
 * 
 */
/**
 * @author Administrator
 *
 */
package com.ink.channel.core.boofoopay.fileLoad.demo;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import com.ink.channel.core.boofoopay.fileLoad.http.HttpUtil;
import com.ink.channel.core.boofoopay.fileLoad.util.SecurityUtil;

import java.util.Date;
/*
 * 下载对账文件接口测试用例
 * 本例将对账文件下载到本地。
 * 用户通过解压文件，并解析压缩文件内的内容，
 * 本例公供学习宝付下载对账文件使用，以下代码公供参考
 * 
 * 测试前请联系宝付，绑定一个测试IP
 * 作者：宝付技术（大圣）
 * 日期：2016-01
 */
public class TestDemo{
	
	public static void main(String[] args) throws IOException{
		
		
		Map<String,String> poststr = new HashMap<String,String>();
		
		poststr.put("version", "4.0.0.1");
		poststr.put("member_id", "100000178");//商户号
		poststr.put("file_type", "fi");//收款：fi   出款：fo
		poststr.put("client_ip", "116.236.217.150");//要与服务器IP保持一致
		poststr.put("settle_date", "2016-07-04");//指定日期的对帐文件（除当天）
		
		String request_url = "https://tgw.baofoo.com/boas/api/fileLoadRequest";//测试请求地址
		 String PostString  = HttpUtil.RequestForm(request_url, poststr);	
		log("返回："+PostString);
		String[]  splitstr = PostString.split("=");	//解板返回的文件参数	
		int StrOf = PostString.indexOf("resp_code=0000");
		if(StrOf<0){			
			log("下载失败!");
			return ;
		}
		byte[] Restr= SecurityUtil.Base64Decode(splitstr[3]);//进行base64解码，解密后为byte类型
		String filename = "d:/"+getDateDay()+".zip";	//存在本地的路径（自行设置）
		log("存放路径："+filename);
		InputStream DateByte = new ByteArrayInputStream(Restr);//把获取的zip文件的byte放入输入流
		File targetFile = new File(filename); 
		targetFile.createNewFile(); //创建文件
		OutputStream outStream = new FileOutputStream(targetFile); 
		byte[] by = new byte[1024]; 
		while (DateByte.available() > 0) 
		{ 
			DateByte.read(by); //读取接收的文件流
			outStream.write(by); //写入文件
		} 
			DateByte.close(); 
			outStream.flush(); 
			outStream.close();
			log("下载成功！");
	}
	
	
	public static void log(String msg) {
		System.out.println(getTime() + "\t: " + msg);
	}
	
	private static String getTime() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
	
	private static String getDateDay() {
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}
	
}