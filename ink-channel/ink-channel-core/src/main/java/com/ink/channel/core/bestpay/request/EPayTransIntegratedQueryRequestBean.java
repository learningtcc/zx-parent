package com.ink.channel.core.bestpay.request;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 翼支付交易综合查询请求消息类
 * @author huohb
 *
 */
@XmlRootElement(name = "data")
public class EPayTransIntegratedQueryRequestBean extends EPayBaseRequestBean {

	private static final long serialVersionUID = 1L;
	
	private String extOrderSeq;// 外部订单号
	
	private String originalSeq;// 原请求流水号
	
	private String trsSeq;// 原交易流水号
	
	private Integer currentPage;// 当前页
	
	private Integer pageSize;// 每页条数

	public String getExtOrderSeq() {
		return extOrderSeq;
	}

	public void setExtOrderSeq(String extOrderSeq) {
		this.extOrderSeq = extOrderSeq;
	}

	public String getOriginalSeq() {
		return originalSeq;
	}

	public void setOriginalSeq(String originalSeq) {
		this.originalSeq = originalSeq;
	}

	public String getTrsSeq() {
		return trsSeq;
	}

	public void setTrsSeq(String trsSeq) {
		this.trsSeq = trsSeq;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
