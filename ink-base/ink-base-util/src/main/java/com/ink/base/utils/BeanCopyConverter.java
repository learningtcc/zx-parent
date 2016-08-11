/**
 *
 */
package com.ink.base.utils;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @author wangxk
 * @date 2016年3月10日
 * @time 上午10:10:59 类转化器 从一个类转化为另一个类 主要是用来转化同一个模型的Model和PO 他们具有基本相同的属性名称
 */
public final class BeanCopyConverter {
	/**
	 * 具有相同属性名称的对象转化
	 * 
	 * @param srcClazz
	 * @param dstClazz
	 * @return
	 */
	public static <T1, T2> T1 converterClass(final T2 srcClazz, Class<T1> dstClazz) {
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(srcClazz);
		if (jsonObject == null) {
			return null;
		}
		return JSONObject.toJavaObject(jsonObject, dstClazz);
	}

	/**
	 * 集合转化
	 * 
	 * @param srcClazzCollection
	 * @param dstClazz
	 * @return
	 */
	public static <T1, T2> Collection<T1> converterClass(final Collection<T2> srcClazzCollection, Class<T1> dstClazz) {
		JSONArray jsonArray = (JSONArray) JSONObject.toJSON(srcClazzCollection);
		if (jsonArray == null) {
			return null;
		}
		return JSONArray.parseArray(jsonArray.toJSONString(), dstClazz);
	}

	/**
	 * 数组转化
	 * 
	 * @param srcClazzArray
	 * @param dstClazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T1, T2> T1[] converterClass(final T2[] srcClazzArray, Class<T1> dstClazz) {
		JSONArray jsonArray = (JSONArray) JSONObject.toJSON(srcClazzArray);
		if (jsonArray == null) {
			return null;
		}
		List<T1> result = JSONArray.parseArray(jsonArray.toJSONString(), dstClazz);
		if (result == null) {
			return null;
		}

		return (T1[]) result.toArray();
	}

	public static void main(String adsf[]) {

	}

	@SuppressWarnings("unchecked")
	public static List<Map<String,Object>> parserToMap(List<?> listObj) {
		String jsonStr = JSON.toJSONString(listObj);
		List<Map<String,Object>> maplist = (List<Map<String, Object>>) JSON.parse(jsonStr);
		return maplist;
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String,Object> parserToMap(Object obj) {
		String jsonStr = JSON.toJSONString(obj);
		Map<String,Object> map = (Map<String, Object>) JSON.parse(jsonStr);
		return map;
	}

}
