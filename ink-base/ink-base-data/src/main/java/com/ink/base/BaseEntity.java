package com.ink.base;
/**
 * @author king
 */
public class BaseEntity implements java.io.Serializable {

	private static final long serialVersionUID = -7200095849148417467L;

	protected static final String DATE_FORMAT = "yyyy-MM-dd";
	
	protected static final String TIME_FORMAT = "HH:mm:ss";
	
	protected static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	protected static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss.S";

	public String toString(String separator) {
		return super.toString();
	}
	
	/**
	 * 获取主键数值
	 * @return
	 */
	public String getPkValue() {
		return "";
	}
	
	/**
	 * 关键属性数值
	 * @return
	 */
	public String getKeyValue() {
		return "";
	}
}
