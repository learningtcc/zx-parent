package com.ink.msgcenter.api.model.output;

import java.io.Serializable;

import com.ink.msgcenter.api.util.MsgCode;

/**
 * 响应头信息
 * @author zongtt
 * 2016年5月12日11:20:48
 */
public class MsgOutput implements Serializable{

	private static final long serialVersionUID = -7379175432164387126L;
	
	public MsgOutput() {
	}

	public MsgOutput(String retCode) {
		this.retCode = retCode;
	}
	
	/**
	 * 响应代码
	 */
	private String retCode;
	
	/**
	 * 响应消息
	 */
	private String retMsg;
	
	/**
	 * 消息ID
	 */
	private String msgId;

	public String getRetCode() {
		return retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	public String getRetMsg() {
		return retMsg;
	}

	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}
	
	public boolean isSuccess(){
		return MsgCode.SUCCESS.equalsIgnoreCase(retCode);
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	@Override
	public String toString() {
		return "MsgOutput{" +
				"retCode='" + retCode + '\'' +
				", retMsg='" + retMsg + '\'' +
				", msgId='" + msgId + '\'' +
				'}';
	}
}