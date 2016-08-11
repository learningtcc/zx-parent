package com.ink.trade.service.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ink.base.log.util.YinkerLogger;
import com.ink.trade.service.dto.NotifyMerchantDto;
import com.ink.util.HttpClientUtils;

/**
 * 异步通知商户工具类
 * Created by huohb on 2016/7/1.
 */
public class NotifyMerchantUtil {

    private static final YinkerLogger logger = YinkerLogger.getLogger(NotifyMerchantUtil.class);

    private static final String NOTIFY_SUCCESS_RESP = "SUCCESS";

    /**
     * 回调商户
     * @param url
     * @param dto
     * @return
     */
    public static boolean notify(String url , NotifyMerchantDto dto){
        // 校验URL，dto中字段不校验
        if(StringUtils.isBlank(url)){
            throw new RuntimeException("url can not be null");
        }
        Map<String,Object> params = reflectObject2Map(dto);
        Map<String,String> result = null;
        try {
            result = HttpClientUtils.execute(url,params,60000);
            return NOTIFY_SUCCESS_RESP.equalsIgnoreCase(result.get("result"));
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 反射将Object的属性名及值转换为Map
     * @param dto
     * @return
     */
    private static Map<String,Object> reflectObject2Map(NotifyMerchantDto dto){
        Map<String,Object> map = new HashMap<String,Object>();

        Class clazz =dto.getClass();
        Field[] fields = clazz.getDeclaredFields();
        String key = null;
        Object value = null;
        for(Field field : fields){
            key = field.getName();
            // 序列化字段忽略
            if("serialVersionUID".equalsIgnoreCase(key)){
                continue;
            }
            value = getValueByFieldName(dto,key);
            map.put(key,value);
        }
        return map;
    }

    /**
     * 字符串首字母大写
     * @param str
     * @return
     */
    private static String upperFirstLetter(String str){
       return str.substring(0,1).toUpperCase().concat(str.substring(1));
    }

    /**
     * 根据字段名获取值
     * @param obj
     * @param fieldName
     * @return
     */
    private static Object getValueByFieldName(Object obj,String fieldName){
        String getMethodName = "get".concat(upperFirstLetter(fieldName));
        Object value = null;
        try {
            Method method = obj.getClass().getMethod(getMethodName,null);
            return method.invoke(obj,null);
        } catch (Exception e) {
            logger.error("reflect failed",e);
            return null;
        }
    }
}
