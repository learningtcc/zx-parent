package com.ink.base.log.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class IpUtils {
	
	private static String localIp = getLocalIp();

    /**
     * 使用 {@code java.net.InetAddress.isSiteLocalAddress()} 获取内网IP
     *
     * @return 内网IP
     */
    private static String getLocalIp() {
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
    
    public static String getLocalAddress() {
        return localIp;
    }
    
    /**
	 * 获取用户真实的IP
	 * @author zongtt
	 * @param request
	 * @return
	 * 2016年4月19日16:35:25
	 */
	public static String getRemortIP(HttpServletRequest request) {
		
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}

		return ip;
	}
}
