package com.ink.base.log.filter;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.MDC;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ink.base.log.support.ServletControllerContext;
import com.ink.base.log.util.IpUtils;
import com.ink.base.log.util.LogConst;
import com.ink.base.log.util.YinkerLogger;

public class LoggerMDCFilter extends OncePerRequestFilter implements Filter{
	
	private YinkerLogger logger = YinkerLogger.getLogger(getClass());

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
//			long start = System.currentTimeMillis();
            //示例为一个固定的登陆用户,请直接修改代码
        	String userName = (String)request.getSession().getAttribute("SESSION_USERNAME");
        	String userId = (String)request.getSession().getAttribute("SESSION_USERID");
        	
        	MDC.put(LogConst.USER_NAME, userName == null ? "guest":userName);
        	MDC.put(LogConst.USER_ID, userId == null ? "guest":userId);
            MDC.put(LogConst.REQ_URI, StringUtils.defaultString(request.getRequestURI()));
            MDC.put(LogConst.REQ_QUERYSTRING, StringUtils.defaultString(request.getQueryString()));
            MDC.put(LogConst.REQ_URIWITHQUERYSTRING, request.getRequestURI() + (request.getQueryString() == null ? "" : "?"+request.getQueryString()));
            MDC.put(LogConst.REQ_REMOTEADDR, IpUtils.getRemortIP(request));
//    		String context = request.getContextPath();
    		//获取logback中配置的contextname
    		MDC.put(LogConst.REQ_CONTEXT, logger.getLogContext());
    		MDC.put(LogConst.CONTEXT, logger.getLogContext());
            //为每一个请求创建一个ID，方便查找日志时可以根据ID查找出一个http请求所有相关日志
            MDC.put(LogConst.REQ_ID, StringUtils.remove(UUID.randomUUID().toString(),"-")); 
            MDC.put(LogConst.REQ_SERVERIP, IpUtils.getLocalAddress());
            MDC.put(LogConst.LOG_SEQ, "1");
            MDC.put(LogConst.SESSION_ID, request.getSession(true).getId()) ;
            
            ServletControllerContext.setHttpContext(request, response);
            setHttpOnlyJsessionId(request, response);
            
            filterChain.doFilter(request, response);
            
//            long end = System.currentTimeMillis();
            
//            logger.time(ModuleCode.PUBLIC, end-start);
        }finally {
            MDC.clear();
            ServletControllerContext.removeHttpContext();
        }
	}
	
	private void setHttpOnlyJsessionId(HttpServletRequest request,
			HttpServletResponse response) {
		if (response.containsHeader("SET-COOKIE")) {
			String sessionid = request.getSession().getId();
			String contextPath = request.getContextPath();
			String secure = "";
			if (request.isSecure()) {
				secure = "; Secure";
			}
			response.setHeader("SET-COOKIE", "JSESSIONID=" + sessionid
					+ "; Path=" + contextPath + "; HttpOnly" + secure);
		}
	}
}
