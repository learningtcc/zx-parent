package com.ink.channel.api.model.out;

import java.io.Serializable;

public class AuthorityOutput implements Serializable{
    private static final long serialVersionUID = 2653279744253943328L;
    private String resCode;
    private String resMsg;
    private String identityId;

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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AuthorityOutput [resCode=");
		builder.append(resCode);
		builder.append(", resMsg=");
		builder.append(resMsg);
		builder.append(", identityId=");
		builder.append(identityId);
		builder.append("]");
		return builder.toString();
	}
}
