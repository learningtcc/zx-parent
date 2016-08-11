package com.ink.channel.core.minsheng.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
/**
 * 民生电商请求消息根实体
 * @author huohb
 *
 */
@XmlRootElement(name = "message")
public class CmbcRootBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private CmbcHeadBean head;
	
	private CmbcBodyBean body;

	public CmbcHeadBean getHead() {
		return head;
	}

	public void setHead(CmbcHeadBean head) {
		this.head = head;
	}

	public CmbcBodyBean getBody() {
		return body;
	}

	public void setBody(CmbcBodyBean body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "CmbcRootBean [head=" + head + ", body=" + body + "]";
	}
	
}
