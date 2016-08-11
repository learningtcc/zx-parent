package com.ink.user.common.constant;

/**
 * @Description: 日志的模块代码和业务代码常量定义
 * @author wanghao
 * @date 2016年4月29日 上午11:15:25
 */
public class UserLoggerCnst {

	// 对账系统模块代码
	// 冲值功能
	public static final String USER_RECHARGE_MOUDLE = "1003001";
	// 提现功能
	public static final String USER_WITHDRAW_MOUDLE = "1003002";
	// 转账功能
	public static final String USER_TRANSFERS_MOUDLE = "1003003";
	// 账户功能
	public static final String USER_ACCOUNT_MOUDLE = "1003004";
	// 查询功能
	public static final String USER_QUERY_MOUDLE = "1003005";
	// 绑卡功能
	public static final String USER_BINDCAD_MOUDLE = "1003006";
	// 计息功能
	public static final String USER_INTEREST_MOUDLE = "1003007";

	/**
	 * 业务代码(按流程分业务代码)
	 */
	// 充值
	public static final String USER_AR = "10030010001";

	// 提现
	public static final String USER_AWF = "10030020001";
	public static final String USER_AWA = "10030020002";
	// 提现撤回
	public static final String USER_AWC = "10030020003";

	// 转账
	public static final String USER_AT = "10030030001";
	// 商户账户转账
	public static final String USER_MAT = "10030030002";

	// 体验金发放
	public static final String USER_EGG = "10030040001";
	public static final String USER_EGGB = "10030040002";
	// 体验金回收
	public static final String USER_EGR = "10030040003";
	// 开户
	public static final String USER_OA = "10030040004";
	// 修改客户身份证资料
	public static final String USER_MUII = "10030040005";
	// 修改客户身份证资料
	public static final String USER_MUBI = "10030040006";
	// 修改客户身份证资料
	public static final String USER_MUMI = "10030040007";

	// 交易结果查询
	public static final String USER_QTR = "10030050001";
	// 检查用户合法性
	public static final String USER_CC = "10030050002";
	// 查询商户信息
	public static final String USER_QMI = "10030050003";
	// 查询商户账户余额
	public static final String USER_QMAB = "10030050004";
	
	// 绑卡
	public static final String USER_BC = "10030060001";
	// 解绑
	public static final String USER_UC = "10030060002";
	// 绑卡前置检查
	public static final String USER_CBC = "10030060003";

	// 查询个人账户信息
	public static final String USER_QCA = "10030060004";
	// 查询个人账户余额
	public static final String USER_QCAB = "10030060005";

	// 计息
	public static final String USER_AI = "10030070001";
	
	// 其他
	public static final String USER_NORMAL = "10000000000";

	// // 平台数据查询参数
	// public static final String PLATFORM_QUERY_BUS = "BUS1008";

}
