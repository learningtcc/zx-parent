/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.user.core.query;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import com.ink.base.BaseQuery;

/**
 * @author  zongtt
 * @version 1.0
 * @since 1.0
 */

public class AccAccQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    
	/** 数据库表主键 */
	private Long id;
	/** 创建时间 */
	private Date createTimeBegin;
	private Date createTimeEnd;
	/** 最后修改时间 */
	private Date lastUpdateTimeBegin;
	private Date lastUpdateTimeEnd;
	/** 子帐户控制表主键 */
	private Long accSacId;
	/** 子帐户号 */
	private Long sacId;
	/** 主帐户控制表主键 */
	private Long accPacId;
	/** 主账号 */
	private Long pacId;
	/** 子帐户种类 */
	private String sacType;
	/** 币种 */
	private String cur;
	/** 上级科目号 */
	private String upItemId;
	/** 昨日余额 日终批处理更新 */
	private BigDecimal lstBal;
	/** 当前余额 */
	private BigDecimal curBal;
	/** 临时余额 日终使用 */
	private BigDecimal tmpBal;
	/** 冻结金额 */
	private BigDecimal frozenAmt;
	/** 开户日期 */
	private Date openDateBegin;
	private Date openDateEnd;
	/** 销户日期 */
	private Date closeDateBegin;
	private Date closeDateEnd;
	/** 状态 1-启用 2-停用 9-注销 */
	private int status;
	/** 资金账户认证信息 */
	private String accMac;
	/** 删除标识 0-正常 1-删除 */
	private Boolean delFlag;
	/** version */
	private Integer version;
	/** 客户号 */
	private Long uid;
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long value) {
		this.id = value;
	}
	
	public Date getCreateTimeBegin() {
		return this.createTimeBegin;
	}
	
	public void setCreateTimeBegin(Date value) {
		this.createTimeBegin = value;
	}	
	
	public Date getCreateTimeEnd() {
		return this.createTimeEnd;
	}
	
	public void setCreateTimeEnd(Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.createTimeEnd = calendar.getTime();
		}else {
			this.createTimeEnd = value;
		}
	}
	
	public Date getLastUpdateTimeBegin() {
		return this.lastUpdateTimeBegin;
	}
	
	public void setLastUpdateTimeBegin(Date value) {
		this.lastUpdateTimeBegin = value;
	}	
	
	public Date getLastUpdateTimeEnd() {
		return this.lastUpdateTimeEnd;
	}
	
	public void setLastUpdateTimeEnd(Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.lastUpdateTimeEnd = calendar.getTime();
		}else {
			this.lastUpdateTimeEnd = value;
		}
	}
	
	public Long getAccSacId() {
		return this.accSacId;
	}
	
	public void setAccSacId(Long value) {
		this.accSacId = value;
	}
	
	public Long getSacId() {
		return this.sacId;
	}
	
	public void setSacId(Long value) {
		this.sacId = value;
	}
	
	public Long getAccPacId() {
		return this.accPacId;
	}
	
	public void setAccPacId(Long value) {
		this.accPacId = value;
	}
	
	public Long getPacId() {
		return this.pacId;
	}
	
	public void setPacId(Long value) {
		this.pacId = value;
	}
	
	public String getSacType() {
		return this.sacType;
	}
	
	public void setSacType(String value) {
		this.sacType = value;
	}
	
	public String getCur() {
		return this.cur;
	}
	
	public void setCur(String value) {
		this.cur = value;
	}
	
	public String getUpItemId() {
		return this.upItemId;
	}
	
	public void setUpItemId(String value) {
		this.upItemId = value;
	}
	
	public BigDecimal getLstBal() {
		return this.lstBal;
	}
	
	public void setLstBal(BigDecimal value) {
		this.lstBal = value;
	}
	
	public BigDecimal getCurBal() {
		return this.curBal;
	}
	
	public void setCurBal(BigDecimal value) {
		this.curBal = value;
	}
	
	public BigDecimal getTmpBal() {
		return this.tmpBal;
	}
	
	public void setTmpBal(BigDecimal value) {
		this.tmpBal = value;
	}
	
	public BigDecimal getFrozenAmt() {
		return this.frozenAmt;
	}
	
	public void setFrozenAmt(BigDecimal value) {
		this.frozenAmt = value;
	}
	
	public Date getOpenDateBegin() {
		return this.openDateBegin;
	}
	
	public void setOpenDateBegin(Date value) {
		this.openDateBegin = value;
	}	
	
	public Date getOpenDateEnd() {
		return this.openDateEnd;
	}
	
	public void setOpenDateEnd(Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.openDateEnd = calendar.getTime();
		}else {
			this.openDateEnd = value;
		}
	}
	
	public Date getCloseDateBegin() {
		return this.closeDateBegin;
	}
	
	public void setCloseDateBegin(Date value) {
		this.closeDateBegin = value;
	}	
	
	public Date getCloseDateEnd() {
		return this.closeDateEnd;
	}
	
	public void setCloseDateEnd(Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.closeDateEnd = calendar.getTime();
		}else {
			this.closeDateEnd = value;
		}
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getAccMac() {
		return this.accMac;
	}
	
	public void setAccMac(String value) {
		this.accMac = value;
	}
	
	public Boolean getDelFlag() {
		return this.delFlag;
	}
	
	public void setDelFlag(Boolean value) {
		this.delFlag = value;
	}
	
	public Integer getVersion() {
		return this.version;
	}
	
	public void setVersion(Integer value) {
		this.version = value;
	}
	
	public Long getUid() {
		return this.uid;
	}
	
	public void setUid(Long value) {
		this.uid = value;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AccAccQuery [id=");
		builder.append(id);
		builder.append(", createTimeBegin=");
		builder.append(createTimeBegin);
		builder.append(", createTimeEnd=");
		builder.append(createTimeEnd);
		builder.append(", lastUpdateTimeBegin=");
		builder.append(lastUpdateTimeBegin);
		builder.append(", lastUpdateTimeEnd=");
		builder.append(lastUpdateTimeEnd);
		builder.append(", accSacId=");
		builder.append(accSacId);
		builder.append(", sacId=");
		builder.append(sacId);
		builder.append(", accPacId=");
		builder.append(accPacId);
		builder.append(", pacId=");
		builder.append(pacId);
		builder.append(", sacType=");
		builder.append(sacType);
		builder.append(", cur=");
		builder.append(cur);
		builder.append(", upItemId=");
		builder.append(upItemId);
		builder.append(", lstBal=");
		builder.append(lstBal);
		builder.append(", curBal=");
		builder.append(curBal);
		builder.append(", tmpBal=");
		builder.append(tmpBal);
		builder.append(", frozenAmt=");
		builder.append(frozenAmt);
		builder.append(", openDateBegin=");
		builder.append(openDateBegin);
		builder.append(", openDateEnd=");
		builder.append(openDateEnd);
		builder.append(", closeDateBegin=");
		builder.append(closeDateBegin);
		builder.append(", closeDateEnd=");
		builder.append(closeDateEnd);
		builder.append(", status=");
		builder.append(status);
		builder.append(", accMac=");
		builder.append(accMac);
		builder.append(", delFlag=");
		builder.append(delFlag);
		builder.append(", version=");
		builder.append(version);
		builder.append(", uid=");
		builder.append(uid);
		builder.append("]");
		return builder.toString();
	}
	
}

