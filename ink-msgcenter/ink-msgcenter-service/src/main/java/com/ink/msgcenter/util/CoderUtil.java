package com.ink.msgcenter.util;

import com.ink.base.utils.IdCodeGenerator;
import com.ink.base.utils.context.SpringApplicationContext;

public class CoderUtil {
	
	public static String genMsgId(){
		IdCodeGenerator idCodeGenerator = (IdCodeGenerator)SpringApplicationContext.getBean("idCodeGenerator");

		return idCodeGenerator.getId();
	}
}
