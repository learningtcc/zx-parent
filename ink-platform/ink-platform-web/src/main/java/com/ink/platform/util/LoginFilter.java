package com.ink.platform.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.ink.platform.controller.BaseController;

/**
 * 登录拦截器
 * @author lww
 *
 */
public class LoginFilter extends BaseController  implements Filter {

	/** 
	 * 需要排除的页面 
	 */  
	private String excludedPages;  

	private String[] excludedPageArray; 

	@Override
	public void destroy() {
		return; 

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain)
					throws IOException, ServletException {
		// 获得在下面代码中要用的request,response,session对象
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		String url =servletRequest.getServletPath();
		boolean isExcludedPage = false;  
		for (String page : excludedPageArray) {//判断是否在过滤url之外  
			if(url.equals(page)){  
				isExcludedPage = true;  
				break;  
			}  
			//忽略静态资源
			if(url.startsWith(page)){  
				isExcludedPage = true;  
				break;  
			}  
			
		} 
		if (isExcludedPage) {//在过滤url之外  
			chain.doFilter(request, response);  
		} else {//不在过滤url之外，判断session是否存在  
			String userr =getSSOUserId(servletRequest);
			if(StringUtils.isBlank(userr)){
				servletResponse.sendRedirect("logOut");
				
			}
			else {  
				chain.doFilter(request, response);  
			}  
		}
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		//初始化，获取不需要过滤的文件
		excludedPages = fConfig.getInitParameter("excludedPages");  
		if (StringUtils.isNotEmpty(excludedPages)) {  
			excludedPageArray = excludedPages.split(",");  
		}  
		return;  
	}  

}
