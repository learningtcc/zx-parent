package com.ink.platform.api.util;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author zhangyuanyang
 * @Description: 查询分页信息
 * @param <T>
 */
public class PageVO<T> {

	// 默认每页显示10条数据
	private static final int DEFAULT_PAGE_SIZE = 20;

	/**
	 * 页内容
	 */
	private List<T> list = new ArrayList<T>();

	/**
	 * 当前页第一条数据在List中的位置,从0开始
	 */
	private int start = 0;
	/**
	 * 当前页
	 */
	private int pageNumber = 1;

	/**
	 * 每页的记录数
	 */
	private int pageSize = DEFAULT_PAGE_SIZE;

	/**
	 * 总计有多少页
	 */
	private int totalPage = 0;
	/**
	 * 总记条数
	 */
	protected int total;
	/**
	 * 排序字符串
	 */
	private String orderBy;
	
	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		if (pageNumber > 0) {
			start = (pageNumber - 1) * pageSize;
			this.pageNumber = pageNumber;
		}
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
		totalPage = (int) Math.ceil((total + pageSize - 1) / pageSize);
		start = (pageNumber - 1) * pageSize;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
}
