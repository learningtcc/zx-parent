package com.ink.base.utils.lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author haoyunfeng
 * @date 2016/7/25
 */
public class DistributeLock extends  LockConstant{


    private int waitTime;
    private CuratorFramework client;
    private InterProcessMutex lock;
    private int maxLockTime;
    private Date lockTime;

    public DistributeLock(String connectStr,int waitTime,int maxLockTime){
        client = CuratorFrameworkFactory.newClient(connectStr, new ExponentialBackoffRetry(BASE_SLEEP_TIMEMS,Integer.MAX_VALUE));
        this.waitTime = waitTime;
        this.maxLockTime = maxLockTime;
    }

    public DistributeLock(String connectStr,int waitTime){
        this(connectStr,waitTime,DEFAULT_MAX_LOCK_TIME);
    }

    public DistributeLock(String connectStr){
        this(connectStr,DEFAULT_WAIT_TIME,DEFAULT_MAX_LOCK_TIME);
    }


    /**
     * 检验节点是否符合规则
     * @param path
     * @return
     */
    private boolean validate(String path){
        String array[] = path.split("/");
        for(String paths:LOCK_PATH){
            if(paths.equals(array[1])){
                return true;
            }
        }
        return false;
    }

    /**
     * 取得锁
     * @param path
     * @return
     */
    public boolean acquireLock(String path){
        boolean lockStatus =false;

        if(!validate(path)){ return lockStatus;}

        if(lock == null){
            initLock(path);
        }

        //如果超过强制解锁时间，解锁
        if(lockTime !=null){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(lockTime);
            calendar.add(Calendar.SECOND, maxLockTime);
            Date expire = calendar.getTime();
            if(expire.before(new Date())){
                realease(path);
            }
        }

        if(!client.getState().equals(CuratorFrameworkState.STARTED)){
            client.start();
        }

        try {
            lockStatus = lock.acquire(waitTime, TimeUnit.SECONDS);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(lockStatus){
            this.lockTime = new Date();
        }
        return lockStatus;
    }

    private void initLock (String path){
        this.lock = new InterProcessMutex(client, "/lock"+path);
    }

    /**
     * 释放锁
     * @return
     */
    public boolean realeaseLock(){
        if(!client.getState().equals(CuratorFrameworkState.STARTED)){
            client.start();
        }
        try {
            lock.release();
            this.lockTime = null;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 强制解锁
     * @return
     */
    public boolean realease(String path){
        if(!client.getState().equals(CuratorFrameworkState.STARTED)){
            client.start();
        }
        try {
            client.delete().deletingChildrenIfNeeded().forPath("/lock"+path);
            this.lockTime = null;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
