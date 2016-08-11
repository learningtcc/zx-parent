package com.ink.base.log.hessian.protocol;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.MDC;

import com.alibaba.com.caucho.hessian.HessianException;
import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.remoting.http.HttpBinder;
import com.alibaba.dubbo.remoting.http.HttpHandler;
import com.alibaba.dubbo.remoting.http.HttpServer;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.protocol.AbstractProxyProtocol;
import com.alibaba.fastjson.util.Base64;
import com.caucho.hessian.client.HessianConnectionException;
import com.caucho.hessian.io.HessianMethodSerializationException;
import com.ink.base.log.hessian.client.YinkerHessianConnectionFactory;
import com.ink.base.log.hessian.client.YinkerHessianProxyFactory;
import com.ink.base.log.hessian.server.YinkerHessianSkeleton;
import com.ink.base.log.util.LogConst;

public class YinkerHessianProtocol extends AbstractProxyProtocol {

	 private final Map<String, HttpServer> serverMap = new ConcurrentHashMap<String, HttpServer>();

	    private final Map<String, YinkerHessianSkeleton> skeletonMap = new ConcurrentHashMap<String, YinkerHessianSkeleton>();

	    private HttpBinder httpBinder;
	    
	    public YinkerHessianProtocol() {
	        super(HessianException.class);
	    }

	    public void setHttpBinder(HttpBinder httpBinder) {
	        this.httpBinder = httpBinder;
	    }

	    public int getDefaultPort() {
	        return 80;
	    }

	    private class HessianHandler implements HttpHandler {
	        
	        public void handle(HttpServletRequest request, HttpServletResponse response)
	                throws IOException, ServletException {
	            String uri = request.getRequestURI();
	            YinkerHessianSkeleton skeleton = skeletonMap.get(uri);
	            if (! request.getMethod().equalsIgnoreCase("POST")) {
	                response.setStatus(500);
	            } else {
	                RpcContext.getContext().setRemoteAddress(request.getRemoteAddr(), request.getRemotePort());
	                try {
	                	setMdcConfig(request);
//	                	ServiceContext.begin(request, response);
	                    skeleton.invoke(request.getInputStream(), response.getOutputStream());
	                } catch (Throwable e) {
	                    throw new ServletException(e);
	                } finally {
	                	MDC.clear();
//	                	ServiceContext.end(); 
	                }
	            }
	        }
	        
	    }

	    protected <T> Runnable doExport(T impl, Class<T> type, URL url) throws RpcException {
	        String addr = url.getIp() + ":" + url.getPort();
	        HttpServer server = serverMap.get(addr);
	        if (server == null) {
	            server = httpBinder.bind(url, new HessianHandler());
	            serverMap.put(addr, server);
	        }
	        final String path = url.getAbsolutePath();
	        YinkerHessianSkeleton skeleton = new YinkerHessianSkeleton(impl, type);
	        skeletonMap.put(path, skeleton);
	        return new Runnable() {
	            public void run() {
	                skeletonMap.remove(path);
	            }
	        };
	    }

	    @SuppressWarnings("unchecked")
	    protected <T> T doRefer(Class<T> serviceType, URL url) throws RpcException {
	        YinkerHessianProxyFactory hessianProxyFactory = new YinkerHessianProxyFactory();
	        hessianProxyFactory.setOverloadEnabled(true); 
	        String client = url.getParameter(Constants.CLIENT_KEY, Constants.DEFAULT_HTTP_CLIENT);
	        if ("httpclient".equals(client)) {
	            hessianProxyFactory.setConnectionFactory(new YinkerHessianConnectionFactory());
	        } else if (client != null && client.length() > 0 && ! Constants.DEFAULT_HTTP_CLIENT.equals(client)) {
	            throw new IllegalStateException("Unsupported http protocol client=\"" + client + "\"!");
	        }
	        int timeout = url.getParameter(Constants.TIMEOUT_KEY, Constants.DEFAULT_TIMEOUT);
	        hessianProxyFactory.setConnectTimeout(timeout);
	        hessianProxyFactory.setReadTimeout(timeout);
	        return (T) hessianProxyFactory.create(serviceType, url.setProtocol("http").toJavaURL(), Thread.currentThread().getContextClassLoader());
	    }

	    protected int getErrorCode(Throwable e) {
	        if (e instanceof HessianConnectionException) {
	            if (e.getCause() != null) {
	                Class<?> cls = e.getCause().getClass();
	                if (SocketTimeoutException.class.equals(cls)) {
	                    return RpcException.TIMEOUT_EXCEPTION;
	                }
	            }
	            return RpcException.NETWORK_EXCEPTION;
	        } else if (e instanceof HessianMethodSerializationException) {
	            return RpcException.SERIALIZATION_EXCEPTION;
	        }
	        return super.getErrorCode(e);
	    }

	    public void destroy() {
	        super.destroy();
	        for (String key : new ArrayList<String>(serverMap.keySet())) {
	            HttpServer server = serverMap.remove(key);
	            if (server != null) {
	                try {
	                    if (logger.isInfoEnabled()) {
	                        logger.info("Close hessian server " + server.getUrl());
	                    }
	                    server.close();
	                } catch (Throwable t) {
	                    logger.warn(t.getMessage(), t);
	                }
	            }
	        }
	    }


	    /**
		 * 解析消息头，设置MDC
		 * @param request
		 */
		private void setMdcConfig(HttpServletRequest request) {
			//将requestLog以消息头的方式传递进来
		    String logstr = request.getHeader("requestLog");
		    if(StringUtils.isNotBlank(logstr)){
		    	String log = new String(Base64.decodeFast(logstr));
		    	
		    	String[] logArray = log.split("\\|");
		    	
		    	if(logArray.length >= 9){
		    		MDC.put(LogConst.USER_ID, logArray[0]);
		    		MDC.put(LogConst.USER_NAME, logArray[1]);
		    		MDC.put(LogConst.REQ_ID, logArray[2]);
		    		MDC.put(LogConst.REQ_REMOTEADDR, logArray[3]);
					MDC.put(LogConst.REQ_SERVERIP, logArray[4]);
					MDC.put(LogConst.REQ_CONTEXT, logArray[5]);
					MDC.put(LogConst.REQ_URI, logArray[6]);
					MDC.put(LogConst.CONTEXT, logArray[7]);
					MDC.put(LogConst.LOG_SEQ, logArray[8]);
		    	}
		    	if(logArray.length == 10) {
		    		MDC.put(LogConst.SESSION_ID, logArray[9]) ;
		    	}
		    }else {
//		    	throw new AuthenticationException(Utils.getRemortIP(request)+"非法访问"+com.cmdi.b2bjoy.common.helper.ServiceContext.getContextName()+"("+com.cmdi.b2bjoy.common.helper.ServiceContext.getLocalIp()+")");
			}
		}
}
