package com.ink.base.log.util;


import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import ch.qos.logback.classic.LoggerContext;


public class YinkerLogger {

    private Logger logger;
    private String className;
    private static LoggerContext loggerContext = (LoggerContext) LoggerFactory
            .getILoggerFactory();

    public static YinkerLogger getLogger(Class clazz){
        return new YinkerLogger(clazz);
    }

    public static YinkerLogger getLogger(String name){
        return new YinkerLogger(name);
    }

    @Deprecated
    public YinkerLogger(Class clazz){
        className = clazz.getName();
        logger = LoggerFactory.getLogger(clazz);
    }
    
    @Deprecated
    public YinkerLogger(String name){
        className = name;
        logger = LoggerFactory.getLogger(name);
    }
    
    @Deprecated
    public YinkerLogger(){
       
    }

    /**
     * 系统日志Debug接口<br>
     * @author wangxk
     * @param message 消息
     */
    public void debug(String message){
    	if(logger.isDebugEnabled()){
    		logger.debug(message);
            autoIncrSeq();
    	}
    }

    /**
     * 执行时间日志接口，INFO级别
     * @author wangxk
     * @param module 模块
     */
    public void time(String module, long time){

        MDC.put("op.time", "executeTime");
        MDC.put("op.module", module);

        logger.info(String.valueOf(time)+"ms");

        MDC.remove("op.time");
        MDC.remove("op.module");
        autoIncrSeq();
    }

    /**
     * 执行时间日志接口，INFO级别
     * @author wangxk
     * @param module 模块
     * @param method 方法名
     */
    public void time(String module, String method, long time){

        MDC.put("op.time", "executeTime");
        MDC.put("op.module", module);
        MDC.put("op.method", method);

        logger.info(String.valueOf(time)+"ms");

        MDC.remove("op.time");
        MDC.remove("op.module");
        MDC.remove("op.method");

        autoIncrSeq();
    }

    /**
     * 系统日志info接口<br>
     * @author wangxk
     * @param content 操作内容
     */
    public void info(String content){
        logger.info(content);

        autoIncrSeq();
    }

    /**
     * info <p>系统info日志</p>
     * <p>于2016年3月18日 由 guokemeng 创建 </p>
     * @author  <p>当前负责人 guokemeng</p>
     * @param content
     * @param arguments
     */
    public void info(String content,Object... arguments){
        logger.info(content,arguments);

        autoIncrSeq();
    }

    /**
     * 业务日志info接口<br>
     * @author wangxk
     * @param module 模块代码
     * @param content 操作内容
     */
    public void info(String module, String content){
        info(module, null, content);
    }

    /**
     *
     * @param module    模块代码
     * @param infoId     业务ID
     * @param content   操作内容
     * @param treadId  流水ID
     */
    public void info(String module, String infoId, String content,String treadId){
        info(module, infoId, content, null,treadId);
    }
    /**
     * 业务日志info接口<br>
     * @author wangxk
     * @param module 模块代码
     * @param infoId 业务ID
     * @param content 操作内容
     */
    public void info(String module, String infoId, String content){
        info(module, infoId, content, null,null);
    }

    /**
     * 业务日志info接口<br>
     * @author wangxk
     * @param module 模块代码
     * @param content 操作内容
     * @param result 操作结果
     */
    public void info(String module, String content, Boolean result){
        info(module, null, content, result,null);
    }

    /**
     * 业务日志info接口<br>
     * @author wangxk
     * @param module 模块代码
     * @param infoId 业务代码
     * @param content 操作内容
     * @param result 操作结果
     */
    public void info(String module, String infoId, String content, Boolean result,String treadId){

        //标识是业务日志
        //  MDC.put("op.type", "bizType");
        MDC.put("op.module", module);
        if(result != null){
            MDC.put("op.result", true == result ? "成功":"失败");
        }
        if(infoId!=null&&!"".equals(infoId)){
            MDC.put("op.infoId", infoId);
        }
        if(treadId!=null&&!"".equals(treadId)){
            MDC.put("tread.id", treadId);
        }else {
            MDC.put("tread.id", "");
        }
        
        logger.info(content);

        MDC.remove("op.module");
        MDC.remove("op.result");
        MDC.remove("op.type");
        MDC.remove("op.infoId");
        MDC.remove("tread.id");

        autoIncrSeq();
    }
    
    public void data(String msg){
    	MDC.put("op.type", "DATA");
    	logger.info(msg);
    	MDC.remove("op.type");
    }

    /**
     * 系统日志error接口<br>
     * @author wangxk
     * @param msg 消息
     * @since 2012年3月30日 16:54:48
     */
    public void error(String msg) {
        logger.error(msg);

        autoIncrSeq();
    }

    /**
     * 系统日志error接口<br>
     * @author wangxk
     * @param msg 消息
     * @param throwable 异常
     */
    public void error(String msg, Throwable throwable) {
        logger.error(msg, throwable);

        autoIncrSeq();
    }

    /**
     * 业务日志error接口<br>
     * @author wangxk
     * @param module 模块代码
     * @param content 操作内容
     */
    public void error(String module, String content) {
        error(module, null, content,null);
    }

    /**
     *  业务日志error接口<br>
     * @param module
     * @param infoId
     * @param content
     * @param treadId
     */
    public void error(String module, String infoId, String content,String treadId) {
        error(module, infoId, content, null,treadId);
    }

    /**
     * 业务日志error接口<br>
     * @author wangxk
     * @param module 模块代码
     * @param content 操作内容
     * @param t
     */
    public void error(String module, String content, Throwable t) {
        error(module, null, content, t,null);
    }

    /**
     * 业务日志error接口<br>
     * @author wangxk
     * @param module 模块代码
     * @param infoId 业务ID
     * @param content 操作内容
     * @param t
     */
    public void error(String module, String infoId, String content, Throwable t,String treadId) {

        //标识是业务日志
        MDC.put("op.type", "bizType");
        MDC.put("op.module", module);
        MDC.put("op.result", "失败");
        if(infoId!=null&&!"".equals(infoId)){
            MDC.put("op.infoId", infoId);
        }
        if(treadId!=null&&!"".equals(treadId)){
            MDC.put("tread.id", treadId);
        }else {
            MDC.put("tread.id", "");
        }

        if(t == null){
            logger.error(content);
        }else {
            content = content +"\r\n"+getExceptionMessage(t);
            logger.error(content, t);
        }

        MDC.remove("op.module");
        MDC.remove("op.result");
        MDC.remove("op.type");
        MDC.remove("tread.id");
        MDC.remove("op.infoId");

        autoIncrSeq();
    }

    private String getExceptionMessage(Throwable t) {
        StringBuffer remark=new StringBuffer("异常类型:"+t.getClass().getName()+";异常信息:"+t.getMessage()+"  ");
        StackTraceElement[] trace=t.getStackTrace();
        for (int i=0; i < trace.length; i++){
            remark.append("\r\n"+trace[i]+";");
            if(remark.length()>800){
                break;
            }
        }

        return remark.toString();
    }

    public String getLogContext(){
        return loggerContext.getName();
    }

    /**
     * 日志序列自增
     */
    public void autoIncrSeq(){
        String temp = MDC.get(LogConst.LOG_SEQ);
        if(StringUtils.isNumeric(temp)){
            int logSeq = Integer.parseInt(temp);
            MDC.put(LogConst.LOG_SEQ, String.valueOf(++logSeq));
        }
    }

    public boolean isDebugEnabled(){
        return logger.isDebugEnabled();
    }
}