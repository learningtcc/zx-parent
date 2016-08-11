package com.ink.channel.core.utils;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;

/**
 * 采用Dom4j进行解析XML，然后反射生成vo
 * 
 * @author huohb
 *
 */
public class Dom4jXmlUtil {

	private static final Dom4jXmlUtil instance = new Dom4jXmlUtil();

	// singleton
	private Dom4jXmlUtil() {

	}

	public static Dom4jXmlUtil getInstance() {
		return instance;
	}

	/**
	 * 将对象转换为XML
	 * 
	 * @param obj
	 */
	public String convertVoToXml(Object obj) {
		Document doc = DocumentHelper.createDocument();
		// 获取实体类注解
		XmlRootElement annotation = obj.getClass().getAnnotation(XmlRootElement.class);
		String rootElementName = (annotation == null) ? obj.getClass().getSimpleName() : annotation.name();
		try {
			convertVoToElement(obj, doc.addElement(rootElementName));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		// 生成XML
		StringWriter writer = new StringWriter();
		XMLWriter out = new XMLWriter(writer);
		try {
			out.write(doc);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			if(writer != null){
				try {
					writer.close();
				} catch (IOException e) {
				}
			}
			if(out != null){
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}
		
		return writer.toString();
	}

	/**
	 * 转换对象到Element对象
	 * 
	 * @param obj
	 * @param element
	 */
	private void convertVoToElement(Object obj, Element element) throws Exception{
		// 转换自定义类 vo，通过反射处理 get 方法
		Method[] methods = obj.getClass().getMethods();
		Method m = null;
		for (int i = 0; i < methods.length; i++) {
			m = methods[i];
			// 判断是否 get 方法
			if (m.getName().startsWith("get") && m.getParameterTypes().length == 0) {
				// 获取结果
				Object methodReturnValue = m.invoke(obj, new Object[] {});
				String methodReturnTypeName = m.getReturnType().getName();
				String nodeName = lowerFirstLetter(m.getName().substring(3));
				// 不处理 getClass
				if (nodeName.equalsIgnoreCase("Class")) {
					continue;
				}
				// 如果 get 的结果为 null
				if (methodReturnValue == null) {
					appendNewNode(element, nodeName, null);
					continue;
				}
				// 判断并处理容器对象，暂只支持 List
				if (methodReturnTypeName.equalsIgnoreCase("java.util.List")) {
					convertListToElement((List)methodReturnValue, element, nodeName, m);
					continue;
				}
				// 尝试按照 java.xx 类型和简单类型进行处理
				if (!convertJavaSimpleObjectToDomNode(element, nodeName, methodReturnTypeName, methodReturnValue)) {
					XmlRootElement annotation = methodReturnValue.getClass().getAnnotation(XmlRootElement.class);
					String elementName = (annotation == null) ? nodeName : annotation.name();
					Element voElement = element.addElement(elementName);
					// 对于自定义 Vo，递归调用
					convertVoToElement(methodReturnValue, voElement);
				}
			}
		}
	}
	/**
	 * 将List转换为Element
	 * @param obj
	 * @param node
	 * @param nodeName
	 * @param method
	 * @throws Exception
	 */
	private  void convertListToElement(List list, Element node,
			String nodeName, Method method) throws Exception{
		Element listNode = node.addElement(nodeName);
		if (!CollectionUtils.isEmpty(list)) {
			Class<?> genericClass = list.get(0).getClass();
			XmlRootElement annotation = genericClass.getAnnotation(XmlRootElement.class);
			// 如果List泛型类有注解，采用注解的name，否则采用类名简称
			String genericClassName = (annotation == null) ? genericClass.getSimpleName() : annotation.name();
			// 循环处理每一个对象
			for (Object objItem : list) {
				Element genericNode = listNode.addElement(genericClassName);
				// 循环调用VoToElement
				convertVoToElement(objItem, genericNode);
			}
		}
	}
	/**
	 * 转换 java.xx 类型和简单类型对象
	 * @param node
	 * @param nodeName
	 * @param objTypeName
	 * @param objValue
	 * @return
	 */
	private  boolean convertJavaSimpleObjectToDomNode(Element node,
			String nodeName, String objTypeName, Object objValue) {
		
		if (objTypeName.equalsIgnoreCase("java.lang.String")) {
			// String
			appendNewNode(node, nodeName, (String) objValue);
		} else if (objTypeName.equalsIgnoreCase("java.sql.Timestamp")) {
			// java.sql.Timestamp
			SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			appendNewNode(node, nodeName, timeFormat.format(new Date(
					((Timestamp) objValue).getTime())));
		} else if (objTypeName.equalsIgnoreCase("java.util.Date")) {
			// Date
			SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			appendNewNode(node, nodeName, timeFormat.format(objValue));
		} else if (objTypeName.equalsIgnoreCase("java.math.BigDecimal")) {
			// BigDecimal
			appendNewNode(node, nodeName, ((BigDecimal) objValue).toString());
		} else if (objTypeName.equalsIgnoreCase("long")
				|| objTypeName.equalsIgnoreCase("int")
				|| objTypeName.equalsIgnoreCase("boolean")) {
			// Simple Type
			appendNewNode(node, nodeName, String.valueOf(objValue));
		} else {
			// 其它类型，直接返回 false
			return false;
		}
		
		return true;
	}
	/**
	 * 添加节点
	 * @param node
	 * @param nodeName
	 * @param nodeText
	 */
	private void appendNewNode(Element node, String nodeName, String nodeText){
		// 根据 nodeName 分别处理 List 等容器内嵌 Java 对象和自定义 Vo 属性
		if (nodeName != null) {
			// 非 List 等容器内嵌 Java 对象，首先根据属性名创建节点
			Element newElement = node.addElement(nodeName);
			// 处理节点处理内容
			if (nodeText != null && nodeText.trim().length() > 0) {
				newElement.setText(nodeText);
			}
		}
	}
	/**
	 * 转换Dom4j中Element为JavaBean
	 * 
	 * @param element
	 * @param obj
	 * @throws Exception
	 */
	public void convertElementToBean(Element element, Object obj) throws Exception {

		List<?> childNodes = element.elements();
		Method[] methods = obj.getClass().getMethods();
		for (int i = 0; i < childNodes.size(); i++) {
			Element nodeItem = (Element) childNodes.get(i);
			for (int j = 0; j < methods.length; j++) {
				Method m = methods[j];
				if (!m.getName().startsWith("set"))
					continue;
				// 判断方法名是否与节点名一致
				if (m.getName().substring(3).equalsIgnoreCase(nodeItem.getName())) {
					Class<?>[] parameterTypes = m.getParameterTypes();
					if (parameterTypes.length != 1) {
						continue;
					}
					String parameterName = parameterTypes[0].getName();
					// 获取 node value
					String nodeValue = nodeItem.getStringValue();
					// 处理 list
					if (parameterName.equalsIgnoreCase("java.util.List")) {
						convertDomNodeToList(nodeItem, parameterName, obj, m);
						continue;
					}
					// 如果还存在子节点说明此节点不是数据节点
					if (parameterName.contains("com.")) {
						getInnerObj(obj, m, nodeItem, parameterName);
						continue;
					}
					// 检查 null
					if (nodeValue == null) {
						continue;
					}
					// 最后按 Java 类型和简单类型处理
					if (!convertDomNodeToVoAttributes(parameterName, nodeValue, obj, m)) {
						throw new Exception("尚未支持的类型:" + parameterName);
					}
				}
			}
		}
	}

	/**
	 * 转换 XML 到 List
	 * 
	 * @param nodeItem
	 * @param parameterName
	 * @param nodeValue
	 * @param obj
	 * @param m
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	private void convertDomNodeToList(Element nodeItem, String parameterName, Object obj, Method m) throws Exception {
		// 获取所有子节点
		List<?> nodeList = nodeItem.elements();
		List list = new ArrayList();
		// 去掉set
		String listType = m.getName().substring(3);
		// 首字母大小写转换
		listType = listType.replaceFirst(listType.charAt(0) + "", String.valueOf(listType.charAt(0)).toLowerCase().toCharArray()[0] + "");
		ParameterizedType pt = null;
		try {
			pt = (ParameterizedType) obj.getClass().getDeclaredField(listType).getGenericType();
		} catch (Exception e) {
			throw new Exception("List类型" + listType + "没有指定泛型不能进行解析");
		}
		if (pt == null)
			return;
		Field field = obj.getClass().getDeclaredField(listType);

		// 判断泛型类型是否为null 不进行解析

		// 循环处理
		for (int i = 0; i < nodeList.size(); i++) {
			Element nodeSubItem = (Element) nodeList.get(i);
			Class itemClass = (Class) pt.getActualTypeArguments()[0];
			String itemClassName = itemClass.getName();
			String nodeSubValue = nodeSubItem.getStringValue();
			Object objResultItem = convertDomNodeToSimpleJavaObject(itemClassName, nodeSubValue, obj, m);
			if (objResultItem != null) {
				// 检查 List 中是否为 Java 简单类型
				list.add(objResultItem);
			} else {
				// 调整位置 简单类型不能实例化会报错
				Object objNewItem = itemClass.newInstance();
				convertElementToBean(nodeSubItem, objNewItem);
				list.add(objNewItem);
			}

		}
		m.invoke(obj, new Object[] { list });
	}

	/**
	 * 转换 XML 为 Java 类型或简单类型
	 * 
	 * @param parameterName
	 * @param nodeValue
	 * @return
	 * @throws Exception
	 */
	private Object convertDomNodeToSimpleJavaObject(String parameterName, String nodeValue, Object obj, Method m) throws Exception {
		if (parameterName.equalsIgnoreCase("java.lang.String")) {
			return nodeValue;
		} else if (parameterName.equals("java.sql.Timestamp")) {
			// 时间类型 
			SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				Timestamp time = new Timestamp(timeFormat.parse(nodeValue).getTime());
				m.invoke(obj, (Object[]) new Timestamp[] { time });
			} catch (Exception e) {
				SimpleDateFormat timeFormat2 = new SimpleDateFormat("yyyy-MM-dd");
				try {
					Timestamp time = new Timestamp(timeFormat2.parse(nodeValue).getTime());
					m.invoke(obj, (Object[]) new Timestamp[] { time });
				} catch (Exception ex) {
					throwException(parameterName, nodeValue, obj, m);
				}
			}
		} else if (parameterName.equals("java.util.Date")) {
			// 时间类型
			SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				Date date = timeFormat.parse(nodeValue);
				m.invoke(obj, (Object[]) new Date[] { date });
			} catch (Exception e) {
				SimpleDateFormat timeFormat2 = new SimpleDateFormat("yyyy-MM-dd");
				try {
					Date date = timeFormat2.parse(nodeValue);
					m.invoke(obj, (Object[]) new Date[] { date });
				} catch (Exception ex) {
					throwException(parameterName, nodeValue, obj, m);
				}
			}
		} else if (parameterName.equals("java.math.BigDecimal")) {
			try {
				return new BigDecimal(nodeValue);
			} catch (Exception e) {
				throwException(parameterName, nodeValue, obj, m);
			}
		} else if (parameterName.equals("java.lang.Long")) {
			try {
				return new Long(nodeValue);
			} catch (Exception e) {
				throwException(parameterName, nodeValue, obj, m);
			}
		} else if (parameterName.equals("java.lang.Integer")) {
			try {
				return new Integer(nodeValue);
			} catch (Exception e) {
				throwException(parameterName, nodeValue, obj, m);
			}
		} else if (parameterName.equals("java.lang.Boolean")) {
			try {
				return new Boolean(nodeValue);
			} catch (Exception e) {
				throwException(parameterName, nodeValue, obj, m);
			}
		}
		return null;
	}

	/**
	 * 转换 XML 为 VO的 Java 类型或简单类型 属性
	 * 
	 * @param parameterName
	 * @param nodeValue
	 * @param obj
	 * @param m
	 * @return
	 * @throws Exception
	 */
	private boolean convertDomNodeToVoAttributes(String parameterName, String nodeValue, Object obj, Method m) throws Exception {
		if (parameterName.equalsIgnoreCase("java.lang.String")) {
			m.invoke(obj, (Object[]) new String[] { nodeValue });
		} else if (parameterName.equals("java.sql.Timestamp")) {
			// 时间类型
			SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				Timestamp time = new Timestamp(timeFormat.parse(nodeValue).getTime());
				m.invoke(obj, (Object[]) new Timestamp[] { time });
			} catch (Exception e) {
				SimpleDateFormat timeFormat2 = new SimpleDateFormat("yyyy-MM-dd");
				try {
					Timestamp time = new Timestamp(timeFormat2.parse(nodeValue).getTime());
					m.invoke(obj, (Object[]) new Timestamp[] { time });
				} catch (Exception ex) {
					throwException(parameterName, nodeValue, obj, m);
				}
			}
		} else if (parameterName.equals("java.util.Date")) {
			// 时间类型
			SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				Date date = timeFormat.parse(nodeValue);
				m.invoke(obj, (Object[]) new Date[] { date });
			} catch (Exception e) {
				try {
					SimpleDateFormat timeFormat2 = new SimpleDateFormat("yyyy-MM-dd");
					Date date = timeFormat2.parse(nodeValue);
					m.invoke(obj, (Object[]) new Date[] { date });
				} catch (Exception ex) {
					throwException(parameterName, nodeValue, obj, m);
				}
			}
		} else if (parameterName.equals("java.math.BigDecimal")) {
			try {
				BigDecimal big = new BigDecimal(nodeValue);
				m.invoke(obj, (Object[]) new BigDecimal[] { big });
			} catch (Exception e) {
				throwException(parameterName, nodeValue, obj, m);
			}
		} else if (parameterName.equals("long")) {
			try {
				Long lo = new Long(nodeValue);
				m.invoke(obj, (Object[]) new Long[] { lo });
			} catch (Exception e) {
				throwException(parameterName, nodeValue, obj, m);
			}
		} else if (parameterName.equals("int")) {
			try {
				Integer integer = new Integer(nodeValue);
				m.invoke(obj, (Object[]) new Integer[] { integer });
			} catch (Exception e) {
				throwException(parameterName, nodeValue, obj, m);
			}
		} else if (parameterName.equals("boolean")) {
			try {
				Boolean bool = new Boolean(nodeValue);
				m.invoke(obj, (Object[]) new Boolean[] { bool });
			} catch (Exception e) {
				throwException(parameterName, nodeValue, obj, m);
			}
		} else {
			return false;
		}
		return true;
	}

	/**
	 * 处理内部vo
	 * 
	 * @param obj
	 * @param m
	 * @param nodeInerItem
	 * @param parameterName
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	private void getInnerObj(Object obj, Method m, Element nodeInerItem, String parameterName) throws Exception {
		Class itemClass = Class.forName(parameterName);
		Object objInner = itemClass.newInstance();
		convertElementToBean(nodeInerItem, objInner);
		m.invoke(obj, (Object[]) new Object[] { objInner });
	}

	/**
	 * 构造并抛出异常
	 * 
	 * @param msg
	 * @param parameterName
	 * @param nodeValue
	 * @param obj
	 * @param m
	 * @throws Exception
	 */
	private void throwException(String parameterName, String nodeValue, Object obj, Method m) throws Exception {
		// 获取类型
		String type = parameterName;
		int index = type.lastIndexOf(".");
		if (index > 0) {
			type = type.substring(index + 1);
		}

		throw new Exception("Unsupport " + type + "(" + nodeValue + ") at " + obj.getClass().getName() + "." + m.getName() + "(" + parameterName + ")");
	}
	/**
	 * 首字母小写
	 * @param str
	 * @return
	 */
	private String lowerFirstLetter(String str){
		
		if(str == null || "".trim().equals(str)){
			return "";
		}
		
		return String.valueOf(str.charAt(0)).toLowerCase().concat(str.substring(1));
		
	}

}
