package com.ink.channel.core.quickpay.message;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.apache.commons.lang.builder.ToStringBuilder;
/**
 * 快钱卡信息查询消息类
 * @author huohb
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class QryCardContent {
	
	private String txnType;// 查询类型  
    private String cardNo;// 卡号
    
	public String getTxnType() {
		return txnType;
	}
	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
