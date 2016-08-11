package com.ink.balance.api.constants;

/**
 * @author 作者 :bo.chen
 * @version 创建时间：2016年4月1日 下午5:36:57
 * @description 描述：日志的模块代码和业务代码常量定义
 */
public class LoggerCnst {
    /**
     * 功能模块代码代码
     */

    // 对账功能模块代码
    public static final String BALANCE_MOUDLE = "1002001";
    // 对账查询模块代码
    public static final String BALANCE_QUERY = "1002002";
    // 差异处理模块代码
    public static final String DIFFERENCE_HANDLE = "1002003";


    //----------------------------------------------------------

    /**
     * 业务代码(按流程分业务代码)
     */

    // 读取渠道数据文件解析、入库
    public static final String SPRING_BATCH_BUS = "10020010001";

    // 渠道、平台数据获取
    public static final String GET_DATA_BUS = "10020010002";

    // 执行对账
    public static final String EXE_BALANCE_BUS = "10020010003";

    // TFS功能
    public static final String TFS_DATA_BUS = "10020010004";

    // REIDS加载数据处理
    public static final String REDIS_DATA_BUS = "10020010001";

    // 设置对账状态及相关参数
    public static final String SET_BALANCE_BUS = "10020010001";

    //----------------

    //主对账查询参数
    public static final String CHECK_MAIN_QUERY_BUS = "10020020001";

    //对账差异查询参数
    public static final String CHECK_DIFFERENCE_QUERY_BUS = "10020020002";

    //渠道数据查询参数
    public static final String CHANNEL_QUERY_BUS = "10020020003";

    //平台数据查询参数
    public static final String PLATFORM_QUERY_BUS = "10020020004";

    //调账数据查询参数
    public static final String BALANCE_QUERY_BUS = "10020020005";

    //----------------

    //双单边差异处理
    public static final String TWO_SIDE_HANDLE_BUS = "10020030001";

    //调账差异处理
    public static final String BALANCE_DIFFERENCE_HANDLE_BUS = "10020030002";


}
