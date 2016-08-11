package com.ink.base.redis.client;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

import com.ink.base.utils.dateUtil.DateUtil;

public class CacheObject implements Serializable {

	private static final long serialVersionUID = -7683781723383656112L;

	private Object value;
	
	private Date loadDate;

	public CacheObject() {
		super();
	}

	public CacheObject(Object result, Date loadDate) {
		this.value = result;
		this.loadDate = loadDate;
	}
	
	public CacheObject(Object result, String loadDate) {
		this.value = result;
		try {
			this.loadDate = DateUtil.formatToDayByYYYYMMDDMMHH(loadDate);
		} catch (ParseException e) {
			this.loadDate = new Date();
		}
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object result) {
		this.value = result;
	}

	public Date getLoadDate() {
		return loadDate;
	}

	public void setLoadDate(Date loadDate) {
		this.loadDate = loadDate;
	}
}