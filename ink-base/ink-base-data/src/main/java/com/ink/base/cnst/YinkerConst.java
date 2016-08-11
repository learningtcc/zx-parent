package com.ink.base.cnst;
/**
 * 平台常量类
 * @author king
 * @time  2015年7月17日 上午9:27:56
 */
public interface YinkerConst {
    /**
     * 逻辑值：否
     */
    int 	ALIAS_NO = 0;
    /**
     * 逻辑值：是
     */
    int 	ALIAS_YES = 1;

    /**
     * 有效
     */
    int 	ALIAS_ENABLE = 0;
    /**
     * 无效
     */
    int 	ALIAS_DISNABLE = 1;



    /**
     * 异常编码未设置，默认值
     */
    int 	ALIAS_EXCEPTION_CODE_INIT = 900000;
    String 	ALIAS_EXCEPTION_MSG_INIT = "异常编码未设置";
    /**
     * 表不存在
     */
    int 	ALIAS_EXCEPTION_CODE_DB_TABLENOTFOUND = 900001;
    String 	ALIAS_EXCEPTION_MSG_DB_TABLENOTFOUND = "数据库中表不存在";
    /**
     * 重复键值（主键重复等）
     */
    int 	ALIAS_EXCEPTION_CODE_DB_DUPLICATEKEY = 900002;
    String 	ALIAS_EXCEPTION_MSG_DB_DUPLICATEKEY = "键值重复";
    /**
     * 不唯一
     */
    int 	ALIAS_EXCEPTION_CODE_DB_NONUNIQUENESS = 900003;
    String 	ALIAS_EXCEPTION_MSG_DB_NONUNIQUENESS = "数据不唯一";

    /**
     * 未设置值
     */
    int 	ALIAS_EXCEPTION_CODE_BIZ_NOSPECIFIEDVALUE = 901001;
    String 	ALIAS_EXCEPTION_MSG_BIZ_NOSPECIFIEDVALUE = "未设置值";
    /**
     * 调用第三方服务
     */
    int 	ALIAS_EXCEPTION_CODE_BIZ_REMOTECALL = 901002;
    String 	ALIAS_EXCEPTION_MSG_BIZ_REMOTECALL = "调用第三方服务异常";


    String 	ALIAS_EXCEPTION_TYPE_DB = "dbError";
    String 	ALIAS_EXCEPTION_TYPE_BIZ = "bizError";

}