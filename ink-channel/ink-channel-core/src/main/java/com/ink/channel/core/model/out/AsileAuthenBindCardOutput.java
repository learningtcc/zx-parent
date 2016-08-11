package com.ink.channel.core.model.out;

import java.io.Serializable;

public class AsileAuthenBindCardOutput implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4240309592729716955L;
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
    
}
