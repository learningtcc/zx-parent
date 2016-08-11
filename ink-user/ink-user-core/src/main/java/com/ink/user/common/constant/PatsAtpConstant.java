package com.ink.user.common.constant;

/**
 * 
 * @ClassName: PatsAtpConstant 
 * @Description: 常量类 
 * @author guojie.gao 
 * @date 2015年10月15日 下午2:16:39 
 *
 */
public class PatsAtpConstant {

	//商户状态类型
	public static final int MCH_STATUS_1 = 1;//1-启用
	public static final int MCH_STATUS_2 = 2;//2-停用
	public static final int MCH_STATUS_3 = 3;//3-待审核
	public static final int MCH_STATUS_4 = 4;//4-审核拒绝 
	public static final int MCH_STATUS_9 = 9;//9-注销
	
	//物理删除标志
	public static final String DEL_FLAG_0 = "0";//0-正常
	public static final String DEL_FLAG_1 = "1";//1-删除
	
	//客户状态类型
	public static final int CUST_STATUS_1 = 1;//1-启用
	public static final int CUST_STATUS_2 = 2;//2-停用 
	public static final int CUST_STATUS_9 = 9;//9-注销
	
	
	//银行卡状态
	public static final int CARD_STATUS_1 = 1;//1-启用
	public static final int CARD_STATUS_2 = 2;//2-停用 
	public static final int CARD_STATUS_9 = 9;//9-注销
	
	//日期格式
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String TIME_FORMAT = "HH:mm:ss";
	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss.S";
}
