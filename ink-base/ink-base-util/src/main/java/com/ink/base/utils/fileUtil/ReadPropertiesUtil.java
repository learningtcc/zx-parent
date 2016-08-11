package com.ink.base.utils.fileUtil;

import com.ink.base.utils.context.SpringApplicationContext;
import org.springframework.core.io.Resource;

import java.io.InputStream;

/**
 * 读取配置文件工具类
 * 使用Spring读取配置文件的方式
 * Created by aiyungui on 2016/5/11.
 */
public class ReadPropertiesUtil {

   public static String getPropertiesContent(String defauleFilePath,String filePath)throws Exception{

       StringBuilder resultBuilder = new StringBuilder();
       Resource resource = SpringApplicationContext.getApplicationContext().getResource(defauleFilePath);
       if (resource == null || !resource.exists()){
           resource = SpringApplicationContext.getApplicationContext().getResource(filePath);
       }
       InputStream inputStream = resource.getInputStream();
       byte[] bytes = new byte[1024];
       while ((inputStream.read(bytes)) != -1){
           resultBuilder.append(new String(bytes,"UTF-8"));
           bytes = new byte[1024];
       }
       return resultBuilder.toString();
    }
}
