package com.ink.balance.base;

import java.io.Serializable;
import java.util.List;

public class ObjectJson implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8028636292845805852L;

	private int total;//分页总数
	
	private List<?> result;

	public ObjectJson() {
		super();
	}

	public ObjectJson(int total, List<?> result) {
		super();
		this.total = total;
		this.result = result;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<?> getResult() {
		return result;
	}

	public void setResult(List<?> result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "ObjectJson [total=" + total + ", rows=" + result + "]";
	}
	
	

}
