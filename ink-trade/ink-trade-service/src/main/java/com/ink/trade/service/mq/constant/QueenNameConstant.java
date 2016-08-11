/**
 * yinker.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description TODO
 * @author xuguoqi
 * @date 2016年4月28日 上午11:47:53
 */
package com.ink.trade.service.mq.constant;

/**
 * @Description mq队列名常量
 * @author xuguoqi
 * @date 2016年4月28日 上午11:47:53
 */
public class QueenNameConstant {
	
	/*签约申请校验  发送已签约对列*/
	public static final String CHECK_SIGN_TO_ACC_QUEEN = "ACC38070.queue";
    /*余额充值补偿*/
//    public static final String BALANCE_RECHARGE_QUEUE_NAME = "ACC30010.queue";
//    /*活期充值补偿*/
//    public static final String CURRENT_RECHARGE_QUEUE_NAME = "ACC30150.queue";
//    /*定期充值补偿*/
//    public static final String REGULAR_RECHARGE_QUEUE_NAME = "ACC30160.queue";
    // 对账业务prepare队列
    public static final String BALANCE_PREPARED_MQ_NAME = "yinker.balance.platform.notifyData";
    // 对账业务update队列
    public static final String BALANCE_UPDATE_MQ_NAME = "yinker.balance.platform.updateData";
    
    //绑卡 BC
    public static final String ACC_BIND_CARD_MQ_NAME = "BindCard.queue";
    // 解绑 UC
    public static final String ACC_UNBUND_CARD_MQ_NAME = "UnbundCard.queue";
    //  充值 AR
    public static final String ACC_RECHARG_MQ_NAME = "AccRecharge.queue";
    //  冻结 AWF
    public static final String ACC_WITHDRAW_FREEZE_MQ_NAME = "AccWithdrawFreeze.queue";
    //  提现 AWA
    public static final String ACC_WITHDRAW_ACCEPT_MQ_NAME = "AccWithdrawAccept.queue";
    //   提现撤回 AWC
    public static final String ACC_WITHDRAW_CANCEL_MQ_NAME = "AccWithdrawCancel.queue";






}
