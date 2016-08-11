/**
 * yinker.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description TODO
 * @author xuguoqi
 * @date 2016年6月24日 下午3:46:08
 */
package com.ink.channel.core.jdPay.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;

import com.ink.base.log.util.YinkerLogger;

import freemarker.cache.TemplateLoader;

/**
 * @Description TODO
 * @author xuguoqi
 * @date 2016年6月24日 下午3:46:08
 */
public class JDPayTemplateLoader implements TemplateLoader {
	YinkerLogger log  = YinkerLogger.getLogger(JDPayTemplateLoader.class);
	
	private final String  requestFileDate =  "http_des_req_data";
	private final String  requestFile =  "http_des_req";

	/**
	 * @Description TODO
	 * @author xuguoqi
	 * @date 2016年6月24日 下午3:46:08
	 * @param name
	 * @return
	 * @throws IOException
	 */
	@Override
	public Object findTemplateSource(String name) throws IOException {
		// TODO Auto-generated method stub
//		name.replace("zh_CN", "")
		System.out.println("JDPayTemplateLoader=========== find file name ="+name);
		log.info("JDPayTemplateLoader=========== find file name ="+name);
		String replace = "";
		
		if(name.contains(requestFileDate)&& name.indexOf("data")>0){
			replace= requestFileDate+".xml";
		}else if(name.contains(requestFile)&& name.indexOf("data")<0){
			replace= requestFile+".xml";
		}else{
			replace=name;
		}
		log.info("JDPayTemplateLoader=========== find repalce file name ="+replace);
		File file = new File(replace);
		InputStream inputStream = JDPayTemplateLoader.class.getClassLoader().getResourceAsStream("jdPay/"+replace);;
		if(inputStream==null){
			log.error("jd 模版文件未找到! name = "+name);
			throw new FileNotFoundException("jd 模版文件未找到! name = "+name);
		}
		OutputStream os =  new FileOutputStream(file);;
		try {
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = inputStream.read(buffer, 0, 8192)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
			os.close();
			inputStream.close();
		} catch (Exception e) {
			os.close();
			inputStream.close();
			e.printStackTrace();
		}finally {
			if(os!=null){
				os.close();
			}
			if(inputStream!=null){
				inputStream.close();
			}
		}
		return file;
	}

	/**
	 * @Description TODO
	 * @author xuguoqi
	 * @date 2016年6月24日 下午3:46:08
	 * @param templateSource
	 * @return
	 */
	@Override
	 public long getLastModified(final Object templateSource)
    {
        return ((Long)(AccessController.doPrivileged(new PrivilegedAction()
        {
            public Object run()
            {
                return new Long(((File)templateSource).lastModified());
            }
        }))).longValue();
        
        
    }

	/**
	 * @Description TODO
	 * @author xuguoqi
	 * @date 2016年6月24日 下午3:46:08
	 * @param templateSource
	 * @param encoding
	 * @return
	 * @throws IOException
	 */
	@Override
	public Reader getReader(final Object templateSource, final String encoding) throws IOException {
		try {
			return (Reader) AccessController.doPrivileged(new PrivilegedExceptionAction() {
				public Object run() throws IOException {
					if (!(templateSource instanceof File)) {
						throw new IllegalArgumentException(
								"templateSource is a: " + templateSource.getClass().getName());
					}
					return new InputStreamReader(new FileInputStream((File) templateSource), encoding);
				}
			});
		} catch (PrivilegedActionException e) {
			throw (IOException) e.getException();
		}
	}

	/**
	 * @Description TODO
	 * @author xuguoqi
	 * @date 2016年6月24日 下午3:46:08
	 * @param templateSource
	 * @throws IOException
	 */
	@Override
	public void closeTemplateSource(Object templateSource) throws IOException {
		// TODO Auto-generated method stub

	}

}
