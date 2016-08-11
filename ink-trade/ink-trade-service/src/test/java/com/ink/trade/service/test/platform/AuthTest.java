/**
 * yinker.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月27日 下午2:26:58
 */
package com.ink.trade.service.test.platform;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ink.base.page.Page;
import com.ink.trade.api.platform.common.CommonResult;
import com.ink.trade.api.platform.trade.model.base.AuthEntity;
import com.ink.trade.api.platform.trade.model.in.AuthQueryInput;
import com.ink.trade.api.platform.trade.service.IAuthService;

/**
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月27日 下午2:26:58
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class AuthTest {
	
	@Autowired
	private IAuthService authService;
//	
//	@Autowired
//	private IAsileSignService asileSignService;
//	
	@Test
	public void testFindPage(){
		AuthQueryInput input = new AuthQueryInput();
		input.setPageNumber(1);
		input.setPageSize(10);
		CommonResult<Page<AuthEntity>> ret = authService.findPage(input);
		System.out.println("ret: "+ret.getCode());
		
	}
	
//	@Test
//	public void testFindPage2(){
//		AsileSignQueryInput input = new AsileSignQueryInput();
//		input.setPageNumber(1);
//		input.setPageSize(10);
//		CommonResult<Page<AsileSignEntity>> ret = asileSignService.findPage(input);
//		System.out.println("ret: "+ret.getCode());
//		
//	}
	
	
	
	

}
