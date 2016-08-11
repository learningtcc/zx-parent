package com.ink.channel.core.model.out;

public class AsileAuthorityOut {
	
	private String resCode;// 返回码
	
	private String resMsg;// 返回描述
	
    private String signId;//翼支付鉴权返回值signId    快钱鉴权获取验证码返回值 token

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

	public String getSignId() {
        return signId;
    }

    public void setSignId(String signId) {
        this.signId = signId;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AsileAuthorityOut [resCode=");
		builder.append(resCode);
		builder.append(", resMsg=");
		builder.append(resMsg);
		builder.append(", signId=");
		builder.append(signId);
		builder.append("]");
		return builder.toString();
	}
    

}
