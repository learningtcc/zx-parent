package com.ink.base.log.hessian.client;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.net.URL;

import com.caucho.hessian.client.HessianConnectionFactory;
import com.caucho.hessian.client.HessianProxyFactory;
import com.caucho.hessian.io.HessianRemoteObject;

/**
 * 实现将RequestLog以消息头的形式传递给服务端
 * 
 * @author zongtt
 * @since 2016年6月15日18:41:16
 */
public class YinkerHessianProxyFactory extends HessianProxyFactory {

//	private JoyLogger logger = JoyLogger.getLogger(getClass());

	private long readTimeOut = 0;

	private long connectTimeOut = 0;

	@Override
	public Object create(Class<?> api, URL url, ClassLoader loader) {
		if (api == null)
			throw new NullPointerException(
					"api must not be null for HessianProxyFactory.create()");

		InvocationHandler handler = null;
		
		if(readTimeOut > 0){
			this.setReadTimeout(readTimeOut);
		}
		
		if(connectTimeOut > 0){
			this.setConnectTimeout(connectTimeOut);
		}

		handler = new YinkerHessianProxy(url, this, api);

		return Proxy.newProxyInstance(loader, new Class[] { api,
				HessianRemoteObject.class }, handler);
	}

	@Override
	protected HessianConnectionFactory createHessianConnectionFactory() {
		String className = System.getProperty(HessianConnectionFactory.class
				.getName());

		HessianConnectionFactory factory = null;

		try {
			if (className != null) {
				ClassLoader loader = Thread.currentThread()
						.getContextClassLoader();

				Class<?> cl = Class.forName(className, false, loader);

				factory = (HessianConnectionFactory) cl.newInstance();
				return factory;
			}
		} catch (Exception e) {
//			logger.error(ModuleCode.PUBLIC_HESSIAN_CLIENT, e);
			throw new RuntimeException(e);
		}

		return new YinkerHessianConnectionFactory();
	}

	public long getReadTimeOut() {
		return readTimeOut;
	}

	public void setReadTimeOut(long readTimeOut) {
		this.readTimeOut = readTimeOut;
	}

	public long getConnectTimeOut() {
		return connectTimeOut;
	}

	public void setConnectTimeOut(long connectTimeOut) {
		this.connectTimeOut = connectTimeOut;
	}
}