package com.ink.channel.core.epro;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;


public class CheckUtils {

	public static final String COMMON_FIELD = "flowID,initiator,";


	/**
	 * ��֤�����Ƿ�ΪNULL,���ַ������飬�յ�Collection��Map(ֻ�пո���ַ�Ҳ��Ϊ�ǿմ�)
	 * @param obj ����֤�Ķ���
	 * @param message �쳣��Ϣ
	 */
	@SuppressWarnings("rawtypes")
	public static void notEmpty(Object obj, String message) {
		if (obj == null){
			throw new IllegalArgumentException(message + " must be specified");
		}
		if (obj instanceof String && obj.toString().trim().length()==0){
			throw new IllegalArgumentException(message + " must be specified");
		}
		if (obj.getClass().isArray() && Array.getLength(obj)==0){
			throw new IllegalArgumentException(message + " must be specified");
		}
		if (obj instanceof Collection && ((Collection)obj).isEmpty()){
			throw new IllegalArgumentException(message + " must be specified");
		}
		if (obj instanceof Map && ((Map)obj).isEmpty()){
			throw new IllegalArgumentException(message + " must be specified");
		}
	}
	

}
