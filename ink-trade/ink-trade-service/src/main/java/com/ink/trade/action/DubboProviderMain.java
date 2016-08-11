package com.ink.trade.action;

import java.io.IOException;

import com.sun.tools.doclets.internal.toolkit.util.SourcePath;
import org.apache.xpath.SourceTree;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
