package com.ink.channel.core.utils;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;

import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLFilterImpl;

/**
 * Jaxb javabean和XML互相转换工具类
 * @author huohb
 *
 */
public class JaxbUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(JaxbUtil.class);
	
	/*
	 * 转换成不带<?xml version="1.0" encoding="utf-8" standalone="yes"?>字符串
	 */
	public static String convertToNoDeclareXml(Object obj){
		return convertToXml(obj,true);
	}
	
	public static String convertToNoDeclareXml(Object obj,String encoding){
		return convertToXml(obj,encoding,true);
	}
	
	/*
	 * 转换成带<?xml version="1.0" encoding="utf-8" standalone="yes"?>字符串
	 */
	public static String convertToXml(Object obj){
		return convertToXml(obj,false);
	}
	
	public static String convertToXml(Object obj,boolean fragment) {  
        return convertToXml(obj, "UTF-8",fragment);  
    }
	
	 /** 
     * JavaBean转换成xml 
     * @param obj 
     * @param encoding  
     * @return  
     */ 
	public static String convertToXml(Object obj, String encoding,boolean fragment) {  
        try {  
        	 JAXBContext context = JAXBContext.newInstance(obj.getClass());

             Marshaller marshaller = context.createMarshaller();
             marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);  // 是否格式化生成的xml串
             marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);  //编码格式
             marshaller.setProperty(Marshaller.JAXB_FRAGMENT, fragment);// 是否省略xm头声明信息

             StringWriter out = new StringWriter();
             OutputFormat format = new OutputFormat();
             format.setIndent(false);
             format.setNewlines(false);
             format.setNewLineAfterDeclaration(false);
             XMLWriter writer = new XMLWriter(out, format);

             XMLFilterImpl nsfFilter = new XMLFilterImpl() {
                 private boolean ignoreNamespace = false;
                 private String rootNamespace = null;
                 private boolean isRootElement = true;

                 @Override
                 public void startDocument() throws SAXException {
                     super.startDocument();
                 }

                 @Override
                 public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
                     if (this.ignoreNamespace) uri = "";
                     if (this.isRootElement) this.isRootElement = false;
                     else if (!uri.equals("") && !localName.contains("xmlns")) localName = localName + " xmlns=\"" + uri + "\"";

                     super.startElement(uri, localName, localName, atts);
                 }

                 @Override
                 public void endElement(String uri, String localName, String qName) throws SAXException {
                     if (this.ignoreNamespace) uri = "";
                     super.endElement(uri, localName, localName);
                 }

                 @Override
                 public void startPrefixMapping(String prefix, String url) throws SAXException {
                     if (this.rootNamespace != null) url = this.rootNamespace;
                     if (!this.ignoreNamespace) super.startPrefixMapping("", url);

                 }
             };
             nsfFilter.setContentHandler(writer);
             marshaller.marshal(obj, nsfFilter);
             return out.toString();
        } catch (Exception e) {  
        	logger.error("消息格式转换错误 object->xml", e);
			throw new RuntimeException(e);  
        }  
    } 
	
	/** 
     * xml转换成JavaBean 
     * @param xml 
     * @param c 
     * @return 
     */
	@SuppressWarnings("unchecked")  
    public static <T> T converyToJavaBean(String xml, Class<T> c) {  
        T t = null;  
        try {  
            JAXBContext context = JAXBContext.newInstance(c);  
            Unmarshaller unmarshaller = context.createUnmarshaller(); 
            unmarshaller.getAdapter(XmlAdapter.class);
            StringReader reader = new StringReader(xml);  
            SAXParserFactory sax = SAXParserFactory.newInstance();  
            sax.setNamespaceAware(false);
            XMLReader xmlReader = sax.newSAXParser().getXMLReader();  
            Source source = new SAXSource(xmlReader, new InputSource(reader)); 
            t = (T) unmarshaller.unmarshal(source);  
        } catch (Exception e) {  
        	logger.error("消息数据格式转换错误，XML->object", e);
			throw new RuntimeException(e);
        }  
        return t;  
    }
}
