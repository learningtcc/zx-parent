package com.ink.channel.core.model.out;

public class AsileAuthorityOutput {
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
		builder.append("AsileAuthorityOutput [resCode=");
		builder.append(resCode);
		builder.append(", resMsg=");
		builder.append(resMsg);
		builder.append(", identityId=");
		builder.append(identityId);
		builder.append("]");
		return builder.toString();
	}
}
