package com.ink.channel.core.quickpay.batch;

import java.io.IOException;
import java.util.Properties;


public class SFTPInfo {
	public static Properties getFTPInfo(String path){
		Properties sftpInfo=new Properties();
		try {
			sftpInfo.load(SftpUtil.class.getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sftpInfo;
	}
}
