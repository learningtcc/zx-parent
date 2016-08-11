package com.ink.msgcenter.api.service;

import com.ink.msgcenter.api.model.input.EmailInput;
import com.ink.msgcenter.api.model.output.MsgOutput;

public interface EmailService {
	
	/**
	 * 邮件发送
	 * @param email
	 * @return
	 */
	public MsgOutput sendEmail(EmailInput email);
}