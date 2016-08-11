/*package com.yinker.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yinker.channel.core.dao.IAsileResCodeDao;
import com.yinker.channel.core.po.AsileResCode;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml"})
public class ResCodeTest {
	@Autowired
	private IAsileResCodeDao asileResCodeDao;
	
	@Test
	public void testResCode(){
		
		AsileResCode res = asileResCodeDao.getChanPlatResCodeRel("10002", "ESC0001000");
		if(res != null){
			System.out.println("渠道名称："+res.getAsileName()+",返回信息："+res.getResMsg());
		}else{
			System.out.println("未查询到关联关系");
		}
		
	}

}
*/