package com.ink.platform.util;

import java.util.ArrayList;
import java.util.List;


public class StrSubStringUtil {

	/**
	 * 此类用来把字符串以 ，为截取段，截取字符串，返回list数组
	 * @param obj
	 * @return
	 */
	public static List<String> strSubStr(String obj){
		
		List<String> list = new ArrayList<>();
		// String str = "721936290643578880,721936428275470336,721936464094826496,724488760406183936,724492577285738496";  
	       String regex = ",";  
	       String[] strs = obj.split(regex);  
	       for(int i = 0; i < strs.length; i++) {  
	           System.out.printf("strs[%d] = %s%n", i, strs[i]);  
	           
	           list.add(strs[i]);
	       }
		return list;  
	}
	/**
	 * list集合：拼接字符串以，为分隔
	 * @param obj
	 * @return
	 */
	public static String strMosaic(List<Object> obj){
	//	String[] ary = {"abc", "123", "45"}; 
		if(obj.size()>0){
			StringBuffer sb = new StringBuffer();
			for(int i = 0; i < obj.size(); i++)	{
				sb. append(obj.get(i)+","); 
				} 
			String newStr = sb.toString();
			return  newStr.substring(0, newStr.length() - 1);
		}else{
			return null;
		}
	}
	/**
	 * 数组对象：转字符串拼接
	 * @param ary
	 * @return
	 */
	public static String strMosaic(String[] ary){
		//String[] ary = {"abc", "123", "45"}; 
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < ary.length; i++)	{
			sb. append(ary[i].trim()+","); 
			} 
		String newStr = sb.toString();
		System.out.println(newStr.substring(0, newStr.length() - 1));
		return  newStr.substring(0, newStr.length() - 1);
	}
	public static void main(String[] args) {

	}
}
	
