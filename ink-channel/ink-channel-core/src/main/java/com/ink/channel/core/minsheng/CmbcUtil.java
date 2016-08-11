package com.ink.channel.core.minsheng;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;


public class CmbcUtil {
    
    /**
     * getOrderNumber <p>获取商户唯一订单号</p>   
     * <p>于2015年3月3日 由 董小满 创建 </p>
     * @author  <p>当前负责人 董小满</p>
     * @return      订单号=业务ID+时间戳+随机数
     */
    public static String getOrderNumber(String bussinesType){
        int randomNum = (int) ((Math.random() * 9 + 1) * 10000);
        return bussinesType + System.currentTimeMillis() + randomNum;
    }
    
//    public static String getCmbcQuickPayNumber(String type){
//        SimpleDateFormat sdfsdf = new SimpleDateFormat("yyyyMMddHHmmss");
//        int randomNum = (int) ((Math.random() * 9 + 1) * 1000);
//        return CmbcPayInfo.quickmerchantNo+sdfsdf.format(new Date())+randomNum+type;
//    }
    
    public Map<String, Object> Xmlprocessor(String param){
        try {
            // 1.通过DOM解析
            Document doc = DocumentHelper.parseText(param);
            // 2.获取跟节点
            Element rootElt = doc.getRootElement();
            // 3.获取根节点下的子节点head
            Iterator<?> iter = rootElt.elementIterator("head");
            while (iter.hasNext()) {
                Element recordEle = (Element) iter.next();
                // 5.拿到head节点下的子节点respCode值
                // respCode = recordEle.elementTextTrim("respCode");
                // respMsg = recordEle.elementTextTrim("respMsg");
            }
        } catch (Exception e) {
            System.out.println("DOM解析成MAP出现异常，异常信息:" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    
//    /**
//     * 获得报文头字符串
//     * <p> Create Date: 2014-5-10 </p>
//     * @param tranCode
//     * @return
//     */
//    public static String getMsgHeadXml(String tranCode, String currTime){
//        StringBuffer headXml = new StringBuffer();
//        headXml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><message><head><version>01</version>");
//        headXml.append("<msgType>0001</msgType><chanId>").append(CmbcPayInfo.chanId).append("</chanId><merchantNo>").append(CmbcPayInfo.quickmerchantNo).append("</merchantNo>");
//        headXml.append("<clientDate>").append(currTime).append("</clientDate>");
//        headXml.append("<serverDate></serverDate><tranFlow>").append(getCmbcQuickPayNumber("5")).append("</tranFlow><tranCode>").append(tranCode).append("</tranCode>");
//        headXml.append("<respCode></respCode><respMsg></respMsg></head>");
//        
//        return headXml.toString();
//    }
//    
//    public static String getMsgMac(String xml){
//        return DigestUtils.md5Hex(xml + CmbcPayInfo.quickpayKey);
//    }
    
    
}
