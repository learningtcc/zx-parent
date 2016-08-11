package com.ink.base.log.job;

import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.slf4j.MDC;

import com.ink.base.log.util.IpUtils;
import com.ink.base.log.util.LogConst;
import com.ink.base.log.util.YinkerLogger;

/**
 * 任务日志记录模板类
 * Created by aiyungui on 2016/6/1.
 */
public abstract class JobLoggerable {

    /**
     * 初始化MDC
     */
    protected void init() {
        String serverIp = IpUtils.getLocalAddress();
        YinkerLogger loger = YinkerLogger.getLogger(JobLoggerable.class);

        MDC.put(LogConst.USER_NAME, "task" );//用户名
        MDC.put(LogConst.USER_ID, "task");//用户ID
        MDC.put(LogConst.REQ_ID,  StringUtils.remove(UUID.randomUUID().toString(),"-"));//请求流水
        MDC.put(LogConst.REQ_REMOTEADDR, serverIp); //用户IP
        MDC.put(LogConst.REQ_SERVERIP, serverIp); //服务ip
        MDC.put(LogConst.REQ_CONTEXT, loger.getLogContext());//请求工程
        MDC.put(LogConst.CONTEXT, loger.getLogContext());//响应工程
        MDC.put(LogConst.REQ_URI, this.getClass().getName());//请求url
        MDC.put(LogConst.LOG_SEQ, "1");//请求序列

    }

    /**
     * 子任务job
     */
    public void executeJob(){

        //初始化logger日志信息
        init();
        try{
            //执行业务逻辑
            executeService();
        }catch (Exception e){
            e.getMessage();
        }
        //清空logger日志信息
        clear();
    }

    /**
     *清空MDC
     */
    protected void clear() {
        MDC.clear();
    }

    /**
     * 执行任务业务逻辑信息
     */
    protected abstract void executeService();


}
