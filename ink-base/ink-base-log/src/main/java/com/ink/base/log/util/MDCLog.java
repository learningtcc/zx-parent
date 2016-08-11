package com.ink.base.log.util;

import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.slf4j.MDC;

public class MDCLog {
	
	private static YinkerLogger loger = YinkerLogger.getLogger(MDCLog.class);
	
	public static void setMdcConfig(String logStr,String guest){
		
		if(StringUtils.isBlank(logStr)){
			MDC.put(LogConst.USER_ID, guest);
    		MDC.put(LogConst.USER_NAME, guest);
    		MDC.put(LogConst.REQ_ID, StringUtils.remove(UUID.randomUUID().toString(),"-"));
    		MDC.put(LogConst.REQ_REMOTEADDR, "0.0.0.0");
			MDC.put(LogConst.REQ_SERVERIP, IpUtils.getLocalAddress());
			MDC.put(LogConst.REQ_CONTEXT, "");
			MDC.put(LogConst.REQ_URI, "");
			MDC.put(LogConst.LOG_SEQ, String.valueOf(1));
			MDC.put(LogConst.SESSION_ID, "") ;
			
		}else{
			String log = new String(Base64.decodeBase64(logStr));
			
	    	String[] logArray = log.split("\\|");
	    	
	    	if(logArray.length >= 9){
	    		MDC.put(LogConst.USER_ID, logArray[0]);
	    		MDC.put(LogConst.USER_NAME, logArray[1]);
	    		MDC.put(LogConst.REQ_ID, logArray[2]);
	    		MDC.put(LogConst.REQ_REMOTEADDR, logArray[3]);
				MDC.put(LogConst.REQ_SERVERIP, IpUtils.getLocalAddress());
				MDC.put(LogConst.REQ_CONTEXT, logArray[5]);
				MDC.put(LogConst.REQ_URI, logArray[6]);
				MDC.put(LogConst.LOG_SEQ, logArray[7]);
				MDC.put(LogConst.SESSION_ID, logArray[8]) ;
	    	}else{//解决服务调用方未配置dubbo filter的问题
	    		MDC.put(LogConst.USER_ID, guest);
	    		MDC.put(LogConst.USER_NAME, guest);
	    		MDC.put(LogConst.REQ_ID, StringUtils.remove(UUID.randomUUID().toString(),"-"));
	    		MDC.put(LogConst.REQ_REMOTEADDR, "0.0.0.0");
				MDC.put(LogConst.REQ_SERVERIP, IpUtils.getLocalAddress());
				MDC.put(LogConst.REQ_CONTEXT, "");
				MDC.put(LogConst.REQ_URI, "");
				MDC.put(LogConst.LOG_SEQ, String.valueOf(1));
				MDC.put(LogConst.SESSION_ID, "") ;
	    	}
		}
    	//获取当前应用的context
    	MDC.put(LogConst.CONTEXT, loger.getLogContext());
		
	}
	
	public static void clear(){
		MDC.clear();
	}
	
	
	/**
	 * 设置线程池中线程的日志参数
	 * 用于spring batch中的线程池
	 */
	public static void setThreadPoolLog(){
		setMdcConfig(null, "threadPool");
	}

}
