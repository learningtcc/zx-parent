package com.ink.trade.service.dto;

import java.io.Serializable;

/**
 * 异步通知商户商户信息dto
 * Created by Lenovo on 2016/7/1.
 */
public class NotifyMerchantDto implements Serializable{

    private static final long serialVersionUID = -9091021682924571634L;

    private String merOrderNo;// 商户订单号

    private String platOrderNo;// 平台订单号

    private String orderStatus;// 订单状态（2:成功,3:失败）

    public String getMerOrderNo() {
        return merOrderNo;
    }

    public void setMerOrderNo(String merOrderNo) {
        this.merOrderNo = merOrderNo;
    }

    public String getPlatOrderNo() {
        return platOrderNo;
    }

    public void setPlatOrderNo(String platOrderNo) {
        this.platOrderNo = platOrderNo;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
