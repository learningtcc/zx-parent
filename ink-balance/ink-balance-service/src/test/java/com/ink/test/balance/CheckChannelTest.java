/**
 * yinker.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description TODO
 * @author xuguoqi
 * @date 2016年4月7日 下午5:12:55
 */
package com.ink.test.balance;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ink.balance.api.model.in.CheckQueryParamInput;
import com.ink.balance.api.model.in.PageParamInput;
import com.ink.balance.api.model.out.CheckChannelOutput;
import com.ink.balance.api.model.out.CommonOutput;
import com.ink.balance.api.service.ICheckMainService;
import com.ink.balance.core.po.CheckChannel;
import com.ink.base.page.Page;

/**
 * @Description TODO
 * @author xuguoqi
 * @date 2016年4月7日 下午5:12:55
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml"})
public class CheckChannelTest {
	
	@Autowired
	private ICheckMainService checkMainService;
	
	@Test
	public void testPageQuery(){
		 PageParamInput pageParam = new PageParamInput();
		 pageParam.setNumPerPage(10);
		 pageParam.setPageNum(1);
		CommonOutput<Object> ret = checkMainService.pageQuery(new CheckQueryParamInput(), pageParam);
		@SuppressWarnings("unchecked")
		Page<CheckChannel> page = (Page<CheckChannel>) ret.getBusinessObj();
		System.out.println(page.getResult().get(0));
	}
	
	@Test
	public void testGetDetails(){
		CommonOutput<CheckChannelOutput> ret = checkMainService.getCheckDetails(100000049L);
		System.out.println("ret.code="+ret.getCode());
		System.out.println("obj"+ret.getBusinessObj());
	}
	
	@Test
	public void testUpdateStatusBatch(){
		List<Long> ids =  new ArrayList<>();
		ids.add(100000050L);
		CommonOutput<Object> ret = checkMainService.updateBatchStatus(ids, "对账成功");
		System.out.println("code:"+ret.getCode());
	}

}
