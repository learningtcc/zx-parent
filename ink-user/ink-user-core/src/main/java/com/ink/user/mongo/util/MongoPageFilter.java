package com.ink.user.mongo.util;

import java.io.Serializable;

import org.mongodb.morphia.query.Query;

public class MongoPageFilter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7300600195393760704L;
	protected int pageNumber; // 页码
	protected int pageSize = 10; // 每页size

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getOffset() {
		if (pageSize < 0 || pageNumber < 0) {
			return 0;
		}
		int offset = (pageNumber - 1) * pageSize;
		return offset > 0 ? offset : 0;
	}

	// 处理分页，后面有其他查询的话可以将第二个参数再处理一下，目前没有就先这样吧
	public void pagingHandle(Query<?> query) {
		if (query == null) {
			return;
		}
		if (getPageNumber() == -1 && getPageSize() == -1) {
			// 查询全部
		} else {
			query.offset(getOffset()).limit(getPageSize());
		}
	}
}
