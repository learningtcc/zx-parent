//package com.ink.user.core.model.redis;
//
//import java.io.Serializable;
//import java.math.BigDecimal;
//
//import com.ink.user.core.po.AccIac;
//import TnsAce;
//
//
///** 
// * 充值redis队列中保存对象类型
// * @author yangchen
// * @date 2016年1月29日 上午10:18:12
// */
//public class AccIacRedisBean implements Serializable{
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = -4394312256991132470L;
//	private AccIac accIac;
//	private BigDecimal amt;
//	private TnsAce tnsAce;
//	
//	public AccIac getAccIac() {
//		return accIac;
//	}
//	public void setAccIac(AccIac accIac) {
//		this.accIac = accIac;
//	}
//	public BigDecimal getAmt() {
//		return amt;
//	}
//	public void setAmt(BigDecimal amt) {
//		this.amt = amt;
//	}
//	public TnsAce getTnsAce() {
//		return tnsAce;
//	}
//	public void setTnsAce(TnsAce tnsAce) {
//		this.tnsAce = tnsAce;
//	}
//	@Override
//	public String toString() {
//		return "AccIacBean [accIac=" + accIac + ", amt=" + amt + ", tnsAce="
//				+ tnsAce + "]";
//	}
//	
//
//}
