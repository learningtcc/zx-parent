package com.ink.platform.util;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

/**
 * 扩展fastjson，做一些特殊操作
 * @author yangchen
 * @date 2016年3月19日 下午5:36:33
 */
public class JsonUtil {
	/**
	 * 对序列化的Long类型进行特殊处理,避免位数过大导致和js精度的丢失,只用于向页面发送json数据时使用
	 */
	static ObjectSerializer longSerializer = new ObjectSerializer() {

		@Override
		public void write(JSONSerializer serializer, Object object,
				Object fieldName, Type fieldType, int features)
				throws IOException {
			SerializeWriter out = serializer.getWriter();
			if (object == null) {
				if (out.isEnabled(SerializerFeature.WriteNullNumberAsZero)) {
					out.write('0');
				} else {
					out.writeNull();
				}
				return;
			}
			out.writeString(object.toString());
		}

	};

	/**
	 * 对Long型兼容js的json串
	 * <p>
	 *
	 * @param object
	 *            对象
	 * @return json字符串
	 */
	public static final String toCompatibleJSONString(Object object, String format) {
		SerializeWriter out = new SerializeWriter();
		try {
			// 此处必须new一个SerializeConfig,防止修改默认的配置
			JSONSerializer serializer = new JSONSerializer(out,
					new SerializeConfig());
			serializer.getMapping().put(Long.class, longSerializer);
			if (format != null) {
				serializer.getMapping().put(Date.class,
						new SimpleDateFormatSerializer(format));
			}
			serializer.write(object);
			return out.toString();
		} finally {
			out.close();
		}
	}

	public static final String toCompatibleJSONString(Object object) {
		return toCompatibleJSONString(object, null);
	}

}