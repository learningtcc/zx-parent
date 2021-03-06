package com.ink.channel.core.quickpay.message;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
/**
 * 快钱请求消息根类
 * @author huohb
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="MasMessage",namespace="http://www.99bill.com/mas_cnp_merchant_interface")
public class MasMessage {

	@XmlElement(name = "version")
	private String version;
	
	@XmlElement(name = "PciQueryContent")
	private PciQueryContent pciQueryContent;
	
	@XmlElement(name = "indAuthContent")
	private IndAuthContent indAuthContent;
	
	@XmlElement(name = "TxnMsgContent")
	private TxnMsgContent txnMsgContent;
	
	@XmlElement(name = "indAuthDynVerifyContent")
	private IndAuthDynVerifyContent indAuthDynVerifyContent;
	
	@XmlElement(name = "QryTxnMsgContent")
	private QryTxnMsgContent qryTxnMsgContent;
	
	@XmlElement(name = "PciDeleteContent")
	private PciDeleteContent pciDeleteContent;
	
	@XmlElement(name = "GetDynNumContent")
	private GetDynNumContent getDynNumContent;
	
	@XmlElement(name = "QryCardContent")
	private QryCardContent qryCardContent;
	
	public QryCardContent getQryCardContent() {
		return qryCardContent;
	}

	public void setQryCardContent(QryCardContent qryCardContent) {
		this.qryCardContent = qryCardContent;
	}

	public GetDynNumContent getGetDynNumContent() {
		return getDynNumContent;
	}

	public void setGetDynNumContent(GetDynNumContent getDynNumContent) {
		this.getDynNumContent = getDynNumContent;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public PciQueryContent getPciQueryContent() {
		return pciQueryContent;
	}

	public void setPciQueryContent(PciQueryContent pciQueryContent) {
		this.pciQueryContent = pciQueryContent;
	}

	public IndAuthContent getIndAuthContent() {
		return indAuthContent;
	}

	public void setIndAuthContent(IndAuthContent indAuthContent) {
		this.indAuthContent = indAuthContent;
	}

	public TxnMsgContent getTxnMsgContent() {
		return txnMsgContent;
	}

	public void setTxnMsgContent(TxnMsgContent txnMsgContent) {
		this.txnMsgContent = txnMsgContent;
	}

	public IndAuthDynVerifyContent getIndAuthDynVerifyContent() {
		return indAuthDynVerifyContent;
	}

	public void setIndAuthDynVerifyContent(IndAuthDynVerifyContent indAuthDynVerifyContent) {
		this.indAuthDynVerifyContent = indAuthDynVerifyContent;
	}

	public QryTxnMsgContent getQryTxnMsgContent() {
		return qryTxnMsgContent;
	}

	public void setQryTxnMsgContent(QryTxnMsgContent qryTxnMsgContent) {
		this.qryTxnMsgContent = qryTxnMsgContent;
	}

	public PciDeleteContent getPciDeleteContent() {
		return pciDeleteContent;
	}

	public void setPciDeleteContent(PciDeleteContent pciDeleteContent) {
		this.pciDeleteContent = pciDeleteContent;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	
}
