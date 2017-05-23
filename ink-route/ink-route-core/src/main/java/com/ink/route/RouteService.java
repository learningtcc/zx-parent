package com.ink.route;

import com.ink.route.api.enums.PayTypeEnum;
import com.ink.route.api.model.Channel;
import com.ink.route.api.model.Order;
import org.kie.api.cdi.KSession;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by YKDZ075 on 2016/8/29.
 */
@Service("routeService")
public class RouteService {
    @KSession("ksession_route")
   private KieSession kieSession;
    public void route(){
//        Message message = new Message();
//        message.setMessage("Hello World");
//        message.setStatus(Message.HELLO);
//        System.out.println(message.getStatus());
//        //将实体类插入执行规则
//        kieSession.insert(message);
//        kieSession.fireAllRules();
//        System.out.println(message.getStatus()+"  "+Message.GOODBYE);
//        System.out.println(message.getMessage());
        Channel c = new Channel();
        c.setName("baofu1");
        Channel channel = new Channel();
        channel.setName("baofu");
        channel.setPayType(PayTypeEnum.QuickPay.getCode());
        c.setPayType(PayTypeEnum.QuickPay.getCode());
        Order o=new Order();
        o.setAmount(BigDecimal.ZERO);
        o.setPayType(PayTypeEnum.QuickPay.getCode());
        //将实体类插入执行规则
        kieSession.insert(o);
        kieSession.insert(c);
        kieSession.insert(channel);
        kieSession.fireAllRules();
//        System.out.println(message.getStatus()+"  "+Message.GOODBYE);
//        System.out.println(message.getMessage());
    }
}
