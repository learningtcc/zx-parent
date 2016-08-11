package com.ink.msgcenter.cache.object;

public class EmailChnCache {
	
	private Long id;
	
	private String chnCode;
	
	/**
	 * 邮箱服务器
	 */
	private String host;
	
	/**
	 * 邮箱端口
	 */
	private int port;
	
	/**
	 * 用户账号
	 */
	private String userName;
	
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 发送邮箱
	 */
	private String mail;
	/**
	 * 优先级
	 */
	private int priority;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getChnCode() {
		return chnCode;
	}

	public void setChnCode(String chnCode) {
		this.chnCode = chnCode;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}