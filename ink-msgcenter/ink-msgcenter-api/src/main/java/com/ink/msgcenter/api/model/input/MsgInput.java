package com.ink.msgcenter.api.model.input;

import java.io.Serializable;

/**
 * 请求头信息
 * @author zongtt
 * 2016年5月12日11:20:48
 */
public class MsgInput implements Serializable{

	private static final long serialVersionUID = 2369456324193243638L;
	
	/**
	 * 商户编号
	 */
	private String merctCode;

	public String getMerctCode() {
		return merctCode;
	}

	public void setMerctCode(String merctCode) {
		this.merctCode = merctCode;
	}
}