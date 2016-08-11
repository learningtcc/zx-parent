package com.ink.scheduler.core.util;

/**
 * @author haoyunfeng
 * @date 2016/4/18
 */
public final class TaskJobConstants {
    public static final String STATUS_RUNNING = "1";
    public static final String STATUS_FORBIDDEN = "0";
    public static final String STATUS_DELETE = "2";
    public static final String JOB_STATUS_RUNNING = "0";
    public static final String JOB_STATUS_NOT_RUNNING = "1";
    public static final String CONCURRENT_IS = "1";
    public static final String CONCURRENT_NOT = "0";

    public static final String TASK_JOB_LOCK = "0";       //加锁
    public static final String TASK_JOB_UNLOCK = "1";     //解锁

    public static final String JOB_HTTP_DEFAULT_CLASS = "YinkerJob";  //http任务默认class名

    public static final String VERSION_LOCK="1";        //版本控制（加锁）
    public static final String VERSION_UNLOCK="0";      //版本控制（解锁）

    public static final String JOB_HTTP_STATUS_CODE = "statusCode";
    public static final String JOB_REQ_ID = "reqId";

    public static final String JOB_EXECUTE_STATUS_SUCCESS = "0";   //任务执行结果 成功
    public static final String JOB_EXECUTE_STATUS_ERROR = "1";     //任务执行结果 失败
}
