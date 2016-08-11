package com.ink.user.util;

import java.util.ResourceBundle;


public class ResourceBundleUtils {
	
	public  static Long getValue(String key,String fileName) {
		if(fileName==null||fileName.isEmpty()){
			return (Long.valueOf(getResouce("").getString(key)));
		}
	
		return (Long.valueOf(getResouce(fileName).getString(key)));
	}
	
	private static  ResourceBundle getResouce(String fileName) {
		if(fileName!=null&&!fileName.isEmpty()){
			return ResourceBundle.getBundle(fileName);
		}
		
		return ResourceBundle.getBundle("");
	}
	
	public static void main(String[] args) {
		System.out.println(ResourceBundleUtils.getValue("idKey", "conf/app"));
	}
}
