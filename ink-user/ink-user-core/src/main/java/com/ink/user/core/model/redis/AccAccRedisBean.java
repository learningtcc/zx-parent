package com.ink.user.core.model.redis;

import java.io.Serializable;
import java.math.BigDecimal;

import com.ink.user.core.po.AccAcc;
import com.ink.user.core.po.TnsAce;


/** 
 * 充值redis队列中保存对象类型
 * @author yangchen
 * @date 2016年1月29日 上午10:18:12
 */
public class AccAccRedisBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5804059748498222051L;
	private AccAcc accAcc;
	private BigDecimal amt;
	private TnsAce tnsAce;
	
	public AccAcc getAccAcc() {
		return accAcc;
	}
	public void setAccAcc(AccAcc accAcc) {
		this.accAcc = accAcc;
	}
	public BigDecimal getAmt() {
		return amt;
	}
	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}
	public TnsAce getTnsAce() {
		return tnsAce;
	}
	public void setTnsAce(TnsAce tnsAce) {
		this.tnsAce = tnsAce;
	}
	@Override
	public String toString() {
		return "AccAccBean [accAcc=" + accAcc + ", amt=" + amt + ", tnsAce="
				+ tnsAce + "]";
	}
	

}
