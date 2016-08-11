package com.ink.base;


import com.ink.base.page.PageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseQuery extends PageRequest implements java.io.Serializable {
	
	private static Logger log = LoggerFactory.getLogger(BaseQuery.class);
	
	private static final long serialVersionUID = -360860474471966681L;
	public static final int DEFAULT_PAGE_SIZE = 10;
	public static final String QUERY_LIKE = "1";
	public static final String QUERY_EQUAL = "0";
	//查询类型：精确查询、模糊查询
	private String queryType = QUERY_EQUAL;
	
    static {
        log.debug("BaseQuery.DEFAULT_PAGE_SIZE=" + DEFAULT_PAGE_SIZE);
    }
    
	public BaseQuery() {
		setPageSize(DEFAULT_PAGE_SIZE);
	}

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}
	  
}
