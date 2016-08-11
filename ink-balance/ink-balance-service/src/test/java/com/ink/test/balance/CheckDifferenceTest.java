/**
 * yinker.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description TODO
 * @author xuguoqi
 * @date 2016年4月11日 上午10:38:40
 */
package com.ink.test.balance;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ink.balance.api.model.in.PageParamInput;
import com.ink.balance.api.service.ICheckDifferenceService;

/**
 * @Description TODO
 * @author xuguoqi
 * @date 2016年4月11日 上午10:38:40
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml"})
public class CheckDifferenceTest {
	
	@Autowired
	private ICheckDifferenceService checkDifferenceService;
	
	@Test
	public void testPageQuery(){
		PageParamInput paramInput = new PageParamInput(1, 10);
		paramInput.setNumPerPage(10);
		paramInput.setPageNum(1);
//		CommonOutput<Object> ret = checkDifferenceService.pageQuery(paramInput);
//		System.out.println("code;"+ret.getCode());
//		System.out.println(ret.getBusinessObj().toString());
	}
	@Test
	public void handle2oneSideToMatch(){
		System.out.println(checkDifferenceService.handle2oneSideToMatch(100000125L,100000126L));
	}

}
