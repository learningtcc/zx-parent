package com.ink.route.action;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

@SuppressWarnings("resource")
public class DubboProviderMain {
    public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(    
                new String[]{"spring/applicationContext.xml"});    
        context.start();  
    
        System.out.println("Press any key to exit.");    
        System.in.read(); 
    }

}
