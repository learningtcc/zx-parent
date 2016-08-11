package com.ink.base.log.hessian.client;

import java.net.URL;

import com.caucho.hessian.client.HessianConnection;
import com.caucho.hessian.client.HessianProxy;
import com.caucho.hessian.client.HessianProxyFactory;
import com.ink.base.log.util.RequestLog;

/**
 * 实现将RequestLog以消息头的形式传递给服务端
 * @author zongtt
 * @since 2016年6月15日18:41:06
 */
public class YinkerHessianProxy extends HessianProxy {

	private static final long serialVersionUID = 2859862209319514496L;

	public YinkerHessianProxy(URL url, HessianProxyFactory factory, Class<?> type) {
		super(url, factory, type);
	}

	@Override
	protected void addRequestHeaders(HessianConnection conn) {
		super.addRequestHeaders(conn);
		
		//将requestlog转为json格式以消息头的形式传递
		RequestLog log = RequestLog.getInstance();
		conn.addHeader("requestLog", log.encodeBase64());
	}
}