/**
 * yinker.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description TODO
 * @author xuguoqi
 * @date 2016年4月11日 上午11:31:09
 */
package com.ink.test.balance;

import com.ink.balance.api.model.in.OperationLogInput;
import com.ink.balance.api.model.in.PageParamInput;
import com.ink.balance.api.model.out.ChannelDataOutput;
import com.ink.balance.api.model.out.CheckCommonPageOutput;
import com.ink.balance.api.model.out.CommonOutput;
import com.ink.balance.api.service.IChannelDataService;
import com.ink.balance.api.service.IOperationLogService;
import com.ink.balance.core.manager.IOperationLogManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Description TODO
 * @author xuguoqi
 * @date 2016年4月11日 上午11:31:09
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml"})
public class ChannelDataTest {
	
	@Autowired
	private IChannelDataService channelDataService;

	@Autowired
	private IOperationLogService operationLogService;
	
	@Test
	public void testPageQuery(){

		PageParamInput paramInput = new PageParamInput(0, 10);
		paramInput.setNumPerPage(10);
		paramInput.setPageNum(1);
		CommonOutput<CheckCommonPageOutput<ChannelDataOutput>> ret = channelDataService.pageQuery(paramInput);
		System.out.println("code="+ret.getCode());
		
		if(ret.getCode()==0){
			CheckCommonPageOutput<ChannelDataOutput> pageOutput = ret.getBusinessObj();
			System.out.println(pageOutput.toString());
		}
	}
	@Test
	public void saveOperationLog(){

		OperationLogInput operationLogInput=new OperationLogInput();
		operationLogInput.setOperator("1111");
		operationLogInput.setNewContent("new");
		operationLogInput.setOldContent("old");
		int ret=operationLogService.save(operationLogInput);

		if(ret==1){
			System.out.println("插入成功");
		}
	}


}
