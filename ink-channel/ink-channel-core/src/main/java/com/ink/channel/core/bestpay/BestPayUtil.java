package com.ink.channel.core.bestpay;
import org.apache.commons.lang.StringUtils;

import net.sf.json.JSON;
import net.sf.json.xml.XMLSerializer;
public class BestPayUtil {
    
    private static int i = 0;
    
    /**
     * XML转JSON
     * @param xml
     * @return
     */
    public static JSON getJSONFromXml(String xml){
        XMLSerializer xmlSerializer = new XMLSerializer();
        JSON json = xmlSerializer.read(xml);
        return json;
    }
    
    /**
     * 翼支付代收交易流水号生成方法<p>
     * 服务器编码+13位系统当前毫秒+100内的递增数(每10毫秒可生成100个序列号)
     * @param serverName
     * @return
     */
    public synchronized static String getSerialNumber(String serverName){
        i = i  % 100;
        String index = (i < 10) ? ("0" + i):""+i;
        String orderNumber = serverName + System.currentTimeMillis() + index;
        i++;
        return orderNumber;
    }
    
    /**
     * 获取IP 4段的集合
     * @param ip
     * @return
     */
    public static String[] getIpArray(String ip){
        if(StringUtils.isBlank(ip)){
            return null;
        }
        String[] ipArray = ip.split("\\.");
        return ipArray;
    }
    
    public static void main(String[] args) {
        /*for (int i = 0; i < 1000; i++) {
            System.out.println(getSerialNumber("97"));
        }*/
        System.out.println(getIpArray("127.0.0.1")[3]);
    }
    
}
