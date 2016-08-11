package com.ink.balance.core.util.oss;

import com.alibaba.fastjson.JSON;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.ink.balance.core.util.oss.consts.OssDef;
import com.ink.balance.core.util.oss.enums.OssTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @ClassName: OSSUtil
 * @Description 处理阿里云oss存储业务
 * @author bo.chen
 * @date 2016年07月14日 下午12:03:34
 */
@Component
public class OSSUtil {

	@Autowired
	OSSConfigCenter ossConfigCenter;

	/**
	 * @MethodName putFile
	 * @Description 通过文件路程将文件上传至OSS
	 * @param filePath 文件路径
	 * @return String 返回类型
	 * @author bo.chen
	 * @date 2016年07月14日 下午12:03:34
	 * @throws
	 */
	public  String putFile(String fileName, String filePath){
		OSSData data = new OSSData();
		data.setErrorcode("0000");
		@SuppressWarnings("unused")
		Map<String, String> map;

		Map<String,String> ossConfig = ossConfigCenter.getOssConfig();

		// 初始化OSSClient
		OSSClient client = new OSSClient(ossConfig.get(OssDef.OSS_END_POINT),
				ossConfig.get(OssDef.OSS_ACCESS_KEY_ID), ossConfig.get(OssDef.OSS_ACCESS_KEY_SECRET));

		// 获取指定文件的输入流
		File file = new File(filePath);
		InputStream content;
		try {
			content = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			//文件不存在
			client = null;
			e.printStackTrace();
			data.setErrorcode("0001");
			return JSON.toJSONString(data);
		}
		map = new HashMap<String, String>();
		// 创建上传Object的Metadata
		ObjectMetadata meta = new ObjectMetadata();
		// 必须设置ContentLength
		meta.setContentLength(file.length());

		// 上传Object.
		PutObjectResult result = client.putObject(ossConfig.get(OssDef.OSS_BUCKET_NAME), fileName, content, meta);
		// 打印ETag
		System.out.println(result.getETag());
		client = null;

		return JSON.toJSONString(data);
	}

	/**
	 * @MethodName putFileByStream
	 * @Description 使用流方式上传文件
	 * @param @param content
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author bo.chen
	 * @date 2016年07月14日 下午12:03:34
	 * @throws
	 */
	public  String putFileByStream(String path, String fileName, Long size, InputStream content){
		OSSData data = new OSSData();
		data.setErrorcode("0000");
		Map<String, String> map = new HashMap<String, String>();
		// 初始化OSSClient
		Map<String,String> ossConfig = ossConfigCenter.getOssConfig();
		OSSClient client = new OSSClient(ossConfig.get(OssDef.OSS_END_POINT),
				ossConfig.get(OssDef.OSS_ACCESS_KEY_ID), ossConfig.get(OssDef.OSS_ACCESS_KEY_SECRET));

		InputStream fileData = content;

		// 创建上传Object的Metadata
		ObjectMetadata meta = new ObjectMetadata();
		// 必须设置ContentLength
		meta.setContentLength(size);

		// 上传Object.
		PutObjectResult result = client.putObject(ossConfig.get(OssDef.OSS_BUCKET_NAME), path+"/"+fileName, fileData, meta);
		map.put("ETag", result.getETag());
		data.setData(map);

		return JSON.toJSONString(data);
	}

	/**
	 * 获取文件
	 * @param path
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public  byte[] getFile(String path, String fileName) throws IOException {
		// 初始化OSSClient
		Map<String,String> ossConfig = ossConfigCenter.getOssConfig();
		OSSClient client = new OSSClient(ossConfig.get(OssDef.OSS_END_POINT),
				ossConfig.get(OssDef.OSS_ACCESS_KEY_ID), ossConfig.get(OssDef.OSS_ACCESS_KEY_SECRET));

		// 获取Object，返回结果为OSSObject对象
		OSSObject object = client.getObject(ossConfig.get(OssDef.OSS_BUCKET_NAME), path + "/" + fileName);

		// 获取ObjectMeta
		ObjectMetadata meta = object.getObjectMetadata();

		int count = (int)meta.getContentLength();

		byte[] file = new byte[count];

		// 获取Object的输入流
		InputStream objectContent = object.getObjectContent();

		int readCount = 0; // 已经成功读取的字节的个数
		while (readCount < count) {
			readCount += objectContent.read(file, readCount, count - readCount);
		}
		objectContent.read(file);
		objectContent.close();

		return file;
	}

	/**
	 * 上传文件，返回文件key
	 */
	public  String putFile(OssTypeEnum type, String filePath){
		OSSData data = new OSSData();
		data.setErrorcode("0000");
		@SuppressWarnings("unused")
		Map<String, String> map;
		// 初始化OSSClient
		Map<String,String> ossConfig = ossConfigCenter.getOssConfig();
		OSSClient client = new OSSClient(ossConfig.get(OssDef.OSS_END_POINT),
				ossConfig.get(OssDef.OSS_ACCESS_KEY_ID), ossConfig.get(OssDef.OSS_ACCESS_KEY_SECRET));

		// 获取指定文件的输入流
		File file = new File(filePath);
		InputStream content;
		try {
			content = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			//文件不存在
			client = null;
			e.printStackTrace();
			data.setErrorcode("0001");
			return JSON.toJSONString(data);
		}
		map = new HashMap<String, String>();
		// 创建上传Object的Metadata
		ObjectMetadata meta = new ObjectMetadata();
		// 必须设置ContentLength
		meta.setContentLength(file.length());
		// 获取上传路径
		String key = getFileName(type);

		// 上传Object.
		PutObjectResult result = client.putObject(ossConfig.get(OssDef.OSS_BUCKET_NAME), getPathByKey(key), content, meta);
		// 打印ETag
		System.out.println(result.getETag());
		return key;
	}

	/**
	 * 上传文件流，返回key
	 */
	public  String putFile(OssTypeEnum type, Long size, InputStream content){
		String key = getFileName(type);
		// 初始化OSSClient
		Map<String,String> ossConfig = ossConfigCenter.getOssConfig();
		OSSClient client = new OSSClient(ossConfig.get(OssDef.OSS_END_POINT),
				ossConfig.get(OssDef.OSS_ACCESS_KEY_ID), ossConfig.get(OssDef.OSS_ACCESS_KEY_SECRET));
		// 创建上传Object的Metadata
		ObjectMetadata meta = new ObjectMetadata();
		// 必须设置ContentLength
		meta.setContentLength(size);

		// 上传Object.
		PutObjectResult result = client.putObject(ossConfig.get(OssDef.OSS_BUCKET_NAME), getPathByKey(key), content, meta);

		return key;
	}

	/**
	 * 根据文件key获取文件流
	 * @param key
	 * @return
	 */
	public  byte[] getFileByKey(String key) throws Exception{
		// 初始化OSSClient
		Map<String,String> ossConfig = ossConfigCenter.getOssConfig();
		OSSClient client = new OSSClient(ossConfig.get(OssDef.OSS_END_POINT),
				ossConfig.get(OssDef.OSS_ACCESS_KEY_ID), ossConfig.get(OssDef.OSS_ACCESS_KEY_SECRET));
		// 获取Object，返回结果为OSSObject对象
		OSSObject object = client.getObject(ossConfig.get(OssDef.OSS_BUCKET_NAME), getPathByKey(key));

		// 获取ObjectMeta
		ObjectMetadata meta = object.getObjectMetadata();

		int count = (int)meta.getContentLength();

		byte[] file = new byte[count];
		// 获取Object的输入流
		InputStream objectContent = object.getObjectContent();

		int readCount = 0; // 已经成功读取的字节的个数
		while (readCount < count) {
			readCount += objectContent.read(file, readCount, count - readCount);
		}
		objectContent.read(file);
		objectContent.close();

		return file;
	}



	/**
	 * 根据文件key获取文件路径
	 */
	public static String getPathByKey(String key){
		int type = Integer.parseInt(key.substring(0,2));
		String rootPath = OssTypeEnum.getPathByType(type);
		String yy = key.substring(2, 4);
		String mm = key.substring(4, 6);
		String dd = key.substring(6, 8);
		StringBuffer sb = new StringBuffer(rootPath).append('/')
				.append(yy).append('/')
				.append(mm).append('/')
				.append(dd).append('/')
				.append(key);
		return sb.toString();
	}

	/**
	 * 生成文件名
	 * @规则： type + yy + MM + dd + UUID
	 */
	private static String getFileName(OssTypeEnum type){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
		int date = Integer.parseInt(dateFormat.format(new Date()));
		String formatDate = getFormatInt(date);
		String uuid = UUID.randomUUID().toString().split("-")[0];
		StringBuffer sb = new StringBuffer(String.format("%02d", type   .getType()))
				.append(formatDate).append(uuid);
		return sb.toString();
	}

	private static String getFormatInt(long num){
		char[] chars = (num+"").toCharArray();
		char[] result = new char[chars.length];
		for (int i=0; i<chars.length; i++){
			result[i] = (char)(chars[i]+17);
		}
		return new String(result);
	}


}
