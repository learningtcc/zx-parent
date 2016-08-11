/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.user.core.query;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Calendar;

import com.ink.base.BaseQuery;

/**
 * @author  zongtt
 * @version 1.0
 * @since 1.0
 */

public class AccRsakeyQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    
	/** 主键 */
	private java.lang.Long id;
	/** 商户号 */
	private java.lang.Long mchid;
	/** rsa公钥 */
	private java.lang.String rsapukey;
	/** rsa私钥 */
	private java.lang.String rsaprkey;
	/** 最后更新时间 */
	private java.util.Date lastupdatetimeBegin;
	private java.util.Date lastupdatetimeEnd;
	/** 创建时间 */
	private java.util.Date createtimeBegin;
	private java.util.Date createtimeEnd;
	/** 创建人 */
	private java.lang.String owner;
	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.Long getMchid() {
		return this.mchid;
	}
	
	public void setMchid(java.lang.Long value) {
		this.mchid = value;
	}
	
	public java.lang.String getRsapukey() {
		return this.rsapukey;
	}
	
	public void setRsapukey(java.lang.String value) {
		this.rsapukey = value;
	}
	
	public java.lang.String getRsaprkey() {
		return this.rsaprkey;
	}
	
	public void setRsaprkey(java.lang.String value) {
		this.rsaprkey = value;
	}
	
	public java.util.Date getLastupdatetimeBegin() {
		return this.lastupdatetimeBegin;
	}
	
	public void setLastupdatetimeBegin(java.util.Date value) {
		this.lastupdatetimeBegin = value;
	}	
	
	public java.util.Date getLastupdatetimeEnd() {
		return this.lastupdatetimeEnd;
	}
	
	public void setLastupdatetimeEnd(java.util.Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.lastupdatetimeEnd = calendar.getTime();
		}else {
			this.lastupdatetimeEnd = value;
		}
	}
	
	public java.util.Date getCreatetimeBegin() {
		return this.createtimeBegin;
	}
	
	public void setCreatetimeBegin(java.util.Date value) {
		this.createtimeBegin = value;
	}	
	
	public java.util.Date getCreatetimeEnd() {
		return this.createtimeEnd;
	}
	
	public void setCreatetimeEnd(java.util.Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.createtimeEnd = calendar.getTime();
		}else {
			this.createtimeEnd = value;
		}
	}
	
	public java.lang.String getOwner() {
		return this.owner;
	}
	
	public void setOwner(java.lang.String value) {
		this.owner = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

