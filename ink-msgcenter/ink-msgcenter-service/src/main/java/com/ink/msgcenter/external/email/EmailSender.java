package com.ink.msgcenter.external.email;

import com.ink.base.log.util.YinkerLogger;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

@Component("emailSender")
public class EmailSender {
	
	private ConcurrentHashMap<String, JavaMailSender> handlerMap = new ConcurrentHashMap<String,JavaMailSender>();
	private YinkerLogger logger = YinkerLogger.getLogger(getClass());
	
	public String send(EmailMsg msg){
		
		String result = "";
		
//		logger.info("begin="+System.currentTimeMillis()+"");
		
		String chn = msg.getMailChn();
		
		JavaMailSender sender = handlerMap.get(chn);
		
		try{
			if(sender == null){
				sender = newJavaMailSender(msg);
				handlerMap.put(chn, sender);
			}
			
			MimeMessage mimeMessage = sender.createMimeMessage();
			
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
			//设置发件人Email
            messageHelper.setFrom(msg.getMailFrom());
            //设置邮件主题
            messageHelper.setSubject(msg.getSubject());
            
            mimeMessage.addRecipients(RecipientType.TO, msg.getEmail());
            
            mimeMessage.setDataHandler(new DataHandler(   
                    new ByteArrayDataSource(msg.getContent(), "text/html")));
            
            sender.send(mimeMessage);
            
		}catch(MailException e){
			e.printStackTrace();
			//将已建立的通道移除，便于下次重新建立连接
			handlerMap.remove(chn);
			result = e.getMessage();
		} catch (MessagingException e) {
			e.printStackTrace();
			//将已建立的通道移除，便于下次重新建立连接
			handlerMap.remove(chn);
			result = e.getMessage();
		} catch (IOException e) {
			e.printStackTrace();
			//将已建立的通道移除，便于下次重新建立连接
			handlerMap.remove(chn);
			result = e.getMessage();
		}
		
//		logger.info("end="+System.currentTimeMillis()+"");
		int exceptionLen = result.length();
		if(exceptionLen > 0){
			if(exceptionLen > 250){
				result = result.substring(0, 250);
			}
		}
		
		return result;
	}
	
	private JavaMailSenderImpl newJavaMailSender(EmailMsg msg){
		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
		senderImpl.setPort(msg.getMailPort());
		senderImpl.setHost(msg.getMailHost());
		senderImpl.setDefaultEncoding("UTF-8");
		senderImpl.setUsername(msg.getMailUserName());
		senderImpl.setPassword(msg.getMailPassword());
		Properties javaMailProperties = new Properties();
		javaMailProperties.setProperty("mail.smtp.auth", "true");
		senderImpl.setJavaMailProperties(javaMailProperties);
		return senderImpl;
	}
	
	public static void main(String[] args) {
		String x = "123456";
		System.out.println(x.length()+x.substring(0, 6));
	}

}
