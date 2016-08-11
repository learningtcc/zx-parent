package com.test;


import com.ink.base.log.rabbit.core.MessageLog;
import com.ink.monitor.core.po.MonitorMqLog;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by Lenovo on 2016/7/26.
 */
public class BeanCopyTest {

    public static void main(String args[]){

        MessageLog messageLog = new MessageLog();
        messageLog.setClassName("aiyungui");
        messageLog.setExchange("key");
        messageLog.setLocalAddress("1234545");
        messageLog.setSysCode("00000");


        try {
            MonitorMqLog monitorMqLog = new MonitorMqLog();
            BeanUtils.copyProperties(monitorMqLog,messageLog);
            System.out.println(monitorMqLog);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
