package com.ink.balance.api.model.in;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description 交易分页查询条件参数
 * @author xuguoqi
 * @date 2016年4月7日 上午10:38:27
 */
public class TradeQueryParamInput implements Serializable {


    private static final long serialVersionUID = 611750445553805467L;

    private int channelNo;

    private int payType;

    private Date tradeBeginTime;

    private Date trandeEndTime;

    private int dealType;

    private String platOrderId;

    private String bankFlow;

    private Date checkBeginTime;

    private Date checkEndTime;

    public TradeQueryParamInput() {
        super();
    }

    public TradeQueryParamInput(int channelNo, int payType, Date tradeBeginTime, Date trandeEndTime, int dealType,
                                String platOrderId, String bankFlow, Date checkBeginTime, Date checkEndTime) {
        super();
        this.channelNo = channelNo;
        this.payType = payType;
        this.tradeBeginTime = tradeBeginTime;
        this.trandeEndTime = trandeEndTime;
        this.dealType = dealType;
        this.platOrderId = platOrderId;
        this.bankFlow = bankFlow;
        this.checkBeginTime = checkBeginTime;
        this.checkEndTime = checkEndTime;
    }

    @Override
    public String toString() {
        return "TradeQueryParamInput [channelNo=" + channelNo + ", payType=" + payType + ", tradeBeginTime="
                + tradeBeginTime + ", trandeEndTime=" + trandeEndTime + ", dealType=" + dealType + ", platOrderId="
                + platOrderId + ", bankFlow=" + bankFlow + ", checkBeginTime=" + checkBeginTime + ", checkEndTime="
                + checkEndTime + "]";
    }


}
