package com.ink.msgcenter.util;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.*;

/**
 * xml解析工具类
 * Created by aiyungui on 2016/5/19.
 */
public class XmlAnalysisUtil {

    /**
     * 解析xml文本
     * @param xmlContent
     * @return
     */
    public static Map<String,String> analysisXml(String xmlContent){

        Map<String,String> resultMap = new HashMap<String,String>();

        if (StringUtils.isBlank(xmlContent)){
            return resultMap;
        }
        try {
            Document document = DocumentHelper.parseText(xmlContent);
            Element rootElement = document.getRootElement();
            Iterator<Element> it = rootElement.elementIterator();
            while (it.hasNext()) {
                Element element = it.next();
                resultMap.put(element.getName(),element.getTextTrim());
            }
        } catch (Exception e) {
//            e.printStackTrace();
        }

        return resultMap;
    }

    /**
     * 解析xml文本
     * @param xmlContent
     * @return
     */
    public static List<Map<String,String>> analysisXmlForSecond(String xmlContent){

        List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();

        if (StringUtils.isBlank(xmlContent)){
            return mapList;
        }
        try {
            Document document = DocumentHelper.parseText(xmlContent);
            Element rootElement = document.getRootElement();
            Iterator<Element> firstIt = rootElement.elementIterator();
            while (firstIt.hasNext()) {
                Element firstElement = firstIt.next();
                Iterator<Element> secondIt = firstElement.elementIterator();
                Map<String,String> resultMap = new HashMap<String,String>();
                while (secondIt.hasNext()) {
                    Element element = secondIt.next();
                    resultMap.put(element.getName(),element.getTextTrim());
                }
                if (!resultMap.isEmpty()){
                    mapList.add(resultMap);
                }
            }
        } catch (Exception e) {
//            e.printStackTrace();
        }

        return mapList;
    }
}
