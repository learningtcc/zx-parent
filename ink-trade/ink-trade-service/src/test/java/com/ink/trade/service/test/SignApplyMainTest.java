/**
 * yinker.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description TODO
 * @author xuguoqi
 * @date 2016年4月29日 上午11:02:23
 */
package com.ink.trade.service.test;

import org.junit.Test;

import com.ink.trade.api.model.in.SignApplyInput;

/**
 * @Description TODO
 * @author xuguoqi
 * @date 2016年4月29日 上午11:02:23
 */
public class SignApplyMainTest {
	
	@Test
	public void teetInputParam(){
		SignApplyInput input = new SignApplyInput();
		System.out.println("input :"+input);
	}
	
	@Test
	public void subStirng(){
		String bb = "880808";
		String  ss  = "880808kfadfa";
		String substring = ss.substring(0, bb.length());
		System.out.println("sub; "+substring);
	}

}
