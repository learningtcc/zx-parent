package com.ink.balance.action;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * dubbo服务启动入口
 */
public class DubboProviderMain {

    @SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"spring/applicationContext.xml"});
        context.start();
        System.out.println("Press any key to exit . ");
        System.in.read();
    }


}
