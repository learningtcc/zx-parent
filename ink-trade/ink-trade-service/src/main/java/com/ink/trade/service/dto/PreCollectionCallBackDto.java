package com.ink.trade.service.dto;

import java.io.Serializable;

/**
 * 代收回调请求参数
 * Created by huohb on 2016/5/26.
 */
public class PreCollectionCallBackDto  implements Serializable{
    private static final long serialVersionUID = -5625070181834688583L;

    private String orderNo;// 订单号

    private String orderStatus;// 订单状态（01：成功，02：未知，03：失败）

    private String tranFlowNo;// 流水号（渠道返回）

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getTranFlowNo() {
        return tranFlowNo;
    }

    public void setTranFlowNo(String tranFlowNo) {
        this.tranFlowNo = tranFlowNo;
    }
}
