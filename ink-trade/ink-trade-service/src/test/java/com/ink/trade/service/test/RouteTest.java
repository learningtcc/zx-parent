package com.ink.trade.service.test;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ink.asile.core.manager.IAsileBankManager;
import com.ink.asile.core.po.AsileBank;
import com.ink.trade.api.enums.RouteBusinessType;
import com.ink.trade.api.model.in.AsileRouteInput;
import com.ink.trade.api.model.out.AsileRouteOutput;
import com.ink.trade.api.rule.IAsileRoute;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class RouteTest {
	
	@Autowired
	private IAsileRoute asileRoute;
//	@Autowired
//	private IChannelBusinessSupportManager channelBusinessSupportManager;
	@Autowired
	private IAsileBankManager asileBankManager;
	@Test
	public void asileRouteTest() throws Exception{

		System.out.println("route test begin");
		AsileRouteInput ari = new AsileRouteInput();
		// 代收签约，不发短信
		ari.setRouteBusinessType(RouteBusinessType.AUTH);
		ari.setTradeDate(new Date());
		ari.setBankShort("ZSYH");
		ari.setAmt(new BigDecimal(200));

		AsileRouteOutput out = asileRoute.getTradeAsile(ari);
		System.out.println("路由结果"+out.toString());
	}
	@Test
	public void refreshCache(){

//		ChannelBusinessSupport channelBusinessSupport = channelBusinessSupportManager.getById(5L);
//		channelBusinessSupport.setIsCertifiedpaySupport("Y");
//		int affectRow = channelBusinessSupportManager.update(channelBusinessSupport);

		AsileBank asileBank = asileBankManager.getById(5L);
		asileBank.setBankShort("ZSYH");
		int affectRow = asileBankManager.update(asileBank);
		System.out.println("更新影响"+affectRow+"条");
	}
	@Test
	public void testBean(){
		System.out.println("=====");
	}

}
