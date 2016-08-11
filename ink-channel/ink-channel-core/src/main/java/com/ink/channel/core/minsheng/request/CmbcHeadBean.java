package com.ink.channel.core.minsheng.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "head")
public class CmbcHeadBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	private String version;// 版本号
    private String msgType;// 报文类型(0001请求报文；0002接收报文)
    private String chanId;// 渠道代号
    private String merchantNo;// 商户号
    private String clientDate;// 商户端发送日期 
    private String serverDate;// 服务端日期
    private String tranFlow;// 交易流水号
    private String tranCode;// 交易代码
    private String respCode;// 返回码
    private String respMsg;// 返回描述
    
    private String msgtype;
    private String channelno;// 渠道号
    private String trandate;//
    private String trantime;
    private String trancode;
    private String bussflowno;
    private String merchantno;
    
	public String getMerchantno() {
		return merchantno;
	}
	public void setMerchantno(String merchantno) {
		this.merchantno = merchantno;
	}
	public String getTrancode() {
		return trancode;
	}
	public void setTrancode(String trancode) {
		this.trancode = trancode;
	}
	public String getBussflowno() {
		return bussflowno;
	}
	public void setBussflowno(String bussflowno) {
		this.bussflowno = bussflowno;
	}
	public String getMsgtype() {
		return msgtype;
	}
	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
	public String getChannelno() {
		return channelno;
	}
	public void setChannelno(String channelno) {
		this.channelno = channelno;
	}
	public String getTrandate() {
		return trandate;
	}
	public void setTrandate(String trandate) {
		this.trandate = trandate;
	}
	public String getTrantime() {
		return trantime;
	}
	public void setTrantime(String trantime) {
		this.trantime = trantime;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getRespCode() {
		return respCode;
	}
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
	public String getRespMsg() {
		return respMsg;
	}
	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getChanId() {
		return chanId;
	}
	public void setChanId(String chanId) {
		this.chanId = chanId;
	}
	public String getMerchantNo() {
		return merchantNo;
	}
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}
	public String getClientDate() {
		return clientDate;
	}
	public void setClientDate(String clientDate) {
		this.clientDate = clientDate;
	}
	public String getServerDate() {
		return serverDate;
	}
	public void setServerDate(String serverDate) {
		this.serverDate = serverDate;
	}
	public String getTranFlow() {
		return tranFlow;
	}
	public void setTranFlow(String tranFlow) {
		this.tranFlow = tranFlow;
	}
	public String getTranCode() {
		return tranCode;
	}
	public void setTranCode(String tranCode) {
		this.tranCode = tranCode;
	}
	@Override
	public String toString() {
		return "CmbcHeadBean [version=" + version + ", msgType=" + msgType + ", chanId=" + chanId + ", merchantNo="
				+ merchantNo + ", clientDate=" + clientDate + ", serverDate=" + serverDate + ", tranFlow=" + tranFlow
				+ ", tranCode=" + tranCode + ", respCode=" + respCode + ", respMsg=" + respMsg + ", msgtype=" + msgtype
				+ ", channelno=" + channelno + ", trandate=" + trandate + ", trantime=" + trantime + ", trancode="
				+ trancode + ", bussflowno=" + bussflowno + ", merchantno=" + merchantno + "]";
	}
	
	
	
}
