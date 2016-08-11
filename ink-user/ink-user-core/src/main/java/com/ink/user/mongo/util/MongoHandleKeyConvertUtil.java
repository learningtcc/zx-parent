package com.ink.user.mongo.util;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.LinkedList;

import org.apache.commons.lang.StringUtils;
import org.mongodb.morphia.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 注解处理工具
 * @author yangchen
 * @date 2016年1月30日 下午5:32:27
 */
public class MongoHandleKeyConvertUtil {
	
	private static Logger logger = LoggerFactory.getLogger(MongoHandleKeyConvertUtil.class);
	
	public static Query<?> queryKeyConvert(Object queryFilter, Query<?> query) {
		
		if(query == null || queryFilter == null){
			return query;
		}
		// 其实下面的代码写的有点挫，赶时间，而且exceptionLog查询情况也不会太多，就这样吧，
		// 优化的话可以加缓存
		Class<?> queryFilterClass = queryFilter.getClass();
		Field[] fields = queryFilterClass.getDeclaredFields();
		for (Field field : fields) {
			try {
				if (field.isAnnotationPresent(MongoQueryKey.class)) {
					
					MongoQueryKey mk = field.getAnnotation(MongoQueryKey.class);
					String key = mk.value();
					if(key == null){
						logger.info("key is null");
						continue;
					}
					String action = mk.acction();
					
					String fieldname = field.getName();
					Method method = queryFilterClass.getMethod("get" + StringUtils.capitalize(fieldname));
					
					Object result = method.invoke(queryFilter);
					logger.info("fieldname:{}, action:{}, key:{}, result:{}",fieldname,action,key,result);
					
					if (result != null && !"".equals(result)) {
						if(action != null && action.length() > 0){
							if("equal".equals(action)){
								query = query.field(key).equal(result);
							}else if("greaterThanOrEq".equals(action)){
								query = query.field(key).greaterThanOrEq(result);
							}else if("lessThanOrEq".equals(action)){
								query = query.field(key).lessThanOrEq(result);
							}else if("exists".equals(action) && "1".equals(result)){
								query = query.field(key).exists();
							}else if("in".equals(action)){
								Iterable<?> values = getValues(result);
								query = query.field(key).in(values);
							}else if("order".equals(action)){
								String orders = result.toString();
								if(orders == null || orders.length() < 1){
									continue;
								}
								String[] ods = orders.split(",");
								if(ods == null || ods.length < 1){
									continue;
								}
								for(String od : ods){
									if(od == null || od.length() < 1){
										continue;
									}
									query = query.order(od);
								}
							}
						}
					}
				}else{
					logger.info("field is not MongoQueryKey");
				}
			} catch (Exception e) {
				logger.error("", e);
				logger.error("field:{}", field);
			}
		}
		
		logger.info("query:{}", query);
		return query;
	}
	
	private static Iterable<?> getValues(Object result) {
		LinkedList<String> list = new LinkedList<String>();
		list.add(result.toString());
		return list;
	}
	
}