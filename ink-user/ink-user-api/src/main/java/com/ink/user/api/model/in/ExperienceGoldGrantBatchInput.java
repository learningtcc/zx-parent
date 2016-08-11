package com.ink.user.api.model.in;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.ink.user.api.model.GoldGrantBean;
import org.hibernate.validator.constraints.Length;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @Description: 体验金发放批量
 * @author wanghao
 * @date 2016年5月5日 上午10:30:38
 */
public class ExperienceGoldGrantBatchInput implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7611365787680656028L;

	@NotNull
	@Length(max = 64)
	private String id;
	// 订单日期(YYYYMMDD)
	@NotNull
	@Length(max = 14)
	private String tradeDate;
	// 交易代码
	@NotNull
	@Length(max = 8)
	private String txnCode;
	// 商户编号（托管商户编号）
	@NotNull
	@Length(max = 24)
	private String mchId;
	@NotNull
	@JSONField(serialize = false)
	private List<GoldGrantBean> list = new ArrayList<GoldGrantBean>();// 不序列化改字段

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTxnCode() {
		return txnCode;
	}

	public void setTxnCode(String txnCode) {
		this.txnCode = txnCode;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public List<GoldGrantBean> getList() {
		return list;
	}

	public void setList(List<GoldGrantBean> list) {
		this.list = list;
	}

	public String getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ExperienceGoldGrantBatchInput [id=");
		builder.append(id);
		builder.append(", tradeDate=");
		builder.append(tradeDate);
		builder.append(", txnCode=");
		builder.append(txnCode);
		builder.append(", mchId=");
		builder.append(mchId);
		builder.append(", list=");
		builder.append(list);
		builder.append("]");
		return builder.toString();
	}

}
