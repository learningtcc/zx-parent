/**
 * yinker.com Copyright (c) 2016-2025 All Rights Reserved.
 *
 * @Description TODO
 * @author xuguoqi
 * @date 2016年4月25日 下午1:48:35
 */
package com.ink.trade.service.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ink.asile.core.manager.IAsileInfoManager;
import com.ink.asile.core.manager.IAsileResCodeManager;
import com.ink.asile.core.manager.IAsileSignManager;
import com.ink.asile.core.po.AsileInfo;
import com.ink.asile.core.po.AsileResCode;
import com.ink.base.log.util.YinkerLogger;
import com.ink.basic.core.manager.IBankcardBinManager;
import com.ink.channel.api.service.AuthorityService;
import com.ink.trade.api.service.IPrecollectionService;
import com.ink.trade.api.service.IPrepaidService;
import com.ink.trade.api.service.ISignApplyService;
import com.ink.trade.core.manager.IAuthManager;
import com.ink.trade.core.manager.IAuthOrderManager;
import com.ink.trade.core.po.Auth;
import com.ink.trade.core.po.AuthOrder;
import com.ink.trade.core.query.AuthQuery;
import com.ink.trade.service.mq.AsileSignToAccProducer;

/**
 * @Description TODO
 * @author xuguoqi
 * @date 2016年4月25日 下午1:48:35
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class AuthTest {
    YinkerLogger log = YinkerLogger.getLogger(AuthTest.class);
//    @Autowired
//    private IAuthManager authManager;
    @Autowired
    private ISignApplyService signApplyService;
    @Autowired
    private IPrecollectionService precollectionService;
    @Autowired
    private IPrepaidService prepaidservice;
//    @Autowired
//    private IAsileResCodeManager asileResCodeManager;
//    @Autowired
//    private IAsileInfoManager asileInfoManager;
    
    
    @Autowired
    private AuthorityService authorityService;
    @Autowired
    private IAuthOrderManager authOrderManager;
    @Autowired
    private IAsileResCodeManager asileResCodeManager;
    @Autowired
    private IAsileSignManager asileSignManager;
    @Autowired
    private AmqpTemplate amqpTemplate;
    @Autowired
    private AsileSignToAccProducer asileSignToAccProducer;
    @Autowired
    private IAuthManager authManager;
    @Autowired
    private IBankcardBinManager bankCardBinManager;
    @Autowired
    private IAsileInfoManager asileInfoManager;
    /**
     *
     * String mchId="1000000000002"; String custId="21234567806"; String custName="zhouxiang"; String idType = "01";
     * String idNo = "143102938471"; String bankMblNo="13800138001"; String cardType="0";//银行卡类型 0-借记卡 1-信用卡 2-准贷记卡
     * 3-储蓄账户 4-企业结算账户 9-未知
     */
    String mchId = "1000000000002";
    String custId = "21234567806";
    String cardNo = "143102938471";

    @Test
    public void testAuth() {
        AuthQuery query = new AuthQuery();
        query.setCardNo(cardNo);
        query.setMchId(mchId);
        query.setUserId(custId);
        List<Auth> find = authManager.find(query);
        System.out.println("size :" + find.size());

    }
    @Test
    public void testRedis(){
        AsileResCode asileResCode= asileResCodeManager.findByAsileCodeAndAsileResCode("10003", "000000");
        System.out.println(asileResCode.toString());
        AsileResCode asileResCode1= asileResCodeManager.findByAsileCodeAndAsileResCode("10003", "000000");
        System.out.println(asileResCode1.toString());
    }
    @Test
    public void test3(){
      AsileInfo asileInfo=  asileInfoManager.getById(1l);
      System.out.println(asileInfo);
    }
    @Test
    public void test4(){
        AuthOrder order=new AuthOrder();
        order.setId(736110681078095872l);
        order.setToken("123");
        order.setReqId("12121");
        authOrderManager.update(order);
    }

}
