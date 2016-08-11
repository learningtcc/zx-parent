package com.ink.base.page;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;
import org.springframework.web.util.WebUtils;

import com.ink.base.BaseQuery;
/**
 * 用于分页组件覆盖的类,新的分页组件覆盖此类的newPageRequest()方法以适合不同的分页创建
 * @author zongtt
 * 2016年4月14日14:33:20
 */
public class PageRequestFactory {
    public static final int MAX_PAGE_SIZE = 1000;
    
    static BeanUtilsBean beanUtils = new BeanUtilsBean();
    static {
    	//用于注册日期类型的转换
    	String[] datePatterns = new String[] {"yyyy-MM-dd","yyyy-MM-dd HH:mm:ss","yyyy-MM-dd HH:mm:ss.SSS","HH:mm:ss"};
    	ConvertRegisterHelper.registerConverters(beanUtils.getConvertUtils(),datePatterns);
    	
//        System.out.println("PageRequestFactory.MAX_PAGE_SIZE="+MAX_PAGE_SIZE);
    }
    
    public static PageRequest bindPageRequest(PageRequest pageRequest,HttpServletRequest request,String defaultSortColumns){
        return bindPageRequest(pageRequest, request, defaultSortColumns, BaseQuery.DEFAULT_PAGE_SIZE);
    }
    
    public static PageRequest bindPageRequest(PageRequest pageRequest, HttpServletRequest request,String defaultSortColumns, int defaultPageSize) {
	    	Map params = WebUtils.getParametersStartingWith(request, "");
	    	
	    	//避免sql注入
	    	if(params.containsKey("sortColumns")){
	    		params.remove("sortColumns");
	    	}
	    	
	    	try {
	    		beanUtils.copyProperties(pageRequest, params);
		    } catch (IllegalAccessException e) {
		    	throw new IllegalArgumentException("beanUtils.copyProperties() error",e);
			} catch (InvocationTargetException e) {
				throw new IllegalArgumentException("beanUtils.copyProperties() error",e.getTargetException());
			}
	        
	        pageRequest.setPageNumber(getIntParameter(request, "pageNumber", 1));
	        pageRequest.setPageSize(getIntParameter(request, "pageSize", defaultPageSize));
	        pageRequest.setSortColumns(getStringParameter(request, "sortColumns",defaultSortColumns));

	        //将请求的参数保存到REQUEST中
	        WebUtils.exposeRequestAttributes(request, params);
	        if(pageRequest.getPageSize() > MAX_PAGE_SIZE) {
	            pageRequest.setPageSize(MAX_PAGE_SIZE);
	        }
	        return pageRequest;
    }
    
    static String getStringParameter(HttpServletRequest request,String paramName, String defaultValue) {
        String value = request.getParameter(paramName);
        return StringUtils.isEmpty(value) ? defaultValue : value;
    }

    static int getIntParameter(HttpServletRequest request,String paramName,int defaultValue) {
    	String value = request.getParameter(paramName);
        
        if(StringUtils.isBlank(value)){
        	return defaultValue;
        }
        
        int result = defaultValue;
        
        try {
			result = Integer.parseInt(value);
		} catch (Exception e) {
			result = defaultValue;
		}
        return result;
    }
    

    public Map<String, Object> getParametersStartingWith(ServletRequest request, String prefix) {
		Assert.notNull(request, "Request must not be null");
		Enumeration paramNames = request.getParameterNames();
		Map<String, Object> params = new TreeMap<String, Object>();
		if (prefix == null) {
			prefix = "";
		}
		while (paramNames != null && paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			if ("".equals(prefix) || paramName.startsWith(prefix)) {
				String unprefixed = paramName.substring(prefix.length());
				String[] values = request.getParameterValues(paramName);
				if (values == null || values.length == 0) {
					// Do nothing, no values found at all.
				}else if (values.length > 1) {
					params.put(unprefixed, values);
				}else {
					params.put(unprefixed, values[0]);
				}
			}
		}
		return params;
	}
}
