package com.ink.user.core.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ink.base.util.DateConvertUtils;
import com.ink.user.common.constant.PatsAtpConstant;

public class AccAcc implements Serializable {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 3973409677764675328L;
	public static final String TABLE_ALIAS = "账户信息";
	//date formats
		public static final String FORMAT_CREATE_TIME = PatsAtpConstant.DATE_TIME_FORMAT;
		public static final String FORMAT_LAST_UPDATE_TIME = PatsAtpConstant.DATE_TIME_FORMAT;
		public static final String FORMAT_OPEN_DATE = PatsAtpConstant.DATE_TIME_FORMAT;
		public static final String FORMAT_CLOSE_DATE = PatsAtpConstant.DATE_TIME_FORMAT;

	/** 数据库表主键 */
	private Long id;

	/** 创建时间 */
	private Date createTime;

	/** 最后修改时间 */
	private Date lastUpdateTime;

	/** 子帐户控制表主键 */
	private Long accSacId;

	/** 子帐户号 */
	private String sacId;

	/** 主帐户控制表主键 */
	private Long accPacId;

	/** 主账号 */
	private Long pacId;

	/** 子帐户类型表主键 */
//	private Long accSacTypeId;

	/** 子帐户种类 */
	private String sacType;

	/** 币种 */
	private String cur;

	/** 昨日余额 日终批处理更新 */
	private BigDecimal lstBal;

	/** 当前余额 */
	private BigDecimal curBal;

	/** 临时余额 日终使用 */
	private BigDecimal tmpBal;

	/** 冻结金额 */
	private BigDecimal frozenAmt;

	/** 开户日期 */
	private Date openDate;

	/** 销户日期 */
	private Date closeDate;

	/** 状态 1-启用 2-停用 9-注销 */
	private Integer status;

	/** 删除标识 0-正常 1-删除 */
	private Integer delFlag;

	/** 预留字段1 */
//	private String filler1;

	/** 预留字段2 */
//	private String filler2;

	/** 预留字段3 */
//	private String filler3;

	/**  */
	private Integer version;

	/** 资金账户认证信息 */
	private String accMac;

	/** 平台唯一标识号 */
	private Long uid;

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public void setPacId(Long pacId) {
		this.pacId = pacId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Long getAccSacId() {
		return accSacId;
	}

	public void setAccSacId(Long accSacId) {
		this.accSacId = accSacId;
	}

	public String getSacId() {
		return sacId;
	}

	public void setSacId(String sacId) {
		this.sacId = sacId;
	}

	public Long getPacId() {
		return pacId;
	}

	public Long getAccPacId() {
		return accPacId;
	}

	public void setAccPacId(Long accPacId) {
		this.accPacId = accPacId;
	}

//	public Long getAccSacTypeId() {
//		return accSacTypeId;
//	}
//
//	public void setAccSacTypeId(Long accSacTypeId) {
//		this.accSacTypeId = accSacTypeId;
//	}

	public String getSacType() {
		return sacType;
	}

	public void setSacType(String sacType) {
		this.sacType = sacType == null ? null : sacType.trim();
	}

	public String getCur() {
		return cur;
	}

	public void setCur(String cur) {
		this.cur = cur == null ? null : cur.trim();
	}

	public BigDecimal getLstBal() {
		return lstBal;
	}

	public void setLstBal(BigDecimal lstBal) {
		this.lstBal = lstBal;
	}

	public BigDecimal getCurBal() {
		return curBal;
	}

	public void setCurBal(BigDecimal curBal) {
		this.curBal = curBal;
	}

	public BigDecimal getTmpBal() {
		return tmpBal;
	}

	public void setTmpBal(BigDecimal tmpBal) {
		this.tmpBal = tmpBal;
	}

	public BigDecimal getFrozenAmt() {
		return frozenAmt;
	}

	public void setFrozenAmt(BigDecimal frozenAmt) {
		this.frozenAmt = frozenAmt;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

//	public String getFiller1() {
//		return filler1;
//	}
//
//	public void setFiller1(String filler1) {
//		this.filler1 = filler1 == null ? null : filler1.trim();
//	}
//
//	public String getFiller2() {
//		return filler2;
//	}
//
//	public void setFiller2(String filler2) {
//		this.filler2 = filler2 == null ? null : filler2.trim();
//	}
//
//	public String getFiller3() {
//		return filler3;
//	}

//	public void setFiller3(String filler3) {
//		this.filler3 = filler3 == null ? null : filler3.trim();
//	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getAccMac() {
		return accMac;
	}

	public void setAccMac(String accMac) {
		this.accMac = accMac == null ? null : accMac.trim();
	}
	public String getCloseDateString() {
		return DateConvertUtils.format(getCloseDate(), FORMAT_CLOSE_DATE);
	}
	public void setCloseDateString(String value) {
		setCloseDate(DateConvertUtils.parse(value, FORMAT_CLOSE_DATE,Date.class));
	}
	public String getOpenDateString() {
		return DateConvertUtils.format(getOpenDate(), FORMAT_OPEN_DATE);
	}
	public void setOpenDateString(String value) {
		setOpenDate(DateConvertUtils.parse(value, FORMAT_OPEN_DATE,Date.class));
	}
	public String getLastUpdateTimeString() {
		return DateConvertUtils.format(getLastUpdateTime(), FORMAT_LAST_UPDATE_TIME);
	}
	public void setLastUpdateTimeString(String value) {
		setLastUpdateTime(DateConvertUtils.parse(value, FORMAT_LAST_UPDATE_TIME,Date.class));
	}
	public String getCreateTimeString() {
		return DateConvertUtils.format(getCreateTime(), FORMAT_CREATE_TIME);
	}
	public void setCreateTimeString(String value) {
		setCreateTime(DateConvertUtils.parse(value, FORMAT_CREATE_TIME,Date.class));
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AccAcc [id=");
		builder.append(id);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append(", lastUpdateTime=");
		builder.append(lastUpdateTime);
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
		builder.append(", lstBal=");
		builder.append(lstBal);
		builder.append(", curBal=");
		builder.append(curBal);
		builder.append(", tmpBal=");
		builder.append(tmpBal);
		builder.append(", frozenAmt=");
		builder.append(frozenAmt);
		builder.append(", openDate=");
		builder.append(openDate);
		builder.append(", closeDate=");
		builder.append(closeDate);
		builder.append(", status=");
		builder.append(status);
		builder.append(", delFlag=");
		builder.append(delFlag);
		builder.append(", version=");
		builder.append(version);
		builder.append(", accMac=");
		builder.append(accMac);
		builder.append(", uid=");
		builder.append(uid);
		builder.append("]");
		return builder.toString();
	}

	
}