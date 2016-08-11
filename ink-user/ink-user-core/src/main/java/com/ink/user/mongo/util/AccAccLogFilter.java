package com.ink.user.mongo.util;

import java.io.Serializable;

import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Version;
import org.mongodb.morphia.utils.IndexDirection;

/**
 * AccAccLog查询条件过滤器
 * @author yangchen
 * @date 2016年1月30日 下午5:29:54
 */
public class AccAccLogFilter extends MongoPageFilter implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1216928312429748601L;

	@MongoQueryKey(value = "id")
	private Long id;

	/** 创建时间 */
	@MongoQueryKey(value = "createTime", acction = "greaterThanOrEq")
	private String beginCreateTime;
	
	@MongoQueryKey(value = "createTime", acction = "lessThanOrEq")
	private String endCreateTime;

	/** 最后修改时间 */
	@MongoQueryKey(value = "lastUpdateTime", acction = "greaterThanOrEq")
	private String beginLastUpdateTime;
	
	@MongoQueryKey(value = "lastUpdateTime", acction = "lessThanOrEq")
	private String endLastUpdateTime;

	/** 子帐户控制表主键 */
	@MongoQueryKey(value = "accSacId")
	private String accSacId;

	/** 子帐户号 */
	@MongoQueryKey(value = "sacId")
	private String sacId;

	/** 主帐户控制表主键 */
	@MongoQueryKey(value = "accPacId")
	private String accPacId;

	/** 主账号 */
	@MongoQueryKey(value = "pacId")
	private String pacId;

	/** 子帐户种类 */
	@MongoQueryKey(value = "sacType")
	private String sacType;

	/** 币种 */
	@MongoQueryKey(value = "cur")
	private String cur;

	/** 昨日余额 日终批处理更新 */
	@MongoQueryKey(value = "lstBal")
	private String lstBal;

	/** 当前余额 */
	@MongoQueryKey(value = "curBal")
	private String curBal;

	/** 临时余额 日终使用 */
	@MongoQueryKey(value = "tmpBal")
	private String tmpBal;

	/** 冻结金额 */
	@MongoQueryKey(value = "frozenAmt")
	private String frozenAmt;

	/** 开户日期 */
	@MongoQueryKey(value = "openDate", acction = "greaterThanOrEq")
	private String beginOpenDate;
	@MongoQueryKey(value = "openDate", acction = "lessThanOrEq")
	private String endOpenDate;
	/** 销户日期 */
	@MongoQueryKey(value = "closeDate", acction = "greaterThanOrEq")
	private String beginCloseDate;
	@MongoQueryKey(value = "closeDate", acction = "lessThanOrEq")
	private String endCloseDate;

	/** 状态 1-启用 2-停用 9-注销 */
	@MongoQueryKey(value = "status")
	private Integer status;

	/** 删除标识 0-正常 1-删除 */
	@MongoQueryKey(value = "delFlag")
	private String delFlag;

	@MongoQueryKey(value = "uid")
	private String uid;

	/**  */
	@MongoQueryKey(value = "uid")
	private Long version;

	/** 资金账户认证信息 */
	@MongoQueryKey(value = "accMac")
	private String accMac;
	/**错误码*/
	@MongoQueryKey(value = "errorCode")
	private String errorCode;
	/**错误描述*/
	@MongoQueryKey(value = "errorMsg")
	private String errorMsg;
	/**交易类型**/
	@MongoQueryKey(value = "tsnCode")
	private String tsnCode; 
	/**操作类型*/
	@MongoQueryKey(value = "operationType")
	private String operationType;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBeginCreateTime() {
		return beginCreateTime;
	}
	public void setBeginCreateTime(String beginCreateTime) {
		this.beginCreateTime = beginCreateTime;
	}
	public String getEndCreateTime() {
		return endCreateTime;
	}
	public void setEndCreateTime(String endCreateTime) {
		this.endCreateTime = endCreateTime;
	}
	public String getBeginLastUpdateTime() {
		return beginLastUpdateTime;
	}
	public void setBeginLastUpdateTime(String beginLastUpdateTime) {
		this.beginLastUpdateTime = beginLastUpdateTime;
	}
	public String getEndLastUpdateTime() {
		return endLastUpdateTime;
	}
	public void setEndLastUpdateTime(String endLastUpdateTime) {
		this.endLastUpdateTime = endLastUpdateTime;
	}
	public String getAccSacId() {
		return accSacId;
	}
	public void setAccSacId(String accSacId) {
		this.accSacId = accSacId;
	}
	public String getSacId() {
		return sacId;
	}
	public void setSacId(String sacId) {
		this.sacId = sacId;
	}
	public String getAccPacId() {
		return accPacId;
	}
	public void setAccPacId(String accPacId) {
		this.accPacId = accPacId;
	}
	public String getPacId() {
		return pacId;
	}
	public void setPacId(String pacId) {
		this.pacId = pacId;
	}
	public String getSacType() {
		return sacType;
	}
	public void setSacType(String sacType) {
		this.sacType = sacType;
	}
	public String getCur() {
		return cur;
	}
	public void setCur(String cur) {
		this.cur = cur;
	}
	public String getLstBal() {
		return lstBal;
	}
	public void setLstBal(String lstBal) {
		this.lstBal = lstBal;
	}
	public String getCurBal() {
		return curBal;
	}
	public void setCurBal(String curBal) {
		this.curBal = curBal;
	}
	public String getTmpBal() {
		return tmpBal;
	}
	public void setTmpBal(String tmpBal) {
		this.tmpBal = tmpBal;
	}
	public String getFrozenAmt() {
		return frozenAmt;
	}
	public void setFrozenAmt(String frozenAmt) {
		this.frozenAmt = frozenAmt;
	}
	public String getBeginOpenDate() {
		return beginOpenDate;
	}
	public void setBeginOpenDate(String beginOpenDate) {
		this.beginOpenDate = beginOpenDate;
	}
	public String getEndOpenDate() {
		return endOpenDate;
	}
	public void setEndOpenDate(String endOpenDate) {
		this.endOpenDate = endOpenDate;
	}
	public String getBeginCloseDate() {
		return beginCloseDate;
	}
	public void setBeginCloseDate(String beginCloseDate) {
		this.beginCloseDate = beginCloseDate;
	}
	public String getEndCloseDate() {
		return endCloseDate;
	}
	public void setEndCloseDate(String endCloseDate) {
		this.endCloseDate = endCloseDate;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	public String getAccMac() {
		return accMac;
	}
	public void setAccMac(String accMac) {
		this.accMac = accMac;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getTsnCode() {
		return tsnCode;
	}
	public void setTsnCode(String tsnCode) {
		this.tsnCode = tsnCode;
	}
	public String getOperationType() {
		return operationType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AccAccLogFilter [id=");
		builder.append(id);
		builder.append(", beginCreateTime=");
		builder.append(beginCreateTime);
		builder.append(", endCreateTime=");
		builder.append(endCreateTime);
		builder.append(", beginLastUpdateTime=");
		builder.append(beginLastUpdateTime);
		builder.append(", endLastUpdateTime=");
		builder.append(endLastUpdateTime);
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
		builder.append(", beginOpenDate=");
		builder.append(beginOpenDate);
		builder.append(", endOpenDate=");
		builder.append(endOpenDate);
		builder.append(", beginCloseDate=");
		builder.append(beginCloseDate);
		builder.append(", endCloseDate=");
		builder.append(endCloseDate);
		builder.append(", status=");
		builder.append(status);
		builder.append(", delFlag=");
		builder.append(delFlag);
		builder.append(", uid=");
		builder.append(uid);
		builder.append(", version=");
		builder.append(version);
		builder.append(", accMac=");
		builder.append(accMac);
		builder.append(", errorCode=");
		builder.append(errorCode);
		builder.append(", errorMsg=");
		builder.append(errorMsg);
		builder.append(", tsnCode=");
		builder.append(tsnCode);
		builder.append(", operationType=");
		builder.append(operationType);
		builder.append("]");
		return builder.toString();
	}
	
}
