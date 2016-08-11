/**
 * yinker.com Copyright (c) 2016-2025 All Rights Reserved.
 *
 * @Description TODO
 * @author xuguoqi
 * @date 2016年4月25日 下午1:48:35
 */
package com.ink.trade.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ink.base.log.util.YinkerLogger;
import com.ink.basic.core.manager.IBankcardBinManager;
import com.ink.basic.core.po.BankcardBin;

/**
 * @Description TODO
 * @author xuguoqi
 * @date 2016年4月25日 下午1:48:35
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class BankCardBinTest {
	YinkerLogger log = YinkerLogger.getLogger(BankCardBinTest.class);

	@Autowired
	private IBankcardBinManager  bankcardBinManager;

    @Test
    public void testQueryCardBin() {
    	String bankNo = "4270281111111111";
    	BankcardBin byCardBin = bankcardBinManager.getByCardBin(bankNo);
    	System.out.println("cardbin: "+byCardBin);
    }

}
