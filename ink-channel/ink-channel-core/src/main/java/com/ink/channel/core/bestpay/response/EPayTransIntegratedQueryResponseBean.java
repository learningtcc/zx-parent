package com.ink.channel.core.bestpay.response;

import java.util.List;

/**
 * 翼支付交易综合查询返回消息类
 * @author huohb
 *
 */
public class EPayTransIntegratedQueryResponseBean extends EPayBaseResponseBean{

	private static final long serialVersionUID = 1L;
	
	private List<FasTransResponse> results;
	
	private Integer totalCount;// 总记录数
	
	private Integer totalPage;// 总页数

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public List<FasTransResponse> getResults() {
		return results;
	}

	public void setResults(List<FasTransResponse> results) {
		this.results = results;
	}
}
