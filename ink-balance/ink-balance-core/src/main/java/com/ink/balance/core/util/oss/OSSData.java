package com.ink.balance.core.util.oss;

import java.util.Map;

/**
 * @ClassName: OSSData
 * @Description (这里用一句话描述这个类的作用)
 * @author bo.chen
 * @date 2016年07月14日 下午12:03:34
 */
public class OSSData {
	
	private String errorcode;
	private Map<String, String> data;
	/**
	 * @return the errorcode
	 */
	public String getErrorcode() {
		return errorcode;
	}
	/**
	 * @param errorcode 要设置的 errorcode
	 */
	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}
	/**
	 * @return the data
	 */
	public Map<String, String> getData() {
		return data;
	}
	/**
	 * @param data 要设置的 data
	 */
	public void setData(Map<String, String> data) {
		this.data = data;
	}
	

}
