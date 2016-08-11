package com.ink.base;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class IpUtils {

    /**
     * 使用 {@code java.net.InetAddress.isSiteLocalAddress()} 获取内网IP
     *
     * @return 内网IP
     */
    public static String getLocalAddress() {
        List<String> ipList = new ArrayList<String>();
        try {
            Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();
            while (nis.hasMoreElements()) {
                NetworkInterface ni = nis.nextElement();
                Enumeration<InetAddress> addrs = ni.getInetAddresses();
                while (addrs.hasMoreElements()) {
                    InetAddress inetAddress = addrs.nextElement();
                    if (inetAddress.isSiteLocalAddress()) {
                        ipList.add(inetAddress.getHostAddress());
                    }
                }
            }

        } catch (SocketException e) {
            throw new RuntimeException("Can't get local ip.", e);
        }
        if (ipList.isEmpty()) {
            throw new RuntimeException("No available local ip.");
        }
        // return ipList.get(ipList.size() - 1);
        return ipList.get(0);// 选择第一个网卡的ip，因为后面的可能有虚拟的
    }
}
