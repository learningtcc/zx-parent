package com.ink.base.utils.logUtil;

import org.slf4j.MDC;


/***
 *
 */
public class TraceUtil {
    public static String traceParam = "trade.id";

    public static void traceMark(String traceId){
        MDC.put(traceParam, traceId);
    }

    public static void removeTraceMark(){
        MDC.remove(TraceUtil.traceParam);
    }

    public static String getTraceId(){
        return MDC.get(TraceUtil.traceParam);
    }

    public static void main (String asdf[]){
        TraceUtil.traceMark("test111");
        System.out.println(TraceUtil.getTraceId());
    }
}
