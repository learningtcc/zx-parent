package com.ink.trade.service.test;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DubboTest {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(    
                new String[]{"spring/applicationContext.xml"});    
        context.start();  
       // HelloWorldTest bean=(HelloWorldTest) context.getBean("helloWorldTest");
        //System.out.println( bean.getHello().say("dfdsfsfds---sdf"));
        System.out.println("Press any key to exit.");    
        System.in.read(); 
    }
}
