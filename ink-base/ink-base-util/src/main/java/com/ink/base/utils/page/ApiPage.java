package com.ink.base.utils.page;



import java.io.Serializable;
import java.util.List;

/**
 * 分页信息
 * 第一页从1开始
 * @author king
 */
public class ApiPage<T> implements Serializable
{

    protected List<T> result;

    protected int pageSize;

    protected int pageNumber;

    protected int totalCount = 0;
    
    private String queryType;

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

   

}

