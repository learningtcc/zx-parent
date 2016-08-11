package com.ink.user.api.model.in;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.alibaba.fastjson.annotation.JSONField;
import com.ink.user.api.model.GoldRecoveryBean;

/**
 * @Description: 体验金回收
 * @author wanghao^_^
 * @date 2016年6月13日 下午2:14:55
 * @version V1.0
 */
public class ExperienceGoldRecoveryInput implements Serializable {

	/**
	* @Fields serialVersionUID : TODO
	*/
	private static final long serialVersionUID = 7660305007374484233L;
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
	private List<GoldRecoveryBean> list = new ArrayList<GoldRecoveryBean>();// 不序列化改字段
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
	public List<GoldRecoveryBean> getList() {
		return list;
	}
	public void setList(List<GoldRecoveryBean> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ExperienceGoldRecoveryInput [txnCode=");
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
