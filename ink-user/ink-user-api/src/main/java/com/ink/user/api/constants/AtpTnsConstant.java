package com.ink.user.api.constants;

/**
 * 
 * @ClassName: PatsAtpConstant
 * @Description: 常量类
 * @author guojie.gao
 * @date 2015年10月15日 下午2:16:39
 * 
 */
public class AtpTnsConstant {

	// 商户状态类型
	public static final Integer MCH_STATUS_1 = 1;// 1-启用
	public static final Integer MCH_STATUS_2 = 2;// 2-停用
	public static final Integer MCH_STATUS_3 = 3;// 3-待审核
	public static final Integer MCH_STATUS_4 = 4;// 4-审核拒绝
	public static final Integer MCH_STATUS_9 = 9;// 9-注销

	// 物理删除标志
	public static final Integer DEL_FLAG_0 = 0;// 0-正常
	public static final Integer DEL_FLAG_1 = 1;// 1-删除

	// 物理删除标志
	public static final Boolean DEL_FLAG_FALSE = false;// 未删除
	public static final Boolean DEL_FLAG_TRUE = true;// 已删除

	// 客户状态类型
	public static final Integer CUST_STATUS_1 = 1;// 1-启用
	public static final Integer CUST_STATUS_2 = 2;// 2-停用
	public static final Integer CUST_STATUS_9 = 9;// 9-注销

	// 资金账户状态
	public static final Integer ACC_STATUS_1 = 1;// 1-启用
	public static final Integer ACC_STATUS_2 = 2;// 2-停用
	public static final Integer ACC_STATUS_9 = 9;// 9-注销

	// 内部资金账户状态
	public static final Integer IAC_STATUS_1 = 1;// 1-启用
	public static final Integer IAC_STATUS_2 = 2;// 2-停用
	public static final Integer IAC_STATUS_9 = 9;// 9-注销

	// 内部资金账户类型
	public static final Integer IAC_TYPE_MCH = 1;// 1-商户账户
	public static final Integer IAC_TYPE_ORG = 2;// 2-机构账户
	public static final Integer IAC_TYPE_ITEM = 3;// 3-科目账户

	// 账户方向
	public static final String ACC_DIR_P = "P";// P-账户方
	public static final String ACC_DIR_A = "A";// A-对转方

	// 账户类型
	public static final Integer ACC_TYPE_CUST = 0;// 0-客户账户
	public static final Integer ACC_TYPE_MCH = 1;// 1-商户账户
	public static final Integer ACC_TYPE_ORG = 2;// 2-机构账户
	public static final Integer ACC_TYPE_ITEM = 3;// 3-科目账户

	// 子账户状态
	public static final Integer SAC_TYPE_STATUS_1 = 1;// 1-启用
	public static final Integer SAC_TYPE_STATUS_2 = 2;// 2-停用
	public static final Integer SAC_TYPE_STATUS_9 = 9;// 9-注销

	// 科目账户状态
	public static final Integer ITEM_STATUS_1 = 1;// 1-启用
	public static final Integer ITEM_STATUS_2 = 2;// 2-停用
	public static final Integer ITEM_STATUS_9 = 9;// 9-注销

	// 交易类型
//	public static final String ACC_30010 = "ACC30010";// 充值
//	public static final String ACC_30050 = "ACC30050";// 提现申请
//	public static final String ACC_30060 = "ACC30060";// 提现受理
//	public static final String ACC_30070 = "ACC30070";// 提现完成
//	public static final String ACC_30100 = "ACC30100";// 交易结果查询
//	public static final String ACC_39080 = "ACC39080";// 提现业务撤回
//
//	public static final String ACC_30080 = "ACC30080";// 余额申购
//	public static final String ACC_30090 = "ACC30090";// 余额赎回
//	public static final String ACC_30110 = "ACC30110";// 银行卡申购
//	public static final String ACC_30120 = "ACC30120";// 银行卡赎回
//	public static final String ACC_30130 = "ACC30130";// 银行卡赎回受理
//	public static final String ACC_30140 = "ACC30140";// 银行卡赎回完成
//	public static final String ACC_30150 = "ACC30150";// 银行卡申购活期
//	public static final String ACC_30160 = "ACC30160";// 银行卡申购定期
//	public static final String ACC_30170 = "ACC30170";// 余额申购活期
//	public static final String ACC_30180 = "ACC30180";// 余额申购定期
//	public static final String ACC_39090 = "ACC39090";// 银行卡赎回业务撤回
//	public static final String ACC_30250 = "ACC30250";// 体验金发放
//	public static final String ACC_30400 = "ACC30400";// 体验金回收批量
//	public static final String ACC_30410 = "ACC30410";// 体验金回收
//	public static final String ACC_30420 = "ACC30420";// 活期计息
//	public static final String ACC_30430 = "ACC30430";// 定期利息计算和本金转移
//	public static final String ACC_30440 = "ACC30440";// 体验金计息
//	public static final String ACC_30260 = "ACC30260";// (活期-余额-银行卡)冻结
//	public static final String ACC_30270 = "ACC30270";// 活期-余额-银行卡)解冻
//	public static final String ACC_30280 = "ACC30280";// 固期-余额-银行卡)冻结
//	public static final String ACC_30290 = "ACC30290";// 固期-余额-银行卡)解冻
//	public static final String ACC_30300 = "ACC30300";// 银行卡-余额-活期
//	public static final String ACC_30310 = "ACC30310";// 银行卡-余额-固期
//	public static final String ACC_30320 = "ACC30320";// 活期购买定期
//	public static final String ACC_30190 = "ACC30190";// 固期赎回活期
//	public static final String ACC_30200 = "ACC30200";// 固期赎回余额
//	public static final String ACC_30210 = "ACC30210";// 活期赎回银行卡冻结申请
//	public static final String ACC_30220 = "ACC30220";// 活期赎回银行卡受理
//	public static final String ACC_30230 = "ACC30230";// 固期赎回银行卡冻结申请
//	public static final String ACC_30240 = "ACC30240";// 固期赎回银行卡受理
//	public static final String ACC_30330 = "ACC30330";// 简理财开户代发体验金
//	public static final String ACC_30340 = "ACC30340";// 余额提现-绑卡操作
//	public static final String ACC_30460 = "ACC30460";
//
//	public static final String ACC_33010 = "ACC33010";// 用户资料查询
//	public static final String ACC_33020 = "ACC33020";// 绑卡状态查询
//	public static final String ACC_33030 = "ACC33030";// 商户状态查询
//	
//	public static final String ACC_37010 = "ACC37010";
//	public static final String ACC_37020 = "ACC37020";
//	// 账户类型
//	public static final String ACC_38040 = "ACC38040";// 个人账户开户
//	public static final String ACC_38050 = "ACC38050";// 个人账户信息查询
//	public static final String ACC_38060 = "ACC38060";// 个人账户余额查询
//	public static final String ACC_38070 = "ACC38070";// 个人账户银行卡绑定
//	public static final String ACC_38080 = "ACC38080";// 个人账户银行卡解绑
//	public static final String ACC_38090 = "ACC38090";// 个人账户银行卡查询
//	public static final String ACC_38100 = "ACC38100";// 综合账户查询
//	public static final String ACC_38110 = "ACC38110";// 子账户开户
//	public static final String ACC_38120 = "ACC38120";// 个人手机号修改
//	// 资料修改
//	public static final String ACC_38010 = "ACC38010";// 个人身份证修改
//	public static final String ACC_38020 = "ACC38020";// 个人手机号修改
	
	public static final String ACC_AR = "AR";
	public static final String ACC_AWF = "AWF";
	public static final String ACC_AWA = "AWA";
	public static final String ACC_AT = "AT";
	public static final String ACC_QTR = "QTR";
	public static final String ACC_EGG = "EGG";
	public static final String ACC_EGGB = "EGGB";
	public static final String ACC_EGR = "EGR";
	public static final String ACC_AI = "AI";
	public static final String ACC_CC = "CC";
	public static final String ACC_QMI = "QMI";
	public static final String ACC_MAT = "MAT";
	public static final String ACC_QMAB = "QMAB";
	public static final String ACC_OA = "OA";
	public static final String ACC_QCA = "QCA";
	public static final String ACC_QCAB = "QCAB";
	public static final String ACC_BC = "BC";
	public static final String ACC_UC = "UC";
	public static final String ACC_CBC = "CBC";
	public static final String ACC_AWC = "AWC";
	public static final String ACC_MUII = "MUII";
	public static final String ACC_MUBI = "MUBI";
	public static final String ACC_MUMI = "MUMI";
	
	// 会计分录组号
	public static final String ACC_30010_0001 = "01";// 充值组号
	public static final String ACC_30060_0001 = "01";// 提现受理组号
	public static final String ACC_30080_0001 = "01";// 余额申购
	public static final String ACC_30090_0001 = "01";// 余额赎回
	public static final String ACC_30110_0001 = "01";// 银行卡申购
	public static final String ACC_30130_0001 = "01";// 银行卡申购
	public static final String ACC_30210_0001 = "01";// 余额到活期账户
	public static final String ACC_30220_0001 = "01";// 活期到定期
	public static final String ACC_30230_0001 = "01";// 定期赎回到活期
	public static final String ACC_30240_0001 = "01";// 活期赎回到余额
	public static final String ACC_30250_0001 = "01";// 体验金发放
	public static final String ACC_30260_0001 = "01";// 体验金回收

	public static final String ACC_30300_0001 = "01";// 银行卡-余额-活期
	public static final String ACC_30300_0002 = "02";// 银行卡-余额-活期

	public static final String ACC_30310_0001 = "01";// 银行卡-余额-固期
	public static final String ACC_30310_0002 = "02";// 银行卡-余额-固期

	// 冲正状态
	public static final String REF_FLG_N = "N";// N-正常
	public static final String REF_FLG_Y = "Y";// Y-红字
	public static final String REF_FLG_B = "B";// B-蓝字

	// 联机交易代码表
	public static final Integer STATUS_1 = 1;// 1-启用
	public static final Integer STATUS_2 = 2;// 2-停用
	public static final Integer STATUS_9 = 9;// 9-注销

	// 货币种类
	public static final String CUR_CNY = "CNY";// 人民币

	// 系统参数
	public static final String DEFAULT_OWNER = "ATP";// 账户系统
	public static final String DEFAULT_OWNER_GROUP = "PATS";// 支付账户体系
	public static final String DEFAULT_CHANNEL_YKZC = "YKZC";// 银客集团资产事业部
	public static final String DEFAULT_CHANNEL_YKLC = "YKLC";// 银客集团理财事业部

	// 开销户标识
	public static final String OPEN_CLOSE_FLG_O = "O";// O-开户
	public static final String OPEN_CLOSE_FLG_C = "C";// C-销户
	public static final String OPEN_CLOSE_FLG_N = "N";// N-无开销户

	// 实时入账标识
	public static final Integer RUNTIME_FLG_0 = 0;// 0-实时入账
	public static final Integer RUNTIME_FLG_1 = 1;// 1-异步入账

	// 是否入账标识
	public static final Integer IN_FLG_0 = 0;// 0-待入账
	public static final Integer IN_FLG_1 = 1;// 1-已入账

	// 分录参数
	public static final Integer ACE_GEN_COUNT = 2;// 分录个数

	// 借贷方向
	public static final String DIR_CREDIT = "C"; // 贷
	public static final String DIR_DEBIT = "D"; // 借
	public static final String DIR_BOTH = "A"; // 双方反映

	// 科目名称
	public static final String ITEM_112201 = "112201";// 应收账款-渠道充值款（-渠道）
	public static final String ITEM_220201 = "220201";// 应付账款-渠道提现款（-渠道）
	public static final String ITEM_224181 = "224181";// 商户发放红包
	public static final String ITEM_224104 = "224104";// 客户收取红包

	// 银行卡状态
	public static final Integer ACC_CARD_STATUS_1 = 1;// 1-启用
	public static final Integer ACC_CARD_STATUS_2 = 2;// 2-停用
	public static final Integer ACC_CARD_STATUS_9 = 9;// 9-注销暂时没用

	// 平台托管类型
	public static final String APT0000001 = "APT0000001";// 1-启用
	public static final String CMBC000001 = "CMBC000001";// 1-启用

	public static final String PATS = "100000000001";//

	public static final String VERSION = "1.0";// 报文版本号

	public static final String MSGTYPE_0001 = "0001";// 请求
	public static final String MSGTYPE_0002 = "0002";// 响应
	public static final String MSGTYPE_0003 = "0003";// 回调请求
	public static final String MSGTYPE_0004 = "0004";// 回调应答

	// 简理财客户ID
	public static final String JLCCustId = "100000000000001";

	// 简理财主账户ID
	public static final Long JLCPacId = 100000000000001L;
	// 简理财商户号
	public static final Long JLCMchId = 1000000000002L;
	// 子账户类型
	public static final String RedPackageAccSubType = "0005";// 红包账户
	public static final String CustFeeAccSubType = "0006";// 手续费账户
	public static final String PacAccType = "0007";// 主账户类型
	public static final String GoldRecoveryAccSubType = "0004";// 体验金账户
	public static final String CurrentAccSubType = "0001";// 活期账户
	public static final String RegularAccSubType = "0002";// 定期账户
	public static final String BalAccSubType = "0003";// 余额账户
	public static final String MchBorrowerAccSubType = "0011";// 商户借款人账户
	
	// 修改作用域
	// 只修改客户资料
	public static final String SCOPE_1 = "1";
	// 只修改银行卡资料
	public static final String SCOPE_2 = "2";
	// 客户和银行卡资料都修改
	public static final String SCOPE_3 = "3";
}
