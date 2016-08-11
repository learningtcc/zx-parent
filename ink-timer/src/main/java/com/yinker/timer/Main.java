package com.yinker.timer;

import com.yinker.base.utils.logUtil.YinkerLoger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author  wangxk
 * @version 1.0
 * @since 1.0
 * 定时任务系统
 */

public class Main {

    private static final YinkerLoger LOGGER =  YinkerLoger.getLogger(Main.class);

    public static final String DEFAULT_SPRING_CONFIG = "classpath*:yinker_timer.xml";

    private static volatile boolean running = true;

    static ClassPathXmlApplicationContext context;

    public static void main(String[] args) {
        LOGGER.info("YinkerTimer  loading........ ");
        try {
            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    try {
                        try {
                            if (context != null) {
                                context.stop();
                                context.close();
                                context = null;
                            }
                        } catch (Throwable e) {
                            LOGGER.error(e.getMessage(), e);
                        }
                        LOGGER.info("yinkerTimer timer stopped!");
                    } catch (Throwable t) {
                        LOGGER.error(t.getMessage(), t);
                    }
                    synchronized (Main.class) {
                        running = false;
                        Main.class.notify();
                    }
                }
            });

            context = new ClassPathXmlApplicationContext(DEFAULT_SPRING_CONFIG);
            context.start();

            LOGGER.info("yinkerTimer timer server started!");
        } catch (Throwable e) {
            LOGGER.error(e.getMessage(), e);
            System.exit(1);
        }
        LOGGER.info("YinkerTimer  start ");
        
        synchronized (Main.class) {
            while (running) {
                try {
                    Main.class.wait();
                } catch (Throwable ignored) {

                }
            }
        }

    }
}
