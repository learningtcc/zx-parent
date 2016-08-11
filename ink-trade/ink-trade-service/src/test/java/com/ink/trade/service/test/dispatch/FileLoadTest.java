/**
 * yinker.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月6日 下午2:14:19
 */
package com.ink.trade.service.test.dispatch;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ink.channel.core.boofoopay.service.BoofooFileLoadService;
import com.ink.channel.core.minsheng.service.CmccAccountVerifyService;
import com.ink.tfs.client.TFSOperator;

/**
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月6日 下午2:14:19
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class FileLoadTest {
	
	@Autowired
	private  CmccAccountVerifyService cmccAccountVerifyService;
	
	@Autowired
	private BoofooFileLoadService boofooFileLoadService;
	
	@Autowired
	private TFSOperator tFSOperator;
	
	@Test
	public void testMinshengFileload(){
		boolean flag = cmccAccountVerifyService.queryHistory();
		System.out.println("isSuccess:"+flag);
		
	}
	
	@Test
	public void testBoofooFileLoad(){
		boofooFileLoadService.fileLoadFiRequest();//收款
		boofooFileLoadService.fileLoadFoRequest();//出款
		
	}
	
	@Test
	public void testTFSFileDownload() throws IOException{
		String fileName= "xuguoqi_boofoo_02.txt";
		String sourceCode = "1001";
		String moduleCode = "10005";
		
		byte[] tfsByte = tFSOperator.getTfsByte(fileName, sourceCode, moduleCode);
		// 把获取的zip文件的byte放入输入流
		ByteArrayInputStream DateByte = new ByteArrayInputStream(tfsByte);
		String filePath="d:/"+fileName;
		File targetFile = new File(filePath);
		targetFile.createNewFile(); // 创建文件
		OutputStream outStream = new FileOutputStream(targetFile);
		byte[] by = new byte[1024];
		while (DateByte.available() > 0) {
			DateByte.read(by); // 读取接收的文件流
			outStream.write(by); // 写入文件
		}
		DateByte.close();
		outStream.flush();
		outStream.close();
	}

}
