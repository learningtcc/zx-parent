package com.ink.user.ext.api.constant;

public class UserMessageConstant {
	public static final int DEL_FLAG_FALSE = 0;
	public static final int DEL_FLAG_TURE = 1;
	
	public static final int SendType_Now = 0;// 及时发送
	public static final int SendType_Delay = 1;// 延时发送
	
	public static final int SENDINFO_STATUS_SAVED = 0; //已保存
	public static final int SENDINFO_STATUS_SENT = 1; //已发送
	public static final int SENDINFO_STATUS_DELETE = 2; //已删除
	
	public static final int USERINFO_STATUS_NORMAL = 0;//正常
	public static final int USERINFO_STATUS_CANCELED = 1;//注销
	
	public static final int USERMESSAGELOG_STATUS_SENT = 0;//已发送
	public static final int USERMESSAGELOG_STATUS_SENT_SUCCESS = 1;//发送成功
	public static final int USERMESSAGELOG_STATUS_SENT_FAIL = 2;
}
