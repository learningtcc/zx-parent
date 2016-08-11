package com.ink.user.api.model.in;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.ink.user.api.model.InterestBean;
import org.hibernate.validator.constraints.Length;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @Description: 计息
 * @author wanghao^_^
 * @date 2016年6月13日 下午3:55:53
 * @version V1.0
 */
public class AccInterestInput implements Serializable {

	/**
	* @Fields serialVersionUID : TODO
	*/
	private static final long serialVersionUID = 7948353875662529871L;
	// 交易代码
	@NotNull
	@Length(max = 8)
	private String txnCode;
	@NotNull
	@Length(max = 64)
	private String id;
	// 订单日期(YYYYMMDD)
	@NotNull
	@Length(max = 14)
	private String tradeDate;
	// 商户编号（托管商户编号）
	@NotNull
	@Length(max = 24)
	private String mchId;
	@NotNull
	@JSONField(serialize = false)
	private List<InterestBean> list = new ArrayList<InterestBean>();// 不序列化改字段
	public String getTxnCode() {
		return txnCode;
	}

	public void setTxnCode(String txnCode) {
		this.txnCode = txnCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public List<InterestBean> getList() {
		return list;
	}

	public void setList(List<InterestBean> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AccInterestInput [txnCode=");
		builder.append(txnCode);
		builder.append(", id=");
		builder.append(id);
		builder.append(", tradeDate=");
		builder.append(tradeDate);
		builder.append(", mchId=");
		builder.append(mchId);
		builder.append(", list=");
		builder.append(list);
		builder.append("]");
		return builder.toString();
	}

}
