package com.ink.balance.api.constants;

/**
 * @author xuguoqi
 * @Description 对账系统返回值常量，主要是web远程调用service返回的状态结果值
 * @date 2016年4月20日 上午10:18:27
 */
public class BalanceCodeUtils {

    public final static int SUCCESS = 0;

    public final static String SUCCESS_MSG = "成功";

    public final static int DABASE_ERROR_CODE = -1;

    public final static String DABASE_ERROR_MSG = "数据库查询异常";

    public final static int RESULT_IS_NULL = -2;

    public final static String RESULT_IS_NULL_MSG = "查询结果为空";

    public final static int DUBBO_EXCEPTION = -100;

    public final static String DUBBO_EXCEPTION_MSG = "系统业务 繁忙";

}
