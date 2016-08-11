package com.ink.base;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ink.base.log.support.ServletControllerContext;
import com.ink.base.page.PageRequest;
import com.ink.base.page.PageRequestFactory;

/**
 * 
 * @author zongtt
 * 2016年4月14日14:16:19
 */
public class BaseController {
	
	public <T extends PageRequest<T>> T newQuery(Class<T> queryClazz,String defaultSortColumns){
		PageRequest query = PageRequestFactory.bindPageRequest(org.springframework.beans.BeanUtils.instantiateClass(queryClazz),getRequest(),defaultSortColumns);
		return (T)query;
    }
	
	public HttpServletRequest getRequest(){
		return ServletControllerContext.getRequest();
	}
	
	public String getContext(){
		return getRequest().getContextPath();
	}

	public HttpServletResponse getResponse() {
		return ServletControllerContext.getResponse();
	}
	
	public HttpSession getLoginSession(){
		HttpSession session = this.getRequest().getSession();
		return session;
	}
	
	protected String writeAjaxResponse(String ajaxString){
		try {
			getResponse().setContentType("text/html;charset=utf-8");
			//getResponse().setCharacterEncoding("UTF-8");
//			getResponse().setContentType("text/json");
			PrintWriter out = getResponse().getWriter();
	        out.write(ajaxString);
	        out.close();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}finally{
			return null;
		}
	}
}