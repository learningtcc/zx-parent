package com.ink.trade.api.model.in;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ink.trade.api.enums.RouteBusinessType;

/**
 * 渠道请求类
 *
 */
public class AsileRouteInput implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1946838659780352600L;
	private String bankShort;// 银行Code
	private String cardId;// 卡ID
	private String cardType;// 卡类型
	private String mchId;// 商户ID
	private Date tradeDate = new Date();// 交易时间
	private RouteBusinessType routeBusinessType;// 路由业务类型
	private BigDecimal amt = BigDecimal.ZERO;// 金额
	private List<String> asileCodeList;

	public String getBankShort() {
		return bankShort;
	}

	public void setBankShort(String bankShort) {
		this.bankShort = bankShort;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public Date getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}

	public RouteBusinessType getRouteBusinessType() {
		return routeBusinessType;
	}

	public void setRouteBusinessType(RouteBusinessType routeBusinessType) {
		this.routeBusinessType = routeBusinessType;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public List<String> getAsileCodeList() {
		return asileCodeList;
	}

	public void setAsileCodeList(List<String> asileCodeList) {
		this.asileCodeList = asileCodeList;
	}

	@Override
	public String toString() {
		return "AsileRouteInput{" +
				"bankShort='" + bankShort + '\'' +
				", cardId='" + cardId + '\'' +
				", cardType='" + cardType + '\'' +
				", mchId='" + mchId + '\'' +
				", tradeDate=" + tradeDate +
				", routeBusinessType=" + routeBusinessType +
				", amt=" + amt +
				", asileCodeList=" + asileCodeList +
				'}';
	}
}
