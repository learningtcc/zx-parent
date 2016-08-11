package com.ink.base.redis.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class CacheTypeUtil {

    private static Logger logger = LoggerFactory.getLogger(CacheTypeUtil.class);

	public static byte[] objectToBytes(Object value) throws IOException{
		if(value == null)
			return null;
		ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
		ObjectOutputStream outputStream = null;
		try{
			outputStream = new ObjectOutputStream(arrayOutputStream);
			outputStream.writeObject(value);
		}catch(IOException e){
			throw new IOException("[ERROR] CacheTypeUtil.objectToBytes ", e);
		}finally{
			if(outputStream != null){
				try {
					outputStream.close();
				} catch (IOException e) {
					throw new IOException("[ERROR] CacheTypeUtil.objectToBytes ", e);
				}
			}
			if(arrayOutputStream != null){
				try {
					arrayOutputStream.close();
				} catch (IOException e) {
					throw new IOException("[ERROR] CacheTypeUtil.objectToBytes ", e);
				}
			}
		}
		return arrayOutputStream.toByteArray();
	}
	
	public static Object bytesToObject(byte[] bytes) throws IOException{
		if(bytes == null || bytes.length == 0){
			return null;
		}
		ObjectInputStream inputStream = null;
		try{
			inputStream = new ObjectInputStream(new ByteArrayInputStream(bytes));
			Object obj = inputStream.readObject();
			return obj;
		}catch(Exception e){
			throw new IOException("[ERROR] CacheTypeUtil.bytesToObject ", e);
		}finally{
			if(inputStream != null){
				try {
					inputStream.close();
				} catch (IOException e) {
					throw new IOException("[ERROR] CacheTypeUtil.bytesToObject ", e);
				}
			}
		}
	}

    public static HashMap<String, String> convertObjectMapToStringMap(
            Map<String, Object> map) {
        HashMap<String, String> resultMap = new HashMap<String, String>();
        Set<String> keys = map.keySet();
        if (keys != null) {
            Iterator<String> iterator = keys.iterator();
            while (iterator.hasNext()) {
                Object key = iterator.next();
                Object value = map.get(key);
                resultMap.put(key.toString(), value==null?"":value.toString());
            }
        }
        return resultMap;
    }
    public static HashMap<String, String> convertObjToHashMap(Object obj) {
        HashMap<String, String> map = new HashMap<String, String>();
        // System.out.println(obj.getClass());
        // 获取f对象对应类中的所有属性域
        Field[] fields = obj.getClass().getDeclaredFields();
        for (int i = 0, len = fields.length; i < len; i++) {
            String varName = fields[i].getName();
            try {
                // 获取原来的访问控制权限
                boolean accessFlag = fields[i].isAccessible();
                // 修改访问控制权限
                fields[i].setAccessible(true);
                // 获取在对象f中属性fields[i]对应的对象中的变量
                Object o = fields[i].get(obj);
                if (o != null){
                    if(fields[i].getType() == java.util.Date.class || fields[i].getType() == java.sql.Date.class){
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//						Date date = new Date(o.toString());
                        // 已经判断过是date类型的变量，可以强转
                        Date date = (Date) o;
                        o = format.format(date);
                    }
                    map.put(varName, o.toString());
                }
                // 恢复访问控制权限
                fields[i].setAccessible(accessFlag);
            } catch (IllegalArgumentException e) {
                logger.error("将Object对象转换为HashMap对象时异常", e);
            } catch (IllegalAccessException e) {
                logger.error("将Object对象转换为HashMap对象时异常", e);
            }
        }
        return map;
    }
    public static HashMap<String, Object> convertStringMapToObjectMap(
            Map<String, String> map) {
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        Set<String> keys = map.keySet();
        if (keys != null) {
            Iterator<String> iterator = keys.iterator();
            while (iterator.hasNext()) {
                Object key = iterator.next();
                Object value = map.get(key);
                resultMap.put(key.toString(), value);
            }
        }
        return resultMap;
    }

    public static <T> T convertMapToObject(Map<String, String> map, Class<T> obj) {
        Field[] fields = obj.getDeclaredFields();
        T t = null;
        try {
            if (fields.length > 0) {
                t = obj.newInstance();
            }
            boolean flag;
            for (Field field : fields) {
                if (map.containsKey(field.getName())
                        && map.get(field.getName()) != null) {
                    flag = false;
                    if (!field.isAccessible()) {
                        field.setAccessible(true);
                        flag = true;
                    }
                    int m = field.getModifiers();
                    if (Modifier.isFinal(m)&&Modifier.isStatic(m)) {
                        // 什么都不做
                        continue;
                    }else if ((field.getType() == java.util.Date.class || field.getType() == java.sql.Date.class)
                            && map.get(field.getName()).getClass() != field.getType()) {// Date
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        field.set(t, format.parse(map.get(field.getName())));
                    } else if (field.getType() == java.sql.Timestamp.class
                            && map.get(field.getName()).getClass() != field
                            .getType()) {// Timestamp
                        field.set(t, Timestamp.valueOf(map.get(field.getName())));
                    } else if (field.getType() == java.lang.Long.class
                            && map.get(field.getName()).getClass() != field.getType()) {// Long
                        field.set(t,Long.parseLong(map.get(field.getName())));
                    } else if ((field.getType() == int.class || field.getType() == java.lang.Integer.class)
                            && map.get(field.getName()).getClass() != field.getType()) {// Integer
                        field.set(t, Integer.parseInt(map.get(field.getName())));
                    } else if (field.getType() == BigDecimal.class
                            && map.get(field.getName()).getClass() != field.getType()) {//BigDecimal
                        field.set(t,new BigDecimal(map.get(field.getName())));
                    } else if ((field.getType() == Boolean.class || field.getType() == boolean.class)
                            && map.get(field.getName()).getClass() != field.getType()) {//Boolean
                        field.set(t, Boolean.parseBoolean(map.get(field.getName())));
                    } else if ((field.getType() == Short.class || field.getType() == short.class)
                            && map.get(field.getName()).getClass() != field.getType()) {//Short
                        field.set(t, Short.parseShort(map.get(field.getName())));
                    }else if ((field.getType() == Byte.class || field.getType() == byte.class)
                            && map.get(field.getName()).getClass() != field.getType()) {//Byte
                        field.set(t, Byte.parseByte(map.get(field.getName())));
                    }else if ((field.getType() == Long.class || field.getType() == long.class)
                            && map.get(field.getName()).getClass() != field.getType()) {//Long
                        field.set(t, Long.parseLong(map.get(field.getName())));
                    }else if ((field.getType() == Float.class || field.getType() == float.class)
                            && map.get(field.getName()).getClass() != field.getType()) {//Float
                        field.set(t, Float.parseFloat(map.get(field.getName())));
                    }else if ((field.getType() == Double.class || field.getType() == double.class)
                            && map.get(field.getName()).getClass() != field.getType()) {//Double
                        field.set(t, Double.parseDouble(map.get(field.getName())));
                    }else if ((field.getType() == Character.class || field.getType() == char.class)
                            && map.get(field.getName()).getClass() != field.getType()) {//Character
                        //是否有问题待测试
                        field.set(t, map.get(field.getName()).charAt(0));
                    } else {
                        field.set(t, map.get(field.getName()));
                    }
                    if (flag) {
                        field.setAccessible(false);
                    }
                }
            }
        } catch (NumberFormatException e) {
            logger.error("将Map对象转换为Object对象时异常", e);
        } catch (SecurityException e) {
            logger.error("将Map对象转换为Object对象时异常", e);
        } catch (IllegalArgumentException e) {
            logger.error("将Map对象转换为Object对象时异常", e);
        } catch (InstantiationException e) {
            logger.error("将Map对象转换为Object对象时异常", e);
        } catch (IllegalAccessException e) {
            logger.error("将Map对象转换为Object对象时异常", e);
        } catch (ParseException e) {
            logger.error("将Map对象转换为Object对象时异常", e);
        }
        return t;
    }
}
