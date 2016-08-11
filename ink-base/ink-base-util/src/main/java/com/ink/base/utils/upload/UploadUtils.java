package com.ink.base.utils.upload;

import java.io.File;
import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.lang3.StringUtils;

import com.ink.base.utils.CommonUtil;

public class UploadUtils {

	
	public static String saveMongoFile(File file){
		 //创建默认的 HttpClient 实例  
       HttpClient httpClient = new HttpClient();  
       PostMethod postMethod = new PostMethod(CommonUtil.MONGOGFS_UPLOADPATH);
       int statusCode = 0;
       String str="";
       String fileId="";
       NameValuePair[] postData=new NameValuePair[1];
       Part[] parts = new Part[1];
       boolean success=false;
       try
		{
       	parts[0] =new FilePart("file", file);
           postMethod.setRequestEntity(new MultipartRequestEntity(parts,postMethod.getParams()));
			statusCode = httpClient.executeMethod(postMethod);
			str = postMethod.getResponseBodyAsString();
			System.err.println(str);
			net.sf.json.JSONObject jobj = net.sf.json.JSONObject.fromObject(str);
			success = (Boolean) jobj.get("success");
			if(success){
				net.sf.json.JSONObject row = jobj.getJSONObject("row");
				fileId=row.getString("filename");
			}else{
				throw new HttpException("上传文件失败");	
			}
		} catch (HttpException ex)
		{
			ex.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		postMethod.releaseConnection();
		if(success&&StringUtils.isNotBlank(fileId)){
			return fileId;
		}else
			return null;
	}
	
	
	
}
