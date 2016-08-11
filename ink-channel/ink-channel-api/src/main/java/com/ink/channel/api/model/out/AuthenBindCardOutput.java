package com.ink.channel.api.model.out;

public class AuthenBindCardOutput {
	private String resCode;
    private String resMsg;
    private String identityid;
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
	public String getIdentityid() {
		return identityid;
	}
	public void setIdentityid(String identityid) {
		this.identityid = identityid;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AuthenBindCardOutput [resCode=");
		builder.append(resCode);
		builder.append(", resMsg=");
		builder.append(resMsg);
		builder.append(", identityid=");
		builder.append(identityid);
		builder.append("]");
		return builder.toString();
	}
    
}
