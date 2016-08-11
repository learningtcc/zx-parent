package com.ink.base.log.hessian.client;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.caucho.hessian.client.HessianConnection;
import com.caucho.hessian.client.HessianConnectionFactory;
import com.caucho.hessian.client.HessianProxyFactory;

public class YinkerHessianConnectionFactory implements HessianConnectionFactory {

	private static final Logger log = LoggerFactory.getLogger(YinkerHessianConnectionFactory.class);;

	private HessianProxyFactory _proxyFactory;

	public void setHessianProxyFactory(HessianProxyFactory factory) {
		_proxyFactory = factory;
	}

	/**
	 * Opens a new or recycled connection to the HTTP server.
	 */
	public HessianConnection open(URL url) throws IOException {

		URLConnection conn = url.openConnection();

		long connectTimeout = _proxyFactory.getConnectTimeout();

		if (connectTimeout >= 0)
			conn.setConnectTimeout((int) connectTimeout);

		conn.setDoOutput(true);

		long readTimeout = _proxyFactory.getReadTimeout();

		if (readTimeout > 0) {
			try {
				conn.setReadTimeout((int) readTimeout);
			} catch (Throwable e) {
				log.error("Hessian链接超时",e);
			}
		}

		return new YinkerHessianURLConnection(url, conn);
	}

}
