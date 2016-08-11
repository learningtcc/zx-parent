package com.ink.msgcenter.external.email;

/**
 * 邮件消息
 * @author zongtt
 * 2016年5月13日13:35:02
 */
public class EmailMsg {
	
	private String subject;
	
	private String email;
	
	private String content;
	
	private String mailChn;
	
	private String mailHost;
	
	private int mailPort;
	
	private String mailUserName;
	
	private String mailPassword;
	
	private String mailFrom;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMailHost() {
		return mailHost;
	}

	public void setMailHost(String mailHost) {
		this.mailHost = mailHost;
	}

	public int getMailPort() {
		return mailPort;
	}

	public void setMailPort(int mailPort) {
		this.mailPort = mailPort;
	}

	public String getMailUserName() {
		return mailUserName;
	}

	public void setMailUserName(String mailUserName) {
		this.mailUserName = mailUserName;
	}

	public String getMailPassword() {
		return mailPassword;
	}

	public void setMailPassword(String mailPassword) {
		this.mailPassword = mailPassword;
	}

	public String getMailFrom() {
		return mailFrom;
	}

	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}

	public String getMailChn() {
		return mailChn;
	}

	public void setMailChn(String mailChn) {
		this.mailChn = mailChn;
	}
}