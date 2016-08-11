/**
 * yinker.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description TODO
 * @author xuguoqi
 * @date 2016年4月11日 下午2:23:44
 */
package com.ink.test.balance;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ink.balance.api.model.in.PageParamInput;
import com.ink.balance.api.model.out.CheckCommonPageOutput;
import com.ink.balance.api.model.out.CommonOutput;
import com.ink.balance.api.model.out.PlatformDataOutput;
import com.ink.balance.api.service.IPlatformDataService;

/**
 * @Description TODO
 * @author xuguoqi
 * @date 2016年4月11日 下午2:23:44
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml"})
public class PlatFormDataTest {
	
	@Autowired
	private IPlatformDataService platformDataService;
	
	@Test
	public void pageQueryTest(){
		PageParamInput paramInput = new PageParamInput();
		paramInput.setNumPerPage(10);
		paramInput.setPageNum(1);
		
		CommonOutput<CheckCommonPageOutput<PlatformDataOutput>> ret = platformDataService.pageQuery(paramInput);
		if(0==ret.getCode()){
			CheckCommonPageOutput<PlatformDataOutput> businessObj = ret.getBusinessObj();
			System.out.println("buss:"+businessObj);
			
		}
		
	}
	
	

}
