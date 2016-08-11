package com.ink.channel.core.utils;

import java.net.InetAddress;

public class GetLocalIpUtil {
    private String LOCAL_IP = null;
    private static volatile GetLocalIpUtil instance = null;

    public static GetLocalIpUtil getInstance() {
        if (instance == null) {
            synchronized (GetLocalIpUtil.class) {
                if (instance == null) {
                    instance = new GetLocalIpUtil();
                }
            }
        }
        return instance;
    }

    private GetLocalIpUtil() {
        try {
            this.LOCAL_IP = InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            this.LOCAL_IP = "127.0.0.1";
        }
    }

    public String getLocalIp() {
        return this.LOCAL_IP;
    }
}