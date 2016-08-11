package com.ink.channel.api.model.out;

import java.io.Serializable;

public class ValidCodeOutput implements Serializable{
    private static final long serialVersionUID = 7439855958189581093L;
    /** 响应码 **/
    private String resCode;
    /** 响应信息 **/
    private String resMsg;
    private String identityId;
    private String reqId;
    private String token;
    public String getResCode() {
        return resCode;
    }
    public void setResCode(String resCode) {
        this.resCode = resCode;
    }
    public String getResMsg() {
        return resMsg;
    }
    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }
	public String getIdentityId() {
		return identityId;
	}
	public void setIdentityId(String identityId) {
		this.identityId = identityId;
	}
	
	public String getReqId() {
		return reqId;
	}
	public void setReqId(String reqId) {
		this.reqId = reqId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ValidCodeOutput [resCode=");
		builder.append(resCode);
		builder.append(", resMsg=");
		builder.append(resMsg);
		builder.append(", identityId=");
		builder.append(identityId);
		builder.append(", reqId=");
		builder.append(reqId);
		builder.append(", token=");
		builder.append(token);
		builder.append("]");
		return builder.toString();
	}
}
