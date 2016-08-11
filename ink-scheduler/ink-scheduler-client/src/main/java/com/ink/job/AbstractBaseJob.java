package com.ink.job;

import com.ink.base.log.util.LogConst;
import com.ink.base.log.util.YinkerLogger;
import com.ink.util.HttpClientUtils;
import org.slf4j.MDC;

import java.util.HashMap;
import java.util.Map;

/**
 * 任务抽象类
 * @author haoyunfeng
 * @date 2016/4/20
 */
public abstract class AbstractBaseJob implements Runnable{

    private YinkerLogger loger=  YinkerLogger.getLogger(AbstractBaseJob.class);

    /** 任务名称 */
    protected String key;

    /** 缓存锁 */
    protected String unlockURL;

    protected String jobSerialId;

    public abstract void execute() throws Exception;

    @Override
    public void run() {
        loger.info("timer client","run","execute job "+key+" begining");
        //通过获取当前对象的代理对象调用execute方法；避免内部调用方法无法被aop拦截
//        if(null != AopContext.currentProxy()) {
//            ((AbstractBaseJob)AopContext.currentProxy()).execute(key,unlockURL);
//        }else {
//              execute(key,unlockURL);
//        }

        String errorMsg ="";
        //执行任务
        try{
            execute();
        }catch (Exception e){
            loger.error("timer","execute job "+key+" fail");
            errorMsg = e.getMessage();
        }

        //解锁数据库锁
        unlock(errorMsg);
        loger.info("timer client","run","execute job "+key+" end");
    }

    private void unlock(final String errorMsg){
        try{
            loger.info("timer","after job","更新数据库版本（version锁）");
            //taskJobService.updateJobVersion(key);
            Map<String,String> resultMap= HttpClientUtils.execute(unlockURL, new HashMap<String, Object>(){
                {
                    put("key", key);
                    put("jobSerialId",jobSerialId);
                    put("reqId", MDC.get(LogConst.REQ_ID));
                    put("errorMsg", errorMsg);
                }
            }, 20000);
            if(resultMap.get("result").equals("true")){
                //更新成功do nothing
            }else {
                loger.info("timer","after job","更新数据库版本（version锁）出错");
            }
        }catch (Exception e){
            loger.info("timer","after job","更新数据库版本（version锁）出错");
            e.printStackTrace();
        }
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setUnlockURL(String unlockURL) {
        this.unlockURL = unlockURL;
    }

    public void setJobSerialId(String jobSerialId) {
        this.jobSerialId = jobSerialId;
    }
}
