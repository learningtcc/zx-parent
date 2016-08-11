package com.ink.msgcenter.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ink.msgcenter.api.model.input.EmailInput;
import com.ink.msgcenter.api.model.output.MsgOutput;
import com.ink.msgcenter.api.service.EmailService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/test-applicationContext.xml")
public class EmailServiceTest {
	
	@Autowired
	private EmailService emailService;
	
//	@Test
	public void sendEmailForSeq(){
		
//		EmailInput input = new EmailInput();
//		input.setBizId("11");
//		input.setMerctCode("10000");
//		input.setTempId("18699");
//		List<TemplateParam> paramList = new ArrayList<TemplateParam>();
//		paramList.add(new TemplateParam("fullName","宗4"));
//		paramList.add(new TemplateParam("goods","245袋红枣"));
////		input.setParamList(paramList);
//		input.setEmail("13811945468@126.com");
//		MsgOutput output = emailService.sendEmail(input);
		
//		for(int i=0;i<200;i++){
//			StringBuffer sb = new StringBuffer(1000000);
//			for (int j = 0; j < 10000; j++) {
//				sb.append(",zongrui@126.com");
//			}
//			input.setEmail(sb.substring(1));
//			MsgOutput output = emailService.sendEmail(input);
//			System.out.println(output.getMsgId());
//		}
	}
	
	@Test
	public void sendEmailForFreemarker(){
		
		EmailInput input = new EmailInput();
		input.setBizId("11");
		input.setMerctCode("10000");
		input.setTempId("18700");
//		List<TemplateParam> paramList = new ArrayList<TemplateParam>();
		
//		paramList.add(new TemplateParam("fullName","宗4"));
//		paramList.add(new TemplateParam("goods","245袋红枣"));
		
		
		List<HashMap<String,String>> userList  = new ArrayList<>();
		
		input.setEmail("13811945468@126.com");
		MsgOutput output = emailService.sendEmail(input);
		
//		for(int i=0;i<200;i++){
//			StringBuffer sb = new StringBuffer(1000000);
//			for (int j = 0; j < 10000; j++) {
//				sb.append(",zongrui@126.com");
//			}
//			input.setEmail(sb.substring(1));
//			MsgOutput output = emailService.sendEmail(input);
//			System.out.println(output.getMsgId());
//		}
	}
}