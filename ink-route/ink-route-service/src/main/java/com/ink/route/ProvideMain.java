package com.ink.route;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by YKDZ075 on 2017/5/23.
 */
public class ProvideMain {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath*:spring/applicationContext.xml");
        ctx.start();
        try {
            System.in.read();
        } catch (Exception ex) {
            System.out.println("args = [" + ex.getMessage() + "]");
        }
    }
}
